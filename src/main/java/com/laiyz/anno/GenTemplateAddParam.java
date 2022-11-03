package com.laiyz.anno;

import com.laiyz.model.vue.VueTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GenTemplateAddParam {
    VueTypeEnum vueType() default VueTypeEnum.input;
    String[] options() default {};
}
