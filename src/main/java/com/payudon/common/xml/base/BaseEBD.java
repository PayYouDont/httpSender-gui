/**   
* @Title: BaseEBD.java 
* @Package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2018年12月17日 上午11:18:04 
*/
package com.payudon.common.xml.base;

import com.payudon.common.config.ApplicationContextRegister;
import com.payudon.common.config.ServerProperties;
import com.payudon.util.DateUtils;
import com.payudon.util.EBDcodeUtil;
import lombok.Data;

/** 
* @ClassName: BaseEBD 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2018年12月17日 上午11:18:04 
*  
*/
@Data
public class BaseEBD{
	private String EBDVersion;
	private String EBDID;
	private String EBDType;
	private SRC SRC;
	private DEST DEST;
	private String EBDTime;
	@Data
	public static class SRC {
		private String EBRID;
		private String URL;
	}

	@Data
	public static class DEST {
		private String EBRID;
	}
	public void setEBDHeader() {
		ServerProperties prop = ApplicationContextRegister.getBean(ServerProperties.class);
		EBDVersion = prop.getServer_version();
		EBDID = EBDcodeUtil.getBaseEBDID();
		SRC = new SRC();
		SRC.setEBRID(prop.getSRC_EBRID());
		DEST = new DEST();
		DEST.setEBRID(prop.getDEST_EBRID());
		EBDTime = DateUtils.getDateTime();
	}
}
