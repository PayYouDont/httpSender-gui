/**   
* @Title: TestUtil.java 
* @Package com.payudon.util 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2018年8月13日 上午10:54:46 
*/
package com.payudon.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.payudon.common.entity.BMap;

/**
 * @ClassName: TestUtil
 * @Description: TODO( )
 * @author peiyongdong
 * @date 2018年8月13日 上午10:54:46
 * 
 */
public class BmapDownLoadUtil {
	/**
	 * 根据经度获取切片规范下的行号
	 * 
	 * @param lon
	 * @param zoom
	 * @return
	 */
	public static int getOSMTileXFromLongitude(double lon, int zoom) {
		return (int) (Math.floor((lon + 180) / 360 * Math.pow(2, zoom)));
	}

	/**
	 * 根据纬度获取切片规范下的列号
	 * 
	 * @param lat
	 * @param zoom
	 * @return
	 */
	public static int getOSMTileYFromLatitude(double lat, int zoom) {
		return (int) (Math
				.floor((1 - Math.log(Math.tan(lat * Math.PI / 180) + 1 / Math.cos(lat * Math.PI / 180)) / Math.PI) / 2
						* Math.pow(2, zoom)));
	}

	/**
	 * 经纬度转墨卡托
	 * 
	 * @param LonLat
	 *            经纬度坐标
	 * @return
	 */
	public static BMap lonLatToMercator(BMap LonLat) {
		BMap mercator = new BMap();
		double x = (LonLat.getLng() * 20037508.342789 / 180);
		double y = (Math.log(Math.tan((90 + LonLat.getLat()) * Math.PI / 360)) / (Math.PI / 180));
		y = (double) (y * 20037508.342789 / 180);
		mercator.setLng(x);
		mercator.setLat(y);
		return mercator;
	}

	/**
	 * 墨卡托转经纬度
	 * 
	 * @param mercator
	 *            墨卡托坐标
	 * @return
	 */
	public static BMap mercatorToLonLat(BMap mercator) {
		BMap lonlat = new BMap();
		double x = (mercator.getLng() / 20037508.342789 * 180);
		double y = (mercator.getLat() / 20037508.342789 * 180);
		y = (double) (180 / Math.PI * (2 * Math.atan(Math.exp(y * Math.PI / 180)) - Math.PI / 2));
		lonlat.setLng(x);
		lonlat.setLat(y);
		return lonlat;
	}

	public static int getRow(BMap mercator) {
		double lng = mercator.getLng() / Math.pow(2, 18 - mercator.getZ());
		return (int) lng / 256;
	}

	public static int getColumn(BMap mercator) {
		double lat = mercator.getLat() / Math.pow(2, 18 - mercator.getZ());
		return (int) lat / 256;
	}

	public static void testDownLoad() {
		String link = "http://online3.map.bdimg.com/onlinelabel/?qt=tile&x={x}&y={y}&z={z}&styles=pl&udt=20170712&scaler=1&p=1";
		int z = 13;// 层级
		int xmin = 1552;// x最小值
		int xmax = 1671;// x最大值
		int ymin = 492;// y最小值
		int ymax = 569;// y最大值
		int c = 0;// 成功数
		int fail = 0;// 失败数量
		for (int i = xmin; i <= xmax; i++) {
			for (int j = ymin; j <= ymax; j++) {
				try {
					URL url = new URL(link.replace("{x}", i + "").replace("{y}", j + "").replace("{z}", z + ""));
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setConnectTimeout(100);
					conn.connect();
					InputStream in = conn.getInputStream();
					File dir = new File("d:/BMapDownLoad/tiles/" + z + "/" + i);
					if (!dir.exists()) {
						dir.mkdirs();
					}
					File file = new File("d:/BMapDownLoad/tiles/" + z + "/" + i + "/" + j + ".jpg");
					if (!file.exists()) {
						file.createNewFile();
					}
					OutputStream out = new FileOutputStream(file);
					byte[] bytes = new byte[1024 * 20];
					int len = 0;
					while ((len = in.read(bytes)) != -1) {
						out.write(bytes, 0, len);
					}
					out.close();
					in.close();
					// System.out.println("已成功下载:" + z + "_" + i + "_" + j + ".jpg");
					c++;
				} catch (Exception e) {
					System.out.println(e.getMessage());
					fail++;
				}
			}
		}
		System.out.println("共下载:   " + c + "   张");
		System.out.println("失败:   " + fail + "   张");
	}

