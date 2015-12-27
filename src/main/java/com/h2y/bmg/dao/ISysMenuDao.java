package com.h2y.bmg.dao;

import com.h2y.bmg.entity.SysButton;
import com.h2y.bmg.entity.SysMenu;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * SysMenuDaoInterface,order CRUD ;CRUD:Create,Retrieve/Read,Update,Delete
 *
 * @author hwttnet
 *         version:1.2
 *         time:2014-10-13
 *         email:info@hwttnet.com
 */
@Component
public interface ISysMenuDao {

    /**
     * add
     */
    public void add(SysMenu sysMenu);

    /**
     * update
     */
    public void update(SysMenu sysMenu);

    /**
     * delete
     */
    public void deleteById(Long id);

    /**
     * deleteByIds
     */
    public void deleteByIds(List<Long> ids);

    /**
     * get
     *
     * @return
     */
    public SysMenu get(Long id);


    /**
     * getList
     *
     * @return
     */
    public List<SysMenu> getList(SysMenu sysMenu);


    /**
     * getListPage
     * <p/>
     * page,pagesize,key
     *
     * @return
     */
    public List<SysMenu> getListPage(Map<String, Object> map);

    /**
     * getRows
     *
     * @param map
     * @return id desc,name ,date asc
     */
    public long getRows(Map<String, Object> map);

    /**
     * 得到菜单树数据
     * @param map
     * key:pid value:父级菜单（null时，不做判断）
     * key:ifGrid value:是否列维护（null时，不做判断）
     * key:ifQuery value:是否查询（null时，不做判断）
     * key:ifValidate value:是否验证（null时，不做判断）
     * @return
     */
    public List<Map<String, Object>> getTreeData(Map<String, Object> map);


    /**
     * 得到用户主页菜单数据
     * @param map
     * key1:unitId value1:单位Id
     * key2:roleList value2:角色Id集合
     * @return
     */
    public List<Map<String, Object>> getUserMenuTreeData(Map<String, Object> map);
    
    
    /**
     * 得到系统菜单数据
     * @return
     */
    public List<Map<String,Object>> getSysMenuTreeData();


    /**
     * 得到菜单列表
     *
     * @param map
     * @return
     */
    public List<Map<String, Object>> getListMap(Map<String, Object> map);


    /**
     * 得到自己菜单数
     * @param menuId
     * @return
     */
    public long getChildMenuRows(long menuId);


    /**
     * 得到父级菜单列表
     * @param menuId
     * @return
     */
    public List<SysMenu> getParentMenus(long menuId);


    /**
     * 根据菜单，删除按钮
     */
    public void deleteByMenuId(Long menuId);

    /**
     * 批量添加
     * @param list
     */
    public void addBatch(List<SysButton> list);


    /**
     * 通过url，得到菜单对象
     * @param map
     * @return
     */
    public List<SysMenu> getMenuByUrl(Map<String,Object> map);
    
    /**
     * 根据父级Id，得到菜单Id集合
     * @param map
     * key:parentIds value:父级Id组合的字符串
     * @return
     */
    public List<Long> getMenuIdByParentIds(Map<String,Object> map);
}