package belto_java_codebase.pdf.base64;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FormData {
	
	private Map<String, String> key_value_pairs;
	private String text;

	
	
	public FormData(Map<String, String> fieldsAndValues, String txt) {
		this.key_value_pairs = fieldsAndValues;
		this.text = txt;
		
		
	}
	
	
	public void exportToJSON() throws JsonProcessingException {
		Map<String, String> dataMap = this.key_value_pairs;
		ObjectMapper objectMapper = new ObjectMapper();
		 // Convert Map to JSON string
        	String jsonString = objectMapper.writeValueAsString(dataMap);
        	String[] tokens = jsonString.split(",");
        	System.out.println("{");
        	System.out.println("\t[");
        	for(String token : tokens) {
        		if(!token.contains("Off")) {
        		System.out.println("\t " + token + ",");
        		}
        	}
        	System.out.println("\t]");
        	System.out.println("}");
	}


	// Need Getter methods for private instance variables
	// Need the code to create an actual JSON object 
	
	
	

}
