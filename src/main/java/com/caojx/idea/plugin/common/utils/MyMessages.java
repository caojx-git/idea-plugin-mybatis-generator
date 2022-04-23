package com.caojx.idea.plugin.common.utils;

import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.NlsContexts;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author caojx
 * @date 2022/4/23 10:49 AM
 */
public class MyMessages {

    private static final NotificationGroup NOTIFICATION_GROUP = new NotificationGroup("Custom Notification Group", NotificationDisplayType.BALLOON, true);

    /**
     * 错误通知
     *
     * @param project 项目
     * @param content 内容
     */
    public static void showErrorNotify(Project project, String content) {
        NOTIFICATION_GROUP.createNotification(content, NotificationType.ERROR).notify(project);
    }

    /**
     * 告警提示
     *
     * @param project 项目
     * @param content 内容
     * @param title   标题
     */
    public static void showWarningDialog(Project project, String content, String title) {
        Messages.showWarningDialog(project, content, title);
    }

    /**
     * 普通提示
     *
     * @param project 项目
     * @param content 内容
     * @param title   标题
     */
    public static void showInfoMessage(Project project, String content, String title) {
        Messages.showInfoMessage(project, content, title);
    }
}
