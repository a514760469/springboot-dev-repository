package com.cplh.springboot.security.core.validate;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

public class ImageCode {

    private BufferedImage bufferedImage;

    private String code;// 存到session

    private LocalDateTime expireTime;// 过期时间

    /**
     * 是否过期
     * @return
     */
    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }

    public ImageCode(BufferedImage bufferedImage, String code, int expireIn) {
        this.bufferedImage = bufferedImage;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public ImageCode(BufferedImage bufferedImage, String code, LocalDateTime expireTime) {
        this.bufferedImage = bufferedImage;
        this.code = code;
        this.expireTime = expireTime;
    }

    public ImageCode() {

    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

}
