package com.tool;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import net.sf.json.JSONObject;

/**
 * httpClient框架的http请求
 * 	 实例:https://www.cnblogs.com/QQParadise/articles/5020215.html
   *       注意:由服务端提供http地址,我们负责获取数据
 *   @author Administrator
 */
public class HttpRequestUtils {
	public static Logger logger = Logger.getLogger(HttpRequestUtils.class);// 日志记录
	/**
	 * httpPost
	 * @param url    路径
	 * @param jsonParam	参数
	 * @return
	 */
	public static String httpPost(String url, JSONObject jsonParam) {
		return httpPost(url, jsonParam, false);
	}

	/**
	 * post请求
	 * @param url地址
	 * @param jsonParam  参数
	 * @param noNeedResponse	不需要返回结果
	 * @return
	 */
	public static String httpPost(String url, JSONObject jsonParam, boolean noNeedResponse) {
		// post请求返回结果

		CloseableHttpClient httpClient = HttpClients.createDefault();
		String str = "";
		HttpPost method = new HttpPost(url);
		try {
			if (null != jsonParam) {
				// 解决中文乱码问题
				StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
				entity.setContentEncoding("UTF-8");
				entity.setContentType("application/json");
				method.setEntity(entity);
			}
			HttpResponse result = httpClient.execute(method);
			url = URLDecoder.decode(url, "UTF-8");
			/** 请求发送成功，并得到响应 **/
			if (result.getStatusLine().getStatusCode() == 200) {

				try {
					/** 读取服务器返回过来的json字符串数据 **/
					str = EntityUtils.toString(result.getEntity());
					if (noNeedResponse) {
						return null;
					}

				} catch (Exception e) {
					logger.error("post请求提交失败:" + url, e);
				}
			}
		} catch (IOException e) {
			logger.error("post请求提交失败:" + url, e);
		}
		return str;
	}

