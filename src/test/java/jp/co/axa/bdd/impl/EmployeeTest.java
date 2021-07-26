package jp.co.axa.bdd.impl;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EmployeeTest {
	
	private Client client; // client to call the rest api
	private Map<String, String> httpHeader;
    
	@Given("I delete all data")
	public void i_delete_all_data() throws IOException {
		Client deleteData = new Client("/api/v1/employees", "DELETE", null);
		deleteData.sendRequest();
		assertEquals(deleteData.getResponseCode(), HttpStatus.OK.value());
	}

	@When("I set header variable {string} and value {string}")
	public void i_set_header_variable_and_value(String key, String value) {
	    if(httpHeader == null)
	    	httpHeader = new HashMap<>();
	    httpHeader.put(key, value);
	}
	
	@When("I call {string} api {string} with body")
	public void i_call_api_with_body(String method, String path, String body) 
		throws IOException {
			client = new Client(path, method, body);
			client.setHeader(httpHeader);
			client.sendRequest();
	}

	@When("I call {string} api {string}")
	public void i_call_api(String method, String path) 
		throws IOException {
			client = new Client(path, method, null);
			client.setHeader(httpHeader);
			client.sendRequest();
	}
	
	@Then("response code is {int}")
	public void response_code_is(Integer responseCode) {
		assertEquals(responseCode.intValue(), client.getResponseCode());
	}

	@Then("response message is")
	public void response_message_is(String docString) throws IOException {
		// compare two json strings
		ObjectMapper mapper = new ObjectMapper();
	    assertEquals(mapper.readTree(docString), mapper.readTree(client.getResponse()));
	}

	private class Client {
		private URL url;
		private HttpURLConnection connection; 
		private final String HOSTNAME = "http://localhost:8080";
		private String path;
		private String method;
		private String body;
		private Map<String, String> headerMap;
		private int responseCode;
		private String response;
		
		public Client(String path, String method, String body) {
			this.path = path;
			this.method = method;
			this.body = body;
		}
		
		public void setHeader(Map<String, String> headerMap) {
			this.headerMap = headerMap;
		}
		
		// function to call the rest api
		public void sendRequest() throws IOException {
			url = new URL(HOSTNAME + path);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod(method);
			
			if(headerMap != null) {
				for(String key : headerMap.keySet())
					connection.setRequestProperty(key, headerMap.get(key));
			}
			// process json for post and put methods
			if(HttpMethod.POST.matches(method) || HttpMethod.PUT.matches(method)) {
				OutputStream os = connection.getOutputStream();
				os.write(body.getBytes());
				os.flush();
			}
	        responseCode = connection.getResponseCode();
	        InputStream inputStream;
	        // get error stream for error response
	        if(responseCode < 300) {
	        	inputStream = connection.getInputStream();
	        } else {
	        	inputStream = connection.getErrorStream();
	        }
	        	
	        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

	        String output;
	        response = "";
	        // concatenate the output
	        while ((output = reader.readLine()) != null) {
	        	response = response + output;
	        }
	        connection.disconnect();
		}
		
		public String getResponse() {
			return response;
		}
		
		public int getResponseCode() {
			return responseCode;
		}
	}

}
