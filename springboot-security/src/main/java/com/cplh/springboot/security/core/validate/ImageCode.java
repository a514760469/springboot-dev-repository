package com.cplh.springboot.security.core.validate;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * 图形验证码
 */
public class ImageCode extends ValidateCode {

    private BufferedImage bufferedImage;

    public ImageCode(BufferedImage bufferedImage, String code, int expireIn) {
        super(code, expireIn);
        this.bufferedImage = bufferedImage;

    }

    public ImageCode(BufferedImage bufferedImage, String code, LocalDateTime expireTime) {
        super(code, expireTime);
        this.bufferedImage = bufferedImage;
    }

    public ImageCode() {

    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }


}
