package x.web3central.streams.middle;

import java.util.LinkedHashMap;
import java.util.Map;

public class MetaData implements Pairs {

	// Instance variables
	public static final DataFormat format = DataFormat.METADATA;
	private Map<String, String> meta_data; 
	
	
	
	// Overloaded Constructors
	public MetaData(String fileType, String fileName, String fileOwner) {
		this.meta_data = new LinkedHashMap<>();
		meta_data.put("File Type", fileType);
		meta_data.put("File Name 2", fileName);
		meta_data.put("File Owner", fileOwner);
	}
	
	public MetaData(String fileType, String fileName) {
		this.meta_data = new LinkedHashMap<>();
		meta_data.put("File Type", fileType);
		meta_data.put("File Name 2", fileName);
	}
	
	public MetaData(String fileType) {
		this.meta_data = new LinkedHashMap<>();
		meta_data.put("File Type", fileType);
	}
	
	public MetaData(Map<String, String> metaData) {
		this.meta_data = metaData;
	}
	
	
	// public method to add a single meta data field
	public void addMetaData(String entry, String entryValue) {
		this.meta_data.put(entry, entryValue);
	}
	
	// public method to add multiple meta data fields
	public void addMetaData(Map<String, String> meta_data) {
		this.meta_data.putAll(meta_data);
	}

	
	@Override
	public Map<String, String> getData() {
		// TODO Auto-generated method stub
		return meta_data;
	}
	
	@Override
	public DataFormat getDataFormat() {
		return format;
	}
	
	

}
