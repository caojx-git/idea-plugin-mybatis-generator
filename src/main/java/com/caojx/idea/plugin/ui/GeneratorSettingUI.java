package com.caojx.idea.plugin.ui;

import com.caojx.idea.plugin.domain.model.GeneratorConfigContext;
import com.caojx.idea.plugin.domain.model.MyBatisGeneratorConfig;
import com.caojx.idea.plugin.domain.service.IGeneratorService;
import com.caojx.idea.plugin.infrastructure.persistent.PersistentState;
import com.caojx.idea.plugin.infrastructure.persistent.PersistentStateService;
import com.caojx.idea.plugin.infrastructure.po.Database;
import com.caojx.idea.plugin.infrastructure.utils.MySQLDBHelper;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.openapi.vfs.VirtualFile;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 生成代码UI配置类
 */
public class GeneratorSettingUI implements Configurable {
    private JPanel mainPanel;
    private JTable table;
    private JComboBox databaseComBox;
    private JTextField tableNameRegex;
    private JButton selectTableBtn;
    private JTextField projectBasePath;
    private JComboBox moduleComboBox;
    private JTextField modelPath;
    private JButton modelPathBtn;
    private JTextField modelPackage;
    private JButton modelPackageBtn;
    private JTextField mapperPath;
    private JTextField mapperXmlPath;
    private JTextField mapperPackage;
    private JButton mapperPathBtn;
    private JButton mapperPackageBtn;
    private JButton mapperXmlPathBtn;
    private JTextField modelNamePattern;
    private JTextField mapperNamePattern;
    private JTextField mapperXmlNamePattern;
    private JButton configDataBaseBtn;
    private JPanel customPanel;

    /**
     * 代码生成上下文
     */
    private GeneratorConfigContext generatorConfigContext;

    /**
     * 当前数据库
     */
    private Database currentDatabase;

    /**
     * 表明列表
     */
    private List<String> tableNames = new ArrayList<>();

    /**
     * 项目
     */
    private Project project;

    private PersistentState persistentState;

    /**
     * 生成代码业务接口
     */
    private IGeneratorService generatorService;


