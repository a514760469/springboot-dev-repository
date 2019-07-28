package com.cplh.springboot.security.core.validate.image;

import javax.imageio.ImageIO;

import com.cplh.springboot.security.core.validate.impl.AbstractValidateCodeProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;


/**
 * 图片验证码处理器
 * 
 * @author zhailiang
 *
 */
@Component("imageCodeProcessor")
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {

	/**
	 * 发送图形验证码，将其写到响应中
	 */
	@Override
	protected void send(ServletWebRequest request, ImageCode imageCode) throws Exception {
		ImageIO.write(imageCode.getBufferedImage(), "JPEG", request.getResponse().getOutputStream());
	}

}
