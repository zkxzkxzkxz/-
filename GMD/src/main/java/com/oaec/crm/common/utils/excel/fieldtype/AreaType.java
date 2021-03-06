/**
 * kaven.wei 2017
 */
package com.oaec.crm.common.utils.excel.fieldtype;

import com.oaec.crm.common.utils.StringUtils;
import com.oaec.crm.modules.sys.entity.Area;
import com.oaec.crm.modules.sys.utils.UserUtils;

/**
 * 字段类型转换
 * @author kaven.wei
 * @version 2017
 */
public class AreaType {

	/**
	 * 获取对象值（导入）
	 */
	public static Object getValue(String val) {
		for (Area e : UserUtils.getAreaList()){
			if (StringUtils.trimToEmpty(val).equals(e.getName())){
				return e;
			}
		}
		return null;
	}

	/**
	 * 获取对象值（导出）
	 */
	public static String setValue(Object val) {
		if (val != null && ((Area)val).getName() != null){
			return ((Area)val).getName();
		}
		return "";
	}
}
