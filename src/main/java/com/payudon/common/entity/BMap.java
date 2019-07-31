
package com.payudon.common.entity;

import java.io.Serializable;

import lombok.Data;

/** 
* @ClassName: BMap 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2018年8月13日 上午9:49:55 
*  
*/
@Data
public class BMap implements Serializable{
	/** 
	* @Fields serialVersionUID : TODO(     ) 
	*/ 
	private static final long serialVersionUID = 1L;
	private int z;//层级
	private int xmin;//x最小值
	private int xmax;//x最大值
	private int ymin;//y最小值
	private int ymax;//y最大值
	private int count;//成功数量
	private int fail;//失败数量
	private double lng;//经度
	private double lat;//纬度
	
}
