package com.orderProcessTesting.testware.utility;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ApiDetailReader {

	Logger log = LoggerFactory.getLogger(this.getClass());

	public String returnCompleteUri(String serviceName, String controllerName, String apiName) {
		log.info("serviceName :: " + serviceName + ", controllerName :: " + controllerName + ", apiName :: " + apiName);

		String jsonBodyOfFile = returnAPIsJsonFileContents(serviceName);
		JSONArray array = new JSONArray(jsonBodyOfFile);
		for (int i = 0; i < array.length(); i++) {
			JSONObject object = array.getJSONObject(i);
			if (object.getString("controllerName").equals(controllerName)) {
				JSONArray jsonArray = new JSONArray(object.getJSONArray("apiList"));
				for (int j = 0; j < jsonArray.length(); j++) {
					JSONObject internalObject = jsonArray.getJSONObject(j);
					if (internalObject.getString("apiName").equals(apiName)) {

						log.info("returning endpoint value :: "+ internalObject.getString("endpoint")+"\n");
						return internalObject.getString("endpoint");
					}
				}
			}
		}

		return "";
	}

	public String returnAPIsJsonFileContents(String jsonFileName) {
		String json = "";
		try {
			String file = Constants.API_FILE_PATH + jsonFileName + ".json";
			log.info("File Location :: " + file);

			return new String(Files.readAllBytes(Paths.get(file)));

		} catch (IOException e) {
			log.error(e.getLocalizedMessage(), "message");
			return json;
		}
	}
}
