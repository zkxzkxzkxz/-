/**
 * kaven.wei 2017
 */
package com.oaec.crm.common.utils.excel.fieldtype;

import com.oaec.crm.common.utils.StringUtils;
import com.oaec.crm.modules.sys.entity.Office;
import com.oaec.crm.modules.sys.utils.UserUtils;

/**
 * 字段类型转换
 * @author kaven.wei
 * @version 2017
 */
public class OfficeType {

	/**
	 * 获取对象值（导入）
	 */
	public static Object getValue(String val) {
		for (Office e : UserUtils.getOfficeList()){
			if (StringUtils.trimToEmpty(val).equals(e.getName())){
				return e;
			}
		}
		return null;
	}

	/**
	 * 设置对象值（导出）
	 */
	public static String setValue(Object val) {
		if (val != null && ((Office)val).getName() != null){
			return ((Office)val).getName();
		}
		return "";
	}
}
