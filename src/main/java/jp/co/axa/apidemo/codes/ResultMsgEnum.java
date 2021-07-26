package jp.co.axa.apidemo.codes;
import lombok.Getter;
/**
 * List of Error codes and error message
 * error codes for client application 
 */
public enum ResultMsgEnum {
	// Normal cases
	SUCCESS("S000", "Success"),
	NO_RESULT("S001", "No result"),
	DELETE_SUCCESS("S002", "Delete success"),
	DELETE_NOT_FOUND("S003", "Delete record not found"),
	UPDATE_SUCCESS("S004", "Update success"),
	UPDATE_NOT_FOUND("S005", "Update record not found"),
	
	// Validation error
	INVALID_INPUT("E001", "Invalid arguments"),
	UNSUPPORTED_METHOD("E002", "Invalid method"),
	INVALID_JSON_INPUT("E003", "Invalid JSON input");
	
	@Getter
	private final String code;
	@Getter
	private final String message;
	
	ResultMsgEnum(String code, String message) {
		this.code = code;
		this.message = message;
	}
}
