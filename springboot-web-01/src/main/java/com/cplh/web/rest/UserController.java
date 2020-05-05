package com.cplh.web.rest;

import com.cplh.dto.User;
import com.cplh.dto.UserQueryCondition;
import com.cplh.springboot.security.core.properties.SecurityProperties;
import com.fasterxml.jackson.annotation.JsonView;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private SecurityProperties securityProperties;
    
    @GetMapping("/me")
    public Object getCurrentUser(@AuthenticationPrincipal UserDetails user) {
        return user;
    }

    @GetMapping("/auth")
    public Object getCurrentUserAuth(Authentication user, HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        String token = StringUtils.substringAfter(header, "Bearer ");
        Claims claims = Jwts.parser()
                .setSigningKey(securityProperties.getOauth2().getJwtSigningKey().getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token).getBody();
//                .parseClaimsJwt(token).getBody();
        String company = (String) claims.get("company");
        System.out.println("----->" + company);
        return user;
    }


    /**
     * rest删除
     * @param id
     */
    @DeleteMapping("/{id:\\d+}")
    public void delete(@PathVariable("id") String id) {
        System.out.println("delete: " + id);
    }

    /**
     * 如果没有 bindingResult 这个参数，@Valid校验失败后会直接拦截回去 报400
     * @param user
     * @param bindingResult Valid验证不满足会记录到bindingResult中
     * @return
     */
    @SuppressWarnings("JavadocReference")
    @PostMapping
    public User create(@Valid @RequestBody User user/*, BindingResult bindingResult*/) {

        /*if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(err -> System.out.println(err.getDefaultMessage()));
        }*/

        System.out.println(ReflectionToStringBuilder.toString(user, ToStringStyle.MULTI_LINE_STYLE));
        user.setId("1");
        return user;
    }

    /**
     * rest 新增
     * @param user
     * @param bindingResult @Valid验证不满足会记录到bindingResult中
     * @return
     */
    @PutMapping("/{id:\\d+}")
    public User update(@Valid @RequestBody User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(err -> {
                FieldError fieldError = (FieldError) err;
                String message = fieldError.getField() + " " + err.getDefaultMessage();
                System.err.println(message);
            });
        }
        System.out.println(ReflectionToStringBuilder.toString(user, ToStringStyle.MULTI_LINE_STYLE));
        user.setId("1");
        return user;
    }


    @GetMapping
    @JsonView(User.UserSimpleView.class)
    public List<User> query(UserQueryCondition condition,
                            @PageableDefault(page = 1, size = 12, sort = "username,asc")
                                    Pageable pageable) {

        System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));

        System.out.println(pageable.getPageSize());
        System.out.println(pageable.getPageNumber());
        System.out.println(pageable.getSort());

        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }

    /**
     *
     * @param id 带有正则表达式验证
     * @return
     */
    @GetMapping("/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    public User getInfo(@PathVariable String id) {

//        throw new RuntimeException(id);

        System.out.println("进入getInfo服务");
        User user = new User();
        user.setUsername("tom");
        return user;
    }



}
