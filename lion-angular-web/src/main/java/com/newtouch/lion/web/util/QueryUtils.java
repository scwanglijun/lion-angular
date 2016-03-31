package com.newtouch.lion.web.util;/**
 * Created by jovi on 3/31/16.
 */

import com.newtouch.lion.admin.web.model.query.QueryReq;
import com.newtouch.lion.query.QueryCriteria;

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
 * Company: XiQing
 * </p>
 *
 * @author MaoJiaWei
 * @version 1.0
 */
public class QueryUtils {
    public static QueryCriteria pageFormat(QueryCriteria criteria, QueryReq req){
        // 设置分页 启始页
        criteria.setStartIndex((req.getPage().getPageNumber()-1)*req.getPage().getPageSize());
        // 每页大小
        criteria.setPageSize(req.getPage().getPageSize());
        return criteria;
    }
}
