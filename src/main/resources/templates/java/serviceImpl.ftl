<#if packageName??>
package ${packageName?string};
</#if>

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ${entityShortName}ServiceImpl implements ${entityShortName}Service {

    @Autowired
    private ${entityShortName}Mapper mapper;

    @Override
    public Page<${dtoBeanName}> listPage(${entityName} param, Integer pageNum, Integer pageSize){

        QueryWrapper<${entityName}> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(param);
        Page<${entityName}> page = mapper.selectPage(new Page<>(pageNum, pageSize), queryWrapper);
        List<${dtoBeanName}> records = page.getRecords().stream().map(new Function<${entityName}, ${dtoBeanName}>() {
            @Override
            public ${dtoBeanName} apply(${entityName} entity) {
                ${dtoBeanName} dto = new ${dtoBeanName}();
                BeanUtils.copyProperties(entity,dto);
                return dto;
            }
        }).collect(Collectors.toList());

        Page<${dtoBeanName}> resultPage = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        resultPage.setRecords(records);

        return resultPage;
    }

    @Override
    public boolean create(${entityName} entity){
        if (Objects.isNull(entity)){
            return false;
        }
        entity.setCreateTime(new Date());
        return mapper.insert(entity) > 1;
    }

    @Override
    public boolean deleteById(Integer id){
        if (Objects.isNull(id)){
            return false;
        }
        return mapper.deleteById(id) > 1;
    }

}