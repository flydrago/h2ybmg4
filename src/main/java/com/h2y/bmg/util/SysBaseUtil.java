package com.h2y.bmg.util;

import com.h2y.util.PropertiesUtil;

public class SysBaseUtil {

	/**
	 * mencached 缓存有效时间（秒）
	 */
	public final static long EXPIRY_TIME = 1800;

	public static String CMBS_URL = "";	

	public static String XGH_URL = "";

	public static String FP_URL = "";
	//小达快报
	public static String XDKB_URL = "";
	
	//投票活动
	public static String ACTIVITY_URL = "";
	
	public static String QRCODE_URL = "";

	static {

		PropertiesUtil mPropertiesUtil = PropertiesUtil.getInstance("/confing.properties");

		if(mPropertiesUtil.getValueByKey("cmbs.url") != null){
			CMBS_URL = mPropertiesUtil.getValueByKey("cmbs.url");
		}

		if(mPropertiesUtil.getValueByKey("xgh.url") != null){
			XGH_URL = mPropertiesUtil.getValueByKey("xgh.url");
		}

		if(mPropertiesUtil.getValueByKey("fp.url") != null){
			FP_URL = mPropertiesUtil.getValueByKey("fp.url");
		}

		if(mPropertiesUtil.getValueByKey("xdkb.url") != null){
			XDKB_URL = mPropertiesUtil.getValueByKey("xdkb.url");
		}
		
		if(mPropertiesUtil.getValueByKey("activity.url") != null){
			ACTIVITY_URL = mPropertiesUtil.getValueByKey("activity.url");
		}
		
		if(mPropertiesUtil.getValueByKey("qrCode.url") != null){
			QRCODE_URL = mPropertiesUtil.getValueByKey("qrCode.url");
		}
	}


	public static void main(String[] args) {


	}



	/**
	 * 侯飞龙
	 * 混合选择窗口，列表健
	 */
	public enum MixSelectListKey{

		/**
		 * 人员列表健
		 */
		pepleKey("p_"),

		/**
		 * 部门列表健
		 */
		deptKey("d_");

		public String key;
		private MixSelectListKey(String result){
			this.key=result;
		}
	}


	/**
	 * 权限类型静态变量
	 */
	public class PrivilegeKey{

		/**
		 * 单位
		 */
		public final static String UNIT = "UNIT";


		/**
		 * 用户
		 */
		public final static String USER = "USER";

		/**
		 * 角色
		 */
		public final static String ROLE = "ROLE";

		/**
		 * 菜单
		 */
		public final static String MENU = "MENU";


		/**
		 * 按钮
		 */
		public final static String BUTTON = "BUTTON";
	}

	/**
	 * mencached健前缀
	 */
	public class MemcachedKeyPrefix{

		/**
		 * 用户Id
		 */
		public final static String USER_ID = "H2Y_USER_ID";


		/**
		 * 单位Id
		 */
		public final static String UNIT_ID = "H2Y_UNIT_ID";

		/**
		 * 系统角色Id
		 */
		public final static String SYS_ROLE_ID = "H2Y_SYS_ROLE_ID";


		/**
		 * 用户对象
		 */
		public final static String USER = "H2Y_USER";

		/**
		 * 单位对象
		 */
		public final static String UNIT = "H2Y_UNIT";
	}


	/**
	 * 系统维护关联类型
	 */
	public class SysJoinType{


		/**
		 * 菜单
		 */
		public final static String MENU = "menu";


		/**
		 * 表格列
		 */
		public final static String GRID = "grid";

		/**
		 * 验证
		 */
		public final static String VALIDATE = "validate";


		/**
		 * 查询
		 */
		public final static String QUERY = "query";
	}



	public class OsPrex{
		public final static String QUERYBAG="bag/queryBag.htm";

		public final static String BAGDETAIL="bag/bagDetail.htm";

		public final static String BAGSUBMIT="spirit/takeGoods.htm";

	}

	public class BagSp{
		//未审核
		public final static String WSH="0";

		//财务审核通过
		public final static String CW_SH_PASS="1";

		//财务审核不通过
		public final static String CW_SH_UNPASS="2";

