package cn.com.liud.aa.pojo;

public class BaseResult<T> {

	// 通用的状态码
	public abstract class CommonCode {
		public static final String SUCCESS = "0"; // 获取数据成功状态码
		public static final String ERROR = "1";	//	请求失败

		public static final String IS_EXIST = "10000"; // 数据已存在
		public static final String INVALID_TOKEN = "20000"; // token有误
		public static final String PARAM_ERROR = "20001"; // 参数传递错误状态码
	}

	// 通用的消息
	public abstract class CommonMessage {
		public static final String SUCCESS = "请求数据成功!"; // 获取数据成功
		public static final String ERROR = "请求数据失败";	//	请求失败
		public static final String IS_EXIST = "记录已存在";
		public static final String INVALID_TOKEN = "无效的token"; // token有误
		public static final String PARAM_ERROR = "请求参数传递错误"; // 参数传递错误
	}



	public BaseResult() {}

	private Boolean isError;
	private T data;
	private String code;
	private String message;

	public Boolean getIsError() {
		return isError;
	}

	public void setIsError(Boolean isError) {
		this.isError = isError;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public BaseResult(Boolean isError, T data, String code, String message) {
		this.isError = isError;
		this.data = data;
		this.code = code;
		this.message = message;
	}

	public static<T> BaseResult<T> getInstance(Boolean isError, T data, String code, String message){
		return new BaseResult<T>(isError, data, code, message);
	}

	public static<T> BaseResult<T> invalidToken(){
		return getInstance(true, null, BaseResult.CommonCode.INVALID_TOKEN, BaseResult.CommonMessage.INVALID_TOKEN);
	}

	public static<T> BaseResult<T> paramError(){
		return getInstance(true, null, BaseResult.CommonCode.PARAM_ERROR, BaseResult.CommonMessage.PARAM_ERROR);
	}

	public static<T> BaseResult<T> success(){
		return getInstance(false, null, BaseResult.CommonCode.SUCCESS, BaseResult.CommonMessage.SUCCESS);
	}

	public static<T> BaseResult<T> success(T data){
		return getInstance(false, data, BaseResult.CommonCode.SUCCESS, BaseResult.CommonMessage.SUCCESS);
	}

	public static<T> BaseResult<T> error(){
		return getInstance(true, null, BaseResult.CommonCode.ERROR, BaseResult.CommonMessage.ERROR);
	}

	public static<T> BaseResult<T> error(String code, String message){
		return getInstance(true, null, code,message);
	}

	public static<T> BaseResult<T> error(T data){
		return getInstance(true, data, BaseResult.CommonCode.ERROR, BaseResult.CommonMessage.ERROR);
	}


}
