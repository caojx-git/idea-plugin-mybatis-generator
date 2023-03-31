package com.caojx.idea.plugin.ui;

import com.caojx.idea.plugin.common.properties.EntityProperties;
import com.caojx.idea.plugin.common.properties.GeneratorProperties;
import com.caojx.idea.plugin.common.utils.ClassUtils;
import com.caojx.idea.plugin.common.utils.MyMessages;
import com.caojx.idea.plugin.persistent.PersistentState;
import com.caojx.idea.plugin.persistent.PersistentStateService;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.components.JBScrollPane;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.JDBCType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 自定义jdbc于java映射关系
 *
 * @author caojx
 * @date 2023/3/31 10:00 AM
 */
public class CustomerJdbcTypeMappingTableDialog extends DialogWrapper {

    /**
     * 表头
     */
    private static final String[] TABLE_COLUMN_NAME = {"序号", "JdbcType", "Java类型"};
    /**
     * 表数据模型
     */
    private final DefaultTableModel TABLE_MODEL = new DefaultTableModel(null, TABLE_COLUMN_NAME);

    private final JTable table = new JTable(TABLE_MODEL);
    private int rowIndex = 1;

    private final Project project;
    private final Map<String, String> customerJdbcTypeMappingMap;
    private final GeneratorSettingUI generatorSettingUI;
    private final PersistentStateService persistentStateService;

    protected CustomerJdbcTypeMappingTableDialog(@NotNull Project project, @NotNull GeneratorSettingUI generatorSettingUI) {
        super(project);
        init();
        setTitle("Jdbc类型映射配置");

        this.project = project;
        this.generatorSettingUI = generatorSettingUI;
        this.persistentStateService = PersistentStateService.getInstance(project);
        PersistentState persistentState = persistentStateService.getState();
        EntityProperties entityProperties = persistentState.getGeneratorProperties().getEntityProperties();
        customerJdbcTypeMappingMap = Optional.ofNullable(entityProperties.getCustomerJdbcTypeMappingMap()).orElse(new HashMap<>(4));

        // 刷新表格
        refreshTable(customerJdbcTypeMappingMap);
    }

    @Override
    protected JComponent createCenterPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());


        JButton addButton = new JButton("新增");
        addButton.addActionListener(e -> addRow());

        JButton deleteButton = new JButton("删除");
        deleteButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                // 删除映射关系
                JDBCType jdbcType = JDBCType.valueOf(TABLE_MODEL.getValueAt(row, 1).toString());
                customerJdbcTypeMappingMap.remove(jdbcType.getName());

                TABLE_MODEL.removeRow(row);
                rowIndex--;

                // 更新编号
                updateIndex();
            }
        });

        JButton confirmButton = new JButton("保存配置");
        confirmButton.addActionListener(e -> {
            boolean allSuccess = true;
            for (int i = 0; i < TABLE_MODEL.getRowCount(); i++) {
                Object[] rowData = new Object[3];
                // 编码
                rowData[0] = TABLE_MODEL.getValueAt(i, 0);
                // jdbcType
                rowData[1] = TABLE_MODEL.getValueAt(i, 1);
                // java全类名
                rowData[2] = TABLE_MODEL.getValueAt(i, 2);

                String errorMessage = putJdbcTypeMapping(rowData[1].toString(), rowData[2].toString());
                if (StringUtils.isNotBlank(errorMessage)) {
                    allSuccess = false;
                    MyMessages.showWarningDialog(this.project, "第" + rowData[0] + "行保存失败：" + errorMessage, "Warning");
                }
            }

            // 持久化映射配置
            if (allSuccess) {
                PersistentState persistentState = persistentStateService.getState();
                GeneratorProperties generatorProperties = Optional.ofNullable(persistentState.getGeneratorProperties()).orElse(new GeneratorProperties());
                EntityProperties entityProperties = generatorProperties.getEntityProperties();
                entityProperties.setCustomerJdbcTypeMappingMap(customerJdbcTypeMappingMap);
                persistentState.setGeneratorProperties(generatorProperties);
                generatorSettingUI.setCustomerJdbcTypeMappingMap(customerJdbcTypeMappingMap);
                MyMessages.showInfoMessage(this.project, "配置保存成功", "info");
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(confirmButton);


        JBScrollPane scrollPane = new JBScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    @Override
    protected JComponent createSouthPanel() {
        JTextArea jTextArea = new JTextArea("1.基本类型直接写(如int) \n" +
                "2.类类型需要Class.forName能识别(如java.time.LocalDateTime或[B) \n" +
                "3.自定义配置会覆盖默认的配");
        jTextArea.setEnabled(false);
        JPanel southPanel = new JPanel();
        southPanel.setSize(250, 60);
        southPanel.add(jTextArea);
        return southPanel;
    }

    private void addRow() {
        Object[] rowData = {rowIndex++, "", ""};
        TABLE_MODEL.addRow(rowData);
    }

    private void addRow(String jdbcTypeName, String javaTypeName) {
        Object[] rowData = {rowIndex++, jdbcTypeName, javaTypeName};
        TABLE_MODEL.addRow(rowData);
    }

    private void updateIndex() {
        for (int i = 0; i < TABLE_MODEL.getRowCount(); i++) {
            TABLE_MODEL.setValueAt(i + 1, i, 0);
        }
    }

    /**
     * 刷新数据库表
     */
    private void refreshTable(Map<String, String> customerJdbcTypeMappingMap) {
        // 刷新数据库表
        TABLE_MODEL.setDataVector(null, TABLE_COLUMN_NAME);

        List<String> itemList = new ArrayList<>();
        for (JDBCType jdbcType : JDBCType.values()) {
            itemList.add(jdbcType.name());
        }
        ComboBox comboBox = new ComboBox(itemList.toArray());
        table.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(comboBox));

        // 初始化自定义映射
        customerJdbcTypeMappingMap.forEach(this::addRow);

        // 更新编号
        updateIndex();
    }


    /**
     * 设置映射关系
     *
     * @param jdbcTypeName jdbc名称
     * @param javaTypeName java类名称
     */
    private String putJdbcTypeMapping(String jdbcTypeName, String javaTypeName) {
        if (jdbcTypeName == null || StringUtils.isBlank(jdbcTypeName.trim())) {
            return "jdbc类型不能为空";
        }
        if (javaTypeName == null || StringUtils.isBlank(javaTypeName.trim())) {
            return "java类型不能为空";
        }
        try {
            JDBCType.valueOf(jdbcTypeName);
            ClassUtils.convertClazz(javaTypeName.trim());
            this.customerJdbcTypeMappingMap.put(jdbcTypeName.trim(), javaTypeName.trim());
        } catch (Exception e) {
            e.printStackTrace();
            return "无法识别的java类型";
        }
        return "";
    }
}
