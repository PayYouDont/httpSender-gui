/**
 * @Title: EBD_EBRPSInfo.java @Package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml @Description: TODO(     ) @author peiyongdong @date 2018年12月13日 下午5:49:55
 */
package com.payudon.common.xml.info;

import com.payudon.common.xml.base.BaseEBD;
import com.payudon.common.xml.base.EBDResponse;
import lombok.EqualsAndHashCode;

/**
 * @author peiyongdong
 * @ClassName: EBD_EBRPSInfo
 * @Description: TODO(平台信息, 应急广播平台信息)
 * @date 2018年12月13日 下午5:49:55
 **/
@lombok.Data
public class EBD_EBRPSInfo implements EBDResponse {
    private EBD EBD;

    @lombok.Data
    @EqualsAndHashCode(callSuper = false)
    public static class EBD extends BaseEBD {
        private EBRPSInfo EBRPSInfo;
    }

    @lombok.Data
    public static class EBRPSInfo {
        private Params Params;
        private EBRPS EBRPS;
    }

    @lombok.Data
    public static class Params {
        private String RptStartTime;
        private String RptEndTime;
        private String RptType;
    }

    @lombok.Data
    public static class EBRPS {
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
        private String URL;
    }

    @lombok.Data
    public static class RelatedEBRPS {
        private String EBRID;
    }

    /**
     * <p>Title: creatResponseXML</p> <p>Description: </p> @return @see com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBD#creatResponseXML() @throws @author peiyongdong @date 2018年12月17日 上午9:36:37
     */
    @Override
    public EBD_EBRPSInfo creatResponse() {/* TODO Auto-generated method stub*/
        return null;
    }

    public EBD_EBRPSInfo() {
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
