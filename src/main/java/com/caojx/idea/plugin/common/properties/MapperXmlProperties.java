package com.caojx.idea.plugin.common.properties;

import java.io.Serializable;

/**
 * MapperXml配置属性
 *
 * @author caojx
 * @date 2022/4/10 12:15 PM
 */
public class MapperXmlProperties implements Serializable {

    /**
     * 是否生成mapperXml
     */
    private boolean selectedGenerateCheckBox = true;

    /**
     * mapperXml路径
     */
    private String path;

    /**
     * mapperXml命名格式
     */
    private String namePattern;

    public boolean isSelectedGenerateCheckBox() {
        return selectedGenerateCheckBox;
    }

    public void setSelectedGenerateCheckBox(boolean selectedGenerateCheckBox) {
        this.selectedGenerateCheckBox = selectedGenerateCheckBox;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getNamePattern() {
        return namePattern;
    }

    public void setNamePattern(String namePattern) {
        this.namePattern = namePattern;
    }
}
