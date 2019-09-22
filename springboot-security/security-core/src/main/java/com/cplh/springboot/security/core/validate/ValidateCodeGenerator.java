package com.cplh.springboot.security.core.validate;

import javax.servlet.http.HttpServletRequest;

public interface ValidateCodeGenerator {

    ValidateCode generate(HttpServletRequest request);

}
