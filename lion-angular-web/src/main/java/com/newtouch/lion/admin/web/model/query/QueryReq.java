package com.newtouch.lion.admin.web.model.query;/**
 * Created by jovi on 3/25/16.
 */

import com.newtouch.lion.web.model.Order;
import com.newtouch.lion.web.model.QueryVo;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * Title:搜索通用类
 * </p>
 * <p>
 * Description:搜索通用类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2016
 * </p>
 * <p>
 * Company: XiQing
 * </p>
 *
 * @author MaoJiaWei
 * @version 1.0
 */
public class QueryReq implements Serializable{
    private GridPage page;

    public GridPage getPage() {
        return page;
    }

    public void setPage(GridPage page) {
        this.page = page;
    }
}
