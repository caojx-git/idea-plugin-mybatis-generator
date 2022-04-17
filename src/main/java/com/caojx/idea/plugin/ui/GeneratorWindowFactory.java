package com.caojx.idea.plugin.ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

/**
 * 创建ToolWindow
 *
 * @author caojx
 * @date 2022/4/17 4:02 PM
 */
public class GeneratorWindowFactory implements ToolWindowFactory {
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        // 创建GeneratorSettingUI
        GeneratorSettingUI generatorSettingUI = new GeneratorSettingUI(project);
        // 获取内功工厂实例
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        // 创建toolWindow显示内容
        Content content = contentFactory.createContent(generatorSettingUI.getContentPanel(), "", false);
        //给toolWindow设置内容
        toolWindow.getContentManager().addContent(content);
    }
}
