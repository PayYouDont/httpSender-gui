/**   
* @Title: EBD_ConnectionCheck.java 
* @Package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2018年12月13日 下午4:47:41 
*/
package com.payudon.common.xml.other;

import com.payudon.common.xml.base.BaseEBD;
import com.payudon.common.xml.base.EBD;
import com.payudon.common.xml.response.EBD_EBDResponse;
import com.payudon.util.DateUtils;
import com.payudon.util.EBDcodeUtil;
import lombok.EqualsAndHashCode;

/** 
* @ClassName: EBD_ConnectionCheck 
* @Description: TODO(心跳检测) 
* @author peiyongdong
* @date 2018年12月13日 下午4:47:41 
*  
*/
@lombok.Data
public class EBD_ConnectionCheck implements EBD {

	private EBD EBD;

	@lombok.Data
	@EqualsAndHashCode(callSuper=false)
	public static class EBD extends BaseEBD {
		private ConnectionCheck ConnectionCheck;
	}
	@lombok.Data
	public static class ConnectionCheck {
		private String RptTime;
	}
	public void init() {
		EBD = new EBD();
		EBD.setEBDHeader();
		EBD.setEBDID (EBDcodeUtil.getEBDID (this));
		EBD.setEBDType("ConnectionCheck");
		EBD.ConnectionCheck = new ConnectionCheck();
		EBD.ConnectionCheck.RptTime = DateUtils.getDateTime ();
	}
	/** 
	 * <p>Title: creatResponseXML</p> 
	 * <p>Description: </p> 
	 * @return
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年12月17日 上午9:19:53
	 */
	@Override
	public EBD_EBDResponse creatResponse() {
		return null;
	}
}
