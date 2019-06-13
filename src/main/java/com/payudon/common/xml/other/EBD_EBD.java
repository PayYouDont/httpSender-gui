/**   
* @Title: EBD_EBD.java 
* @Package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2018年12月13日 下午5:31:14 
*/
package com.payudon.common.xml.other;

import com.payudon.common.xml.base.BaseEBD;
import com.payudon.common.xml.base.EBD;
import lombok.EqualsAndHashCode;

/** 
* @ClassName: EBD_EBD 
* @Description: TODO(应急广播)
* @author peiyongdong
* @date 2018年12月13日 下午5:31:14 
*  
*/
@lombok.Data
public class EBD_EBD implements EBD {

	private EBD EBD;

	@lombok.Data
	@EqualsAndHashCode(callSuper=false)
	public static class EBD extends BaseEBD {
		private RelatedEBD RelatedEBD;
		private EBM EBM;
	}
	@lombok.Data
	public static class RelatedEBD {
		private String EBDID;
	}

	@lombok.Data
	public static class EBM {
		private String EBMVersion;
		private String EBMID;
		private MsgBasicInfo MsgBasicInfo;
		private MsgContent MsgContent;
	}

	@lombok.Data
	public static class MsgBasicInfo {
		private String MsgType;
		private String SenderName;
		private String SenderCode;
		private String SendTime;
		private String EventType;
		private String Severity;
		private String StartTime;
		private String EndTime;
	}

	@lombok.Data
	public static class MsgContent {
		private String LanguageCode;
		private String MsgTitle;
		private String MsgDesc;
		private String AreaCode;
		private Auxiliary Auxiliary;
		private String ProgramNum;
	}

	@lombok.Data
	public static class Auxiliary {
		private String AuxiliaryType;
		private String AuxiliaryDesc;
		private String Size;
	}

	/** 
	 * <p>Title: creatResponseXML</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @throws
	 * @author peiyongdong
	 * @date 2018年12月17日 上午9:34:44
	 */
	@Override
	public EBD_EBD creatResponse() {
		
		return null;
	}
}
