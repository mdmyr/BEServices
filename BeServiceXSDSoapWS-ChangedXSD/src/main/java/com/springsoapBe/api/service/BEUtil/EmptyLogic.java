package com.springsoapBe.api.service.BEUtil;

import java.util.Map;

import com.informatica.mdm.cs.CallContext;
import com.informatica.mdm.spi.cs.StepException;
import com.informatica.mdm.spi.externalcall.CustomLogic;

import commonj.sdo.DataObject;
import commonj.sdo.helper.HelperContext;

public class EmptyLogic implements CustomLogic {

	 //CallContext callcontext = new CallContext(null, null, null);
	
	 public static final DataObject OBJECT = null;
	@Override
	public DataObject process(HelperContext arg0, DataObject arg1, Map<String, Object> arg2, Map<String, Object> arg3)
			throws StepException {
		// TODO Auto-generated method stub
		return OBJECT;  
	}

}
