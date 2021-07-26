package jp.co.axa.apidemo.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import jp.co.axa.apidemo.codes.ResultMsgEnum;
import jp.co.axa.apidemo.entities.Result;

import javax.validation.ConstraintViolationException;
/**
 * This class handles all the exceptions that might occur in the api
 */
@ControllerAdvice
public class ExceptionAdvice {

	
	
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Result processValidation(ConstraintViolationException e) {
		
		return new Result(ResultMsgEnum.INVALID_INPUT);
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ResponseBody
	public Result processUnsupportedMethod(HttpRequestMethodNotSupportedException e) {
		
		return new Result(ResultMsgEnum.UNSUPPORTED_METHOD);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Result processTypeMismatch(MethodArgumentTypeMismatchException e) {
		
		return new Result(ResultMsgEnum.INVALID_INPUT);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Result processArgNotValid(MethodArgumentNotValidException e) {
		
		return new Result(ResultMsgEnum.INVALID_INPUT);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Result processNotReadable(HttpMessageNotReadableException e) {
		
		return new Result(ResultMsgEnum.INVALID_JSON_INPUT);
	}
	
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Result processEmptyResult(EmptyResultDataAccessException e) {
		
		return new Result(ResultMsgEnum.DELETE_NOT_FOUND);
	}
	
	
	
	
}
