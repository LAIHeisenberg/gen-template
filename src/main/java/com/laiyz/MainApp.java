package com.laiyz;

import com.laiyz.config.FreeMarkerConfiguration;
import com.laiyz.entity.ApiMaskingEntity;
import com.laiyz.handler.ClassAnnoHandler;
import com.laiyz.model.java.JavaBeanModel;
import com.laiyz.model.vue.VueModel;
import freemarker.template.Template;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainApp {

    private static final String outputPath = "/home/laiyz/桌面/gen-template-output/";
    private static String componentName = "ApiMasking";
    private static String crudObjName = "apiMasking";
    private static String importJsPath = "/api/masking/api/index";
    private static String importJsDir = "api";
    private static String listUrl = "api/admin/api/masking/list";
    private static String addFormTitle = "脱敏API";


    public static void main(String... args) throws Exception {
        FreeMarkerConfiguration freeMarkerConfiguration = new FreeMarkerConfiguration();
        ClassAnnoHandler classAnnoHandler = new ClassAnnoHandler();
        ClassAnnoHandler.Reslover reslover = classAnnoHandler.handler(ApiMaskingEntity.class);
        //生成java文件
        generateJavaFile(freeMarkerConfiguration, reslover);
        // 生成vue文件
        generateVueFile(freeMarkerConfiguration, reslover);
    }

    public static void generateVueFile(FreeMarkerConfiguration freeMarkerConfiguration, ClassAnnoHandler.Reslover reslover) throws Exception {

        Template template = freeMarkerConfiguration.getTemplate("vue/index.ftl");
        FileWriter fileWriter = getWriter("vue/index", "vue");
        VueModel vueModel = new VueModel()
                .init(componentName, crudObjName, importJsPath, listUrl, addFormTitle)
                .createQueryParams().addAllQueryParams(reslover.getQueryFields())
                .createAddForm().addAllFormItem(reslover.getAddFormFields())
                .createTableColumns().addAllTableColumns(reslover.getTableColumnFields())
                .build();
        template.process(vueModel,fileWriter);

        template = freeMarkerConfiguration.getTemplate("vue/apiJs.ftl");
        File file = new File(outputPath + "vue/" + importJsDir);
        file.mkdir();
        fileWriter = getWriter("vue/"+importJsDir+"/index", "js");
        template.process(null, fileWriter);

    }

    public static void generateJavaFile(FreeMarkerConfiguration freeMarkerConfiguration, ClassAnnoHandler.Reslover reslover) throws Exception {

        FileWriter fileWriter;
        if (reslover.getQueryFields() != null){
            JavaBeanModel javaBeanModel = new JavaBeanModel();
            javaBeanModel.setBeanName(reslover.getQueryBeanName());
            javaBeanModel.setPackageName(reslover.getQueryBeanPackageName());
            javaBeanModel.setFieldList(reslover.getQueryFields());

            Template template = freeMarkerConfiguration.getTemplate("java/bean.ftl");
            fileWriter = getWriter(reslover.getQueryBeanName(),"java");
            template.process(javaBeanModel, fileWriter);
        }

        if (reslover.getAddFormFields() != null){
            JavaBeanModel javaBeanModel = new JavaBeanModel();
            javaBeanModel.setBeanName(reslover.getAddBeanName());
            javaBeanModel.setPackageName(reslover.getAddBeanPackageName());
            javaBeanModel.setFieldList(reslover.getAddFormFields());

            Template template = freeMarkerConfiguration.getTemplate("java/bean.ftl");
            fileWriter = getWriter(reslover.getAddBeanName(),"java");
            template.process(javaBeanModel, fileWriter);
        }

        if (reslover.getTableColumnFields() != null){
            JavaBeanModel javaBeanModel = new JavaBeanModel();
            javaBeanModel.setBeanName(reslover.getViewBeanName());
            javaBeanModel.setPackageName(reslover.getViewBeanPackageName());
            javaBeanModel.setFieldList(reslover.getTableColumnFields());

            Template template = freeMarkerConfiguration.getTemplate("java/bean.ftl");
            fileWriter = getWriter(reslover.getViewBeanName(),"java");
            template.process(javaBeanModel, fileWriter);
        }

        if (reslover.getDtoFields() != null){
            JavaBeanModel javaBeanModel = new JavaBeanModel();
            javaBeanModel.setBeanName(reslover.getDtoBeanName());
            javaBeanModel.setPackageName(reslover.getDtoBeanPackageName());
            javaBeanModel.setFieldList(reslover.getDtoFields());

            Template template = freeMarkerConfiguration.getTemplate("java/bean.ftl");
            fileWriter = getWriter(reslover.getDtoBeanName(),"java");
            template.process(javaBeanModel, fileWriter);
        }

        Template template = freeMarkerConfiguration.getTemplate("java/service.ftl");
        fileWriter = getWriter(reslover.getEntityShortName()+"Service","java");
        template.process(reslover, fileWriter);

        template = freeMarkerConfiguration.getTemplate("java/serviceImpl.ftl");
        fileWriter = getWriter(reslover.getEntityShortName()+"ServiceImpl","java");
        template.process(reslover, fileWriter);

        template = freeMarkerConfiguration.getTemplate("java/facade.ftl");
        fileWriter = getWriter(reslover.getEntityShortName()+"Facade","java");
        template.process(reslover, fileWriter);

        template = freeMarkerConfiguration.getTemplate("java/controller.ftl");
        fileWriter = getWriter(reslover.getEntityShortName()+"Controller","java");
        template.process(reslover, fileWriter);
    }


    private static FileWriter getWriter(String fileName, String suffix) throws IOException {
        return new FileWriter(new File(outputPath + fileName+"."+suffix));
    }

}

