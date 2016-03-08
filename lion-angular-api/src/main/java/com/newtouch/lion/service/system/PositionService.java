/*
 * Copyright (c)  2014, Newtouch
 * All rights reserved. 
 *
 * $id: PositionService.java 9552 2014年12月25日 上午10:26:25 WangLijun$
 */
package com.newtouch.lion.service.system;
import com.newtouch.lion.model.system.Position;
import com.newtouch.lion.model.system.Role;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
/**
 * <p>
 * Title:岗位信息Service层接口定义
 * </p>
 * <p>
 * Description:岗位信息编写
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public interface PositionService {

    /****
     * 条件分页查询查询
     * @param criteria
     * @return
     */
    public PageResult<Position> doFindByCriteria(QueryCriteria criteria);

    /****
     * 新建一个职位信息
     * @param position
     * @return
     */
    public void doCreatePosition(Position position);
    /****
     * 删除一个职位信息
     * @param position
     * @return
     */
    public void doDelete(Position position);

    /****
     * 根据id删除一个职位信息
     * @param id
     * @return
     */
    public int doDeleteById(Long id);


    /****
     * 根据id查询一个职位信息
     * @param id
     * @return
     */
    public Position doFindById(Long id);

    /****
     * 根据id查询一个职位信息
     * @param position
     * @return
     */
    public void doUpdate(Position position);


    /****
     * 根据id查询一个职位信息
     * @param
     * @return
     */
    public boolean doIsExistByNameEn(String nameEn);

    public Position doFindTypeByNameEn(String nameEn);
}
