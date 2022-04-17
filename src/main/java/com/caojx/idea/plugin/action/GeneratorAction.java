package com.caojx.idea.plugin.action;

import com.caojx.idea.plugin.ui.GeneratorSettingUI;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.project.Project;

/**
 * 代码生成Action
 *
 * @author caojx
 * @date 2022/4/10 4:00 PM
 */
public class GeneratorAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        // 项目
        Project project = e.getRequiredData(CommonDataKeys.PROJECT);

        // 显示配置窗体
        GeneratorSettingUI generatorSettingUI = new GeneratorSettingUI(project);
        generatorSettingUI.show();
    }
}
