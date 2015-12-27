package com.h2y.dict;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import com.h2y.bmg.entity.SysDictDetail;
import com.h2y.bmg.entity.SysDictMain;
import com.h2y.bmg.services.ISysDictMainService;
import com.h2y.bmg.util.SysBaseUtil;
import com.h2y.bmg.util.SysBaseUtil.DictClumn;
import com.h2y.bmg.util.SysBaseUtil.DictOrderBy;
import com.h2y.bmg.util.SysBaseUtil.DictPrefix;
import com.h2y.memcached.MemcachedUtil;
import com.h2y.spring.IocUtil;


public class DictUtil {

	private final static Logger logger = Logger.getLogger(DictUtil.class);

	protected static MemcachedUtil memcachedUtil = MemcachedUtil.getInstance();

	private static ISysDictMainService sysDictMainService = null;

	static{

		logger.info("字典加载缓存开始！");
		sysDictMainService = (ISysDictMainService) IocUtil.getBean("sysDictMainService");
		sysDictMainService.loadDictDataToCache();
		logger.info("字典加载缓存结束！");
	}


	/**
	 * 根据key，得到字典主表信息
	 * @param code 主表编码
	 * @return
	 */
	public static SysDictMain getMainByCode(String code){

		SysDictMain sysDictMain = null;

		Object object = memcachedUtil.get(getDictMainKey(code));
		if (object instanceof SysDictMain) {
			sysDictMain = (SysDictMain) object;
		}
		return sysDictMain;
	}


	/**
	 * 根据key，得到字典详细列表信息
	 * @param unitId 单位Id
	 * @param mainCode 主表编码
	 * @return
	 */
	public static List<SysDictDetail> getDetailListByMainCode(long unitId,String mainCode){

		List<SysDictDetail> sysDictDetails = new ArrayList<SysDictDetail>();

		Object object = memcachedUtil.get(getDictDetailKey(unitId, mainCode));
		if (object instanceof List) {
			sysDictDetails = (List<SysDictDetail>) object;
		}

		SysDictMain sysDictMain = getMainByCode(mainCode);

		if (sysDictMain.getIsExtends()==0) {//不可继承

			//添加单位Id为1的字典详细
			if (unitId!=1) {

				Object baseData = memcachedUtil.get(getDictDetailKey(1l, mainCode));
				if (baseData instanceof List) {
					sysDictDetails.addAll((List<SysDictDetail>) baseData);
				}
			}
		}else {//可继承
			
			//得到平台数据
			if (unitId!=1) {
				
				List<String> codeList = new ArrayList<String>();
				for (SysDictDetail unitDictDetail : sysDictDetails) {
					codeList.add(unitDictDetail.getCode());
				}
				
				//获取平台数据
				Object baseData = memcachedUtil.get(getDictDetailKey(1l, mainCode));
				if (baseData instanceof List) {
					List<SysDictDetail> sysDictDetailBaseList =  (List<SysDictDetail>) baseData;
					for (SysDictDetail sysDictDetail : sysDictDetailBaseList) {
						//单位中没有的编码，则取平台的数据
						if (!codeList.contains(sysDictDetail.getCode())) {
							sysDictDetails.add(sysDictDetail);
						}
					}
				}
			}
		}

		if (sysDictDetails!=null) {

			ComparatorSysDictDetail com = new ComparatorSysDictDetail(DictClumn.ord, DictOrderBy.desc);
			Collections.sort(sysDictDetails,com);
		}
		return sysDictDetails;
	}



	/**
	 * 根据字典详细编码，得到字典详细
	 * @param unitId 单位Id
	 * @param mainCode 主表编码
	 * @param code 字典详细数据编码
	 * @return
	 */
	public static SysDictDetail getDetailByCode(long unitId,String mainCode,String code){

		SysDictDetail sysDictDetail = null;

		List<SysDictDetail> sysDictDetails = getDetailListByMainCode(unitId, mainCode);
		if (sysDictDetails!=null) {
			for (SysDictDetail sysDictDetail2 : sysDictDetails) {
				if (sysDictDetail2.getCode().equals(code)) {
					sysDictDetail = sysDictDetail2;
					break;
				}
			}
		}
		return sysDictDetail;
	}


	/**
	 * 得到字典主表的memcached的存取key
	 * @param unitId 单位Id
	 * @param code 字典主表对应的编码
	 * @return
	 */
	public static String getDictMainKey(String code){

		return DictPrefix.DIC_MAIN+"_"+code;
	}


	/**
	 * 得到字典详细的memcached的存取key
	 * @param unitId 单位Id
	 * @param code 字典主表对应的编码
	 * @return
	 */
	public static String getDictDetailKey(long unitId,String code){

		return DictPrefix.DIC_DETAIL+"_"+unitId+"_"+code;
	}
}
