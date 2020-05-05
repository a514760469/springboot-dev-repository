package com.gds.springboot.rabbitmq.rabbit.consumer;

/**
 * @author zhanglifeng
 * @date 2019/11/12/0012
 */
public class Order {

    private Integer id;
    private Integer userId;
    private double amout;
    private String time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public double getAmout() {
        return amout;
    }

    public void setAmout(double amout) {
        this.amout = amout;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", amout=" + amout +
                ", time='" + time + '\'' +
                '}';
    }
}