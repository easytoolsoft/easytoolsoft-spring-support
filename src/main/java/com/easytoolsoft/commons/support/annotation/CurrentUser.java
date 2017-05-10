package com.easytoolsoft.commons.support.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.okcoin.commons.support.consts.UserAuthConsts;

/**
 * 绑定当前登录的用户
 *
 * @author zhiwei.deng
 * @date 2017-03-25
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUser {

    /**
     * 当前用户在request中的名字
     *
     * @return
     */
    String value() default UserAuthConsts.CURRENT_USER;

}
