package jp.co.axa.apidemo.controllers;

import jp.co.axa.apidemo.codes.ResultMsgEnum;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.entities.Result;
import jp.co.axa.apidemo.entities.response.EmployeeListResponse;
import jp.co.axa.apidemo.entities.response.EmployeeResponse;
import jp.co.axa.apidemo.services.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = "/employees",  
	method = RequestMethod.GET,
	produces = "application/json; charset=UTF-8")
    public ResponseEntity<EmployeeListResponse> getEmployees() {
    	EmployeeListResponse response = new EmployeeListResponse();
        List<Employee> employees = employeeService.retrieveEmployees();
        
        Result result;
        if(employees.size() > 0)
        	result = new Result(ResultMsgEnum.SUCCESS);
        else
        	result = new Result(ResultMsgEnum.NO_RESULT);
        response.setEmployeeList(employees);
        response.setResult(result);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/employees/{employeeId}",  
	method = RequestMethod.GET,
	produces = "application/json; charset=UTF-8")
    public ResponseEntity<EmployeeResponse> getEmployee(@PathVariable(name="employeeId")Long employeeId) {
    	EmployeeResponse response = new EmployeeResponse();
    	Optional<Employee> outputEmployee = employeeService.getEmployee(employeeId);
    	Result result;
        if(outputEmployee.isPresent()) {
        	result = new Result(ResultMsgEnum.SUCCESS);
        	response.setEmployee(outputEmployee.get());
        } else {
        	result = new Result(ResultMsgEnum.NO_RESULT);
        	response.setEmployee(new Employee());
        }
    	
    	response.setResult(result);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/employees",  
    		method = RequestMethod.POST,
    		consumes = "application/json",
    		produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<EmployeeResponse> saveEmployee(@RequestBody Employee employee) {
    	EmployeeResponse response = new EmployeeResponse();
    	Employee outputEmployee = employeeService.saveEmployee(employee);
    	response.setEmployee(outputEmployee);
    	response.setResult(new Result(ResultMsgEnum.SUCCESS));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/employees/{employeeId}",  
	method = RequestMethod.DELETE,
	produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<EmployeeResponse> deleteEmployee(@PathVariable(name="employeeId")Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        EmployeeResponse response = new EmployeeResponse();
    	Employee responseEmployee = new Employee();
    	responseEmployee.setId(employeeId);
    	response.setEmployee(responseEmployee);
    	response.setResult(new Result(ResultMsgEnum.DELETE_SUCCESS));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/employees/{employeeId}",  
    		method = RequestMethod.PUT,
    		consumes = "application/json",
    		produces = "application/json; charset=UTF-8")
    	    @ResponseBody
    public ResponseEntity<EmployeeResponse> updateEmployee(@RequestBody Employee employee,
                               @PathVariable(name="employeeId")Long employeeId){
    	EmployeeResponse response = new EmployeeResponse();
    	// search if employee is existing
        Optional<Employee> emp = employeeService.getEmployee(employeeId);
        Employee responseEmployee = new Employee();
    	
        // update the value if exists
        if(emp.isPresent()) {
        	responseEmployee.setId(employeeId);
        	response.setEmployee(responseEmployee);
        	employee.setId(employeeId);
            employeeService.updateEmployee(employee);
            response.setResult(new Result(ResultMsgEnum.UPDATE_SUCCESS));
        } else {
        	response.setEmployee(responseEmployee);
        	response.setResult(new Result(ResultMsgEnum.UPDATE_NOT_FOUND));
        }

        return new ResponseEntity<>(response, HttpStatus.OK); 
    }
    
    /**
     * Only use for testing
     */
    @RequestMapping(value = "/employees",  
    		method = RequestMethod.DELETE,
    		produces = "application/json; charset=UTF-8")
    	    @ResponseBody
    	    public Result deleteAllEmployee() {
    	        employeeService.deleteAll();
    	    	return new Result(ResultMsgEnum.DELETE_SUCCESS);
    	    }

}
