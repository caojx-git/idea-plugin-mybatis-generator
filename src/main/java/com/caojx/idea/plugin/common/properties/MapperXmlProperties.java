package com.caojx.idea.plugin.common.properties;

/**
 * MapperXml配置属性
 *
 * @author caojx
 * @date 2022/4/10 12:15 PM
 */
public class MapperXmlProperties extends MapperProperties {

    /**
     * 是否生成mapperXml
     */
    private boolean mapperXmlGenerateValue;

    /**
     * mapperXml路径
     */
    private String mapperXmlPath;

    /**
     * mapperXml命名格式
     */
    private String mapperXmlNamePattern;

    public boolean getMapperXmlGenerateValue() {
        return mapperXmlGenerateValue;
    }

    public void setMapperXmlGenerateValue(boolean mapperXmlGenerateValue) {
        this.mapperXmlGenerateValue = mapperXmlGenerateValue;
    }

    public String getMapperXmlPath() {
        return mapperXmlPath;
    }

    public void setMapperXmlPath(String mapperXmlPath) {
        this.mapperXmlPath = mapperXmlPath;
    }

    public String getMapperXmlNamePattern() {
        return mapperXmlNamePattern;
    }

    public void setMapperXmlNamePattern(String mapperXmlNamePattern) {
        this.mapperXmlNamePattern = mapperXmlNamePattern;
    }
}
