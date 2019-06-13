/**   
* @Title: SensorTypeAdapter.java 
* @Package com.gospell.chitong.rdcenter.broadcast.commonManage.config 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2018年12月19日 下午3:35:29 
*/
package com.payudon.common.config;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.payudon.common.xml.base.BaseEBD;
import com.payudon.common.xml.base.EBD;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

/** 
* @ClassName: SensorTypeAdapter 
* @Description: TODO(重新排序) 
* @author peiyongdong
* @date 2018年12月19日 下午3:35:29 
*  
*/
public class SensorTypeAdapter extends TypeAdapter<EBD> {

	/** 
	 * <p>Title: write</p> 
	 * <p>Description: </p> 
	 * @param out
	 * @param value
	 * @throws IOException 
	 * @see TypeAdapter#write(JsonWriter, Object)
	 * @throws
	 * @author peiyongdong
	 * @date 2018年12月19日 下午3:36:39
	 */
	@Override
	public void write(JsonWriter out, EBD value) throws IOException {
		Class<? extends BaseEBD> clazz = value.getEBD().getClass();
		//父类属性
		Field[] supfields = clazz.getSuperclass().getDeclaredFields();
		//子类属性
		Field[] subFields = clazz.getDeclaredFields();
		//按自定义顺序输出字段信息
		out.beginObject();
		out.name("EBD");
		out.beginObject();
		setWrite(supfields, out, value.getEBD());
		setWrite(subFields, out, value.getEBD());
		out.endObject();
		out.endObject();
	}

	/**
	 * <p>Title: read</p>
	 * <p>Description: </p>
	 * @param in
	 * @return
	 * @throws IOException
	 * @see TypeAdapter#read(JsonReader)
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年12月19日 下午3:36:39
	 */
	@Override
	public EBD read(JsonReader in) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setWrite(Field[] fields,JsonWriter out,Object object) {
		for(int i=0;i<fields.length;i++) {
			Field field = fields[i];
			field.setAccessible(true); 
			try {
				Object type = field.get(object);
				if(type==null) {
					continue;
				}

				if(type instanceof String){
                    out.name(field.getName()).value(type.toString());
                }else if(type instanceof List){
				    out.name (field.getName ()).beginObject();
				    List list = (List)type;
                    list.forEach (t->{
                        Field[] subFields = t.getClass().getDeclaredFields();
                        setWrite(subFields, out, t);
                    });
                    out.endObject();
                }else{
                    out.name(field.getName()).beginObject();
                    Field[] subFields = type.getClass().getDeclaredFields();
                    setWrite(subFields, out, type);
                    out.endObject();
                }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
