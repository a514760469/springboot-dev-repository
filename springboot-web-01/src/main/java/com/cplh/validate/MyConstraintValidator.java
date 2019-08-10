package com.cplh.validate;

import com.cplh.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 校验器
 * 这个类型不需要加 @Component
 */
public class MyConstraintValidator
        implements ConstraintValidator<MyConstraint, Object> {

    @Autowired
    HelloService helloService;
    /**
     *
     * @param constraintAnnotation
     */
    @Override
    public void initialize(MyConstraint constraintAnnotation) {
        System.out.println("my MyConstraintValidator init ");
    }

    /**
     * 验证
     * @param value
     * @param context
     * @return  false代表验证失败
     */
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        System.out.println(value);
        helloService.greeting("heiha");
        return false;
    }
}