	public static void test() throws IOException{
		String type = "CUSTOM";
		double[] extent = {102.955,30.272,104.075,30.656};
		for (int z = 0; z < 14; z++) {
			// 计算行列号(使用瓦片的中心点经纬度计算)
			// 起始结束行
			int minR = getOSMTileYFromLatitude(extent[3], z);
			int maxR = getOSMTileYFromLatitude(extent[1], z);
			// 起始结束列
			int minC = getOSMTileXFromLongitude(extent[0], z);
			int maxC = getOSMTileXFromLongitude(extent[2], z);
			for (int y = minR; y <= maxR; y++) {
				for (int x = minC; x <= maxC; x++) {
					// String urlstr =
					// "http://t0.tianditu.com/DataServer?T=vec_w&x="+x+"&y="+y+"&l="+z;
					// //天地图服务器t0-t8间选一个
					// String urlstr =
					// "http://mt2.google.cn/vt/lyrs=m&scale=1&hl=zh-CN&gl=cn&x="+x+"&y="+y+"&z="+z;
					// //谷歌地图服务器t0-t2间选一个
					//http://online3.map.bdimg.com/onlinelabel/?qt=tile&x={x}&y={y}&z={z}&styles=pl&udt=20170712&scaler=1&p=1
					// String urlstr =
					//"http://online3.map.bdimg.com/onlinelabel/?qt=tile&x="+x+"&y="+y+"&z="+z+"&&styles=pl&udt=20170712&scaler=1&p=1";
					// //百度地图（加密过的）
					// String urlstr = "http://c.tile.opencyclemap.org/cycle/"+z+"/"+x+"/"+y+".png";
					// //osm地图
					String urlstr = "http://wprd04.is.autonavi.com/appmaptile?x=" + x + "&y=" + y + "&z=" + z
							+ "&lang=zh_cn&size=1&scl=1&style=8"; // 高德地图(6：影像，7：矢量，8：影像路网)
					System.out.println(urlstr);

					String path = null;
					if (type.equals("ArcGIS")) {
						// ArcGIS格式瓦片下载
						path = getTDTilesForArcGISPath(x, y, z);
					} else {
						// 一般格式瓦片下载
						path = getTDTilesForCustomPath(x, y, z);
					}

					File file = new File(path);
					URL url = new URL(urlstr);
					download(url, file);
				}
			}
		}
	}

	private static String makePath(int num) {
		String str = Integer.toHexString(num);
		// ArcGIS行列都是8位长度
		while (str.length() < 8) {
			str = "0" + str;
		}
		return str;
	}

	public static String getTDTilesForArcGISPath(int x, int y, int z) {
		String l = "L" + String.format("%02d", z);
		String r = "R" + makePath(y);
		String c = "C" + makePath(x);
		String path = "E:/打包/" + l + File.separator + r + File.separator + c + ".png";
		return path;
	}

	public static String getTDTilesForCustomPath(int x, int y, int z) {
		String path = "E:/打包/" + z + File.separator + y + File.separator + x + ".png";
		return path;
	}

	public static boolean download(URL url, File file) throws IOException {
		boolean flag = true;
		DataOutputStream dos = null;
		DataInputStream dis = null;
		try {
			if (!file.getParentFile().exists())
				file.getParentFile().mkdirs();
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			dos = new DataOutputStream(new FileOutputStream(file));
			dis = new DataInputStream(conn.getInputStream());
			byte[] data = new byte[2048];
			int i = 0;
			while ((i = dis.read(data)) != -1) {
				dos.write(data, 0, i);
			}
			dos.flush();
		} catch (IOException e) {
			flag = false;
			throw e;
		} finally {
			if (dis != null)
				dis.close();
			if (dos != null)
				dos.close();
		}
		return flag;
	}

	public static void main(String[] args) {
		try {
			test();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
