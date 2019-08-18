package com.cplh.dto;

import com.cplh.validate.MyConstraint;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.Date;

public class User {
    // 使用JsonView的来控制Controller显示不同视图

    /**
     * 简单视图
     */
    public interface UserSimpleView { }

    /**
     * 详细视图
     */
    public interface UserDetailView extends UserSimpleView { }

    @JsonView(UserSimpleView.class)
    private String id;

    @MyConstraint(message = "这是一个测试, username 验证失败")
    @JsonView(UserSimpleView.class)
    private String username;

    @NotBlank(message = "密码不能为空")
    @JsonView(UserDetailView.class)
    private String password;

    @JsonView(UserSimpleView.class)
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @Past(message = "必须是过去的时间")   // 必须是过去的时间
    private Date birthday;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
