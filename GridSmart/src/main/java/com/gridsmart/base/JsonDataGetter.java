package com.gridsmart.base;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class JsonDataGetter {

	JsonObject jsonFile;

	public JsonDataGetter(String jsonFilePath) {

		JsonParser parser = new JsonParser();

		try {

			jsonFile = (JsonObject) parser.parse(new FileReader(jsonFilePath));

		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public String getStrValue(String key) {

		return jsonFile.get(key).getAsString();
	}

}