		//总经理审核通过
		public final static String ZJL_SH_PASS="3";

		//总经理审核不通过
		public final static String ZJL_SH_UNPASS="4";
	}


	/**
	 * 字典前缀 
	 */
	public class DictPrefix{

		/**
		 * 主表
		 */
		public final static String DIC_MAIN = "DIC_MAIN";


		/**
		 * 详细表
		 */
		public final static String DIC_DETAIL = "DIC_DETAIL";
	}


	public enum DictClumn{
		//id
		id,
		//主表Id
		dictMainId,
		//编码
		code,
		//对应值
		value,
		//备注信息
		memo,
		//排序字段
		ord;
	}

	public enum DictOrderBy{
		//降序
		desc,
		//升序
		asc
	}


	/**
	 * 操作类型
	 */
	public enum OpType{

		/**
		 * 登陆
		 */
		login,

		/**
		 * 退出
		 */
		loginOut,

		/**
		 * 添加
		 */
		add,

		/**
		 * 删除
		 */
		delete,

		/**
		 * 修改
		 */
		modify,

		/**
		 * 查询
		 */
		search;
	}

	/**
	 * 操作结果
	 */
	public enum OpRresult{

		/**
		 * 操作成功
		 */
		success,

		/**
		 * 操作失败
		 */
		fail;
	}



	/**
	 * 侯飞龙
	 * 混合选择窗口，列表健
	 */
	public enum BusinessTableName{

		/**
		 * 角色表
		 */
		SysRole("tb_sys_role"),

		/**
		 * 部门表
		 */
		SysDepartment("tb_sys_department"),

		/**
		 * 用户部门用户关联表
		 */
		SysDeptUser("tb_sys_dept_user"),

		/**
		 * 用户表
		 */
		SysUser("tb_sys_user"),

		/**
		 * 权限表
		 */
		SysPrivilegeList("tb_sys_privilege_list"),


		/**
		 * 会员
		 */
		MemberUser("tb_member_user"),


		/**
		 * 广告
		 */
		advert("tb_advert"),

		/**
		 * 广告栏
		 */
		advertColumn("tb_advert_column"),

		/**
		 * 广告栏单位关联
		 */
		advertColumnUnitRt("tb_advert_column_unit_rt"),

		/**
		 * 广告栏位主题关联
		 */
		advertColumnSubjectRt("tb_advert_column_subject_rt"),


		/**
		 * 广告主题商品关联
		 */
		advertSubjectGoodsRt("tb_advert_subject_goods_rt"),

		/**
		 * 广告主题
		 */
		advertSubject("tb_advert_subject"),
		
		/**
		 * 广告主题信息关联（优惠劵、促销活动）
		 */
		advertSubjectInfoRt("tb_advert_subject_info_rt"),

		/**
		 * 广告主题
		 */
		findActivity("tb_find_activity"),


		/**
		 * 微信活动
		 */
		wechatActivity("tb_wechat_activity"),


		/**
		 * 一般活动
		 */
		commonActivity("tb_common_activity"),

		/**
		 * 促销活动主题
		 */
		commonSubject("tb_common_subject"),


		/**
		 * 促销活动主题关联
		 */
		commonActivitySubjectRt("tb_common_activity_subject_rt"),


		/**
		 * 一般活动商品关联
		 */
		commonActivityGoodsRt("tb_common_activity_goods_rt"),

		/**
		 * 商品类型
		 */
		goodsType("tb_goods_type"),

		/**
		 * 商品类型Logo
		 */
		goodsTypeLogo("tb_goods_type_logo"),

		/**
		 * 商品标签
		 */
		goodsMark("tb_goods_mark"),

		/**
		 * 商品
		 */
		goods("tb_goods"),

		/**
		 * 商品定价
		 */
		goodsPrice("tb_goods_price"),

		/**
		 * 商品标签详细
		 */
		goodsMarkInfo("tb_goods_mark_info"),

		/**
		 * 仓库
		 */
		storehouse("tb_storehouse"),

		/**
		 * 仓库商品
		 */
		storehouseGoodsInfo("tb_storehouse_goods_info"),

		/**
		 * 仓库商品历史
		 */
		storehouseGoodsDetail("tb_storehouse_goods_detail"),

