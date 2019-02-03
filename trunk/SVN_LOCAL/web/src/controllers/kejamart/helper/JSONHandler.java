package kejamart.helper;


import java.io.BufferedReader;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

import org.json.JSONObject;
import org.json.JSONArray;

public class JSONHandler {

	private static Logger logger = Logger.getLogger(JSONHandler.class);

	public JSONObject convertRequestBufferToJSONObject(
			HttpServletRequest request) throws Exception {

		StringBuffer jb = new StringBuffer();
		JSONObject jsonObject = null;
		String line = null;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
				jb.append(line);
		} catch (Exception e) { // report an error
			logger.info("error");
		}

		logger.info("Raw JSON>> " + jb.toString());

		try {
			jsonObject = new JSONObject(jb.toString());
		} catch (Exception e) {
			// crash and burn
			logger.info("error");
		}

		return jsonObject;
	}

	
	// String json = jb.toString();
	public static String appendToArray(String jsonString, int key, String value)
			throws Exception {
		
		logger.info("it is functionally working!!!!");
		JSONObject rs;		
		rs = new JSONObject();
		rs.put("id", Integer.parseInt(value));

		JSONArray ja = new JSONArray(jsonString);
		
		key=ja.length();
		ja.put(key, rs);
		String myArray = ja.toString();
		return myArray;
	}

	public static String removeFromArray(String jsonString, int value)
			throws Exception {
		logger.info("inner function");
		JSONArray tempArray = new JSONArray();
		JSONArray jsar =new JSONArray(jsonString);	    
		
		//loop through the result ids			
		for(int j = 0; j < jsar.length(); j++){			
			int  resultIdInt = Integer.parseInt(jsar.getJSONObject(j).get("id").toString());
			logger.info(resultIdInt +"<<--->>"+value);
			if(resultIdInt != value){
				tempArray.put(jsar.getJSONObject(j));
			}
		}
		
		String myArray = tempArray.toString();
		return myArray;
	}

}
