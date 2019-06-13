/**   
* @Title: EBD_EBRSTInfo.java 
* @Package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2019年1月3日 上午10:42:14 
*/
package com.payudon.common.xml.info;

import com.payudon.common.xml.base.BaseEBD;
import com.payudon.common.xml.base.EBD;
import com.payudon.common.xml.base.EBDResponse;

/** 
* @ClassName: EBD_EBRSTInfo 
* @Description: TODO(台站（前端）信息)
* @author peiyongdong
* @date 2019年1月3日 上午10:42:14 
*  
*/
@lombok.Data
public class EBD_EBRSTInfo implements EBDResponse {

	private EBD EBD;

	@lombok.Data
	@lombok.EqualsAndHashCode(callSuper=false)
	public static class EBD extends BaseEBD {
		private EBRSTInfo EBRSTInfo;
	}

	@lombok.Data
	public static class EBRSTInfo {
		private Params Params;
		private EBRST EBRST;
	}

	@lombok.Data
	public static class Params {
		private String RptStartTime;
		private String RptEndTime;
		private String RptType;
	}

	@lombok.Data
	public static class EBRST {
		private String RptTime;
		private String RptType;
		private RelatedEBRPS RelatedEBRPS;
		private String EBRID;
		private String EBRName;
		private String Address;
		private String Contact;
		private String PhoneNumber;
		private String Longitude;
		private String Latitude;
	}

	@lombok.Data
	public static class RelatedEBRPS {
		private String EBRID;
	}

	/** 
	 * <p>Title: creatResponse</p> 
	 * <p>Description: </p> 
	 * @return
	 * @throws 
	 * @author peiyongdong
	 * @date 2019年1月3日 上午10:43:07
	 */
	@Override
	public com.payudon.common.xml.base.EBD creatResponse() {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
    public EBDResponse createFullResponse() {

        return this;
    }

    @Override
    public EBDResponse createIncrementalResponse() {
        setBaseResponse();
        EBD.EBRSTInfo.Params.RptType = "Incremental";
        EBD.EBRSTInfo.EBRST = new EBRST ();
        return this;
    }
    private void setBaseResponse(){

    }
}
