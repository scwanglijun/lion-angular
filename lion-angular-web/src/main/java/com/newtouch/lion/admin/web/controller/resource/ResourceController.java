package com.newtouch.lion.admin.web.controller.resource;

import java.util.ArrayList;
import java.util.List;

import com.newtouch.lion.admin.web.model.resource.*;
import com.newtouch.lion.model.system.Icon;
import com.newtouch.lion.service.system.IconService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.newtouch.lion.model.system.Resource;
import com.newtouch.lion.service.system.ResourceService;
import com.newtouch.lion.web.controller.AbstractController;
import com.newtouch.lion.webtrans.trans.Trans;
import org.springframework.util.CollectionUtils;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2016
 * </p>
 * <p>
 * Company:
 * </p>
 *
 * @author LiXiaoHao
 * @version 1.0
 */
@Controller
public class ResourceController extends AbstractController{

    private static final String INDEX_TREE_TB = "sys_resource_lists_tb";

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private IconService iconService;
    @Trans("system.resource.list")
    public List<TempResourceResp> lists(ResourceGetReq req) {
        List<Resource> result=this.resourceService.doFindAll();

        List<TempResourceResp> re = new ArrayList<TempResourceResp>();

        for(Resource res:result){
            TempResourceResp tempReturn  =  new TempResourceResp();
            BeanUtils.copyProperties(res,tempReturn);
            re.add(tempReturn);
        }
        return re;
    }


    /***
     * 编辑
     * @param req
     * @return
     */
    @Trans("system.resource.resourceGet")
    public TempResourceResp resourceGet(ResourceGetReq req) {

        if(req.getId()==null){
            return null;
        }

        Resource resource = resourceService.doFindById(req.getId());
        if(resource==null){
            return null;
        }
        TempResourceResp resp = new TempResourceResp();
        BeanUtils.copyProperties(resource,resp);

        return resp;
    }

    /***
     * 编辑
     * @param req
     * @return
     */
    @Trans("system.resource.resourceEditor")
    public ResourceEditorResp editor(ResourceEditorReq req) {
        if(req.getId()==null || req.getParentResourceId()==null){
            return new ResourceEditorResp(ResourceEditorResp.FAIL_DATAGRID_EDITOR_CODE,ResourceEditorResp.FAIL_DATAGRID_EDITOR_MESSAGE);
        }
        Resource parentResource = resourceService.doFindById(req.getParentResourceId());
        if(parentResource==null){
            return new ResourceEditorResp(ResourceEditorResp.FAIL_DATAGRID_EDITOR_CODE,ResourceEditorResp.FAIL_DATAGRID_EDITOR_MESSAGE);
        }
        Resource resource = resourceService.doFindById(req.getId());
        if(resource != null){
            BeanUtils.copyProperties(req,resource);
            resourceService.doUpdate(resource);
            return new ResourceEditorResp(ResourceEditorResp.SUCCESS_DATAGRID_EDITOR_CODE,ResourceEditorResp.SUCCESS_DATAGRID_EDITOR_MESSAGE);
        }


        return new ResourceEditorResp(ResourceEditorResp.FAIL_DATAGRID_EDITOR_CODE,ResourceEditorResp.FAIL_DATAGRID_EDITOR_MESSAGE);
    }


    @Trans("system.resource.delete")
    public ResourceResp delete(ResourceDeleteReq req) {
        if(req.getId()==null){
            return new ResourceResp(ResourceResp.FAIL_RESOURCE_CODE,ResourceResp.FAIL_RESOURCE_MESSAGE);
        }
        Resource resource = this.resourceService.doDelete(req.getId());
        if (resource != null) {
           return new ResourceResp(ResourceResp.SUCCESS_RESOURCE_CODE,ResourceResp.SUCCESS_RESOURCE_MESSAGE);
        }

        return new ResourceResp(ResourceResp.FAIL_RESOURCE_CODE,ResourceResp.FAIL_RESOURCE_MESSAGE);
    }

    @Trans("system.resource.add")
    public ResourceResp add(ResourceEditorReq resourceVo) {
       if(resourceVo==null){
           return new ResourceResp(ResourceResp.FAIL_RESOURCE_CODE,ResourceResp.FAIL_RESOURCE_MESSAGE);
       }
        Resource resource = new Resource();
        BeanUtils.copyProperties(resourceVo, resource);
        this.resourceService.doCreateResource(resource);
        return new ResourceResp(ResourceResp.SUCCESS_RESOURCE_CODE,ResourceResp.SUCCESS_RESOURCE_MESSAGE);
    }


   @Trans("system.code.icon")
    public List<Icon> comboxIcon(CodeReq req){

        List<Icon> list=iconService.doFindByType(req.getIconType());
        if(CollectionUtils.isEmpty(list)){
            return list=new ArrayList<Icon>();
        }
        return  list;
    }

}
