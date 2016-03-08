package com.newtouch.lion.admin.web.controller.jstree;

import com.newtouch.lion.tree.Node;
import com.newtouch.lion.tree.State;
import com.newtouch.lion.web.controller.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanglijun on 16/1/7.
 */
@RequestMapping("/jstree")
@Controller
public class JsTreeController  extends AbstractController {

    public static final String INDEX_RETURN="/jstree/index";


    @RequestMapping("/index")
    public String index(){
        logger.info("jstree ....");
        return INDEX_RETURN;
    }


        @RequestMapping("/list")
        @ResponseBody
        public List<Node> treeList(){
            List<Node> treeNodes=new ArrayList<Node>();
            List<Node> children=new ArrayList<Node>();

            Node treeNode3=new Node(null,"fa fa-warning icon-state-danger",3L,1L,new State(true),"node3");
            Node treeNode4=new Node(null,"fa fa-warning icon-state-default",4L,1L,new State(true),"node4");
            Node treeNode5=new Node(null,"fa fa-warning icon-state-warning",5L,1L,new State(true),"node5");
            Node treeNode6=new Node(null,"fa fa-warning icon-state-success",65L,1L,new State(true),"node6");

            children.add(treeNode3);
            children.add(treeNode4);
            children.add(treeNode5);
            children.add(treeNode6);

            Node treeNode1=new Node(children,"",1L,1L,new State(true),"root");
            Node treeNode2=new Node(null,"",2L,1L,new State(true),"root2");



        treeNodes.add(treeNode1);
        treeNodes.add(treeNode2);
        return treeNodes;
    }
}
