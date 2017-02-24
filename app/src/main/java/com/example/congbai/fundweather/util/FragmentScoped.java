package com.example.congbai.fundweather.util;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by fundmarkhua on 2017/2/24
 * Email:57525101@qq.com
 * we create a custom scope to be used by all fragment components.
 * Additionally, a component with a specific scope cannot have a sub component with the same scope.
 */
@Documented
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface FragmentScoped {
}
