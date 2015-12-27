package com.h2y.bmg.services;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.bmg.dao.IGoodsTypeLogoDao;
import com.h2y.bmg.entity.FileDownMode;
import com.h2y.bmg.entity.GoodsTypeLogo;
import com.h2y.bmg.entity.SysDictMain;
import com.h2y.bmg.entity.SysUser;
import com.h2y.security.Base64Util;
import com.h2y.util.DateUtil;
import com.h2y.util.JSONUtil;

/**
 * 类描述：商品类型Logo的业务操作接口 作者：侯飞龙 时间：2015年1月7日上午9:59:28 邮件：1162040314@qq.com
 */
@Service("goodsTypeLogoService")
public class GoodsTypeLogoServiceImpl implements IGoodsTypeLogoService,
		IFileDownService {

	private static final Logger logger = Logger
			.getLogger(GoodsTypeLogoServiceImpl.class);

	@Autowired
	protected IGoodsTypeLogoDao goodsTypeLogoDao;

	public Map<String, Object> getGridData(HttpServletRequest request) {

		Map<String, Object> gridData = new HashMap<String, Object>();

		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		String typeId = request.getParameter("typeId");
		if (sortname != null && !sortname.equals("")) {

			sortname = sortname.toLowerCase();
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", Integer.parseInt(page));
		map.put("pagesize", Integer.parseInt(pagesize));
		map.put("sortorder", sortorder);
		map.put("sortname", sortname);
		map.put("typeId", Long.valueOf(typeId));
		List<Map<String, Object>> dataList = goodsTypeLogoDao.getListMap(map);

		if (dataList == null) {
			dataList = new ArrayList<Map<String, Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", goodsTypeLogoDao.getListRows(map));
		return gridData;
	}

	public void save(HttpServletRequest request, String op,
			GoodsTypeLogo goodsTypeLogo, SysDictMain sysDictMain,
			SysUser sysUser) {

		String iosFileData = request.getParameter("iosFileData");
		String androidFileData = request.getParameter("androidFileData");
		String wechatFileData = request.getParameter("wechatFileData");

		String dictPath = sysDictMain.getDictValue();
		if (op.equals("add")) {

			// 添加ios、android、wechat logo图片
			addFileData(iosFileData, "ios", goodsTypeLogo, dictPath);
			addFileData(androidFileData, "android", goodsTypeLogo, dictPath);
			addFileData(wechatFileData, "wechat", goodsTypeLogo, dictPath);

			goodsTypeLogo.setCreateDate(DateUtil.getSystemTime());
			goodsTypeLogo.setUserId(sysUser.getId());
			goodsTypeLogoDao.add(goodsTypeLogo);
		} else {

			GoodsTypeLogo goodsTypeLogo2 = goodsTypeLogoDao.get(goodsTypeLogo
					.getId());
			goodsTypeLogo2.setLogoName(goodsTypeLogo.getLogoName());
			goodsTypeLogo2.setMemo(goodsTypeLogo.getMemo());
			goodsTypeLogo2.setEndDate(goodsTypeLogo.getEndDate());
			goodsTypeLogo2.setIsDefault(goodsTypeLogo.getIsDefault());
			goodsTypeLogo2.setStartDate(goodsTypeLogo.getStartDate());
			goodsTypeLogo2.setUserId(sysUser.getId());
			goodsTypeLogo2.setStatus(goodsTypeLogo.getStatus());

			// 添加ios、android、wechat logo图片
			addFileData(iosFileData, "ios", goodsTypeLogo2, dictPath);
			addFileData(androidFileData, "android", goodsTypeLogo2, dictPath);
			addFileData(wechatFileData, "wechat", goodsTypeLogo2, dictPath);
			goodsTypeLogoDao.update(goodsTypeLogo2);
		}
	}

	/**
	 * 添加商品图片信息到列表中
	 * 
	 * @param fileData
	 *            图片信息
	 * @param fileType
	 *            图片类型
	 * @param goodsTypeLogo
	 *            商品信息
	 * @param dictPath
	 *            字典存储路径
	 */
	private void addFileData(String fileData, String fileType,
			GoodsTypeLogo goodsTypeLogo, String dictPath) {

		if (null != fileData && !"".equals(fileData)) {
			Map<String, Object> map = JSONUtil.getMap(fileData);
			if (map.get("id") == null) {
				try {
					// 存储路径
					String savePath = Base64Util.decodeBytesInAndroid(map
							.get("savePath") + "");
					String saveName = map.get("saveName") + "";

					if ("ios".equals(fileType)) {// ios
						goodsTypeLogo.setIosFileName(saveName);
					} else if ("android".equals(fileType)) {
						goodsTypeLogo.setAndroidFileName(saveName);
					} else {
						goodsTypeLogo.setWechatFileName(saveName);
					}

					String relative_path = goodsTypeLogo.getRelativePath();
					String root_path = goodsTypeLogo.getRootPath();

					if (null == relative_path || "".equals(relative_path)) {
						// 日期作为相对路径
						SimpleDateFormat formatdate = new SimpleDateFormat(
								"yyyy/MM/dd/");
						relative_path = formatdate.format(new Date());
						goodsTypeLogo.setRelativePath(relative_path);
					}

					if (null == root_path || "".equals(root_path)) {
						root_path = dictPath;
						goodsTypeLogo.setRootPath(root_path);
					}

					FileUtils.copyFile(new File(savePath), new File(dictPath
							+ relative_path + saveName));
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(e.getMessage(), e);
				}
			}
		} else {
			if ("android".equals(fileType)) {
				goodsTypeLogo.setAndroidFileName(null);
			} else if ("ios".equals(fileType)) {
				goodsTypeLogo.setIosFileName(null);
			} else if ("wechat".equals(fileType)) {
				goodsTypeLogo.setWechatFileName(null);
			}
		}
	}

	public FileDownMode getFileInfo(HttpServletRequest request, String id) {

		String type = request.getParameter("type");
		FileDownMode fileDownMode = new FileDownMode();
		if (id != null && !id.equals("")) {

			GoodsTypeLogo goodsTypeLogo = goodsTypeLogoDao
					.get(Long.valueOf(id));
			String fileName = "";
			if ("ios".equals(type)) {
				fileName = goodsTypeLogo.getIosFileName();
			} else if ("android".equals(type)) {
				fileName = goodsTypeLogo.getAndroidFileName();
			} else {
				fileName = goodsTypeLogo.getWechatFileName();
			}
			fileDownMode.setFilePath(goodsTypeLogo.getRootPath()
					+ goodsTypeLogo.getRelativePath() + fileName);
		}
		return fileDownMode;
	}

}