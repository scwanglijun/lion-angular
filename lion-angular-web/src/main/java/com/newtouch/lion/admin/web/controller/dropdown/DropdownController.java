package com.newtouch.lion.admin.web.controller.dropdown;

import com.newtouch.lion.web.controller.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jovi on 1/12/16.
 */
@RequestMapping("/dropdown")
@Controller
public class DropdownController extends AbstractController{

    public static final String INDEX_RETURN="/dropdown/index";

    @RequestMapping("/index")
    public String index(){
        logger.info("dropdown ....");
        return INDEX_RETURN;
    }

}
