package com.laiyz.model.java;

import com.laiyz.model.GenField;
import lombok.Data;

import java.util.List;

@Data
public class JavaBeanModel {

    private String packageName;
    private String beanName;
    private List<GenField> fieldList;

}
