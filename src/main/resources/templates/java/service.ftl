<#if packageName??>
package ${packageName?string};
</#if>

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public interface ${entityShortName}Service {

    Page<${dtoBeanName}> listPage(${entityName} param, Integer pageNum, Integer pageSize);

    boolean create(${entityName} entity);

    boolean deleteById(Integer id);

}