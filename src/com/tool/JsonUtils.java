package com.tool;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
/**
 * Json与javaBean之间的转换工具类
 *	 @author liucong
 * 	 @date 2017年8月4日
 */
public class JsonUtils{
	/**
	 * 将java对象转换成json字符串
	 *  @param  bean java对象。
	 *  @return 返回json格式。
	 */
	public static String beanToJson(Object bean) {
		JSONObject json = JSONObject.fromObject(bean);
		return json.toString();
	}
	/**
	 * 将java对象转换为json字符串格式。
	 * @param bean    Java对象
	 * @param _nory_changes   数组转换指定的值。
	 * @param nory    boolean表达式 true和false true表示转换数组里的值。 false出来数组以外的。
	 * @return 
	 * Field[] getDeclaredFields()返回 Field 对象的一个数组，这些对象反映此 Class 对象所表示的类或接口所声明的所有字段。 
	 * String getName() 以 String 的形式返回此 Class 对象所表示的实体（类、接口、数组类、基本类型或 void）名称。 
	 */
	public static String beanToJson(Object bean, String[] _nory_changes, boolean nory) {
		JSONObject json = null;
		if (nory) {// 转换_nory_changes里的属性
			Field[] fields = bean.getClass().getDeclaredFields();
			String str = "";
			for (Field field : fields) {
				// System.out.println(field.getName());
				str += (":" + field.getName());
			}
			fields = bean.getClass().getSuperclass().getDeclaredFields();
			for (Field field : fields) {
				// System.out.println(field.getName());
				str += (":" + field.getName());
			}
			str += ":";
			for (String s : _nory_changes) {
				str = str.replace(":" + s + ":", ":");
			}
			json = JSONObject.fromObject(bean, configJson(str.split(":")));
		} else {// 转换除了_nory_changes里的属性
			json = JSONObject.fromObject(bean, configJson(_nory_changes));
		}
		return json.toString();
	}

