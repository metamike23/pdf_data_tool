package x.web3central.streams.middle;

import java.util.LinkedHashMap;
import java.util.Map;

// This class is built to hold all the extracted data from a single file
public class SingleUpload {
	
	private Pairs[] key_value_data;
	private Tokens[] string_array_data;
	
	public SingleUpload(Pairs[] pairs, Tokens[] tokens) {
		this.key_value_data = pairs;
		this.string_array_data = tokens;
	}
	
	public SingleUpload(Pairs[] pairs, Tokens token) {
		this.key_value_data = pairs;
		this.string_array_data = new Tokens[1];
		this.string_array_data[0] = token;
	}
	
	public SingleUpload(Pairs pair, Tokens[] tokens) {
		this.key_value_data = new Pairs[1];
		this.key_value_data[0] = pair;
		this.string_array_data = tokens;
	}
	
	public SingleUpload(Pairs[] pairs) {
		this.key_value_data = pairs;
		this.string_array_data = null;
	}
	
	
	public Map<String, String> getMetaData() {
		Map<String, String> temp = new LinkedHashMap<>();
		for(Pairs pair : this.key_value_data) {
			if(pair.getDataFormat() == DataFormat.METADATA) {
				temp.putAll(pair.getData());
			}
		}
		return temp;
		
	}
	
	public Map<String, String> getFormData() {
		Map<String, String> temp = new LinkedHashMap<>();
		for(Pairs pair : this.key_value_data) {
			if(pair.getDataFormat() == DataFormat.FORM) {
				temp.putAll(pair.getData());
			}
		}
		return temp;
		
	}
	
	

}
