package jp.co.axa.apidemo.entities.response;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.entities.Result;
import lombok.Getter;
import lombok.Setter;

public class EmployeeResponse {
	@Getter
    @Setter
	private Employee employee;
	@Getter
    @Setter
	private Result result;
}
