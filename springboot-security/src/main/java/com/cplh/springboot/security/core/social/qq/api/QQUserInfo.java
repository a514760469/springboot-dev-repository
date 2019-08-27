package com.cplh.springboot.security.core.social.qq.api;

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

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFigureurl() {
        return figureurl;
    }

    public void setFigureurl(String figureurl) {
        this.figureurl = figureurl;
    }

    public String getFigureurl_1() {
        return figureurl_1;
    }

    public void setFigureurl_1(String figureurl_1) {
        this.figureurl_1 = figureurl_1;
    }

    public String getFigureurl_2() {
        return figureurl_2;
    }

    public void setFigureurl_2(String figureurl_2) {
        this.figureurl_2 = figureurl_2;
    }

    public String getFigureurl_qq_1() {
        return figureurl_qq_1;
    }

    public void setFigureurl_qq_1(String figureurl_qq_1) {
        this.figureurl_qq_1 = figureurl_qq_1;
    }

    public String getFigureurl_qq_2() {
        return figureurl_qq_2;
    }

    public void setFigureurl_qq_2(String figureurl_qq_2) {
        this.figureurl_qq_2 = figureurl_qq_2;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getFigureurl_qq() {
        return figureurl_qq;
    }

    public void setFigureurl_qq(String figureurl_qq) {
        this.figureurl_qq = figureurl_qq;
    }

    public String getFigureurl_type() {
        return figureurl_type;
    }

    public void setFigureurl_type(String figureurl_type) {
        this.figureurl_type = figureurl_type;
    }

    public String getIs_lost() {
        return is_lost;
    }

    public void setIs_lost(String is_lost) {
        this.is_lost = is_lost;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public String getIs_yellow_vip() {
        return is_yellow_vip;
    }

    public void setIs_yellow_vip(String is_yellow_vip) {
        this.is_yellow_vip = is_yellow_vip;
    }

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

    public String getYellow_vip_level() {
        return yellow_vip_level;
    }

    public void setYellow_vip_level(String yellow_vip_level) {
        this.yellow_vip_level = yellow_vip_level;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getIs_yellow_year_vip() {
        return is_yellow_year_vip;
    }

    public void setIs_yellow_year_vip(String is_yellow_year_vip) {
        this.is_yellow_year_vip = is_yellow_year_vip;
    }

    @Override
    public String toString() {
        return "QQUserInfo{" +
                "ret=" + ret +
                ", msg='" + msg + '\'' +
                ", nickname='" + nickname + '\'' +
                ", figureurl='" + figureurl + '\'' +
                ", figureurl_1='" + figureurl_1 + '\'' +
                ", figureurl_2='" + figureurl_2 + '\'' +
                ", figureurl_qq_1='" + figureurl_qq_1 + '\'' +
                ", figureurl_qq_2='" + figureurl_qq_2 + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
