package com.h2y.bmg.services;

import com.h2y.bmg.dao.ISysGridColumnsDao;
import com.h2y.bmg.dao.ISysTypeDao;
import com.h2y.bmg.entity.SysGridColumns;
import com.h2y.bmg.entity.SysMenu;
import com.h2y.bmg.entity.SysType;
import com.h2y.bmg.util.SysBaseUtil.SysJoinType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
  * ServiceImpl
 * @author hwttnet
 * version:1.2
 * time:2014-10-16
 * email:info@hwttnet.com
 */
@Service("sysGridColumnsService")
public class SysGridColumnsServiceImpl implements ISysGridColumnsService{


	@Autowired
	protected ISysGridColumnsDao sysGridColumnsDao;
	
	@Autowired
	protected ISysMenuService sysMenuService;
	

	@Autowired
	protected ISysTypeDao sysTypeDao;

	/**
	 * Add data, pay attention to the path of the gain of algorithm
	 * 
	 * @param sysGridColumns
	 *
	 */
	public void add(SysGridColumns sysGridColumns) {
		// TODO Auto-generated method stub

		sysGridColumnsDao.add(sysGridColumns);
	}


	public void delete(Long id) {
		// TODO Auto-generated method stub
		sysGridColumnsDao.deleteById(id);
	}

	public void deleteByIds(List<Long> ids){
		sysGridColumnsDao.deleteByIds(ids);
	}

	public void update(SysGridColumns sysGridColumns) {
		// TODO Auto-generated method stub
		sysGridColumnsDao.update(sysGridColumns);
	}

	public SysGridColumns get(Long id) {
		// TODO Auto-generated method stub
		return sysGridColumnsDao.get(id);
	}


	public List<SysGridColumns> getList(SysGridColumns sysGridColumns){
		List<SysGridColumns> list = sysGridColumnsDao.getList(sysGridColumns);
		//
		//sysGridColumns = null;

		return list;
	}


	/**
	 * getListPage
	 * @return
	 */
	public List<SysGridColumns> getListPage(Map<String,Object> map){
		//map.put("aaa", new SysGridColumns());
		return sysGridColumnsDao.getListPage(map);
	}

	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map){

		return sysGridColumnsDao.getRows(map);
	}



    /**
     * 得到表格列数据
     * @param map
     * @return
     */
    public Map<String,Object> getGridData(Map<String,Object> map){


        Map<String,Object> gridData = new HashMap<String, Object>();

        List<Map<String,Object>> dataList = sysGridColumnsDao.getListMap(map);
        if (dataList==null) {
            dataList = new ArrayList<Map<String,Object>>();
        }
        gridData.put("Rows", dataList);
        gridData.put("Total", dataList.size());
        return gridData;
    }

    /**
     * 保存列宽
     * @param joinId
     * @param joinType
     * @param dataList
     */
    public void saveGridColumns(long joinId,String joinType,List<Map<String,Object>> dataList){

        Map<String,Object> params = new HashMap<String, Object>();
        params.put("joinId",joinId);
        params.put("joinType",joinType);

        sysGridColumnsDao.deleteByJoinType(params);

        List<SysGridColumns> columnsList = new ArrayList<SysGridColumns>();

        if (dataList!=null && !dataList.isEmpty()){

            for (Map<String,Object> dataMap: dataList){

                SysGridColumns sysGridColumns = new SysGridColumns();
                sysGridColumns.setJoinId(joinId);
                sysGridColumns.setJoinType(joinType);
                sysGridColumns.setTitle(dataMap.get("TITLE")+"");
                sysGridColumns.setAlign(dataMap.get("ALIGN")+"");
                sysGridColumns.setName(dataMap.get("NAME")+"");
                sysGridColumns.setDataType(dataMap.get("DATA_TYPE")+"");
                sysGridColumns.setWidth(dataMap.get("WIDTH")+"");
                sysGridColumns.setRender(dataMap.get("RENDER")+"");

                if(dataMap.get("IS_SORT")!=null && !dataMap.get("IS_SORT").equals("")){
                    sysGridColumns.setIsSort(Long.valueOf(dataMap.get("IS_SORT")+""));
                }

                if(dataMap.get("IF_WIDTH")!=null && !dataMap.get("IF_WIDTH").equals("")){
                    sysGridColumns.setIfWidth(Long.valueOf(dataMap.get("IF_WIDTH")+""));
                }

                if(dataMap.get("IF_VISIBLE")!=null && !dataMap.get("IF_VISIBLE").equals("")){
                    sysGridColumns.setIfVisible(Long.valueOf(dataMap.get("IF_VISIBLE")+""));
                }

                if(dataMap.get("ORD")!=null && !dataMap.get("ORD").equals("")){
                    sysGridColumns.setOrd(Long.valueOf(dataMap.get("ORD")+""));
                }
                columnsList.add(sysGridColumns);
            }

            sysGridColumnsDao.addBatch(columnsList);
        }
    }
    
    
    
    /**
     * 根据菜单的访问链接，得到对应列维护
     * @param request
     */
    public String getGridColumsByRequest(HttpServletRequest request){
    	
    	StringBuffer dataBuffer = new StringBuffer("");
    	SysMenu sysMenu = sysMenuService.getSysMenuByRequest(request);
    	if (sysMenu!=null && sysMenu.getIfGrid()==1) {
    		
    		addGridColumsDataToBuffer(sysMenu.getId(), SysJoinType.MENU, dataBuffer);
		}
		return dataBuffer.toString();
    }
    
    /**
     * 根据编码，得到对应列维护
     * @param request
     */
    public String getGridColumsByCode(String code){
    	StringBuffer dataBuffer = new StringBuffer("");
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("code", code);
		params.put("type", SysJoinType.GRID);
		SysType sysType = sysTypeDao.getSysTypeByCode(params);
		if (sysType!=null) {
    		addGridColumsDataToBuffer(sysType.getId(), SysJoinType.GRID, dataBuffer);
		}
		return dataBuffer.toString();
    }
    
    
    /**
     * 添加列维护数据到缓冲区中
     * @param joinId 关联Id
     * @param joinType 关联类型（grid:列维护、menu:菜单）
     * @param dataBuffer 列维护数据的Buffer
     */
    private void addGridColumsDataToBuffer(long joinId,String joinType,StringBuffer dataBuffer){
    	
    	
    	Map<String,Object> map = new HashMap<String, Object>();
		map.put("joinId", joinId);
		map.put("joinType", joinType);
		List<SysGridColumns> gridColumns = sysGridColumnsDao.getListByJoinType(map);
		
		if (gridColumns!=null) {
			
			for (SysGridColumns sysGridColumns : gridColumns) {
				
				if (!dataBuffer.toString().equals("")) {
					dataBuffer.append(",");
				}
				String width = sysGridColumns.getIfWidth()==1 ? sysGridColumns.getWidth() : "\""+sysGridColumns.getWidth()+"%\"";
				dataBuffer.append(String.format("{display: \"%s\", name: \"%s\", width: %s, align: \"%s\", type: \"%s\", isSort:%b",
												sysGridColumns.getTitle(),
												sysGridColumns.getName(),
												width,
												sysGridColumns.getAlign(),
												sysGridColumns.getDataType(),
												sysGridColumns.getIsSort()==1));
				
				if (sysGridColumns.getRender()!=null && !sysGridColumns.getRender().equals("")) {
					dataBuffer.append(",render:function(rowdata, index, value){ "+sysGridColumns.getRender()+"}}");
				}else {
					dataBuffer.append("}");
				}
			}
		}
    }
}