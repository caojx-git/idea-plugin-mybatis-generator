package com.caojx.idea.plugin.common.pojo.persistent;

import com.caojx.idea.plugin.common.properties.GeneratorConfigContext;
import com.caojx.idea.plugin.common.pojo.db.Database;

import java.util.ArrayList;
import java.util.List;

/**
 * 持久化数据
 */
public class PersistentState {

    /**
     * 数据库列表
     */
    private List<Database> databases = new ArrayList<>();

    /**
     * 当前数据库
     */
    private Database currentDatabase;

    /**
     * 生成代码配置上线文
     */
    private GeneratorConfigContext generatorConfigContext = new GeneratorConfigContext();


    public List<Database> getDatabases() {
        return databases;
    }

    public void setDatabases(List<Database> databases) {
        this.databases = databases;
    }

    public Database getCurrentDatabase() {
        return currentDatabase;
    }

    public void setCurrentDatabase(Database currentDatabase) {
        this.currentDatabase = currentDatabase;
    }

    public GeneratorConfigContext getGeneratorConfigContext() {
        return generatorConfigContext;
    }

    public void setGeneratorConfigContext(GeneratorConfigContext generatorConfigContext) {
        this.generatorConfigContext = generatorConfigContext;
    }
}
