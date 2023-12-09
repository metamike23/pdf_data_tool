package x.web3central.streams.middle;

import java.util.Map;

public class Forms implements Pairs {

	// Instance variable
	public static final DataFormat format = DataFormat.FORM;
	private Map<String, String> fields_and_values;
	
	
	// Constructor
	public Forms(Map<String, String> data) {
		this.fields_and_values = data;
	}
	

	
	// public method to add multiple key value pairs to the form data map
	public void populateContainer(Map<String, String> data) {
		this.fields_and_values = data;
	}
	
	// Implemented methods from the Pairs interface
	@Override
	public DataFormat getDataFormat() {
		// TODO Auto-generated method stub
		return format;
	}



	@Override
	public Map<String, String> getData() {
		return fields_and_values;
	}


}
