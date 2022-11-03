<#if packageName??>
package ${packageName?string};
</#if>

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.longmai.datakeeper.dto.UserLoginDto;
import com.longmai.datakeeper.utils.PageUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ${entityShortName}Facade {

    @Autowired
    private ${entityShortName}ServiceImpl serviceImpl;

    public Map<String,Object> listPage(${queryBeanName} queryParam, Integer pageNum, Integer pageSize){
        ${entityName} queryEntity = new ${entityName}();
        BeanUtils.copyProperties(queryParam, queryEntity);
        Page<${dtoBeanName}> page = serviceImpl.listPage(queryEntity, pageNum, pageSize);
        List<${viewBeanName}> view = convertView(page.getRecords());
        return PageUtil.toPage(view, page.getTotal());
    }

    public boolean create(${addBeanName} createBean, UserLoginDto userLoginDto){
        ${entityName} newEntity = new ${entityName}();
        BeanUtils.copyProperties(createBean, newEntity);
        newEntity.setCreateById(userLoginDto.getId().intValue());
        newEntity.setCreateByName(userLoginDto.getUserName());
        return serviceImpl.create(newEntity);
    }

    public boolean delete(Integer id){
        return serviceImpl.deleteById(id);
    }

    private List<${viewBeanName}> convertView(List<${dtoBeanName}> dtoList){
        if (CollectionUtils.isEmpty(dtoList)){
            return Collections.emptyList();
        }
        return dtoList.stream().map(dto -> {
            ${viewBeanName} view = new ${viewBeanName}();
            BeanUtils.copyProperties(dto, view);
            return view;
        }).collect(Collectors.toList());
    }

}