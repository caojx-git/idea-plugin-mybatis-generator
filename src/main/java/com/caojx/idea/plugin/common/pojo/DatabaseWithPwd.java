package com.caojx.idea.plugin.common.pojo;

/**
 * 数据库包含密码
 *
 * @author caojx
 * @date 2022/5/1 9:56 AM
 */
public class DatabaseWithPwd extends DatabaseWithOutPwd {

    /**
     * 密码
     */
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
