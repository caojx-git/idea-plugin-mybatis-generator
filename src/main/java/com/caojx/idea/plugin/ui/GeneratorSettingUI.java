package com.caojx.idea.plugin.ui;

import com.caojx.idea.plugin.common.constants.Constant;
import com.caojx.idea.plugin.common.context.GeneratorContext;
import com.caojx.idea.plugin.common.enums.FrameworkTypeEnum;
import com.caojx.idea.plugin.common.pojo.model.Database;
import com.caojx.idea.plugin.common.pojo.model.TableInfo;
import com.caojx.idea.plugin.common.pojo.persistent.PersistentState;
import com.caojx.idea.plugin.common.properties.*;
import com.caojx.idea.plugin.common.utils.MySQLDBHelper;
import com.caojx.idea.plugin.generator.IGeneratorService;
import com.caojx.idea.plugin.generator.PersistentStateService;
import com.caojx.idea.plugin.generator.GeneratorServiceImpl;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.openapi.vfs.VirtualFile;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 代码生成配置UI
 *
 * @author caojx
 * @date 2022/4/10 10:00 AM
 */
public class GeneratorSettingUI extends DialogWrapper {

    private JTabbedPane tabbedPanel;
    private JTextField projectPathTf;
    private JTextField entityPathTf;
    private JTextField entityPackageTf;
    private JTextField mapperPathTf;
    private JTextField mapperPackageTf;
    private JTextField mapperXmlPathTf;
    private JButton entityPathBtn;
    private JButton mapperPathBtn;
    private JButton mapperXmlPathBtn;
    private JTextField entityNamePatternTf;
    private JCheckBox dataCheckBox;
    private JCheckBox builderCheckBox;
    private JCheckBox noArgsConstructorCheckBox;
    private JCheckBox allArgsConstructorCheckBox;
    private JTextField mapperNamePatternTf;
    private JTextField superMapperClassTf;
    private JCheckBox selectByExampleWithBLOBsCheckBox;
    private JCheckBox selectByExampleCheckBox;
    private JCheckBox selectByPrimaryKeyCheckBox;
    private JCheckBox insertCheckBox;
    private JCheckBox insertSelectiveCheckBox;
    private JCheckBox countByExampleCheckBox;
    private JCheckBox updateByExampleCheckBox;
    private JCheckBox updateByExampleSelectiveCheckBox;
    private JCheckBox updateByPrimaryKeyCheckBox;
    private JCheckBox updateByPrimaryKeySelectiveCheckBox;
    private JCheckBox updateByExampleWithBLOBsCheckBox;
    private JCheckBox updateByPrimaryKeyWithBLOBsCheckBox;
    private JCheckBox deleteByExampleCheckBox;
    private JCheckBox deleteByPrimaryKeyCheckBox;
    private JTextField mapperXmlNamePatternTf;
    private JTextField servicePathTf;
    private JTextField servicePackageTf;
    private JTextField serviceNamePatternTf;
    private JTextField superServiceClassTf;
    private JButton servicePathBtn;
    private JTextField serviceImplPathTf;
    private JTextField serviceImplPackageTf;
    private JTextField serviceImplNamePatternTf;
    private JTextField superServiceImplClassTf;
    private JTextField controllerPathTf;
    private JTextField controllerPackageTf;
    private JTextField controllerNamePatternTf;
    private JCheckBox controllerSwaggerCheckBox;
    private JComboBox databaseComboBox;
    private JTextField tableNameRegexTf;
    private JButton selectTableBtn;
    private JButton configDataBaseBtn;
    private JTable table;
    private JComboBox frameworkTypeComboBox;
    private JButton serviceImplPathBtn;
    private JButton controllerPathBtn;
    private JPanel mainPanel;
    private JCheckBox entityGenerateCheckBox;
    private JCheckBox mapperGenerateCheckBox;
    private JCheckBox mapperXmlGenerateCheckBox;
    private JCheckBox serviceGenerateCheckBox;
    private JCheckBox serviceImplGenerateCheckBox;
    private JCheckBox controllerGenerateCheckBox;
    private JTextField authorTf;

    private JButton restBtn;
    private JButton saveConfigBtn;
    private JButton generatorBtn;
    private JButton cancelBtn;


