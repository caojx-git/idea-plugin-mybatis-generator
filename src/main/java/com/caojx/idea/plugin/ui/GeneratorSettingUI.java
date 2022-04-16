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
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.openapi.vfs.VirtualFile;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
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
public class GeneratorSettingUI implements Configurable {

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
        this.project = project;

        // 初始化界面数据
        renderUIData(project);

        // 创建事件监听器
        initActionListener(project);
    }

    @Override
    public @NlsContexts.ConfigurableName String getDisplayName() {
        return "GeneratorSettingUI2";
    }

    @Override
    public @Nullable JComponent createComponent() {
        return mainPanel;
    }

    @Override
    public boolean isModified() {
        return true;
    }

    @Override
    public void apply() throws ConfigurationException {
        // 获取代码生成配置
        GeneratorProperties generatorProperties = getGeneratorProperties();
        // 获取表列表
        List<TableInfo> tables = getTables(selectedDatabase, selectedTableNames);
        GeneratorContext generatorContext = new GeneratorContext();
        generatorContext.setTables(tables);
        generatorContext.setGeneratorProperties(generatorProperties);

        // 持久化
        PersistentState persistentState = PersistentStateService.getInstance(null != project ? project : ProjectManager.getInstance().getDefaultProject()).getState();
        persistentState.setGeneratorContext(generatorContext);

        // 生成代码
        generatorService.doGenerator(project, generatorContext);
        Messages.showWarningDialog(project, "代码生成成功", "info");
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
        entityGenerateCheckBox.setSelected(entityProperties.getEntityGenerateValue());
        entityPathTf.setText(entityProperties.getEntityPath());
        entityPackageTf.setText(entityProperties.getEntityPackage());
        entityNamePatternTf.setText(StringUtils.isBlank(entityProperties.getEntityNamePattern()) ? Constant.ENTITY_NAME_DEFAULT_FORMAT : entityProperties.getEntityNamePattern());
        dataCheckBox.setSelected(entityProperties.getDataCheckBoxValue());
        builderCheckBox.setSelected(entityProperties.getBuilderCheckBoxValue());
        noArgsConstructorCheckBox.setSelected(entityProperties.getNoArgsConstructorCheckBoxValue());
        allArgsConstructorCheckBox.setSelected(entityProperties.getAllArgsConstructorCheckBoxValue());

        // mapper 设置
        MapperProperties mapperProperties = generatorProperties.getMapperProperties();
        mapperGenerateCheckBox.setSelected(mapperProperties.getMapperGenerateValue());
        mapperPathTf.setText(mapperProperties.getMapperPath());
        mapperPackageTf.setText(mapperProperties.getMapperPackage());
        mapperNamePatternTf.setText(StringUtils.isBlank(mapperProperties.getMapperNamePattern()) ? Constant.MAPPER_NAME_DEFAULT_FORMAT : mapperProperties.getMapperNamePattern());
        superMapperClassTf.setText(mapperProperties.getSuperMapperClass());
        selectByExampleWithBLOBsCheckBox.setSelected(mapperProperties.getSelectByExampleWithBLOBsCheckBoxValue());
        selectByExampleCheckBox.setSelected(mapperProperties.getSelectByExampleCheckBoxValue());
        selectByPrimaryKeyCheckBox.setSelected(mapperProperties.getSelectByPrimaryKeyCheckBoxValue());
        insertCheckBox.setSelected(mapperProperties.getInsertCheckBoxValue());
        insertSelectiveCheckBox.setSelected(mapperProperties.getInsertSelectiveCheckBoxValue());
        countByExampleCheckBox.setSelected(mapperProperties.getCountByExampleCheckBoxValue());
        updateByExampleCheckBox.setSelected(mapperProperties.getUpdateByExampleCheckBoxValue());
        updateByExampleSelectiveCheckBox.setSelected(mapperProperties.getUpdateByExampleSelectiveCheckBoxValue());
        updateByPrimaryKeyCheckBox.setSelected(mapperProperties.getUpdateByPrimaryKeyCheckBoxValue());
        updateByPrimaryKeySelectiveCheckBox.setSelected(mapperProperties.getUpdateByPrimaryKeySelectiveCheckBoxValue());
        updateByExampleWithBLOBsCheckBox.setSelected(mapperProperties.getUpdateByExampleWithBLOBsCheckBoxValue());
        updateByPrimaryKeyWithBLOBsCheckBox.setSelected(mapperProperties.getUpdateByPrimaryKeyWithBLOBsCheckBoxValue());
        deleteByExampleCheckBox.setSelected(mapperProperties.getDeleteByExampleCheckBoxValue());
        deleteByPrimaryKeyCheckBox.setSelected(mapperProperties.getDeleteByPrimaryKeyCheckBoxValue());

        // mapperXml 设置
        MapperXmlProperties mapperXmlProperties = generatorProperties.getMapperXmlProperties();
        mapperXmlGenerateCheckBox.setSelected(mapperXmlProperties.getMapperXmlGenerateValue());
        mapperXmlPathTf.setText(mapperXmlProperties.getMapperXmlPath());
        mapperXmlNamePatternTf.setText(StringUtils.isBlank(mapperXmlProperties.getMapperXmlNamePattern()) ? Constant.MAPPER_XML_NAME_DEFAULT_FORMAT : mapperXmlProperties.getMapperXmlNamePattern());

        // service 设置
        ServiceProperties serviceProperties = generatorProperties.getServiceProperties();
        serviceGenerateCheckBox.setSelected(serviceProperties.getServiceGenerateValue());
        servicePathTf.setText(serviceProperties.getServicePath());
        servicePackageTf.setText(serviceProperties.getServicePackage());
        serviceNamePatternTf.setText(StringUtils.isBlank(serviceProperties.getServiceNamePattern()) ? Constant.SERVICE_NAME_DEFAULT_FORMAT : serviceProperties.getServiceNamePattern());
        superServiceClassTf.setText(serviceProperties.getSuperServiceClass());

        // serviceImpl 设置
        ServiceImplProperties serviceImplProperties = generatorProperties.getServiceImplProperties();
        serviceImplGenerateCheckBox.setSelected(serviceImplProperties.getServiceImplGenerateValue());
        serviceImplPathTf.setText(serviceImplProperties.getServiceImplPath());
        serviceImplPackageTf.setText(serviceImplProperties.getServiceImplPackage());
        serviceImplNamePatternTf.setText(StringUtils.isBlank(serviceImplProperties.getServiceImplNamePattern()) ? Constant.SERVICE_IMPL_NAME_DEFAULT_FORMAT : serviceImplProperties.getServiceImplNamePattern());
        superServiceImplClassTf.setText(serviceImplProperties.getSuperServiceImplClass());

        // controller 设置
        ControllerProperties controllerProperties = generatorProperties.getControllerProperties();
        controllerGenerateCheckBox.setSelected(controllerProperties.getControllerGenerateValue());
        controllerPathTf.setText(controllerProperties.getControllerPath());
        controllerPackageTf.setText(controllerProperties.getControllerPackage());
        controllerNamePatternTf.setText(StringUtils.isBlank(controllerProperties.getControllerNamePattern()) ? Constant.CONTROLLER_NAME_DEFAULT_FORMAT : controllerProperties.getControllerNamePattern());
        controllerSwaggerCheckBox.setSelected(controllerProperties.getControllerSwaggerCheckBoxValue());
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
        entityProperties.setEntityGenerateValue(entityGenerateCheckBox.isSelected());
        entityProperties.setEntityPath(entityPathTf.getText());
        entityProperties.setEntityPackage(entityPackageTf.getText());
        entityProperties.setEntityNamePattern(StringUtils.isBlank(entityNamePatternTf.getText()) ? Constant.ENTITY_NAME_DEFAULT_FORMAT : entityNamePatternTf.getText());
        entityProperties.setEntityExampleNamePattern(Constant.ENTITY_EXAMPLE_NAME_DEFAULT_FORMAT);
        entityProperties.setDataCheckBoxValue(dataCheckBox.isSelected());
        entityProperties.setBuilderCheckBoxValue(builderCheckBox.isSelected());
        entityProperties.setNoArgsConstructorCheckBoxValue(noArgsConstructorCheckBox.isSelected());
        entityProperties.setAllArgsConstructorCheckBoxValue(allArgsConstructorCheckBox.isSelected());
        generatorProperties.setEntityProperties(entityProperties);

        // mapper配置
        MapperProperties mapperProperties = new MapperProperties();
        mapperProperties.setMapperGenerateValue(mapperGenerateCheckBox.isSelected());
        mapperProperties.setMapperPath(mapperPathTf.getText());
        mapperProperties.setMapperPackage(mapperPackageTf.getText());
        mapperProperties.setMapperNamePattern(StringUtils.isBlank(mapperNamePatternTf.getText()) ? Constant.MAPPER_NAME_DEFAULT_FORMAT : mapperNamePatternTf.getText());
        mapperProperties.setSuperMapperClass(superMapperClassTf.getText());
        mapperProperties.setSelectByExampleWithBLOBsCheckBoxValue(selectByExampleWithBLOBsCheckBox.isSelected());
        mapperProperties.setSelectByExampleCheckBoxValue(selectByExampleCheckBox.isSelected());
        mapperProperties.setSelectByPrimaryKeyCheckBoxValue(selectByPrimaryKeyCheckBox.isSelected());
        mapperProperties.setInsertCheckBoxValue(insertCheckBox.isSelected());
        mapperProperties.setInsertSelectiveCheckBoxValue(insertSelectiveCheckBox.isSelected());
        mapperProperties.setCountByExampleCheckBoxValue(countByExampleCheckBox.isSelected());
        mapperProperties.setUpdateByExampleCheckBoxValue(updateByExampleCheckBox.isSelected());
        mapperProperties.setUpdateByExampleSelectiveCheckBoxValue(updateByExampleSelectiveCheckBox.isSelected());
        mapperProperties.setUpdateByPrimaryKeyCheckBoxValue(updateByPrimaryKeyCheckBox.isSelected());
        mapperProperties.setUpdateByPrimaryKeySelectiveCheckBoxValue(updateByPrimaryKeySelectiveCheckBox.isSelected());
        mapperProperties.setUpdateByExampleWithBLOBsCheckBoxValue(updateByExampleWithBLOBsCheckBox.isSelected());
        mapperProperties.setUpdateByPrimaryKeyWithBLOBsCheckBoxValue(updateByPrimaryKeyWithBLOBsCheckBox.isSelected());
        mapperProperties.setDeleteByExampleCheckBoxValue(deleteByExampleCheckBox.isSelected());
        mapperProperties.setDeleteByPrimaryKeyCheckBoxValue(deleteByPrimaryKeyCheckBox.isSelected());
        generatorProperties.setMapperProperties(mapperProperties);

        // mapperXml配置
        MapperXmlProperties mapperXmlProperties = new MapperXmlProperties();
        mapperXmlProperties.setMapperXmlGenerateValue(mapperXmlGenerateCheckBox.isSelected());
        mapperXmlProperties.setMapperXmlPath(mapperXmlPathTf.getText());
        mapperXmlProperties.setMapperXmlNamePattern(mapperXmlNamePatternTf.getText());
        mapperXmlProperties.setMapperPath(mapperPathTf.getText());
        mapperXmlProperties.setMapperPackage(mapperPackageTf.getText());
        mapperXmlProperties.setMapperNamePattern(StringUtils.isBlank(mapperNamePatternTf.getText()) ? Constant.MAPPER_NAME_DEFAULT_FORMAT : mapperNamePatternTf.getText());
        mapperXmlProperties.setSuperMapperClass(superMapperClassTf.getText());
        mapperXmlProperties.setSelectByExampleWithBLOBsCheckBoxValue(selectByExampleWithBLOBsCheckBox.isSelected());
        mapperXmlProperties.setSelectByExampleCheckBoxValue(selectByExampleCheckBox.isSelected());
        mapperXmlProperties.setSelectByPrimaryKeyCheckBoxValue(selectByPrimaryKeyCheckBox.isSelected());
        mapperXmlProperties.setInsertCheckBoxValue(insertCheckBox.isSelected());
        mapperXmlProperties.setInsertSelectiveCheckBoxValue(insertSelectiveCheckBox.isSelected());
        mapperXmlProperties.setCountByExampleCheckBoxValue(countByExampleCheckBox.isSelected());
        mapperXmlProperties.setUpdateByExampleCheckBoxValue(updateByExampleCheckBox.isSelected());
        mapperXmlProperties.setUpdateByExampleSelectiveCheckBoxValue(updateByExampleSelectiveCheckBox.isSelected());
        mapperXmlProperties.setUpdateByPrimaryKeyCheckBoxValue(updateByPrimaryKeyCheckBox.isSelected());
        mapperXmlProperties.setUpdateByPrimaryKeySelectiveCheckBoxValue(updateByPrimaryKeySelectiveCheckBox.isSelected());
        mapperXmlProperties.setUpdateByExampleWithBLOBsCheckBoxValue(updateByExampleWithBLOBsCheckBox.isSelected());
        mapperXmlProperties.setUpdateByPrimaryKeyWithBLOBsCheckBoxValue(updateByPrimaryKeyWithBLOBsCheckBox.isSelected());
        mapperXmlProperties.setDeleteByExampleCheckBoxValue(deleteByExampleCheckBox.isSelected());
        mapperXmlProperties.setDeleteByPrimaryKeyCheckBoxValue(deleteByPrimaryKeyCheckBox.isSelected());
        generatorProperties.setMapperXmlProperties(mapperXmlProperties);

        // service配置
        ServiceProperties serviceProperties = new ServiceProperties();
        serviceProperties.setServiceGenerateValue(serviceGenerateCheckBox.isSelected());
        serviceProperties.setServicePath(serviceImplPathTf.getText());
        serviceProperties.setServicePackage(servicePackageTf.getText());
        serviceProperties.setServiceNamePattern(StringUtils.isBlank(serviceNamePatternTf.getText()) ? Constant.SERVICE_NAME_DEFAULT_FORMAT : serviceNamePatternTf.getText());
        serviceProperties.setSuperServiceClass(superServiceImplClassTf.getText());
        generatorProperties.setServiceProperties(serviceProperties);

        // serviceImpl配置
        ServiceImplProperties serviceImplProperties = new ServiceImplProperties();
        serviceImplProperties.setServiceImplGenerateValue(serviceImplGenerateCheckBox.isSelected());
        serviceImplProperties.setServiceImplPath(servicePathTf.getText());
        serviceImplProperties.setServiceImplPackage(serviceImplPackageTf.getText());
        serviceImplProperties.setServiceImplNamePattern(StringUtils.isBlank(serviceImplNamePatternTf.getText()) ? Constant.SERVICE_IMPL_NAME_DEFAULT_FORMAT : serviceImplNamePatternTf.getText());
        serviceImplProperties.setSuperServiceImplClass(superServiceImplClassTf.getText());
        generatorProperties.setServiceImplProperties(serviceImplProperties);

        // controller配置
        ControllerProperties controllerProperties = new ControllerProperties();
        controllerProperties.setControllerGenerateValue(controllerGenerateCheckBox.isSelected());
        controllerProperties.setControllerPath(controllerPathTf.getText());
        controllerProperties.setControllerPackage(controllerPackageTf.getText());
        controllerProperties.setControllerNamePattern(StringUtils.isBlank(controllerNamePatternTf.getText()) ? Constant.CONTROLLER_NAME_DEFAULT_FORMAT : controllerNamePatternTf.getText());
        controllerProperties.setControllerSwaggerCheckBoxValue(controllerSwaggerCheckBox.isSelected());
        generatorProperties.setControllerProperties(controllerProperties);

        return generatorProperties;
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
