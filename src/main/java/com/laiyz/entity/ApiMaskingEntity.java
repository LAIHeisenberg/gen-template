package com.laiyz.entity;

import com.laiyz.anno.*;
import com.laiyz.model.vue.VueTypeEnum;
import lombok.Data;

@Data
@GenTemplateQuery(name = "ApiMaskingQueryParam")
@GenTemplateAdd(name = "ApiMaskingCreate")
@GenTemplateView(name = "ApiMaskingView")
@GenTemplateDto(name = "ApiMaskingDto", packageName = "com.laiyz.entity")
@GenTemplateEntity(name = "ApiMaskingEntity", shortName = "ApiMasking")
public class ApiMaskingEntity {

    @GenTemplateField(desc = "id")
    @GenTemplateTableColumn(vueType = VueTypeEnum.selection)
    @GenTemplateDtoField
    private Integer id;

    @GenTemplateField(desc = "HOST")
    @GenTemplateQueryParam
    @GenTemplateTableColumn
    @GenTemplateAddParam
    @GenTemplateDtoField
    private String host;

    @GenTemplateField(desc = "路径")
    @GenTemplateQueryParam
    @GenTemplateTableColumn
    @GenTemplateAddParam
    @GenTemplateDtoField
    private String path;

    @GenTemplateField(desc = "请求URL")
    @GenTemplateTableColumn
    @GenTemplateAddParam
    @GenTemplateDtoField
    private String apiUrl;

    @GenTemplateField(desc = "返回类型")
    @GenTemplateTableColumn
    @GenTemplateAddParam(vueType = VueTypeEnum.select, options = {"json,1","xml,2"})
    @GenTemplateQueryParam(vueType = VueTypeEnum.select, options = {"json,1","xml,2"})
    @GenTemplateDtoField
    private String retType;

    @GenTemplateField(desc = "模板ID")
    @GenTemplateTableColumn
    @GenTemplateDtoField
    private Integer templateId;

}
