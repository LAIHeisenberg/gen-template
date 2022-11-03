package com.laiyz.model;

import com.laiyz.model.vue.Option;
import com.laiyz.model.vue.VueTypeEnum;
import lombok.Data;

import java.util.List;

@Data
public class GenField {

    private String name;
    private String desc;
    private VueTypeEnum vueType;
    private String dataType;
    private List<Option> options;

    public GenField(String name, String dataType, String desc){
        this(name,dataType,desc,VueTypeEnum.plain);
    }

    public GenField(String name, String dataType, String desc, VueTypeEnum vueType){
        this.name = name;
        this.dataType = dataType;
        this.desc = desc;
        this.vueType = vueType;
    }

    public GenField(String name, String dataType, String desc, List<Option> options){
        this(name,dataType,desc,VueTypeEnum.select);
        this.options = options;
    }



}
