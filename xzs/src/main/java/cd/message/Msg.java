package cd.message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 返回状态通用实体0代表成功1代表失败
 * @author ws
 *
 */
public class Msg {

	private int code;	//返回状态0表示成功1表示失败
	private String msg;  //返回消息
	private int count; //upload状态
	private boolean flag=false;
	private String token=""; //toekn


	private Map<String,Object> map = new HashMap<String, Object>(); //用户要返回给游览器的信息

	//返回结果成功的函数
	public static Msg success() {
		Msg result = new Msg();
		result.setCode(0);
		result.setMsg("处理成功");
		return result;
	}


	public static Msg setSuccess(boolean bool,String token){
		Msg result = new Msg();
		result.setCode(0);
		result.setToken(token);
		result.setMsg("访问成功");
		return result;
	}

	public static Msg error(String msg){
		Msg result = new Msg();
		result.setCode(1);
		result.setMsg(msg);
		return result;
	}

	//返回用户的数据
	public Msg add(String string,Object object) {
		this.getMap().put(string, object);
		return this;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