    public GeneratorSettingUI(Project project) {
        this.project = project;

        // 获取持久化数据
        this.persistentState = PersistentStateService.getInstance(null != project ? project : ProjectManager.getInstance().getDefaultProject()).getState();

        // 初始化界面数据
        initGeneratorSettingUIData(project, persistentState);

        // 项目模块（todo）

        // model生成路径选择
        modelPathBtn.addActionListener(e -> {
            VirtualFile virtualFile = FileChooser.chooseFile(FileChooserDescriptorFactory.createSingleFolderDescriptor(), project, project.getBaseDir());
            if (virtualFile != null) {
                modelPath.setText(virtualFile.getPath());
            }
        });
        // model包名 todo

        // mapper生成路径选择
        mapperPathBtn.addActionListener(e -> {
            VirtualFile virtualFile = FileChooser.chooseFile(FileChooserDescriptorFactory.createSingleFolderDescriptor(), project, project.getBaseDir());
            if (virtualFile != null) {
                mapperPath.setText(virtualFile.getPath());
            }
        });

        // mapper包名 todo

        // mapperXml生成路径选择
        mapperXmlPathBtn.addActionListener(e -> {
            VirtualFile virtualFile = FileChooser.chooseFile(FileChooserDescriptorFactory.createSingleFolderDescriptor(), project, project.getBaseDir());
            if (virtualFile != null) {
                mapperXmlPath.setText(virtualFile.getPath());
            }
        });

        // 数据库下拉框
        databaseComBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                this.currentDatabase = persistentState.getDatabases().stream().filter(database -> database.getDatabaseName().equals(e.getItem().toString())).findFirst().get();
            }
        });

        // 配置数据库
        configDataBaseBtn.addActionListener(e -> {
            DataSourcesSettingUI dataSourcesSettingUI = new DataSourcesSettingUI(project, this);
            dataSourcesSettingUI.show();
        });

        // 查询表
        selectTableBtn.addActionListener(e -> {
            try {
                MySQLDBHelper dbHelper = new MySQLDBHelper(currentDatabase.getHost(), currentDatabase.getPort(), currentDatabase.getUserName(), currentDatabase.getPassword(), currentDatabase.getDatabaseName());

                // 获取表明列表
                String tableNamePattern = StringUtils.isBlank(tableNameRegex.getText()) ? "%" : tableNameRegex.getText();
                this.tableNames = dbHelper.getTableName(currentDatabase.getDatabaseName(), tableNamePattern);

                // 显示表明列表
                String[] title = {"", "表名"};
                // 行index,列index
                Object[][] data = new Object[this.tableNames.size()][2];
                for (int i = 0; i < this.tableNames.size(); i++) {
                    data[i][1] = this.tableNames.get(i);
                }

                table.setModel(new DefaultTableModel(data, title));

                // 设置列为单选框
                TableColumn tableColumn = table.getColumnModel().getColumn(0);
                tableColumn.setCellEditor(new DefaultCellEditor(new JCheckBox()));
                tableColumn.setCellEditor(table.getDefaultEditor(Boolean.class));
                tableColumn.setCellRenderer(table.getDefaultRenderer(Boolean.class));
                tableColumn.setMaxWidth(100);
            } catch (Exception ex) {
                Messages.showWarningDialog(project, "数据库连接错误,请检查配置.", "Warning");
            }
        });

        // 表添加监听事件
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int rowIdx = table.rowAtPoint(e.getPoint());
                    Boolean flag = (Boolean) table.getValueAt(rowIdx, 0);
                    if (Objects.nonNull(flag) && flag) {
                        tableNames.add(table.getValueAt(rowIdx, 1).toString());
                    } else {
                        tableNames.remove(table.getValueAt(rowIdx, 1).toString());
                    }
                }
            }
        });
    }

    @Override
    @NlsContexts.ConfigurableName
    public String getDisplayName() {
        return "GeneratorSettingUI";
    }

    @Override
    @Nullable
    public JComponent createComponent() {
        return mainPanel;
    }

    @Override
    public boolean isModified() {
        return true;
    }

    @Override
    public void apply() throws ConfigurationException {
        // 设置代码生成配置
        MyBatisGeneratorConfig myBatisGeneratorConfig = this.generatorConfigContext.getMyBatisGeneratorConfig();
        myBatisGeneratorConfig.setModelPath(this.modelPath.getText());
        myBatisGeneratorConfig.setModelPackage(this.modelPackage.getText());
        myBatisGeneratorConfig.setModelNamePattern(this.modelNamePattern.getText());

        myBatisGeneratorConfig.setMapperPath(this.mapperPath.getText());
        myBatisGeneratorConfig.setMapperPackage(this.mapperPackage.getText());
        myBatisGeneratorConfig.setMapperNamePattern(this.mapperNamePattern.getText());

        myBatisGeneratorConfig.setMapperXmlPath(this.mapperXmlPath.getText());
        myBatisGeneratorConfig.setMapperXmlNamePattern(this.mapperXmlNamePattern.getText());


        // 生成代码
        generatorService.doGenerator(project, this.generatorConfigContext);
        Messages.showWarningDialog(project, "代码生成成功", "info");
    }

    /**
     * 初始化界面数据
     *
     * @param project         项目
     * @param persistentState 持久化数据
     */
    private void initGeneratorSettingUIData(Project project, PersistentState persistentState) {
        this.generatorConfigContext = persistentState.getGeneratorConfigContext();

        // 项目路径
        this.projectBasePath.setText(project.getBasePath());
        MyBatisGeneratorConfig myBatisGeneratorConfig = generatorConfigContext.getMyBatisGeneratorConfig();
        this.modelPath.setText(myBatisGeneratorConfig.getModelPath());
        this.modelPackage.setText(myBatisGeneratorConfig.getModelPackage());
        this.modelNamePattern.setText(myBatisGeneratorConfig.getModelNamePattern());

        this.mapperPath.setText(myBatisGeneratorConfig.getMapperPath());
        this.mapperPackage.setText(myBatisGeneratorConfig.getMapperPackage());
        this.mapperNamePattern.setText(myBatisGeneratorConfig.getMapperNamePattern());

        this.mapperXmlPath.setText(myBatisGeneratorConfig.getMapperXmlPath());
        this.mapperXmlNamePattern.setText(myBatisGeneratorConfig.getMapperNamePattern());


        // 数据库下拉框
        List<Database> databases = persistentState.getDatabases();
        if (CollectionUtils.isNotEmpty(databases)) {
            databases.forEach(database -> databaseComBox.addItem(database.getDatabaseName()));
        }
        // 默认选中
        if (Objects.nonNull(currentDatabase)) {
            databaseComBox.setSelectedItem(currentDatabase.getDatabaseName());
        }
    }

    /**
     * 刷新数据库下拉框
     */
    public void refreshDatabaseComBox(List<Database> databases) {
        String[] databaseNames = new String[databases.size()];
        for (int i = 0; i < databases.size(); i++) {
            databaseNames[i] = databases.get(i).getDatabaseName();
        }
        ComboBoxModel comboBoxModel = new DefaultComboBoxModel(databaseNames);
        databaseComBox.setModel(comboBoxModel);

        if (databases.size() == 1) {
            this.currentDatabase = databases.get(0);
        }
    }
}
