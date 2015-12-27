package com.h2y.bmg.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.bmg.dao.ISysTypeDao;
import com.h2y.bmg.entity.SysType;

/**
  * ServiceImpl
 * @author hwttnet
 * version:1.2
 * time:2014-10-17
 * email:info@hwttnet.com
 */
@Service("sysTypeService")
public class SysTypeServiceImpl implements ISysTypeService{


	@Autowired
	protected ISysTypeDao sysTypeDao;

	/**
	 * Add data, pay attention to the path of the gain of algorithm
	 * 
	 * @param sysType
	 *
	 */
	public void add(SysType sysType) {
		// TODO Auto-generated method stub

		sysTypeDao.add(sysType);
	}


	public void delete(Long id) {
		// TODO Auto-generated method stub
		sysTypeDao.deleteById(id);
	}

	public void deleteByIds(List<Long> ids){
		sysTypeDao.deleteByIds(ids);
	}

	public void update(SysType sysType) {
		// TODO Auto-generated method stub
		sysTypeDao.update(sysType);
	}

	public SysType get(Long id) {
		// TODO Auto-generated method stub
		return sysTypeDao.get(id);
	}


	public List<SysType> getList(SysType sysType){
		List<SysType> list = sysTypeDao.getList(sysType);
		//
		//sysType = null;

		return list;
	}


	/**
	 * getListPage
	 * @return
	 */
	public List<SysType> getListPage(Map<String,Object> map){
		//map.put("aaa", new SysType());
		return sysTypeDao.getListPage(map);
	}

	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map){

		return sysTypeDao.getRows(map);
	}

    /**
     *
     * @param type
     * @param pid
     * @return
     */
    public List<Map<String,Object>> getChildTreeData(String type, Long pid){

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("type",type);
        map.put("pid",pid);
        List<Map<String,Object>> list = sysTypeDao.getTreeData(map);

        return list!=null ? list : new ArrayList<Map<String,Object>>();
    }

    /**
     * 得到树数据
     * @param type 系统类型（gird:列维护类型、query:查询类型、validate：验证类型）
     * @return
     */
    public List<Map<String,Object>> getTreeData(String type){

        return getChildTreeData(type,null);
    }


    /**
     * 得到列表数据
     * @param type 系统类型（gird:列维护类型、query:查询类型、validate：验证类型）
     * @param pid 父级Id
     * @return
     */
    public Map<String,Object> getGridData(String type,long pid){

        Map<String,Object> gridData = new HashMap<String, Object>();

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("type",type);
        map.put("pid",pid);
        List<Map<String,Object>> dataList = sysTypeDao.getListMap(map);
        if (dataList==null) {
            dataList = new ArrayList<Map<String,Object>>();
        }
        gridData.put("Rows", dataList);
        gridData.put("Total", dataList.size());
        return gridData;
    }

    /**
     *
     * @param type
     * @param pid
     */
    public void save(String type,long pid){


    }


    /**
     * 相同编码验证
     * @param op
     * @param sysType
     * @return
     */
    public boolean isHasSameCode(String op, SysType sysType){

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("code",sysType.getCode());
        map.put("type",sysType.getType());
        if(op.equals("modify")){
            map.put("id",sysType.getId());
        }
        return sysTypeDao.getSameCodeRows(map)>0;
    }




    /**
     * 是否有子级
     * @param sysType
     * @return
     */
    public boolean isHasChild(SysType sysType){

        return sysTypeDao.getChildTypeRows(sysType.getId())>0;
    }
}