	private static JsonConfig configJson(String[] excludes) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(excludes);
		jsonConfig.setIgnoreDefaultExcludes(false);
		return jsonConfig;
	}


	/**
	 * 从一个JSON对象字符格式中转换得到一个java对象
	 * @param <T>
	 * @param jsonString  String类型的jsond对象
	 * @param beanCalss  具体的对象。
	 * @return 返回一个具体的实例对象。
	 * String str ="{\"address\":[\"长沙\",\"北京\"],\"sex\":\"男\",\"name\":\"张三\",\"age\":15}";
	 *	System.out.println(JsonUtils.jsonToBean(str, Student.class));
	 */
	@SuppressWarnings("unchecked")
	public static <T> T jsonToBean(String jsonString, Class<T> beanCalss) {
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		T bean = (T) JSONObject.toBean(jsonObject, beanCalss);
		return bean;
	}

	/**
	 * 将java对象List集合转换成json字符串
	 *  @param beans
	 *  @return
	 */
	public static String beanListToJson(List<?> beans) {
		StringBuffer rest = new StringBuffer();
		rest.append("[");
		int size = beans.size();
		for (int i = 0; i < size; i++) {
			rest.append(beanToJson(beans.get(i)) + ((i < size - 1) ? "," : "")); //调用了对象转json格式的方法。beanToJosn(Object bena)
		}
		rest.append("]");
		return rest.toString();
	}

	/**
	 * 将java对象List集合转换成json字符串 多个
	 * @param beans
	 * @param _no_changes
	 * @return
	 */
	public static String beanListToJson(List<?> beans, String[] _nory_changes, boolean nory) {

		StringBuffer rest = new StringBuffer();

		rest.append("[");

		int size = beans.size();

		for (int i = 0; i < size; i++) {
			try {
				rest.append(beanToJson(beans.get(i), _nory_changes, nory));
				if (i < size - 1) {
					rest.append(",");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		rest.append("]");
		return rest.toString();
	}




	/**
	 * 从json HASH表达式中获取一个map，改map支持嵌套功能
	 *
	 * @param jsonString
	 * @return
	 */
	public static Map<String, Object> jsonToMap(String jsonString) {
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		Iterator<?> keyIter = jsonObject.keys();
		String key;
		Object value;
		Map<String, Object> valueMap = new HashMap<>();
		while (keyIter.hasNext()) {
			key = (String) keyIter.next();
			value = jsonObject.get(key).toString();
			valueMap.put(key, value);
		}
		return valueMap;
	}
	

	/**
	 * map集合转换成json格式数据
	 * @param map
	 * @return
	 */
	public static String mapToJson(Map<String, ?> map, String[] _nory_changes, boolean nory) {

		String s_json = "{";

		Set<String> key = map.keySet();
		for (Iterator<?> it = key.iterator(); it.hasNext();) {
			String s = (String) it.next();
			if (map.get(s) == null) {

			} else if (map.get(s) instanceof List<?>) {
				s_json += (s + ":" + JsonUtils.beanListToJson((List<?>) map.get(s), _nory_changes, nory));
			} else {
				JSONObject json = JSONObject.fromObject(map);
				s_json += (s + ":" + json.toString());
				;
			}
			if (it.hasNext()) {
				s_json += ",";
			}
		}
		s_json += "}";
		return s_json;
	}


	/**
	 * 从json数组中得到相应java数组
	 * @param jsonString
	 * @return
	 */
	public static Object[] jsonToObjectArray(String jsonString) {
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		return jsonArray.toArray();

	}

	public static String listToJson(List<?> list) {
		JSONArray jsonArray = JSONArray.fromObject(list);
		return jsonArray.toString();

	}


	/**
	 * 从json对象集合表达式中得到一个java对象列表
	 *
	 * @param jsonString
	 * @param beanClass
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> jsonToBeanList(String jsonString, Class<T> beanClass) {
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		JSONObject jsonObject;
		T bean;
		int size = jsonArray.size();
		List<T> list = new ArrayList<T>(size);

		for (int i = 0; i < size; i++) {

			jsonObject = jsonArray.getJSONObject(i);
			bean = (T) JSONObject.toBean(jsonObject, beanClass);
			list.add(bean);
		}
		return list;
	}


	/**
	 * 从json数组中解析出java字符串数组
	 *
	 * @param jsonString
	 * @return
	 */
	public static String[] jsonToStringArray(String jsonString) {
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		String[] stringArray = new String[jsonArray.size()];
		int size = jsonArray.size();
		for (int i = 0; i < size; i++) {
			stringArray[i] = jsonArray.getString(i);
		}
		return stringArray;
	}


	/**
	   *   从json数组中解析出javaLong型对象数组
	 *@param jsonString
	 * @return
	 */
	public static Long[] jsonToLongArray(String jsonString) {
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		int size = jsonArray.size();
		Long[] longArray = new Long[size];
		for (int i = 0; i < size; i++) {
			longArray[i] = jsonArray.getLong(i);
		}
		return longArray;
	}

	/**
	   * 从json数组中解析出java Integer型对象数组
	 *@param jsonString
	 * @return
	 */
	public static Integer[] jsonToIntegerArray(String jsonString) {
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		int size = jsonArray.size();
		Integer[] integerArray = new Integer[size];
		for (int i = 0; i < size; i++) {
			integerArray[i] = jsonArray.getInt(i);
		}
		return integerArray;
	}


	/**
	   *  从json数组中解析出java Double型对象数组
	 * @param jsonString
	 * @return
	 */
	public static Double[] jsonToDoubleArray(String jsonString) {
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		int size = jsonArray.size();
		Double[] doubleArray = new Double[size];
		for (int i = 0; i < size; i++) {
			doubleArray[i] = jsonArray.getDouble(i);
		}
		return doubleArray;
	}
}
