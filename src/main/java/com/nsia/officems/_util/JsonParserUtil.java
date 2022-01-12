package com.nsia.officems._util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import com.fasterxml.jackson.databind.ObjectMapper;

// import org.json.XML;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;


@Component
public class JsonParserUtil {

	private ObjectMapper mapper = new ObjectMapper();

	public boolean isValid(String jsonString) {
		try {
			new JSONObject(jsonString);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean isValidArray(String jsonString) {
		try {
			new JSONArray(jsonString);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public JSONObject parse(String jsonString) {
		try {
			JSONObject json = new JSONObject(jsonString);
			return json;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public JSONArray parseAsArray(String jsonString) {
		try {
			JSONArray json = new JSONArray(jsonString);
			return json;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String convertToString(JSONObject jsonObj) {
//		#TODO
		return null;
	}
	
	public String parseJsonObjToXFormValue(Object obj) throws JSONException {
		//if obj is array then it should be converted to space seperated string
		StringJoiner strJoiner = new StringJoiner(" ");
		
		if(obj instanceof JSONArray) {
			JSONArray array =  (JSONArray) obj;
			for (int i = 0; i < array.length(); i++) {
				Object item = array.get(i);
				strJoiner.add(item.toString());
			}
			
	    } else {
	    	strJoiner.add(obj.toString());
	    }
		
		return strJoiner.toString();
	}

	public Map<String, Object> jsonToMap(JSONObject json) throws JSONException {
        Map<String, Object> retMap = new HashMap<String, Object>();

        if(json != JSONObject.NULL) {
            retMap = toMap(json);
        }
        return retMap;
    }

    private Map<String, Object> toMap(JSONObject object) throws JSONException {
        Map<String, Object> map = new HashMap<String, Object>();

        Iterator<String> keysItr = object.keys();
        while(keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);

            if(value instanceof JSONArray) {
                value = toList((JSONArray) value);
            }

            else if(value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            map.put(key, value);
        }
        return map;
    }

    private List<Object> toList(JSONArray array) throws JSONException {
        List<Object> list = new ArrayList<Object>();
        for(int i = 0; i < array.length(); i++) {
            Object value = array.get(i);
            if(value instanceof JSONArray) {
                value = toList((JSONArray) value);
            }

            else if(value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            list.add(value);
        }
        return list;
	}
	

	public Map<String, String> convertJsonStringToMap(String jsonString) throws IOException
	{
		Map<String,String> map =mapper.readValue(jsonString, Map.class);
		return map;
	}
  
	public static String jsonToXml(String jsonContent)
    {
        // //converting json to xml
        // String xmlData= XML.toString(jsonContent);
        // System.out.println(xmlData);
		// return xmlData;
		return null;
    }
}
