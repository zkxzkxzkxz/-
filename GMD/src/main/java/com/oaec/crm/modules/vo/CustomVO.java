package com.oaec.crm.modules.vo;

import java.util.ArrayList;
import java.util.List;

import com.oaec.crm.modules.crm.entity.CrmCustom;

public class CustomVO extends CrmCustom {
	 List<CrmCustom> customs;

	public List<CrmCustom> getCustoms() {
		return customs;
	}

	public void setCustoms(List<CrmCustom> customs) {
		this.customs = customs;
	}
}
