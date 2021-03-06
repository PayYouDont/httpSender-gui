/**   
* @Title: EBD_EBRPSState.java 
* @Package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2018年12月13日 下午6:01:44 
*/
package com.payudon.common.xml.state;


import com.payudon.common.xml.base.BaseEBD;
import com.payudon.common.xml.base.EBDResponse;
import lombok.EqualsAndHashCode;

/**
 * @ClassName: EBD_EBRPSState
 * @Description: TODO(应急广播平台状态文件)
 * @author peiyongdong
 * @date 2018年12月13日 下午6:01:44
 * 
 */
@lombok.Data
public class EBD_EBRPSState implements EBDResponse {

	private EBD EBD;

	@lombok.Data
	@EqualsAndHashCode(callSuper=false)
	public static class EBD extends BaseEBD {
		private EBRPSState EBRPSState;
	}

	@lombok.Data
	public static class SRC {
		private String EBRID;
	}

	@lombok.Data
	public static class DEST {
		private String EBRID;
	}
	
	@lombok.Data
	public static class EBRPSState {
		private EBRPS EBRPS;
	}

	@lombok.Data
	public static class EBRPS {
		private String RptTime;
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
	 * <p>Title: creatResponseXML</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @throws
	 * @author peiyongdong
	 * @date 2018年12月17日 上午9:36:46
	 */
	@Override
	public EBD_EBRPSState creatResponse() {
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