    /**
     * 项目
     */
    private Project project;

    /**
     * 数据库列表
     */
    private List<Database> databases = new ArrayList<>();

    /**
     * 选择的数据库
     */
    private Database selectedDatabase;

    /**
     * 选中的表名列表
     */
    private List<String> selectedTableNames = new ArrayList<>();

    /**
     * 生成代码业务接口
     */
    private IGeneratorService generatorService = new GeneratorServiceImpl();

    public GeneratorSettingUI(Project project) {
        super(true);
        init();
        this.project = project;

        // 初始化界面数据
        renderUIData(project);

        // 创建事件监听器
        initActionListener(project);
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        return mainPanel;
    }

    @Override
    protected JComponent createSouthPanel() {
        JPanel southPanel = new JPanel(new FlowLayout());
        // 重置配置
        restBtn = new JButton("重置配置");
        southPanel.add(restBtn);

        // 保存配置
        saveConfigBtn = new JButton("保存配置");
        southPanel.add(saveConfigBtn);

        // 生成代码
        generatorBtn = new JButton("生成代码");
        southPanel.add(generatorBtn);

        // 取消配置
        cancelBtn = new JButton("取消");
        southPanel.add(cancelBtn);
        return southPanel;
    }

    /**
     * 渲染UI数据
     *
     * @param project 项目
     */
    private void renderUIData(Project project) {
        // 获取持久化数据
        PersistentState persistentState = PersistentStateService.getInstance(null != project ? project : ProjectManager.getInstance().getDefaultProject()).getState();
        GeneratorProperties generatorProperties = persistentState.getGeneratorContext().getGeneratorProperties();

        // 获取生成配置
        CommonProperties commonProperties = generatorProperties.getCommonProperties();

        // 获取代码生成作者
        authorTf.setText(commonProperties.getAuthor());
        if (StringUtils.isBlank(commonProperties.getAuthor())) {
            authorTf.setText(System.getenv().get("USER"));
        }

        // 框架类型
        FrameworkTypeEnum.getFrameworkNames().forEach(frameworkTypeName -> frameworkTypeComboBox.addItem(frameworkTypeName));
        frameworkTypeComboBox.setSelectedItem(FrameworkTypeEnum.getFrameworkNames().get(0));
        if (StringUtils.isNotBlank(commonProperties.getFrameworkTypeComboBoxValue())) {
            frameworkTypeComboBox.setSelectedItem(commonProperties.getFrameworkTypeComboBoxValue());
        }

        // 项目路径
        projectPathTf.setText(project.getBasePath());

        // 数据库
        databases = commonProperties.getDatabases();
        if (CollectionUtils.isNotEmpty(databases)) {
            databases.forEach(database -> databaseComboBox.addItem(database.getDatabaseName()));

            // 设置默认数据库
            databaseComboBox.setSelectedItem(databases.get(0).getDatabaseName());
            if (StringUtils.isNotBlank(commonProperties.getDatabaseComboBoxValue())) {
                databaseComboBox.setSelectedItem(commonProperties.getDatabaseComboBoxValue());
            }

            for (Database database : databases) {
                if (database.getDatabaseName().equals(databaseComboBox.getSelectedItem())) {
                    selectedDatabase = database;
                }
            }
        }

        // 表名格式
        tableNameRegexTf.setText(commonProperties.getTableNameRegex());

        // entity 设置
        EntityProperties entityProperties = generatorProperties.getEntityProperties();
        entityGenerateCheckBox.setSelected(entityProperties.isSelectedGenerateCheckBox());
        entityPathTf.setText(entityProperties.getPath());
        entityPackageTf.setText(entityProperties.getPackageName());
        entityNamePatternTf.setText(StringUtils.isBlank(entityProperties.getNamePattern()) ? Constant.ENTITY_NAME_DEFAULT_FORMAT : entityProperties.getNamePattern());
        dataCheckBox.setSelected(entityProperties.isSelectedDataCheckBox());
        builderCheckBox.setSelected(entityProperties.isSelectedBuilderCheckBox());
        noArgsConstructorCheckBox.setSelected(entityProperties.isSelectedNoArgsConstructorCheckBox());
        allArgsConstructorCheckBox.setSelected(entityProperties.isSelectedAllArgsConstructorCheckBox());

        // mapper 设置
        MapperProperties mapperProperties = generatorProperties.getMapperProperties();
        mapperGenerateCheckBox.setSelected(mapperProperties.isSelectedGenerateCheckBox());
        mapperPathTf.setText(mapperProperties.getPath());
        mapperPackageTf.setText(mapperProperties.getPackageName());
        mapperNamePatternTf.setText(StringUtils.isBlank(mapperProperties.getNamePattern()) ? Constant.MAPPER_NAME_DEFAULT_FORMAT : mapperProperties.getNamePattern());
        superMapperClassTf.setText(mapperProperties.getSuperMapperClass());
        selectByExampleWithBLOBsCheckBox.setSelected(mapperProperties.isSelectedSelectByExampleWithBLOBsCheckBox());
        selectByExampleCheckBox.setSelected(mapperProperties.isSelectedSelectByExampleCheckBox());
        selectByPrimaryKeyCheckBox.setSelected(mapperProperties.isSelectedSelectByPrimaryKeyCheckBox());
        insertCheckBox.setSelected(mapperProperties.isSelectedInsertCheckBox());
        insertSelectiveCheckBox.setSelected(mapperProperties.isSelectedInsertSelectiveCheckBox());
        countByExampleCheckBox.setSelected(mapperProperties.isSelectedCountByExampleCheckBox());
        updateByExampleCheckBox.setSelected(mapperProperties.isSelectedUpdateByExampleCheckBox());
        updateByExampleSelectiveCheckBox.setSelected(mapperProperties.isSelectedUpdateByExampleSelectiveCheckBox());
        updateByPrimaryKeyCheckBox.setSelected(mapperProperties.isSelectedUpdateByPrimaryKeyCheckBox());
        updateByPrimaryKeySelectiveCheckBox.setSelected(mapperProperties.isSelectedUpdateByPrimaryKeySelectiveCheckBox());
        updateByExampleWithBLOBsCheckBox.setSelected(mapperProperties.isSelectedUpdateByExampleWithBLOBsCheckBox());
        updateByPrimaryKeyWithBLOBsCheckBox.setSelected(mapperProperties.isSelectedUpdateByPrimaryKeyWithBLOBsCheckBox());
        deleteByExampleCheckBox.setSelected(mapperProperties.isSelectedDeleteByExampleCheckBox());
        deleteByPrimaryKeyCheckBox.setSelected(mapperProperties.isSelectedDeleteByPrimaryKeyCheckBox());

        // mapperXml 设置
        MapperXmlProperties mapperXmlProperties = generatorProperties.getMapperXmlProperties();
        mapperXmlGenerateCheckBox.setSelected(mapperXmlProperties.isSelectedGenerateCheckBox());
        mapperXmlPathTf.setText(mapperXmlProperties.getPath());
        mapperXmlNamePatternTf.setText(StringUtils.isBlank(mapperXmlProperties.getNamePattern()) ? Constant.MAPPER_XML_NAME_DEFAULT_FORMAT : mapperXmlProperties.getNamePattern());

        // service 设置
        ServiceProperties serviceProperties = generatorProperties.getServiceProperties();
        serviceGenerateCheckBox.setSelected(serviceProperties.isSelectedGenerateCheckBox());
        servicePathTf.setText(serviceProperties.getPath());
        servicePackageTf.setText(serviceProperties.getPackageName());
        serviceNamePatternTf.setText(StringUtils.isBlank(serviceProperties.getNamePattern()) ? Constant.SERVICE_NAME_DEFAULT_FORMAT : serviceProperties.getNamePattern());
        superServiceClassTf.setText(serviceProperties.getSuperServiceClass());

        // serviceImpl 设置
        ServiceImplProperties serviceImplProperties = generatorProperties.getServiceImplProperties();
        serviceImplGenerateCheckBox.setSelected(serviceImplProperties.isSelectedGenerateCheckBox());
        serviceImplPathTf.setText(serviceImplProperties.getPath());
        serviceImplPackageTf.setText(serviceImplProperties.getPackageName());
        serviceImplNamePatternTf.setText(StringUtils.isBlank(serviceImplProperties.getNamePattern()) ? Constant.SERVICE_IMPL_NAME_DEFAULT_FORMAT : serviceImplProperties.getNamePattern());
        superServiceImplClassTf.setText(serviceImplProperties.getSuperServiceImplClass());

        // controller 设置
        ControllerProperties controllerProperties = generatorProperties.getControllerProperties();
        controllerGenerateCheckBox.setSelected(controllerProperties.isSelectedGenerateCheckBox());
        controllerPathTf.setText(controllerProperties.getPath());
        controllerPackageTf.setText(controllerProperties.getPackageName());
        controllerNamePatternTf.setText(StringUtils.isBlank(controllerProperties.getNamePattern()) ? Constant.CONTROLLER_NAME_DEFAULT_FORMAT : controllerProperties.getNamePattern());
        controllerSwaggerCheckBox.setSelected(controllerProperties.isSelectedSwaggerCheckBox());
    }

