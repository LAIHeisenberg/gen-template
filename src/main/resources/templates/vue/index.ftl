<#escape x as x?html>
<!--  本文件由FreeMarker生成   -->
<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <div v-if="crud.props.searchToggle">
        <!-- 搜索 -->
        <#list queryParams>
          <#items as qParam >
              <#if qParam.vueType == 'input'>
                <el-input v-model="query.${qParam.name}" clearable size="small" placeholder="输入${qParam.desc}搜索" style="width: 200px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
              <#elseif qParam.vueType == 'select'>
                <el-select v-model="query.${qParam.name}" placeholder="请选择${qParam.desc}">
                  <#list qParam.options as option>
                    <el-option key="${option.value}" label="${option.label}" value="${option.value}"></el-option>
                  </#list>
                </el-select>
              </#if>
          </#items>
        </#list>
        <rrOperation />
      </div>
      <crudOperation :permission="permission" />
    </div>
    <!--表单组件-->
    <#list addForm>
    <el-dialog append-to-body :close-on-click-modal="false" :before-close="crud.cancelCU" :visible.sync="crud.status.cu > 0" :title="crud.status.title" width="500px">
      <el-form ref="form" inline :model="form" size="small" label-width="80px">
        <#items as formItem>
            <#if formItem.vueType == 'input'>
              <el-form-item label="${formItem.desc}" prop="${formItem.name}">
                <el-input v-model="form.${formItem.name}" style="width: 370px;" />
              </el-form-item>
            <#elseif formItem.vueType == 'select'>
              <el-form-item label="${formItem.desc}" prop="${formItem.name}">
                <el-select v-model="form.${formItem.name}" placeholder="${formItem.desc}">
                  <#list formItem.options as option>
                    <el-option key="${option.value}" label="${option.label}" value="${option.value}"></el-option>
                  </#list>
                </el-select>
              </el-form-item>
            </#if>
        </#items>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="text" @click="crud.cancelCU">取消</el-button>
        <el-button :loading="crud.status.cu === 2" type="primary" @click="crud.submitCU">确认</el-button>
      </div>
    </el-dialog>
    </#list>
    <!--表格渲染-->
    <el-table
      ref="table"
      v-loading="crud.loading"
      lazy
      :data="crud.data"
      @select="crud.selectChange"
      @select-all="crud.selectAllChange"
      @selection-change="crud.selectionChangeHandler"
    >
      <#list tableColumns as column>
        <#if column.vueType == 'selection' >
          <el-table-column type="selection" width="55" />
        <#elseif column.vueType == 'plain'>
          <el-table-column label="${column.desc}" prop="${column.name}" />
        </#if>
      </#list>
      <el-table-column label="操作" width="130px" align="center" fixed="right">
        <template slot-scope="scope">
          <udOperation
            :data="scope.row"
            :permission="permission"
            msg="确定删除该条记录?"
          />
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import ${crudObjName} from '@${importJsPath}'
import CRUD, { presenter, header, form, crud } from '@crud/crud'
import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'

const defaultForm = {
  <#list addForm>
      <#items as form >
          ${form.name} : null <#sep>,
      </#items>
  </#list>
}
export default {
  name: '${componentName}',
  components: {crudOperation, rrOperation, udOperation },
  cruds(){
    return CRUD({ title: '${(addFormTitle)!"unknown"}', url: '${listUrl}', crudMethod: { ...${crudObjName} }})
  },
  mixins: [presenter(), header(), form(defaultForm), crud()],
  data() {
    return {
      permission: {
        add: ['admin' ],
        edit: ['admin'],
        del: ['admin']
      },
    }
  },
  methods: {

  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
 ::v-deep .el-input-number .el-input__inner {
    text-align: left;
  }
</style>
</#escape>