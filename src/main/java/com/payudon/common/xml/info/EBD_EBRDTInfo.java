/**   
* @Title: EBD_EBRDTInfo.java 
* @Package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2019年1月3日 上午10:35:02 
*/
package com.payudon.common.xml.info;

import com.payudon.common.xml.base.BaseEBD;
import com.payudon.common.xml.base.EBDResponse;
import com.payudon.common.xml.response.EBD_EBDResponse;

/** 
* @ClassName: EBD_EBRDTInfo 
* @Description: TODO(平台设备及终端信息)
* @author peiyongdong
* @date 2019年1月3日 上午10:35:02 
*  
*/
@lombok.Data
public class EBD_EBRDTInfo implements EBDResponse {
	
	private EBD EBD;

	@lombok.Data
	@lombok.EqualsAndHashCode(callSuper=false)
	public static class EBD extends BaseEBD {
		private EBRDTInfo EBRDTInfo;
	}

	@lombok.Data
	public static class EBRDTInfo {
		private EBRDT EBRDT;
	}

	@lombok.Data
	public static class EBRDT {
		private String RptTime;
		private String RptType;
		private RelatedEBRPS RelatedEBRPS;
		private String EBRID;
		private String EBRName;
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
	 * @date 2019年1月3日 上午10:36:35
	 */
	@Override
	public EBD_EBDResponse creatResponse() {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
    public EBDResponse createFullResponse() {
        return null;
    }

    @Override
    public EBDResponse createIncrementalResponse() {
        return null;
    }
}
