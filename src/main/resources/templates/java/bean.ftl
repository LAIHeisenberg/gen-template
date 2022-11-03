<#if packageName!="">
package ${packageName?string};
</#if>

import lombok.Data;

@Data
public class ${beanName} {

<#list fieldList as field>
    private ${field.dataType} ${field.name};
</#list>

}