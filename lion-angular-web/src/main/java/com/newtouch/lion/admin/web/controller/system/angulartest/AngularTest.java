package com.newtouch.lion.admin.web.controller.system.angulartest;

import com.newtouch.lion.model.system.Icon;
import com.newtouch.lion.model.system.Tasks;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.system.IconService;
import com.newtouch.lion.service.system.TasksService;
import com.newtouch.lion.web.controller.AbstractController;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by lixiaohao on 2016/3/23.
 */
@Controller
@RequestMapping("/system/angular/")
public class AngularTest extends AbstractController {

    /** 默认排序字段名称 */
    private static final String DEFAULT_ORDER_FILED_NAME = "id";

    @Autowired
    private IconService iconService;

    @RequestMapping("list.json")
    @ResponseBody
    public List<Icon> tasksList(){
        QueryCriteria queryCriteria = new QueryCriteria();

        // 设置分页 启始页
        queryCriteria.setStartIndex(1);
        // 每页大小
        queryCriteria.setPageSize(10);
        // 设置排序字段及排序方向

            queryCriteria.setOrderField(DEFAULT_ORDER_FILED_NAME);
            queryCriteria.setOrderDirection(QueryCriteria.ASC);

        //查询条件 图标类名按模糊查询
//        if(StringUtils.isNotEmpty(iconVo.getIconClass())){
//            queryCriteria.addQueryCondition("iconClass","%"+iconVo.getIconClass()+"%");
//        }
//
//        //查询条件 图标类型
//        if(StringUtils.isNotEmpty(iconVo.getIconType())){
//            queryCriteria.addQueryCondition("iconType",iconVo.getIconType());
//        }

        PageResult<Icon> pageResult = iconService.doFindByCriteria(queryCriteria);
        return  pageResult.getContent();
    }
}
