/**   
* @Title: OMDRequest.java 
* @Package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2019年1月3日 上午10:22:28 
*/
package com.payudon.common.xml.request;


import com.payudon.common.xml.base.BaseEBD;
import com.payudon.common.xml.base.EBD;
import com.payudon.common.xml.base.EBDResponse;
import com.payudon.common.xml.info.*;
import com.payudon.common.xml.state.EBD_EBRASState;
import com.payudon.common.xml.state.EBD_EBRBSState;
import com.payudon.common.xml.state.EBD_EBRDTState;
import com.payudon.common.xml.state.EBD_EBRPSState;
import com.payudon.util.DateUtils;

/**
* @ClassName: OMDRequest 
* @Description: TODO(运维数据请求文件)
* @author peiyongdong
* @date 2019年1月3日 上午10:22:28 
*  
*/
@lombok.Data
public class EBD_OMDRequest implements EBD {

	public static final String RptType_Full = "Full";
	public static final String RptType_Incremental = "Incremental";
	private EBD EBD;

	@lombok.Data
	@lombok.EqualsAndHashCode(callSuper=false)
	public static class EBD  extends BaseEBD {
		private OMDRequest OMDRequest;
	}
	@lombok.Data
	public static class OMDRequest {
		private String OMDType;
		private Params Params;
	}

	@lombok.Data
	public static class Params {
		private String RptStartTime;
		private String RptEndTime;
		private String RptType;
	}

	/** 
	 * <p>Title: creatResponse</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @throws
	 * @author peiyongdong
	 * @date 2019年1月3日 上午10:23:43
	 */
	@Override
	public com.payudon.common.xml.base.EBD creatResponse() {
		Class clazz = getClassByOMDType ();
		if(clazz.getSuperclass ()== EBDResponse.class){
		    try{
                EBDResponse ebd = (EBDResponse)clazz.newInstance ();
                if("Full".equals (this.EBD.OMDRequest.Params.RptType)){
                    return ebd.createFullResponse ();
                }else{
                    return ebd.createIncrementalResponse ();
                }
            }catch (Exception e){
		        e.printStackTrace ();
            }
        }
		return null;
	}
	public Class getClassByOMDType(){
	    switch (this.EBD.OMDRequest.OMDType){
            case "EBRPSInfo":
                //应急广播平台信息
                return EBD_EBRPSInfo.class;
            case "EBRSTInfo":
                //台站（前端）信息
                return EBD_EBRSTInfo.class;
            case "EBRASInfo":
                //应急广播适配器信息
                return EBD_EBRASInfo.class;
            case "EBRBSInfo":
                //传输覆盖播出设备信息
                return EBD_EBRBSInfo.class;
            case "EBRDTInfo":
                //平台设备及终端信息；
                return EBD_EBRDTInfo.class;
            case "EBRPSState":
                //应急广播平台状态文件
                return EBD_EBRPSState.class;
            case "EBRASState":
                //应急广播适配器状态
                return EBD_EBRASState.class;
            case "EBRBSState":
                //：传输覆盖播出设备状态
                return EBD_EBRBSState.class;
            case "EBRDTState":
                //平台设备及终端状态
                return EBD_EBRDTState.class;
        }
	    return null;
    }
	public EBD_OMDRequest() {
		super();
	}
	
	public EBD_OMDRequest(String OMDType,String RptType) {
		EBD = new EBD();
		EBD.setEBDHeader();
		EBD.setEBDType("OMDRequest");
		EBD.OMDRequest = new OMDRequest();
		EBD.OMDRequest.OMDType = OMDType;
		EBD.OMDRequest.Params = new Params();
		EBD.OMDRequest.Params.RptStartTime = DateUtils.getDateTime();
		EBD.OMDRequest.Params.RptEndTime = DateUtils.getDateTime();
		EBD.OMDRequest.Params.RptType = RptType;
	}
}
