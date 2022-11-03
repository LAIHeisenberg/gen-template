package com.laiyz.config;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.IOException;

public class FreeMarkerConfiguration {

    private Configuration cfg;

    public FreeMarkerConfiguration(){
        cfg = new Configuration(Configuration.VERSION_2_3_31);
        try {
            cfg.setDirectoryForTemplateLoading(new File(FreeMarkerConfiguration.class.getClassLoader().getResource("templates").getFile()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }

    public Template getTemplate(String templateName){
        try {
            return cfg.getTemplate(templateName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
