/**   
* @Title: EBD_EBDResponse.java 
* @Package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2019年1月2日 下午2:41:16 
*/
package com.payudon.common.xml.response;

import com.payudon.common.xml.base.BaseEBD;
import com.payudon.common.xml.base.EBD;
import lombok.EqualsAndHashCode;

/** 
* @ClassName: EBD_EBDResponse 
* @Description: TODO(接收回执文件)
* @author peiyongdong
* @date 2019年1月2日 下午2:41:16 
*  
*/
@lombok.Data
public class EBD_EBDResponse implements EBD {

	private EBD EBD;

	/*
	 * 0：收到数据未处理
	 * 1：接收解析及数据校验成功
	 * 2：接收解析失败
	 * 3：数据内容缺失
	 * 4：签名验证失败
	 * 5：其他错误
	 */
	public static final String UNPROCESSED = "0";
	public static final String SUCCESS = "1";
	public static final String PARS_FAILED = "2";
	public static final String MISSING_DATA = "3";
	public static final String SIGN_VERIF_FAILED = "4";
	public static final String OTHER_ERROR = "5";
	@lombok.Data
	@EqualsAndHashCode(callSuper=false)
	public static class EBD extends BaseEBD {
		private EBDResponse EBDResponse;
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
	public static class EBDResponse {
		private String ResultCode;
		private String ResultDesc;
	}

	/** 
	 * <p>Title: creatResponse</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @throws
	 * @author peiyongdong
	 * @date 2019年1月2日 下午2:42:22
	 */
	@Override
	public EBD_EBDResponse creatResponse() {
		return null;
	}

	public EBD_EBDResponse() {
		EBD = new EBD();
		EBD.setEBDHeader();
		EBD.setEBDType("EBDResponse");
		EBD.EBDResponse = new EBDResponse();
		EBD.EBDResponse.ResultCode = "1";
		EBD.EBDResponse.ResultDesc = "已接收完成";
	}
}
