package cd.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class URLUtils {

	/**
	 * @param
	 *            :请求接口  /访问地址
	 * @param httpArg
	 *            :参数    /请求参数
	 * @return 返回string字符串
	 */
	public static String getUrl(String httpUrl, String httpArg) {
	    BufferedReader reader = null;
	    String result = null;
	    StringBuffer sbf = new StringBuffer();
	    if(httpArg == null){
			httpUrl = httpUrl;
		}else {
			httpUrl = httpUrl + "?" + httpArg;
		}
	    try {
	        URL url = new URL(httpUrl);
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setRequestMethod("GET");       
	        connection.connect();
	        InputStream is = connection.getInputStream();
	        reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	        String strRead = null;
	        while ((strRead = reader.readLine()) != null) {
	            sbf.append(strRead);
	            sbf.append("\r\n");
	        }
	        reader.close();
	        result = sbf.toString();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return result;
	}
	
	
	
	/**
	 * POST请求
	 * @param postUrl  //post请求地址
	 * @param postData   //请求参数
	 * @return
	 */
	public static String postUrl(String postUrl,String postData) {
		
		try {
			//创建连接
			URL url = new URL(postUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //获得连接对象
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestMethod("POST");  //请求方式
			connection.setRequestProperty("Accept", "application/json"); //接收数据的格式
			connection.setRequestProperty("Content-Type", "application/json"); //设置发送的格式
			connection.connect();
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(),"UTF-8"); //往connection.getOutputStream()中写入数据，设置UTF-8编码格式
			out.append(postData); //添加数据
			out.flush(); //刷新缓冲区
			out.close();
			//完成请求，读取响应
			int length = connection.getContentLength();  //获取长度
			InputStream input = connection.getInputStream();
			
			if( length != 1 ) {
				byte[] data =  new byte[length];
				byte[] temp = new byte[512];
				int readLen = 0;
				int destPos = 0;
				while((readLen = input.read(temp)) > 0) {
					System.arraycopy(temp, 0, data, destPos, readLen);
					destPos += readLen;
				}
				String result = new String(data,"UTF-8");
				return result; //返回结果
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "error";  //返回请求错误
	}	
	
}
