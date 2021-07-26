Feature: Validation Test case

	Background:
		Given I delete all data
		
	Scenario: Invalid Method
	  When I set header variable "Content-Type" and value "application/json"
    When I call "POST" api "/api/v1/employees/1" with body
    """
    {
			"name": "takahashi ran"
		}
    """
    Then response code is 405
    Then response message is
    """
		{
		  "code": "E002",
		  "message": "Invalid method"
		}
    """
    
  Scenario: salary out of bound
    When I set header variable "Content-Type" and value "application/json"
    When I call "POST" api "/api/v1/employees" with body
    """
    {
			"name": "takahashi ran",
			"salary": -1,
			"department": "human resource"
		}
    """
    Then response code is 400
    Then response message is
    """
		{
		  "code": "E001",
		  "message": "Invalid arguments"
		}
    """
    
   Scenario: invalid json input missing quote
    When I set header variable "Content-Type" and value "application/json"
    When I call "POST" api "/api/v1/employees" with body
    """
    {
			"name": "takahashi ran",
			"salary": 12000,
			"department": "human resource
		}
    """
    Then response code is 400
    Then response message is
    """
		{
		  "code": "E003",
		  "message": "Invalid JSON input"
		}
    """
    
  Scenario: update data does not exists
    When I set header variable "Content-Type" and value "application/json"
    When I call "PUT" api "/api/v1/employees/2" with body
    """
    {
			"name": "kageyama hironobu",
			"salary": 360000,
			"department": "singer"
		}
    """
    Then response code is 200
    Then response message is
    """
		{
		  "employee": {},
		  "result": {
		    "code": "S005",
		    "message": "Update record not found"
		  }
		}
    """

  Scenario: delete data does not exists
    When I call "DELETE" api "/api/v1/employees/2"
    Then response code is 200
    Then response message is
    """
		{
		  "code": "S003",
		  "message": "Delete record not found"
		}
    """  