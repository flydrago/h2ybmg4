package com.h2y.bmg.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.bmg.dao.ISysTypeDao;
import com.h2y.bmg.dao.ISysValidationDao;
import com.h2y.bmg.entity.SysMenu;
import com.h2y.bmg.entity.SysType;
import com.h2y.bmg.entity.SysValidation;
import com.h2y.bmg.util.SysBaseUtil.SysJoinType;

/**
 * ServiceImpl
 * @author hwttnet
 * version:1.2
 * time:2014-10-25
 * email:info@hwttnet.com
 */
@Service("sysValidationService")
public class SysValidationServiceImpl implements ISysValidationService{


	@Autowired
	protected ISysValidationDao sysValidationDao;

	@Autowired
	protected ISysTypeDao sysTypeDao;
	
	@Autowired
	protected ISysMenuService sysMenuService;


	/**
	 * Add data, pay attention to the path of the gain of algorithm
	 * 
	 * @param sysValidation
	 *
	 */
	public void add(SysValidation sysValidation) {
		// TODO Auto-generated method stub

		sysValidationDao.add(sysValidation);
	}


	public void delete(long id) {
		// TODO Auto-generated method stub
		sysValidationDao.deleteById(id);
	}

	//public void deleteByIds(List<long> ids){
	//	sysValidationDao.deleteByIds(ids);
	//}

	public void update(SysValidation sysValidation) {
		// TODO Auto-generated method stub
		sysValidationDao.update(sysValidation);
	}

	public SysValidation get(long id) {
		// TODO Auto-generated method stub
		return sysValidationDao.get(id);
	}


	/**
	 * 得到验证列表数据
	 * @param map
	 * @return
	 */
	public Map<String,Object> getGridData(Map<String,Object> map){


		Map<String,Object> gridData = new HashMap<String, Object>();

		List<Map<String,Object>> dataList = sysValidationDao.getListMap(map);
		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", dataList.size());
		return gridData;
	}


	/**
	 * 保存验证
	 * @param joinId
	 * @param joinType
	 * @param dataList
	 */
	public void saveValidation(long joinId,String joinType,List<Map<String,Object>> dataList){


		Map<String,Object> params = new HashMap<String, Object>();
		params.put("joinId",joinId);
		params.put("joinType",joinType);

		sysValidationDao.deleteByJoinType(params);

		List<SysValidation> validationList = new ArrayList<SysValidation>();

		if (dataList!=null && !dataList.isEmpty()){

			for (Map<String,Object> dataMap: dataList){

				SysValidation sysValidation = new SysValidation();
				sysValidation.setJoinId(joinId);
				sysValidation.setJoinType(joinType);
				sysValidation.setFieldId(dataMap.get("FIELD_ID")+"");
				sysValidation.setMsg(dataMap.get("MSG")+"");
				sysValidation.setRuleType(dataMap.get("RULE_TYPE")+"");
				sysValidation.setRuleTypeValue(dataMap.get("RULE_TYPE_VALUE")+"");

				if (dataMap.get("IF_WORK")!=null && !dataMap.get("IF_WORK").equals("")) {
					sysValidation.setIfWork(Long.valueOf(dataMap.get("IF_WORK")+""));
				}
				validationList.add(sysValidation);
			}
			sysValidationDao.addBatch(validationList);
		}
	}


	/**
	 * 根据菜单的访问链接，得到对应验证
	 * @param request
	 */
	public String getValidationByRequest(HttpServletRequest request){
		
		StringBuffer dataBuffer = new StringBuffer("");
    	SysMenu sysMenu = sysMenuService.getSysMenuByRequest(request);
    	if (sysMenu!=null && sysMenu.getIfValidate()==1) {
    		
			List<SysValidation> validations = getListByJoinType(sysMenu.getId(), SysJoinType.MENU);
			addValidationDataToBuffer(dataBuffer, validations);
    	}
    	return dataBuffer.toString();
	}


	/**
	 * 根据编码得到验证信息
	 * @param code 验证编码
	 * @return
	 */
	public String getValidationByCode(String code){

		StringBuffer dataBuffer = new StringBuffer("");

		Map<String,Object> params = new HashMap<String, Object>();
		params.put("code", code);
		params.put("type", SysJoinType.VALIDATE);
		SysType sysType = sysTypeDao.getSysTypeByCode(params);

		if (sysType!=null) {

			List<SysValidation> validations = getListByJoinType(sysType.getId(), SysJoinType.VALIDATE);
			addValidationDataToBuffer(dataBuffer, validations);
		}
		return dataBuffer.toString();
	}
	
	
	/**
	 * 添加验证信息到字符串缓冲区中
	 * @param dataBuffer
	 * @param validations
	 */
	private void addValidationDataToBuffer(StringBuffer dataBuffer,List<SysValidation> validations){
		
		if (validations!=null) {

			//提示信息
			Map<String,String> msgInfo = new HashMap<String, String>();
			//验证规则信息
			Map<String,String> ruleInfo = new HashMap<String, String>();

			for (SysValidation sysValidation : validations) {
				String fieldId = sysValidation.getFieldId();
				String msg = sysValidation.getMsg();
				String ruleType = sysValidation.getRuleType();
				String ruleTypeValue = sysValidation.getRuleTypeValue();
				
				if (fieldId==null) {
					continue;
				}

				//提示信息
				if (msg!=null && !msg.equals("")) {
					String oldMsg = msgInfo.get(fieldId);
					if (oldMsg!=null) {
						oldMsg += ",'"+ruleType+"':'"+msg+"'";
					}else {
						oldMsg = "'"+ruleType+"':'"+msg+"'";
					}
					msgInfo.put(fieldId, oldMsg);
				}
				
				//验证信息
				if (ruleTypeValue!=null && !ruleTypeValue.equals("")) {
					String oldRule = ruleInfo.get(fieldId);
					if (oldRule!=null) {
						oldRule += ",'"+ruleType+"':"+ruleTypeValue+"";
					}else {
						oldRule = "'"+ruleType+"':"+ruleTypeValue+"";
					}
					ruleInfo.put(fieldId, oldRule);
				}
			}
			
			for (Entry<String, String> entry : ruleInfo.entrySet()) {
				
				String ruleMsg = msgInfo.get(entry.getKey());
				String info = "";
				if (ruleMsg!=null) {
					info = String.format("$(\"#%s\").attr(\"validate\",\"{%s,messages:{%s}}\");\n",
							entry.getKey(),
							entry.getValue(),
							ruleMsg);
				}else {
					info = String.format("$(\"#%s\").attr(\"validate\",\"{%s}\");\n",
							entry.getKey(),
							entry.getValue());
				}
				dataBuffer.append(info);
			}
		}
	}
	
	
	

	/**
	 * 根据joinType，得到验证列表
	 * @param joinId
	 * @param joinType
	 * @return
	 */
	private List<SysValidation> getListByJoinType(long joinId,String joinType){

		Map<String,Object> params = new HashMap<String, Object>();
		params.put("joinId", joinId);
		params.put("joinType", joinType);
		return sysValidationDao.getListByJoinType(params);
	}
}