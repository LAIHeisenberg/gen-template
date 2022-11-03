package com.laiyz.handler;

import com.laiyz.anno.*;
import com.laiyz.model.GenField;
import com.laiyz.model.vue.Option;
import com.laiyz.model.vue.VueTypeEnum;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClassAnnoHandler {

    public <T> Reslover handler(Class<T> entityClazz){

        List<GenField> queryFields = new ArrayList<>();
        List<GenField> addFormFields = new ArrayList<>();
        List<GenField> tableColumnFields = new ArrayList<>();
        List<GenField> dtoFields = new ArrayList<>();
        Field[] declaredFields = entityClazz.getDeclaredFields();
        Reslover reslover = new Reslover();

        GenTemplateEntity genTemplateEntity = entityClazz.getAnnotation(GenTemplateEntity.class);
        reslover.setEntityName(genTemplateEntity.name());
        reslover.setEntityShortName(genTemplateEntity.shortName());

        GenTemplateQuery genTemplateQuery = entityClazz.getAnnotation(GenTemplateQuery.class);
        if (Objects.nonNull(genTemplateQuery)){
            String queryBeanName = genTemplateQuery.name();
            if (queryBeanName.equals("")){
                queryBeanName = entityClazz.getSimpleName().replace("Entity","QueryParam");
            }
            String queryBeanPackageName = genTemplateQuery.packageName();
            reslover.setQueryBeanName(queryBeanName);
            reslover.setQueryBeanPackageName(queryBeanPackageName);
            for (Field field : declaredFields){
                String name = field.getName();
                GenTemplateField annotation = field.getAnnotation(GenTemplateField.class);
                String desc = annotation.desc();
                String dataType = annotation.dataType().equals("") ? field.getType().getSimpleName() : annotation.dataType();
                name = annotation.name().equals("") ? name : annotation.name();
                GenTemplateQueryParam queryParam = field.getAnnotation(GenTemplateQueryParam.class);
                if (Objects.nonNull(queryParam)){
                    VueTypeEnum type = queryParam.vueType();
                    if (VueTypeEnum.select == type){
                        String[] optionStr = queryParam.options();
                        List<Option> optionList = new ArrayList<>();
                        for (String s : optionStr){
                            String[] split = s.split(",");
                            Option option = new Option(split[0], split[1]);
                            optionList.add(option);
                        }
                        queryFields.add(new GenField(name, dataType, desc, optionList));
                    }else {
                        queryFields.add(new GenField(name, dataType, desc, type));
                    }
                }
                reslover.setQueryFields(queryFields);
            }
        }

        GenTemplateAdd genTemplateAdd = entityClazz.getAnnotation(GenTemplateAdd.class);
        if (Objects.nonNull(genTemplateAdd)){
            String addBeanName = genTemplateAdd.name();
            if (addBeanName.equals("")){
                addBeanName = entityClazz.getSimpleName().replace("Entity","CreateParam");
            }
            String addBeanPackageName = genTemplateAdd.packageName();
            reslover.setAddBeanName(addBeanName);
            reslover.setAddBeanPackageName(addBeanPackageName);
            for (Field field : declaredFields) {
                String name = field.getName();
                GenTemplateField annotation = field.getAnnotation(GenTemplateField.class);
                String desc = annotation.desc();
                String dataType = annotation.dataType().equals("") ? field.getType().getSimpleName() : annotation.dataType();
                name = annotation.name().equals("") ? name : annotation.name();
                GenTemplateAddParam addParam = field.getAnnotation(GenTemplateAddParam.class);
                if (Objects.nonNull(addParam)){
                    VueTypeEnum type = addParam.vueType();
                    if (VueTypeEnum.select == type){
                        String[] optionStr = addParam.options();
                        List<Option> optionList = new ArrayList<>();
                        for (String s : optionStr){
                            String[] split = s.split(",");
                            Option option = new Option(split[0], split[1]);
                            optionList.add(option);
                        }
                        addFormFields.add(new GenField(name, dataType, desc, optionList));
                    }else {
                        addFormFields.add(new GenField(name, dataType, desc, type));
                    }
                }
            }
            reslover.setAddFormFields(addFormFields);
        }

        GenTemplateView genTemplateView = entityClazz.getAnnotation(GenTemplateView.class);
        if (Objects.nonNull(genTemplateView)){
            String viewBeanName = genTemplateView.name();
            if (viewBeanName.equals("")){
                viewBeanName = entityClazz.getSimpleName().replace("Entity", "View");
            }
            String viewBeanPackageName = genTemplateView.packageName();
            reslover.setViewBeanName(viewBeanName);
            reslover.setViewBeanPackageName(viewBeanPackageName);
            for (Field field : declaredFields) {
                String name = field.getName();
                GenTemplateField annotation = field.getAnnotation(GenTemplateField.class);
                String desc = annotation.desc();
                String dataType = annotation.dataType().equals("") ? field.getType().getSimpleName(): annotation.dataType();
                name = annotation.name().equals("") ? name : annotation.name();
                GenTemplateTableColumn tableColumn = field.getAnnotation(GenTemplateTableColumn.class);
                if (Objects.nonNull(tableColumn)){
                    VueTypeEnum type = tableColumn.vueType();
                    if (VueTypeEnum.select == type){
                        String[] optionStr = tableColumn.options();
                        List<Option> optionList = new ArrayList<>();
                        for (String s : optionStr){
                            String[] split = s.split(",");
                            Option option = new Option(split[0], split[1]);
                            optionList.add(option);
                        }
                        tableColumnFields.add(new GenField(name, dataType, desc, optionList));
                    }else {
                        tableColumnFields.add(new GenField(name, dataType, desc, type));
                    }
                }
            }
            reslover.setTableColumnFields(tableColumnFields);
        }

        GenTemplateDto genTemplateDto = entityClazz.getAnnotation(GenTemplateDto.class);
        if (Objects.nonNull(genTemplateAdd)){
            String dtoName = genTemplateDto.name();
            if (dtoName.equals("")){
                dtoName = entityClazz.getSimpleName().replace("Entity", "Dto");
            }
            reslover.setDtoBeanName(dtoName);
            reslover.setDtoBeanPackageName(genTemplateDto.packageName());
            for (Field field : declaredFields) {
                String name = field.getName();
                GenTemplateField annotation = field.getAnnotation(GenTemplateField.class);
                String desc = annotation.desc();
                String dataType = annotation.dataType().equals("") ? field.getType().getSimpleName(): annotation.dataType();
                name = annotation.name().equals("") ? name : annotation.name();
                GenTemplateDtoField dtoField = field.getAnnotation(GenTemplateDtoField.class);
                if (Objects.nonNull(dtoField)){
                    dtoFields.add(new GenField(name, dataType, desc));
                }
            }
            reslover.setDtoFields(dtoFields);
        }

        return reslover;
    }

    @Data
    public static class Reslover{

        private String entityName;
        private String entityShortName;
        private String queryBeanName;
        private String queryBeanPackageName;
        private String addBeanName;
        private String addBeanPackageName;
        private String viewBeanName;
        private String viewBeanPackageName;
        private String dtoBeanName;
        private String dtoBeanPackageName;

        private List<GenField> queryFields;
        private List<GenField> addFormFields;
        private List<GenField> tableColumnFields;
        private List<GenField> dtoFields;

    }




}
