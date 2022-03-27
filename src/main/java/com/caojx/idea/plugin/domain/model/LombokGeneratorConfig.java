package com.caojx.idea.plugin.domain.model;

/**
 * Lombok生成配置类
 */
public class LombokGeneratorConfig {

    /**
     * 是否生成@Data注解
     */
    private boolean dataAnnotation;

    /**
     * 是否生成@GetterSetter注解
     */
    private boolean getterSetterAnnotation;

    /**
     * 是否生成@Builder注解
     */
    private boolean builderAnnotation;

    /**
     * 是否生成@NoArgsConstructor注解
     */
    private boolean noArgsConstructorAnnotation;

    /**
     * 是否生成@AllArgsConstructor注解
     */
    private boolean allArgsConstructorAnnotation;

    public boolean isDataAnnotation() {
        return dataAnnotation;
    }

    public void setDataAnnotation(boolean dataAnnotation) {
        this.dataAnnotation = dataAnnotation;
    }

    public boolean isGetterSetterAnnotation() {
        return getterSetterAnnotation;
    }

    public void setGetterSetterAnnotation(boolean getterSetterAnnotation) {
        this.getterSetterAnnotation = getterSetterAnnotation;
    }

    public boolean isBuilderAnnotation() {
        return builderAnnotation;
    }

    public void setBuilderAnnotation(boolean builderAnnotation) {
        this.builderAnnotation = builderAnnotation;
    }

    public boolean isNoArgsConstructorAnnotation() {
        return noArgsConstructorAnnotation;
    }

    public void setNoArgsConstructorAnnotation(boolean noArgsConstructorAnnotation) {
        this.noArgsConstructorAnnotation = noArgsConstructorAnnotation;
    }

    public boolean isAllArgsConstructorAnnotation() {
        return allArgsConstructorAnnotation;
    }

    public void setAllArgsConstructorAnnotation(boolean allArgsConstructorAnnotation) {
        this.allArgsConstructorAnnotation = allArgsConstructorAnnotation;
    }

}