		/**
		 * 区域表
		 */
		Zone("tb_zone"),

		/**
		 * 发现服务
		 */
		findService("tb_find_service"),

		/**
		 * 发现服务单位关联
		 */
		findServiceUnit("tb_find_service_unit"),

		/**
		 * 会员中心
		 */
		memberUser("tb_member_user"),


		/**
		 * 图片维护
		 */
		commonImage("tb_common_image"),


		xgh("xgh"),

		/**
		 * 单位经营分类
		 */
		unitSort("tb_unit_sort"),

		/**
		 * 生活+筛选条件
		 */
		shoplifeCondition("tb_shoplife_condition"),
		
		/**
		 * 优惠券
		 */
		coupons("tb_coupons"),
		
		/**
		 * 优惠券来源
		 */
		couponsSource("tb_coupons_source"),
		
		/**
		 * 优惠劵来源关联
		 */
		couponsSourceRt("tb_coupons_source_rt"),
		
		/**
		 * 推广活动
		 */
		promoteActivity("tb_promote_activity"),
		
		/**
		 * 推广活动奖励
		 */
		promoteActivityRewardRt("tb_promote_activity_reward_rt"),
		
		/**
		 * 推广活动明细
		 */
		promoteActivityDetail("tb_promote_activity_detail"),
		
		/**
		 * 分享链接
		 */
		shareHref("tb_share_href"),
		
		/**
		 * 分享 推广关联
		 */
		shareHrefDataRt("tb_share_href_data_rt"),
		
		/**
		 * 投票主题
		 */
		voteSubject("tb_vote_subject"),
		
		/**
		 * 投票候选人
		 */
		candidate("tb_vote_candidate");



		public String name;
		private BusinessTableName(String name){
			this.name=name;
		}
	}



	/**
	 * 侯飞龙
	 * 混合选择窗口，列表健
	 */
	public enum SmsContentTemplate{

		/**
		 * 后台用户注册密码短信模板
		 */
		bmgUserRegisterPwd("bmgUserRegisterPwd","【H2Y】账号注册成功，密码为：${pwd}，请注意保存密码，并且打死都不说出来！"),

		/**
		 * 后台用户重置密码短信模板
		 */
		bmgUserResetPwd("bmgUserResetPwd","【H2Y】密码重置成功，密码为：${pwd}，请注意保存密码，并且打死都不说出来！");

		public final String code;
		public final String value;
		private SmsContentTemplate(String code,String value){

			this.code=code;
			this.value=value;
		}
	}


	/**
	 * 广告对象数组
	 */
	public enum AdvertObj{


		/**
		 * android客户端
		 */
		Android("android",1),

		/**
		 * Ios客户端
		 */
		IOS("ios",2),

		/**
		 * 微信客户端
		 */
		WeChat("wechat",4);

		public final String name;//字典code
		public final int pow;//次方
		private AdvertObj(String name,int pow){
			this.name = name;
			this.pow = pow;
		}
	}


	/**
	 * 类描述：微信活动类型   
	 * 作者：侯飞龙
	 * 时间：2014年12月17日下午6:03:49
	 * 邮件：1162040314@qq.com
	 */
	public enum WechatActivityType{

		/**
		 * 大转盘
		 */
		wheel("wheel"),

		/**
		 * 刮刮卡
		 */
		card("card"),

		/**
		 * 砸金蛋
		 */
		egg("egg");

		public final String name;
		private WechatActivityType(String name){
			this.name = name;
		}
	}


	/**
	 * 类描述：导入礼包任务  
	 * 作者：侯飞龙
	 * 时间：2014年12月17日下午6:03:49
	 * 邮件：1162040314@qq.com
	 */
	public enum ImportBagTask{

		/**
		 * 发起审核
		 */
		start("start","发起审核"),

		/**
		 * 一级审核
		 */
		check1("check1","一级审核"),

		/**
		 * 二级审核
		 */
		check2("check2","二级审核"),

		/**
		 * 审核通过
		 */
		end("end","审核通过");

		public final String code;
		public final String name;
		private ImportBagTask(String code,String name){
			this.code = code;
			this.name = name;
		}
	}
}
