package com.caojx.idea.plugin.common.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;

/**
 * json 序列工具类
 *
 * @author caojx
 * @since 2024/8/12 09:59
 */
public class JsonUtils {

    /**
     * 解析json
     */
    public static <T> T parseObject(@NotNull Path path, Class<T> clazz) {
        try (FileReader fileReader = new FileReader(path.toFile());
             BufferedReader reader = new BufferedReader(fileReader)) {
            String json = "";
            String line = "";
            while ((line = reader.readLine()) != null) {
                json = json + line;
            }
            return JSON.parseObject(json, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将对象保存为json
     */
    public static void writeJSON(@NotNull Path path, Object object) {
        try (FileWriter fileWriter = new FileWriter(path.toFile());
             BufferedWriter writer = new BufferedWriter(fileWriter)) {
            String str = JSON.toJSONString(object, JSONWriter.Feature.PrettyFormat);
            writer.write(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
