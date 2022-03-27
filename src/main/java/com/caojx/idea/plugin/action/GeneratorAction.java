package com.caojx.idea.plugin.action;

import com.caojx.idea.plugin.ui.GeneratorSettingUI;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.openapi.project.Project;

public class GeneratorAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        // 项目
        Project project = e.getRequiredData(CommonDataKeys.PROJECT);

        // 显示配置窗体
        ShowSettingsUtil.getInstance().editConfigurable(project, new GeneratorSettingUI(project));
    }
}
