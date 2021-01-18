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
 * Json��javaBean֮���ת��������
 *	 @author liucong
 * 	 @date 2017��8��4��
 */
public class JsonUtils{
	/**
	 * ��java����ת����json�ַ���
	 *  @param  bean java����
	 *  @return ����json��ʽ��
	 */
	public static String beanToJson(Object bean) {
		JSONObject json = JSONObject.fromObject(bean);
		return json.toString();
	}
	/**
	 * ��java����ת��Ϊjson�ַ�����ʽ��
	 * @param bean    Java����
	 * @param _nory_changes   ����ת��ָ����ֵ��
	 * @param nory    boolean���ʽ true��false true��ʾת���������ֵ�� false������������ġ�
	 * @return 
	 * Field[] getDeclaredFields()���� Field �����һ�����飬��Щ����ӳ�� Class ��������ʾ�����ӿ��������������ֶΡ� 
	 * String getName() �� String ����ʽ���ش� Class ��������ʾ��ʵ�壨�ࡢ�ӿڡ������ࡢ�������ͻ� void�����ơ� 
	 */
	public static String beanToJson(Object bean, String[] _nory_changes, boolean nory) {
		JSONObject json = null;
		if (nory) {// ת��_nory_changes�������
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
		} else {// ת������_nory_changes�������
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
	 * ��һ��JSON�����ַ���ʽ��ת���õ�һ��java����
	 * @param <T>
	 * @param jsonString  String���͵�jsond����
	 * @param beanCalss  ����Ķ���
	 * @return ����һ�������ʵ������
	 * String str ="{\"address\":[\"��ɳ\",\"����\"],\"sex\":\"��\",\"name\":\"����\",\"age\":15}";
	 *	System.out.println(JsonUtils.jsonToBean(str, Student.class));
	 */
	@SuppressWarnings("unchecked")
	public static <T> T jsonToBean(String jsonString, Class<T> beanCalss) {
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		T bean = (T) JSONObject.toBean(jsonObject, beanCalss);
		return bean;
	}

	/**
	 * ��java����List����ת����json�ַ���
	 *  @param beans
	 *  @return
	 */
	public static String beanListToJson(List<?> beans) {
		StringBuffer rest = new StringBuffer();
		rest.append("[");
		int size = beans.size();
		for (int i = 0; i < size; i++) {
			rest.append(beanToJson(beans.get(i)) + ((i < size - 1) ? "," : "")); //�����˶���תjson��ʽ�ķ�����beanToJosn(Object bena)
		}
		rest.append("]");
		return rest.toString();
	}

	/**
	 * ��java����List����ת����json�ַ��� ���
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
	 * ��json HASH���ʽ�л�ȡһ��map����map֧��Ƕ�׹���
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
	 * map����ת����json��ʽ����
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
	 * ��json�����еõ���Ӧjava����
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
	 * ��json���󼯺ϱ��ʽ�еõ�һ��java�����б�
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
	 * ��json�����н�����java�ַ�������
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
	   *   ��json�����н�����javaLong�Ͷ�������
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
	   * ��json�����н�����java Integer�Ͷ�������
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
	   *  ��json�����н�����java Double�Ͷ�������
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
