package com.cplh.springboot.security.core.social.qq.api;

import lombok.Data;

@Data
public class QQUserInfo {

    private int ret;// 	返回码

    private String msg;//	如果ret<0，会有相应的错误信息提示，返回数据全部用UTF-8编码。

    private String nickname;//	用户在QQ空间的昵称。

    private String figureurl;//	大小为30×30像素的QQ空间头像URL。

    private String figureurl_1;//	大小为50×50像素的QQ空间头像URL。

    private String figureurl_2;//	大小为100×100像素的QQ空间头像URL。

    private String figureurl_qq_1;//	大小为40×40像素的QQ头像URL。

    private String figureurl_qq_2;//	大小为100×100像素的QQ头像URL。需要注意，不是所有的用户都拥有QQ的100x100的头像，但40x40像素则是一定会有。

    private String figureurl_qq;

    private String figureurl_type;

    private String gender;// 性别

    private Integer gender_type;

    private String is_lost;

    private String province;// 省

    private String city;// 城市

    private String year;// 年

    private String constellation;// 星座

    private String is_yellow_vip;

    private String vip;

    private String yellow_vip_level;

    private String level;

    private String is_yellow_year_vip;

    private String openId;

}
