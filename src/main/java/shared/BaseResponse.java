package shared;

public class BaseResponse {
	private boolean success;
	private Object data;
	private String message;
	
	private BaseResponse(boolean success, Object data, String message) {
		this.success = success;
		this.data = data;
		this.message = message;
	}
	
	public static BaseResponse success(Object data) {
		return new BaseResponse(true, data, null);
	}
	
	public static BaseResponse error(String message) {
		return new BaseResponse(false, null, message);
	}
	
}
