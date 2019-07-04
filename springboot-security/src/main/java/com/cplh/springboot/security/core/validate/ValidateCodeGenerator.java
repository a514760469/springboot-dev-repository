package com.cplh.springboot.security.core.validate;

import javax.servlet.http.HttpServletRequest;

public interface ValidateCodeGenerator {

    ImageCode generate(HttpServletRequest request);

}
