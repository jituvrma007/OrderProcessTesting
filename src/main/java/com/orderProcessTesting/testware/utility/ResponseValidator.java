package com.orderProcessTesting.testware.utility;

import java.io.File;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.jayway.jsonpath.JsonPath;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.lang3.StringUtils;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;


public class ResponseValidator {

	public String jsonStringValue(Response response, String regPath) {
		return JsonPath.read(response.getBody().asString(),regPath).toString();	
	} 

	public List<String> jsonListOfStringValue(Response response, String regPath ) {
		return JsonPath.read(response.getBody().asString(),regPath);
	}

	public Set<String> jsonSetOfStringValue(Response response, String regPath ) {
		return new LinkedHashSet<>(JsonPath.read(response.getBody().asString(),regPath));
	}

	public boolean schemaValidator(Response response,  String schemaFilePath){
		if(!StringUtils.isBlank(schemaFilePath)){
			response.then().assertThat().contentType(ContentType.JSON)
					.and()
					.body(matchesJsonSchema(new File(schemaFilePath)));
			return true;
		}
		return false;
	}

}
