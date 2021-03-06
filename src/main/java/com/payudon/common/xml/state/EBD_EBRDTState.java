/**   
* @Title: EBD_EBRDTState.java 
* @Package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2019年1月3日 上午10:52:45 
*/
package com.payudon.common.xml.state;


import com.payudon.common.xml.base.BaseEBD;
import com.payudon.common.xml.base.EBDResponse;
import com.payudon.common.xml.response.EBD_EBDResponse;

/**
* @ClassName: EBD_EBRDTState 
* @Description: TODO(平台设备及终端状态)
* @author peiyongdong
* @date 2019年1月3日 上午10:52:45 
*  
*/
@lombok.Data
public class EBD_EBRDTState implements EBDResponse {

	private EBD EBD;

	@lombok.Data
	@lombok.EqualsAndHashCode(callSuper=false)
	public static class EBD extends BaseEBD {
		private EBRDTState EBRDTState;
	}

	@lombok.Data
	public static class EBRDTState {
		private EBRDT EBRDT;
	}

	@lombok.Data
	public static class EBRDT {
		private String RptTime;
		private String EBRID;
		/*
		 * 1：开机/运行正常
		 * 2：关机/停止运行
		 * 3：故障
		 * 4：故障恢复
		 * 5：播发中
		 */
		private String StateCode;
		private String StateDesc;
	}

	/** 
	 * <p>Title: creatResponse</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @throws
	 * @author peiyongdong
	 * @date 2019年1月3日 上午10:54:42
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
