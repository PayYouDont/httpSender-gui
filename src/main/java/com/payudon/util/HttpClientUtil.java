package com.payudon.util;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClientUtil {

	public static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
	
	public static String sendPostFile(String url,String tarPath)throws Exception  {
		File tar = new File(tarPath);
		return postUpload(url, tar);
	}
	
	public static String postUpload(String url,File file) {
		// 创建httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		//创建post方式请求
		HttpPost httpPost = new HttpPost(url);
		String result = "";
		try {
			 //设置超时
	        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(100000).build();
	        httpPost.setConfig(requestConfig);
	        //
	        FileBody bin = new FileBody(file); 
	        StringBody comment = new StringBody("This is comment", ContentType.TEXT_PLAIN); 
	        HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("file", bin).addPart("comment", comment).build(); 
	        httpPost.setEntity(reqEntity); 
	        response = httpClient.execute(httpPost); 
	        HttpEntity resEntity = response.getEntity();
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
	        	InputStream in = resEntity.getContent();
                String filename = getFileName(response);
                if(filename!=null){
                    String outPath = FileUtil.copyFile(in, "D:\\tar\\test\\get", filename);
                    result = "返回文件获取完毕,文件路径："+outPath;
                }
	        }else{
                result = EntityUtils.toString(response.getEntity(), "utf-8");
            }
	        EntityUtils.consume(resEntity);
	        return result;
		}catch(Exception e) {
			 try {
				if(response!=null) {
					response.close(); 
				}
				if(httpClient!=null) {
					httpClient.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return e.getMessage();
		}
	}
    private static String getFileName(HttpResponse response) {
        Header contentHeader = response.getFirstHeader("Content-Disposition");
        String filename = null;
        if (contentHeader != null) {
            HeaderElement[] values = contentHeader.getElements();
            if (values.length == 1) {
                NameValuePair param = values[0].getParameterByName("filename");
                if (param != null) {
                    try {
                        // 此处根据具体编码来设置
                        filename = new String(param.getValue().getBytes("ISO-8859-1"), "GBK");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return filename;
    }

    /**
	 *post请求传输json数据
	 * @Title: sendPostDataByJson 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param url
	 * @param @param json
	 * @param @param encoding
	 * @param @return
	 * @param @throws ClientProtocolException
	 * @param @throws IOException    设定文件 
	 * @return String    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月7日 下午4:10:14
	 */
	public static String sendPostDataByJson(String url, String json) throws Exception {
		if(url.indexOf("http:")==-1) {
			url = "http://"+url;
		}
		String result = "";

        // 创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);
        //设置超时
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(100000).build();
        httpPost.setConfig(requestConfig);
        // 设置参数到请求对象中
        StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
        stringEntity.setContentEncoding("utf-8");
        httpPost.setEntity(stringEntity);

        // 执行请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse response = httpClient.execute(httpPost);

        // 获取结果实体
        // 判断网络连接状态码是否正常(0--200都数正常)
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            result = EntityUtils.toString(response.getEntity(), "utf-8");
        }
        // 释放链接
        response.close();

        return result;
    }
	/**
	 * get请求传输数据
	 * @Title: sendGetData 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param url
	 * @param @param encoding
	 * @param @return
	 * @param @throws ClientProtocolException
	 * @param @throws IOException    设定文件 
	 * @return String    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月7日 下午4:11:23
	 */
	public static String sendGetData(String url,String param) throws ClientProtocolException, IOException {
        String result = "";
        if(url.indexOf("http:")==-1) {
			url = "http://"+url;
		}
        // 创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("param1", param));
        @SuppressWarnings("deprecation")
		String getParams = EntityUtils.toString(new UrlEncodedFormEntity(list, HTTP.UTF_8));
        // 创建get方式请求对象
        HttpGet httpGet = new HttpGet(url + "?" + getParams);     
        //httpGet.addHeader("Content-type", "application/json");
        // 通过请求对象获取响应对象
        CloseableHttpResponse response = httpClient.execute(httpGet);

        // 获取结果实体
        // 判断网络连接状态码是否正常(0--200都数正常)
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            result = EntityUtils.toString(response.getEntity(), "utf-8");
        }
        // 释放链接
        response.close();

        return result;
    }
	public static String sendPostTar(String url, String tarPath,String path){
		File tar = new File(tarPath);
		if(!tar.exists()) {
			return null;
		}
		path += File.separatorChar + tar.getName();
		String result = postUpload(url, tar);
		FileUtil.writeString(result, path);
		return result;
	}
	public static String post(String url,String param) throws Exception{
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
	    try{
            URLConnection connection = new URL(url).openConnection ();
            // 设置通用的请求属性
            connection.setRequestProperty ("accept","*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            // 发送POST请求必须设置如下两行
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(connection.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        }catch (Exception e){
           throw e;
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }
}
