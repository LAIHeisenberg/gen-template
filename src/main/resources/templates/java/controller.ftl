<#if packageName??>
package ${packageName?string};
</#if>

import com.longmai.datakeeper.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("")
public class ${entityShortName}Controller extends BaseController {

    @Autowired
    private ${entityShortName}Facade facade;

    @GetMapping("/list")
    public ResponseEntity<Object> listPage(${queryBeanName} queryParam, Integer page, Integer size){
        return new ResponseEntity<>(facade.listPage(queryParam, page, size), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody ${addBeanName} createBean){
        facade.create(createBean, getCurrentUser());
        return new ResponseEntity(true, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody List<Integer> ids){
        return new ResponseEntity(facade.delete(ids.get(0)), HttpStatus.OK);
    }

}