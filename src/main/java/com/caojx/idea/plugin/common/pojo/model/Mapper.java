package com.caojx.idea.plugin.common.pojo.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Mapper数据模型
 */
public class Mapper extends Base {

    /**
     * 数据模型entity
     */
    private final Entity entity;

    /**
     * 构造器
     *
     * @param comment       注释
     * @param fullClazzName 全类名
     * @param entity         数据模型
     */
    public Mapper(String comment, String fullClazzName, Entity entity) {
        super(comment, fullClazzName);
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }

    @Override
    public Set<String> getImports() {
        Set<String> imports = new HashSet<>();
        imports.add(entity.getPackage() + "." + entity.getSimpleName());
        List<Field> fields = entity.getFields();
        for (Field field : fields) {
            if (field.isId() && field.isImport()) {
                imports.add(field.getFullClazzName());
                break;
            }
        }
        return imports;
    }

}