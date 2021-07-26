Feature: Normal Test case

	Background:
		Given I delete all data
		
  Scenario: Add employee, retrive all employees
    When I set header variable "Content-Type" and value "application/json"
    When I call "POST" api "/api/v1/employees" with body
    """
    {
			"name": "takahashi ran",
			"salary": 200000,
			"department": "human resource"
		}
    """
    Then response code is 200
    Then response message is
    """
		{
	    "employee": {
			  "id": 1,
			  "name": "takahashi ran",
			  "salary": 200000,
			  "department": "human resource"
			},
			"result": {
			  "code": "S000",
			  "message": "Success"
			}
	  }
    """
    When I call "GET" api "/api/v1/employees"
    Then response code is 200
    Then response message is
    """
		{
		  "employeeList": [
		    {
		      "id": 1,
		      "name": "takahashi ran",
		      "salary": 200000,
		      "department": "human resource"
		    }
		  ],
		  "result": {
		    "code": "S000",
		    "message": "Success"
		  }
		}
    """
    
  Scenario: Add employee, retrive all employees, update employee, search employee 
    When I set header variable "Content-Type" and value "application/json"
 		When I call "POST" api "/api/v1/employees" with body
    """
    {
			"name": "hamasaki ayumi",
			"salary": 300000,
			"department": "talent"
		}
    """
    Then response code is 200
    When I call "POST" api "/api/v1/employees" with body
    """
    {
			"name": "utada hikaru",
			"salary": 350000,
			"department": "talent"
		}
    """
    Then response code is 200
    When I call "GET" api "/api/v1/employees"
    Then response code is 200
    Then response message is
    """
		{
		  "employeeList": [
		    {
		      "id": 1,
		      "name": "hamasaki ayumi",
		      "salary": 300000,
		      "department": "talent"
		    },
		    {
		      "id": 2,
		      "name": "utada hikaru",
		      "salary": 350000,
		      "department": "talent"
		    }
		  ],
		  "result": {
		    "code": "S000",
		    "message": "Success"
		  }
		}
    """
    When I call "PUT" api "/api/v1/employees/2" with body
    """
    {
			"name": "utada hikaru",
			"salary": 360000,
			"department": "talent"
		}
    """
    Then response code is 200
    When I call "GET" api "/api/v1/employees/2"
    Then response code is 200
    Then response message is
    """
		{
		  "employee": {
		    "id": 2,
		    "name": "utada hikaru",
		    "salary": 360000,
		    "department": "talent"
		  },
		  "result": {
		    "code": "S000",
		    "message": "Success"
		  }
		}
    """
    
  Scenario: Add employee, delete employee, search employee 
    When I set header variable "Content-Type" and value "application/json"
 		When I call "POST" api "/api/v1/employees" with body
    """
    {
			"name": "koike yuriko",
			"salary": 1500000,
			"department": "governor"
		}
    """
    Then response code is 200
    When I call "DELETE" api "/api/v1/employees/1"
    Then response code is 200
    When I call "GET" api "/api/v1/employees"
    Then response code is 200
    Then response message is
    """
		{
		  "employeeList": [],
		  "result": {
		    "code": "S001",
		    "message": "No result"
		  }
		}
    """
    
    
    