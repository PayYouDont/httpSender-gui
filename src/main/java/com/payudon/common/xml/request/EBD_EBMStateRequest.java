/**   
* @Title: EBD_EBMStateRequest.java 
* @Package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2018年12月13日 下午5:58:26 
*/
package com.payudon.common.xml.request;

import com.payudon.common.xml.base.BaseEBD;
import com.payudon.common.xml.response.EBD_EBMStateResponse;

/**
 * @ClassName: EBD_EBMStateRequest
 * @Description: TODO(播发状态请求,应急广播消息播发状态查询文件)
 * @author peiyongdong
 * @date 2018年12月13日 下午5:58:26
 * 
 */
@lombok.Data
public class EBD_EBMStateRequest implements com.payudon.common.xml.base.EBD {

	private EBD EBD;

	@lombok.Data
	@lombok.EqualsAndHashCode(callSuper=false)
	public static class EBD extends BaseEBD {
		private EBMStateRequest EBMStateRequest;
	}

	@lombok.Data
	public static class EBMStateRequest {
		private EBM EBM;
	}

	@lombok.Data
	public static class EBM {
		private String EBMID;
	}
	@Override
	public EBD_EBMStateResponse creatResponse() {
		return null;
	}

}
