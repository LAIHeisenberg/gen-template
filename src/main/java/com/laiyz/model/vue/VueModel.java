package com.laiyz.model.vue;

import com.laiyz.model.GenField;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Getter
public class VueModel {

    private String componentName;
    private String crudObjName;
    private String importJsPath;
    private String listUrl;
    private String addFormTitle;
    private List<GenField> queryParams;
    private List<GenField> addForm;
    private List<GenField> tableColumns;

    public VueModel init(String componentName, String crudObjName, String importJsPath, String listUrl, String addFormTitle){
        this.componentName = componentName;
        this.crudObjName = crudObjName;
        this.importJsPath = importJsPath;
        this.listUrl = listUrl;
        this.addFormTitle = addFormTitle;

        return this;
    }

    public VueModel build(){
        return this;
    }

    public VueModel addQueryParams(GenField ... field){
        if (queryParams == null){
            throw new IllegalArgumentException("please create queryParams before add");
        }
        queryParams.addAll(Arrays.asList(field));
        return this;
    }

    public VueModel addAllQueryParams(List<GenField> genFieldList){
        if (Objects.isNull(genFieldList)){
            throw new IllegalArgumentException("please create queryParams before add");
        }
        queryParams.addAll(genFieldList);
        return this;
    }

    public VueModel addFormItem(GenField ... field){
        if (addForm == null){
            throw new IllegalArgumentException("please create addForm before add");
        }
        addForm.addAll(Arrays.asList(field));
        return this;
    }

    public VueModel addAllFormItem(List<GenField> genFieldList){
        if (Objects.isNull(genFieldList)){
            throw new IllegalArgumentException("please create addForm before add");
        }
        addForm.addAll(genFieldList);
        return this;
    }

    public VueModel addTableColumns(GenField ... field){
        if (tableColumns == null){
            throw new IllegalArgumentException("please create tableColumns before add");
        }
        tableColumns.addAll(Arrays.asList(field));
        return this;
    }

    public VueModel addAllTableColumns(List<GenField> genFieldList){
        if (Objects.isNull(genFieldList)){
            throw new IllegalArgumentException("please create tableColumns before add");
        }
        tableColumns.addAll(genFieldList);
        return this;
    }

    public VueModel createQueryParams(){
        this.queryParams = new ArrayList<>();
        return this;
    }

    public VueModel createAddForm(){
        this.addForm = new ArrayList<>();
        return this;
    }

    public VueModel createTableColumns(){
        this.tableColumns = new ArrayList<>();
        return this;
    }

}
