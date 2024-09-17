package com.caojx.idea.plugin.persistent;

import com.caojx.idea.plugin.common.pojo.DatabaseWithOutPwd;
import com.caojx.idea.plugin.common.utils.JsonUtils;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 持久化扩展配置
 *
 * @author caojx
 * @since 2024/8/12 10:17
 */
public class PersistentExtConfig implements Serializable {

    /**
     * 数据库配置，保存到json文件中，所有项目共享
     */
    private List<DatabaseWithOutPwd> databases = new ArrayList<>();

    public List<DatabaseWithOutPwd> getDatabases() {
        return databases;
    }

    public void setDatabases(List<DatabaseWithOutPwd> databases) {
        this.databases = databases;
    }


    /**
     * 获取扩展配置目录
     */
    public static Path getPersistentExtConfigPath() {
        Path extConfigPath = null;
        try {
            Path path = Paths.get(System.getProperty("user.home"), ".myBatisCodeGenerator");
            if (!Files.exists(path)) {
                Files.createDirectory(path);
            }

            extConfigPath = path.resolve("ext-config.json");
            if (!Files.exists(extConfigPath)) {
                Files.createFile(extConfigPath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return extConfigPath;
    }

    /**
     * 保存数据库配置
     */
    public static void saveDatabases(List<DatabaseWithOutPwd> databases) {
        try {
            Path extConfigPath = PersistentExtConfig.getPersistentExtConfigPath();
            PersistentExtConfig persistentExtConfig = JsonUtils.parseObject(extConfigPath, PersistentExtConfig.class);
            if (Objects.isNull(persistentExtConfig)) {
                persistentExtConfig = new PersistentExtConfig();
            }
            persistentExtConfig.setDatabases(databases);
            JsonUtils.writeJSON(extConfigPath, persistentExtConfig);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加载数据库配置
     */
    public static List<DatabaseWithOutPwd> loadDatabase() {
        try {
            Path extConfigPath = PersistentExtConfig.getPersistentExtConfigPath();
            PersistentExtConfig persistentExtConfig = JsonUtils.parseObject(extConfigPath, PersistentExtConfig.class);
            if (persistentExtConfig != null) {
                return persistentExtConfig.getDatabases();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
