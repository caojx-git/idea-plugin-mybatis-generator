package com.caojx.idea.plugin.common.properties;

/**
 * 生成属性配置
 *
 * @author caojx
 * @date 2022/4/10 12:55 PM
 */
public class GeneratorProperties {

    /**
     * 公共配置
     */
    private CommonProperties commonProperties = new CommonProperties();

    /**
     * 实体配置
     */
    private EntityProperties entityProperties = new EntityProperties();

    /**
     * mapper配置
     */
    private MapperProperties mapperProperties = new MapperProperties();

    /**
     * mapperXml配置
     */
    private MapperXmlProperties mapperXmlProperties = new MapperXmlProperties();

    /**
     * service配置
     */
    private ServiceProperties serviceProperties = new ServiceProperties();

    /**
     * serviceImpl配置
     */
    private ServiceImplProperties serviceImplProperties = new ServiceImplProperties();

    /**
     * controller配置
     */
    private ControllerProperties controllerProperties = new ControllerProperties();

    public CommonProperties getCommonProperties() {
        return commonProperties;
    }

    public void setCommonProperties(CommonProperties commonProperties) {
        this.commonProperties = commonProperties;
    }

    public EntityProperties getEntityProperties() {
        return entityProperties;
    }

    public void setEntityProperties(EntityProperties entityProperties) {
        this.entityProperties = entityProperties;
    }

    public MapperProperties getMapperProperties() {
        return mapperProperties;
    }

    public void setMapperProperties(MapperProperties mapperProperties) {
        this.mapperProperties = mapperProperties;
    }

    public MapperXmlProperties getMapperXmlProperties() {
        return mapperXmlProperties;
    }

    public void setMapperXmlProperties(MapperXmlProperties mapperXmlProperties) {
        this.mapperXmlProperties = mapperXmlProperties;
    }

    public ServiceProperties getServiceProperties() {
        return serviceProperties;
    }

    public void setServiceProperties(ServiceProperties serviceProperties) {
        this.serviceProperties = serviceProperties;
    }

    public ServiceImplProperties getServiceImplProperties() {
        return serviceImplProperties;
    }

    public void setServiceImplProperties(ServiceImplProperties serviceImplProperties) {
        this.serviceImplProperties = serviceImplProperties;
    }

    public ControllerProperties getControllerProperties() {
        return controllerProperties;
    }

    public void setControllerProperties(ControllerProperties controllerProperties) {
        this.controllerProperties = controllerProperties;
    }
}
