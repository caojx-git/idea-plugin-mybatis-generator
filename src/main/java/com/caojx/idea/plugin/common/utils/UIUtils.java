package com.caojx.idea.plugin.common.utils;

import com.intellij.ide.util.PackageChooserDialog;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiPackage;
import com.intellij.util.PathUtil;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.util.Optional;

/**
 * UI工具类
 *
 * @author caojx
 * @since 2023/7/2 12:54
 */
public class UIUtils {

    /**
     * 选择文件并设置路径
     *
     * @param project 项目
     * @param pathTf  选择并设置JTextField路径
     */
    public static void chooseFileAndSetPath(Project project, JTextField pathTf) {
        VirtualFile path = project.getBaseDir();
        if (StringUtils.isNotBlank(pathTf.getText()) && FileUtil.exists(pathTf.getText())) {
            VirtualFile virtualFile = LocalFileSystem.getInstance().findFileByPath(pathTf.getText());
            if (virtualFile != null) {
                path = virtualFile;
            }
        }

        VirtualFile virtualFile = FileChooser.chooseFile(FileChooserDescriptorFactory.createSingleFolderDescriptor(), project, path);
        if (virtualFile != null) {
            pathTf.setText(virtualFile.getPath());
        }
    }

    /**
     * 选择包并设置包路径
     *
     * @param project   项目
     * @param packageTf 包名输入框
     */
    public static void choosePackage(Project project, JTextField packageTf) {
        Module selectedModule = chooseModuleDialog(project);
        if (selectedModule == null) {
            return;
        }
        String packageName = StringUtils.isNotBlank(packageTf.getText()) ? packageTf.getText().trim() : "";
        PsiPackage selectedPackage = choosePackageDialog(selectedModule, packageName);
        if (selectedPackage != null) {
            packageTf.setText(selectedPackage.getQualifiedName());
        }
    }

    /**
     * 选择包并设置包路径
     *
     * @param module    模块
     * @param packageTf 包名输入框
     */
    public static void choosePackage(Module module, JTextField packageTf) {
        String packageName = StringUtils.isNotBlank(packageTf.getText()) ? packageTf.getText().trim() : "";
        PsiPackage selectedPackage = choosePackageDialog(module, packageName);
        if (selectedPackage != null) {
            packageTf.setText(selectedPackage.getQualifiedName());
        }
    }

    /**
     * 选择包并设置包路径
     *
     * @param project       项目
     * @param packageTf     包名输入框
     * @param packagePathTf 包路径输入框
     */
    public static void choosePackageAndSetPackagePath(Project project, JTextField packageTf, JTextField packagePathTf) {
        Module selectedModule = chooseModuleDialog(project);
        if (selectedModule == null) {
            return;
        }
        String packageName = StringUtils.isNotBlank(packageTf.getText()) ? packageTf.getText().trim() : "";
        PsiPackage selectedPackage = choosePackageDialog(selectedModule, packageName);
        if (selectedPackage != null) {
            packageTf.setText(selectedPackage.getQualifiedName());
            String packagePath = Optional.of(buildPackagePath(selectedModule, selectedPackage)).orElse("");
            packagePathTf.setText(packagePath);
        }
    }

    /**
     * 构建包路径
     *
     * @param module     模块
     * @param psiPackage 保对象
     */
    public static String buildPackagePath(Module module, PsiPackage psiPackage) {
        if (module == null || psiPackage == null) {
            return "";
        }

        String modulePath = StringUtil.trimEnd(PathUtil.getParentPath(module.getModuleFilePath()), ".idea");
        String packagePath = modulePath + "/src/main/java/" + psiPackage.getQualifiedName().replace(".", "/");
        VirtualFile virtualFile = LocalFileSystem.getInstance().findFileByPath(packagePath);
        if (virtualFile != null) {
            return virtualFile.getPath();
        }
        return "";
    }


    /**
     * 选择项目模块
     *
     * @param project 项目
     */
    public static Module chooseModuleDialog(Project project) {
        Module[] allModules = ModuleManager.getInstance(project).getModules();
        if (allModules.length == 0) {
            return null;
        }

        String[] allModuleNames = new String[allModules.length];
        for (int i = 0; i < allModules.length; i++) {
            allModuleNames[i] = allModules[i].getName();
        }
        String selectedModuleName = Messages.showEditableChooseDialog("Select a module", "", null, allModuleNames, allModuleNames[0], null);
        if (selectedModuleName == null) {
            return null;
        }
        for (Module module : allModules) {
            if (module.getName().equals(selectedModuleName)) {
                return module;
            }
        }
        return null;
    }

    /**
     * 选择项目模块
     *
     * @param project    项目
     * @param moduleName 模块名
     */
    public static Module chooseModuleDialog(Project project, String moduleName) {
        Module[] allModules = ModuleManager.getInstance(project).getModules();
        for (Module module : allModules) {
            if (module.getName().equals(moduleName)) {
                return module;
            }
        }
        return null;
    }

    /**
     * 选择模块，并设置路径
     *
     * @param project    项目
     * @param jTextField 文本框
     */
    public static void chooseModuleAndSetPath(Project project, JTextField jTextField) {
        Module module = chooseModuleDialog(project);
        if (module != null) {
            String modulePath = StringUtil.trimEnd(PathUtil.getParentPath(module.getModuleFilePath()), ".idea");
            VirtualFile virtualFile = LocalFileSystem.getInstance().findFileByPath(modulePath);
            if (virtualFile != null) {
                jTextField.setText(virtualFile.getPath());
            }
        }
    }

    /**
     * 选择模块，并设置路径
     *
     * @param module     模块
     * @param jTextField 文本框
     */
    public static void chooseModuleAndSetPath(Module module, JTextField jTextField) {
        if (module != null) {
            String modulePath = StringUtil.trimEnd(PathUtil.getParentPath(module.getModuleFilePath()), ".idea");
            VirtualFile virtualFile = LocalFileSystem.getInstance().findFileByPath(modulePath);
            if (virtualFile != null) {
                jTextField.setText(virtualFile.getPath());
            }
        }
    }

    /**
     * 返回指定模块、包的包对象
     *
     * @param module      模块
     * @param packageName 包名
     */
    public static PsiPackage choosePackageDialog(Module module, String packageName) {
        PackageChooserDialog packageChooserDialog = new PackageChooserDialog("Select a Package", module);
        if (StringUtils.isNotBlank(packageName)) {
            packageChooserDialog.selectPackage(packageName);
        }
        packageChooserDialog.show();
        return packageChooserDialog.getSelectedPackage();
    }

    /**
     * 选择父类名称
     *
     * @param superClassNames 可供选择的父类列表
     */
    public static String chooseSuperClass(String[] superClassNames) {
        if (superClassNames == null || superClassNames.length == 0) {
            return null;
        }
        return Messages.showEditableChooseDialog("Select a superClass", "", null, superClassNames, superClassNames[0], null);
    }

    /**
     * 选择并设置父类名称
     *
     * @param superClassNames  父类
     * @param superClassNameTf 父类JTextField
     */
    public static void chooseAndSetSuperClass(String[] superClassNames, JTextField superClassNameTf) {
        String selectSuperClassName = chooseSuperClass(superClassNames);
        if (StringUtils.isBlank(selectSuperClassName)) {
            return;
        }
        if (superClassNameTf != null) {
            superClassNameTf.setText(StringUtils.equals(selectSuperClassName, "无") ? "" : selectSuperClassName);
        }
    }
}