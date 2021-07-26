package jp.co.axa.apidemo.entities.response;

import java.util.List;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.entities.Result;
import lombok.Getter;
import lombok.Setter;

public class EmployeeListResponse {
	@Getter
    @Setter
	private List<Employee> employeeList;
	@Getter
    @Setter
	private Result result;
}