	/**
	   * 发送get请求
	 * @param url  路径
	 * @return
	 */
	public static String httpGet(String url) {
		// get请求返回结果
		// JSONObject jsonResult = null;
		try {
			CloseableHttpClient client = HttpClients.createDefault();
		// 发送get请求
			HttpGet request = new HttpGet(url);
			HttpResponse response = client.execute(request);
			/** 请求发送成功，并得到响应 **/
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				/** 读取服务器返回过来的json字符串数据 **/
				String strResult = EntityUtils.toString(response.getEntity(),"utf-8");
				/** 把json字符串转换成json对象 **/
				return strResult;
//				jsonResult = JSONObject.fromObject(strResult);
//				url = URLDecoder.decode(url, "UTF-8");
			} else {
				logger.error("get请求提交失败:" + url);
			}
		} catch (IOException e) {
			logger.error("get请求提交失败:" + url, e);
		}
		return "";
	}
	
	/**
	 * @param url
	 * @param paramsMap
	 * @return
	 */
	public static String doPost(String url, Map<String, Object> paramsMap) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(180 * 1000)
				.setConnectionRequestTimeout(180 * 1000).setSocketTimeout(180 * 1000).setRedirectsEnabled(true).build();
		httpPost.setConfig(requestConfig);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		for (String key : paramsMap.keySet()) {
			nvps.add(new BasicNameValuePair(key, String.valueOf(paramsMap.get(key))));
		}
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
			HttpResponse response = httpClient.execute(httpPost);
			String strResult = "";
			if (response.getStatusLine().getStatusCode() == 200) {
				strResult = EntityUtils.toString(response.getEntity(),"UTF-8");
				return strResult;
			} else {
				return "Error Response: " + response.getStatusLine().toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "post failure :caused by-->" + e.getMessage().toString();
		} finally {
			if (null != httpClient) {
				try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	
	/**
	 * @param url
	 * @param jsonParams
	 * @return
	 */
	public static String doPostForJson(String url, String jsonParams) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(180 * 1000)
				.setConnectionRequestTimeout(180 * 1000).setSocketTimeout(180 * 1000).setRedirectsEnabled(true).build();

		httpPost.setConfig(requestConfig);
		httpPost.setHeader("Content-Type", "application/json"); //
		try {
			httpPost.setEntity(new StringEntity(jsonParams, ContentType.create("application/json", "utf-8")));
			System.out.println("request parameters" + EntityUtils.toString(httpPost.getEntity()));
			HttpResponse response = httpClient.execute(httpPost);
			System.out.println(" code:" + response.getStatusLine().getStatusCode());
			System.out.println("doPostForInfobipUnsub response" + response.getStatusLine().toString());
			return String.valueOf(response.getStatusLine().getStatusCode());
		} catch (Exception e) {
			e.printStackTrace();
			return "post failure :caused by-->" + e.getMessage().toString();
		} finally {
			if (null != httpClient) {
				try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
//		String id = "202";
//		String httpGet = HttpRequestUtils.httpGet("http://localhost:8080/wcrudjquery/userUpdateEcho?"+id);
//		System.out.println(httpGet);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", 202);
		String httpclient = HttpRequestUtils.doPost("http://localhost:8080/wcrudjquerywz/userUpdateEcho", map);
		System.out.println(httpclient);

		
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("username", "kuang");
//		map.put("password", "128128");
////		JSONObject jo = JSONObject.fromObject(map);
//		String a = HttpRequestUtils.doPost("http://localhost:8080/wcrudjquery/loginServlet", map);
//		System.out.println(a);
		
//		Map<String,Object> map = new HashMap<String, Object>();
//		map.put("addName","name");
//		map.put("addAge","age");
//		map.put("addSex","sex");
//		map.put("addShengao","shengao");
//		map.put("addTel","tel");
//		map.put("addAddr","addr");
//		map.put("addSfz","sfz");
//		map.put("addMx","star");
//		map.put("addAihao","aihao");
//		map.put("addBirth","birthday");
//		map.put("addPassword","pawd");
//		map.put("addConfirm_password","confirm_password");
//		map.put("addEmail","email");
//		String str1 = "{\r\n" + 
//				"				\"birthday\": \"2019-09-11 14:41:40\",\r\n" + 
//				"				\"star\": [\"lyf\"],\r\n" + 
//				"				\"aihao\": \"cg\",\r\n" + 
//				"				\"sfz\": \"254441255558852525\",\r\n" + 
//				"				\"sex\": \"1\",\r\n" + 
//				"				\"pawd\": \"128128\",\r\n" + 
//				"				\"confirm_password\": \"128128\",\r\n" + 
//				"				\"shengao\": 12,\r\n" + 
//				"				\"addName\": \"121\",\r\n" + 
//				"				\"tel\": \"12111111111\",\r\n" + 
//				"				\"id\": 0,\r\n" + 
//				"				\"addr\": [\"sh\"],\r\n" + 
//				"				\"addAge\": 12,\r\n" + 
//				"				\"email\": \"123@qq.com\",\r\n" + 
//				"				\"mark\": 0\r\n" + 
//				"			}";
//		
//		String doPostForJson = HttpRequestUtils.doPostForJson("http://localhost:8080/wcrudjquery/userAdd", str1);
//		System.out.println(doPostForJson);
		
//		String str1 = "http://localhost:8080/wcrudjquery/loginServlet";
//		String httpPos1t = HttpRequestUtils.httpPost(str1,"{\"key\":123,\"v\":456}");
//		System.out.println(httpPos1t);
//		String str = "http://localhost:8080/wHttpClientJquery/login";
//		get请求演示
//		String httpGet = HttpRequestUtils.httpGet(str);
//		System.out.println(httpGet);
//		/**
//		 * get请求
//		 */
//		JSONObject jsonObject = HttpRequestUtils.httpGet(url);
//		System.out.println(jsonObject);
//		User user = (User) JSONObject.toBean(jsonObject, User.class);
//		System.out.println(user.getStatus());
//		Data data = user.getData();
//		System.out.println(data.toString());
//		String httpPost = HttpRequestUtils.httpPost(str,JSONObject.fromObject("{\"key\":123,\"v\":456}"));
//		System.out.println(httpPost);
//		/**
//		 * post请求
//		 */
//		String jsonObject2 = HttpRequestUtils.httpPost(url, JSONObject.fromObject("{\"key\":123,\"v\":456}"));
//		System.out.println(jsonObject2);
	}
}