    /**
     * 创建事件监听器
     *
     * @param project 项目
     */
    private void initActionListener(Project project) {
        entityPathBtn.addActionListener(e -> {
            VirtualFile virtualFile = FileChooser.chooseFile(FileChooserDescriptorFactory.createSingleFolderDescriptor(), project, project.getBaseDir());
            if (virtualFile != null) {
                entityPathTf.setText(virtualFile.getPath());
            }
        });
        mapperPathBtn.addActionListener(e -> {
            VirtualFile virtualFile = FileChooser.chooseFile(FileChooserDescriptorFactory.createSingleFolderDescriptor(), project, project.getBaseDir());
            if (virtualFile != null) {
                mapperPathTf.setText(virtualFile.getPath());
            }
        });
        mapperXmlPathBtn.addActionListener(e -> {
            VirtualFile virtualFile = FileChooser.chooseFile(FileChooserDescriptorFactory.createSingleFolderDescriptor(), project, project.getBaseDir());
            if (virtualFile != null) {
                mapperXmlPathTf.setText(virtualFile.getPath());
            }
        });
        servicePathBtn.addActionListener(e -> {
            VirtualFile virtualFile = FileChooser.chooseFile(FileChooserDescriptorFactory.createSingleFolderDescriptor(), project, project.getBaseDir());
            if (virtualFile != null) {
                servicePathTf.setText(virtualFile.getPath());
            }
        });
        serviceImplPathBtn.addActionListener(e -> {
            VirtualFile virtualFile = FileChooser.chooseFile(FileChooserDescriptorFactory.createSingleFolderDescriptor(), project, project.getBaseDir());
            if (virtualFile != null) {
                serviceImplPathTf.setText(virtualFile.getPath());
            }
        });
        controllerPathBtn.addActionListener(e -> {
            VirtualFile virtualFile = FileChooser.chooseFile(FileChooserDescriptorFactory.createSingleFolderDescriptor(), project, project.getBaseDir());
            if (virtualFile != null) {
                controllerPathTf.setText(virtualFile.getPath());
            }
        });
        databaseComboBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                selectedDatabase = databases.stream().filter(database -> database.getDatabaseName().equals(e.getItem())).findAny().get();
            }
        });
        configDataBaseBtn.addActionListener(e -> {
            DataSourcesSettingUI dataSourcesSettingUI = new DataSourcesSettingUI(project, this);
            dataSourcesSettingUI.show();
        });
        selectTableBtn.addActionListener(e -> {
            try {
                MySQLDBHelper dbHelper = new MySQLDBHelper(selectedDatabase);

                // 获取表名列表
                String tableNamePattern = StringUtils.isBlank(tableNameRegexTf.getText()) ? "%" : tableNameRegexTf.getText();
                List<String> tableNames = dbHelper.getTableName(selectedDatabase.getDatabaseName(), tableNamePattern);

                // 显示表名列表
                String[] title = {"", "表名"};
                // 行index,列index
                Object[][] data = new Object[tableNames.size()][2];
                for (int i = 0; i < tableNames.size(); i++) {
                    data[i][1] = tableNames.get(i);
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

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int rowIdx = table.rowAtPoint(e.getPoint());
                    Boolean flag = (Boolean) table.getValueAt(rowIdx, 0);
                    if (Objects.nonNull(flag) && flag) {
                        selectedTableNames.add(table.getValueAt(rowIdx, 1).toString());
                    } else {
                        selectedTableNames.remove(table.getValueAt(rowIdx, 1).toString());
                    }
                }
            }
        });
        restBtn.addActionListener(e -> {

        });
        saveConfigBtn.addActionListener(e -> {
            // 获取代码生成配置
            GeneratorProperties generatorProperties = getGeneratorProperties();
            GeneratorContext generatorContext = new GeneratorContext();
            generatorContext.setGeneratorProperties(generatorProperties);

            // 持久化
            PersistentState persistentState = PersistentStateService.getInstance(null != project ? project : ProjectManager.getInstance().getDefaultProject()).getState();
            persistentState.setGeneratorContext(generatorContext);
        });
        generatorBtn.addActionListener(e -> {
            // 获取代码生成配置
            GeneratorProperties generatorProperties = getGeneratorProperties();

            // 获取表列表
            if (CollectionUtils.isEmpty(selectedTableNames)) {
                Messages.showWarningDialog(project, "请选择要生成的表", "info");
                return;
            }
            List<TableInfo> tables = getTables(selectedDatabase, selectedTableNames);
            GeneratorContext generatorContext = new GeneratorContext();
            generatorContext.setTables(tables);
            generatorContext.setGeneratorProperties(generatorProperties);

            // 持久化
            PersistentState persistentState = PersistentStateService.getInstance(null != project ? project : ProjectManager.getInstance().getDefaultProject()).getState();
            persistentState.setGeneratorContext(generatorContext);

            // 校验数据
            String message = validGeneratorData(generatorProperties);
            if (StringUtils.isNotBlank(message)) {
                Messages.showWarningDialog(project, message, "info");
                return;
            }

            // 生成代码
            generatorService.doGenerator(project, generatorContext);
            Messages.showWarningDialog(project, "代码生成成功", "info");
        });
        cancelBtn.addActionListener(e -> {
            GeneratorSettingUI.this.dispose();
        });
    }

    /**
     * 获取生成代码配置属性
     *
     * @return 生成代码配置属性
     */
    private GeneratorProperties getGeneratorProperties() {
        GeneratorProperties generatorProperties = new GeneratorProperties();

        // 公共配置
        CommonProperties commonProperties = new CommonProperties();
        commonProperties.setAuthor(authorTf.getText());
        commonProperties.setProjectPath(projectPathTf.getText());
        commonProperties.setDatabases(databases);
        commonProperties.setDatabaseComboBoxValue(String.valueOf(databaseComboBox.getSelectedItem()));
        commonProperties.setTableNameRegex(tableNameRegexTf.getText());
        commonProperties.setFrameworkTypeComboBoxValues(FrameworkTypeEnum.getFrameworkNames());
        commonProperties.setFrameworkTypeComboBoxValue(String.valueOf(frameworkTypeComboBox.getSelectedItem()));
        generatorProperties.setCommonProperties(commonProperties);

        // entity配置
        EntityProperties entityProperties = new EntityProperties();
        entityProperties.setSelectedGenerateCheckBox(entityGenerateCheckBox.isSelected());
        entityProperties.setPath(entityPathTf.getText());
        entityProperties.setPackageName(entityPackageTf.getText());
        entityProperties.setNamePattern(StringUtils.isBlank(entityNamePatternTf.getText()) ? Constant.ENTITY_NAME_DEFAULT_FORMAT : entityNamePatternTf.getText());
        entityProperties.setExampleNamePattern(Constant.ENTITY_EXAMPLE_NAME_DEFAULT_FORMAT);
        entityProperties.setSelectedDataCheckBox(dataCheckBox.isSelected());
        entityProperties.setSelectedBuilderCheckBox(builderCheckBox.isSelected());
        entityProperties.setSelectedNoArgsConstructorCheckBox(noArgsConstructorCheckBox.isSelected());
        entityProperties.setSelectedAllArgsConstructorCheckBox(allArgsConstructorCheckBox.isSelected());
        generatorProperties.setEntityProperties(entityProperties);

        // mapper配置
        MapperProperties mapperProperties = new MapperProperties();
        mapperProperties.setSelectedGenerateCheckBox(mapperGenerateCheckBox.isSelected());
        mapperProperties.setPath(mapperPathTf.getText());
        mapperProperties.setPackageName(mapperPackageTf.getText());
        mapperProperties.setNamePattern(StringUtils.isBlank(mapperNamePatternTf.getText()) ? Constant.MAPPER_NAME_DEFAULT_FORMAT : mapperNamePatternTf.getText());
        mapperProperties.setSuperMapperClass(superMapperClassTf.getText());
        mapperProperties.setSelectedSelectByExampleWithBLOBsCheckBox(selectByExampleWithBLOBsCheckBox.isSelected());
        mapperProperties.setSelectedSelectByExampleCheckBox(selectByExampleCheckBox.isSelected());
        mapperProperties.setSelectedSelectByPrimaryKeyCheckBox(selectByPrimaryKeyCheckBox.isSelected());
        mapperProperties.setSelectedInsertCheckBox(insertCheckBox.isSelected());
        mapperProperties.setSelectedInsertSelectiveCheckBox(insertSelectiveCheckBox.isSelected());
        mapperProperties.setSelectedCountByExampleCheckBox(countByExampleCheckBox.isSelected());
        mapperProperties.setSelectedUpdateByExampleCheckBox(updateByExampleCheckBox.isSelected());
        mapperProperties.setSelectedUpdateByExampleSelectiveCheckBox(updateByExampleSelectiveCheckBox.isSelected());
        mapperProperties.setSelectedUpdateByPrimaryKeyCheckBox(updateByPrimaryKeyCheckBox.isSelected());
        mapperProperties.setSelectedUpdateByPrimaryKeySelectiveCheckBox(updateByPrimaryKeySelectiveCheckBox.isSelected());
        mapperProperties.setSelectedUpdateByExampleWithBLOBsCheckBox(updateByExampleWithBLOBsCheckBox.isSelected());
        mapperProperties.setSelectedUpdateByPrimaryKeyWithBLOBsCheckBox(updateByPrimaryKeyWithBLOBsCheckBox.isSelected());
        mapperProperties.setSelectedDeleteByExampleCheckBox(deleteByExampleCheckBox.isSelected());
        mapperProperties.setSelectedDeleteByPrimaryKeyCheckBox(deleteByPrimaryKeyCheckBox.isSelected());
        generatorProperties.setMapperProperties(mapperProperties);

        // mapperXml配置
        MapperXmlProperties mapperXmlProperties = new MapperXmlProperties();
        mapperXmlProperties.setSelectedGenerateCheckBox(mapperXmlGenerateCheckBox.isSelected());
        mapperXmlProperties.setPath(mapperXmlPathTf.getText());
        mapperXmlProperties.setNamePattern(mapperXmlNamePatternTf.getText());
        mapperXmlProperties.setPath(mapperPathTf.getText());
        mapperXmlProperties.setPackageName(mapperPackageTf.getText());
        mapperXmlProperties.setNamePattern(StringUtils.isBlank(mapperNamePatternTf.getText()) ? Constant.MAPPER_NAME_DEFAULT_FORMAT : mapperNamePatternTf.getText());
        mapperXmlProperties.setSuperMapperClass(superMapperClassTf.getText());
        mapperXmlProperties.setSelectedSelectByExampleWithBLOBsCheckBox(selectByExampleWithBLOBsCheckBox.isSelected());
        mapperXmlProperties.setSelectedSelectByExampleCheckBox(selectByExampleCheckBox.isSelected());
        mapperXmlProperties.setSelectedSelectByPrimaryKeyCheckBox(selectByPrimaryKeyCheckBox.isSelected());
        mapperXmlProperties.setSelectedInsertCheckBox(insertCheckBox.isSelected());
        mapperXmlProperties.setSelectedInsertSelectiveCheckBox(insertSelectiveCheckBox.isSelected());
        mapperXmlProperties.setSelectedCountByExampleCheckBox(countByExampleCheckBox.isSelected());
        mapperXmlProperties.setSelectedUpdateByExampleCheckBox(updateByExampleCheckBox.isSelected());
        mapperXmlProperties.setSelectedUpdateByExampleSelectiveCheckBox(updateByExampleSelectiveCheckBox.isSelected());
        mapperXmlProperties.setSelectedUpdateByPrimaryKeyCheckBox(updateByPrimaryKeyCheckBox.isSelected());
        mapperXmlProperties.setSelectedUpdateByPrimaryKeySelectiveCheckBox(updateByPrimaryKeySelectiveCheckBox.isSelected());
        mapperXmlProperties.setSelectedUpdateByExampleWithBLOBsCheckBox(updateByExampleWithBLOBsCheckBox.isSelected());
        mapperXmlProperties.setSelectedUpdateByPrimaryKeyWithBLOBsCheckBox(updateByPrimaryKeyWithBLOBsCheckBox.isSelected());
        mapperXmlProperties.setSelectedDeleteByExampleCheckBox(deleteByExampleCheckBox.isSelected());
        mapperXmlProperties.setSelectedDeleteByPrimaryKeyCheckBox(deleteByPrimaryKeyCheckBox.isSelected());
        generatorProperties.setMapperXmlProperties(mapperXmlProperties);

        // service配置
        ServiceProperties serviceProperties = new ServiceProperties();
        serviceProperties.setSelectedGenerateCheckBox(serviceGenerateCheckBox.isSelected());
        serviceProperties.setPath(serviceImplPathTf.getText());
        serviceProperties.setPackageName(servicePackageTf.getText());
        serviceProperties.setNamePattern(StringUtils.isBlank(serviceNamePatternTf.getText()) ? Constant.SERVICE_NAME_DEFAULT_FORMAT : serviceNamePatternTf.getText());
        serviceProperties.setSuperServiceClass(superServiceImplClassTf.getText());
        generatorProperties.setServiceProperties(serviceProperties);

        // serviceImpl配置
        ServiceImplProperties serviceImplProperties = new ServiceImplProperties();
        serviceImplProperties.setSelectedGenerateCheckBox(serviceImplGenerateCheckBox.isSelected());
        serviceImplProperties.setPath(servicePathTf.getText());
        serviceImplProperties.setPackageName(serviceImplPackageTf.getText());
        serviceImplProperties.setNamePattern(StringUtils.isBlank(serviceImplNamePatternTf.getText()) ? Constant.SERVICE_IMPL_NAME_DEFAULT_FORMAT : serviceImplNamePatternTf.getText());
        serviceImplProperties.setSuperServiceImplClass(superServiceImplClassTf.getText());
        generatorProperties.setServiceImplProperties(serviceImplProperties);

        // controller配置
        ControllerProperties controllerProperties = new ControllerProperties();
        controllerProperties.setSelectedGenerateCheckBox(controllerGenerateCheckBox.isSelected());
        controllerProperties.setPath(controllerPathTf.getText());
        controllerProperties.setPackageName(controllerPackageTf.getText());
        controllerProperties.setNamePattern(StringUtils.isBlank(controllerNamePatternTf.getText()) ? Constant.CONTROLLER_NAME_DEFAULT_FORMAT : controllerNamePatternTf.getText());
        controllerProperties.setSelectedSwaggerCheckBox(controllerSwaggerCheckBox.isSelected());
        generatorProperties.setControllerProperties(controllerProperties);

        return generatorProperties;
    }

    /**
     * 校验生成数据
     *
     * @param generatorProperties 生成配置
     * @return 返回非空字符串，校验不通过
     */
    private String validGeneratorData(GeneratorProperties generatorProperties) {
        // 公共配置校验
        CommonProperties commonProperties = generatorProperties.getCommonProperties();
        if (StringUtils.isBlank(commonProperties.getAuthor())) {
            return "请填写生成作者";
        }

        // 实体校验
        EntityProperties entityProperties = generatorProperties.getEntityProperties();
        if (entityProperties.isSelectedGenerateCheckBox()) {
            if (StringUtils.isBlank(entityProperties.getPath())) {
                return "请选择entity路径";
            }
            if (StringUtils.isBlank(entityProperties.getPackageName())) {
                return "请填写entity包名";
            }
            if (!validNamePattern(entityProperties.getNamePattern())) {
                return "entity命名格式需要包含%s";
            }
        }

        // mapper校验
        MapperProperties mapperProperties = generatorProperties.getMapperProperties();
        if (mapperProperties.isSelectedGenerateCheckBox()) {
            if (StringUtils.isBlank(mapperProperties.getPath())) {
                return "请选择mapper路径";
            }
            if (StringUtils.isBlank(mapperProperties.getPackageName())) {
                return "请填写mapper包名";
            }
            if (!validNamePattern(mapperProperties.getNamePattern())) {
                return "mapper命名格式需要包含%s";
            }
        }

        // mapperXml校验
        MapperXmlProperties mapperXmlProperties = generatorProperties.getMapperXmlProperties();
        if (mapperXmlProperties.isSelectedGenerateCheckBox()) {
            if (StringUtils.isBlank(mapperXmlProperties.getPath())) {
                return "请选择mapperXml路径";
            }
            if (!validNamePattern(mapperXmlProperties.getNamePattern())) {
                return "mapperXml命名格式需要包含%s";
            }
        }

        // service校验
        ServiceProperties serviceProperties = generatorProperties.getServiceProperties();
        if (serviceProperties.isSelectedGenerateCheckBox()) {
            if (StringUtils.isBlank(serviceProperties.getPath())) {
                return "请选择service路径";
            }
            if (StringUtils.isBlank(serviceProperties.getPackageName())) {
                return "请填写service包名";
            }
            if (!validNamePattern(serviceProperties.getNamePattern())) {
                return "service命名格式需要包含%s";
            }
        }

        // serviceImpl校验
        ServiceImplProperties serviceImplProperties = generatorProperties.getServiceImplProperties();
        if (serviceImplProperties.isSelectedGenerateCheckBox()) {
            if (StringUtils.isBlank(serviceImplProperties.getPath())) {
                return "请选择serviceImpl路径";
            }
            if (StringUtils.isBlank(serviceImplProperties.getPackageName())) {
                return "请填写serviceImpl包名";
            }
            if (!validNamePattern(serviceImplProperties.getNamePattern())) {
                return "serviceImpl命名格式需要包含%s";
            }
        }

        // controller
        ControllerProperties controllerProperties = generatorProperties.getControllerProperties();
        if (controllerProperties.isSelectedGenerateCheckBox()) {
            if (StringUtils.isBlank(controllerProperties.getPath())) {
                return "请选择controller路径";
            }
            if (StringUtils.isBlank(controllerProperties.getPackageName())) {
                return "请填写controller包名";
            }
            if (!validNamePattern(controllerProperties.getNamePattern())) {
                return "controller命名格式需要包含%s";
            }
        }

        // 表校验
        if (CollectionUtils.isEmpty(selectedTableNames)) {
            return "请选择要生成的表";
        }

        // 至少要生成一种文件
        if (!(entityProperties.isSelectedGenerateCheckBox()
                || mapperProperties.isSelectedGenerateCheckBox()
                || mapperXmlProperties.isSelectedGenerateCheckBox()
                || serviceProperties.isSelectedGenerateCheckBox()
                || serviceImplProperties.isSelectedGenerateCheckBox()
                || controllerProperties.isSelectedGenerateCheckBox())) {
            return "至少要选择生成一种文件";
        }
        return "";
    }

    /**
     * 校验命名格式
     *
     * @param namePattern 命名格式
     * @return true 校验通过、false 校验失败
     */
    private boolean validNamePattern(String namePattern) {
        return StringUtils.isNotBlank(namePattern) && namePattern.contains("%s");
    }

    /**
     * 查询表列表
     *
     * @param database   数据库
     * @param tableNames 表名列表
     * @return 表列表
     */
    private List<TableInfo> getTables(Database database, List<String> tableNames) {
        MySQLDBHelper mySQLDBHelper = new MySQLDBHelper(database);
        List<TableInfo> tables = new ArrayList<>();
        for (String tableName : tableNames) {
            tables.add(mySQLDBHelper.getTableInfo(tableName));
        }
        return tables;
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
        databaseComboBox.setModel(comboBoxModel);

        if (databases.size() == 1) {
            selectedDatabase = databases.get(0);
        }
        this.databases = databases;
    }

}
