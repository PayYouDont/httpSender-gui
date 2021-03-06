package com.payudon.util;

import com.payudon.common.config.ApplicationContextRegister;
import com.payudon.common.config.ServerProperties;
import com.payudon.common.xml.base.EBD;
import com.payudon.common.xml.other.EBD_ConnectionCheck;

import java.util.Date;

public class EBDcodeUtil {
	
	public static int EBDID = 0;
	
	public static Date DAY = new Date();
	//状态描述
	public static String EBRPSStateDesc = "";
	
	public static String getEBDIDCode() {
		Date now = new Date();
		if(DateUtils.getDistanceOfTwoDate(now, DAY)>=1) {//每天重置EBDID
			DAY = new Date();
			EBDID = 0;
		}
		EBDID++;
		return StringUtil.patch("0", 8, EBDID);
	}
	public static String getConnectionCheckCode() {
		return StringUtil.patch("0", 16, 0);
	}
	
	private static String createBaseDateCode() {
		ServerProperties prop = ApplicationContextRegister.getBean(ServerProperties.class);
		return prop.getSRC_EBRID()+DateUtils.getDate("yyyyMMdd");
	}
	
	public static String getEBMID() {
		EBDID++;
		return createBaseDateCode()+StringUtil.patch("0",4, EBDID);
	}
	public static String getEBDID(Object object) {
		if(object instanceof EBD) {
			if(object instanceof EBD_ConnectionCheck) {
				ServerProperties prop = ApplicationContextRegister.getBean(ServerProperties.class);
				return "01"+prop.getSRC_EBRID()+getConnectionCheckCode();
			}
			return getBaseEBDID();
			
		}
		return null;
	}
	public static String getBaseEBDID() {
		return "10"+createBaseDateCode()+getEBDIDCode();
	}
	public static int getAreaCodeLevel(String areaCode) {
		if(areaCode.length()==12) {
			String first6Str = areaCode.substring(0,6);//前6位
			String last6Str = areaCode.substring(6, 12);//后6位
			if(last6Str.equals("000000")) {
				String str5_6 = first6Str.substring(4,6);//5-6位
				if(!str5_6.equals("00")) {
					return 3;
				}
				String str2_4 = first6Str.substring(2,4);//3-4位
				if(!str2_4.equals("00")) {
					return 2;
				}
				return 1;
			}else {
				String last3Str = last6Str.substring(3,6);//最后3位数
				if(last3Str.equals("000")) {
					return 4;
				}
				return 5;
			}
		}
		return -1;
	}
	public static String getEBRPSStateDesc() {
		if("".equals(EBRPSStateDesc)) {
			EBRPSStateDesc = "开机";
		}else {
			EBRPSStateDesc = "运行正常";
		}
		return EBRPSStateDesc;
	}
	public static String getParentCode(String addressCode){
        Integer level = getAreaCodeLevel (addressCode);
        if(level>0){
            String subCode;
            if(level<4){
                subCode = addressCode.substring (0,level*2);
            }else{
                subCode = addressCode.substring (0,6+(level-3)*3);
            }
            return subCode;
        }
	    return  null;
    }
}
