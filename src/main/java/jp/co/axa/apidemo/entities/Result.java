package jp.co.axa.apidemo.entities;

import jp.co.axa.apidemo.codes.ResultMsgEnum;
import lombok.Getter;
import lombok.Setter;

public class Result {
	@Getter
    @Setter
	private String code;
	
	@Getter
    @Setter
	private String message;
	
	public Result(ResultMsgEnum resultmsg) {
		code = resultmsg.getCode();
		message = resultmsg.getMessage();
	}

}
