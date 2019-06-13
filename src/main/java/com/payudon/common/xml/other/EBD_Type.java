/**   
* @Title: EBD_Type.java 
* @Package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2018年12月14日 下午5:08:04 
*/
package com.payudon.common.xml.other;

import com.payudon.common.xml.base.EBD;
import com.payudon.common.xml.info.*;
import com.payudon.common.xml.request.EBD_EBMStateRequest;
import com.payudon.common.xml.request.EBD_OMDRequest;
import com.payudon.common.xml.response.EBD_EBDResponse;
import com.payudon.common.xml.response.EBD_EBMStateResponse;
import com.payudon.common.xml.state.EBD_EBRASState;
import com.payudon.common.xml.state.EBD_EBRBSState;
import com.payudon.common.xml.state.EBD_EBRDTState;
import com.payudon.common.xml.state.EBD_EBRPSState;

/**
* @ClassName: EBD_Type 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2018年12月14日 下午5:08:04 
*  
*/
public class EBD_Type {
	/*
	 * 1：开机/运行正常
	 * 2：关机/停止运行
	 * 3：故障
	 * 4：故障恢复
	 * 5：播发中
	 */
	public static final String State_Normal = "1";
	public static final String State_Stop = "2";
	public static final String State_Error = "3";
	public static final String State_Recovery = "4";
	public static final String State_Running = "5";
    public static String[] xmlNames ={"自选资源","应急广播测试","播发记录查询","播发状态查询","平台信息查询","平台状态查询","台站信息查询","适配器信息查询","适配器状态查询","传输覆盖设备查询","设备/终端信息查询","设备/终端状态查询"};
    public static String[] EBDS ={null,"EBM","EBMBrdLog","EBMStateRequest","EBRPSInfo","EBRPSState","EBRSTInfo","EBRASInfo","EBRASState","EBRBSInfo","EBRDTInfo","EBRDTState"};
	public static Class<? extends EBD> getClassByEBDType(String EBDType) {
		switch (EBDType) {
		case "ConnectionCheck":
			//心跳
			return  EBD_ConnectionCheck.class;
		case "EBM":
			//应急广播
			return  EBD_EBD.class;
		case "EBMBrdLog":
			//播发记录
			return EBD_EBMBrdLog.class;
		case "EBMStateRequest":
			//应急广播消息播发状态查询文件
			return  EBD_EBMStateRequest.class;
		case "EBMStateResponse":
			//应急广播消息播发状态反馈文件
			return  EBD_EBMStateResponse.class;
		case "OMDRequest":
			//运维数据请求文件
			return EBD_OMDRequest.class;
		case "EBDResponse":
			//接收回执文件
			return  EBD_EBDResponse.class;
		case "EBRPSInfo":
			//应急广播平台信息
			return  EBD_EBRPSInfo.class;
		case "EBRSTInfo":
			//台站（前端）信息
			return EBD_EBRSTInfo.class;
		case "EBRASInfo":
			//应急广播适配器信息
			return  EBD_EBRASInfo.class;
		case "EBRBSInfo":
			//传输覆盖播出设备信息
			return  EBD_EBRBSInfo.class;
		case "EBRDTInfo":
			//平台设备及终端信息；
			return EBD_EBRDTInfo.class;
		case "EBRPSState":
			//应急广播平台状态文件
			return  EBD_EBRPSState.class;
		case "EBRASState":
			//应急广播适配器状态
			return EBD_EBRASState.class;
		case "EBRBSState":
            //：传输覆盖播出设备状态
			return EBD_EBRBSState.class;
		case "EBRDTState":
			//平台设备及终端状态
			return EBD_EBRDTState.class;
		default:
			//签名文件
			return EBD_Signature.class;
		}
	}
}
