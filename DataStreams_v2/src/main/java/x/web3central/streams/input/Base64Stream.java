package x.web3central.streams.input;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.tika.Tika;
import org.apache.tika.metadata.Metadata;

public class Base64Stream {
	
	
	private String base64;
	private byte[] bytes;
	private double byte_size;
	
	
	
	public Base64Stream(String b64) {
		this.base64 = b64;
		byte[] decodedBytes = java.util.Base64.getDecoder().decode(b64);
        this.bytes = decodedBytes;
        this.byte_size = decodedBytes.length;
	}
	
	public InputStream toInputStream(byte[] decodedBytes) {
       
        return new ByteArrayInputStream(decodedBytes);
    }
	
	public Map<String, String> extractMetadata1(InputStream inputStream) throws IOException {
    	Map<String, String> metadataMap = new HashMap<>();
        // metadata extraction logic here using Apache Tika Metadata class
        try {
            Tika tika = new Tika();
            Metadata metadata = new Metadata();
            tika.parse(inputStream, metadata);

            // Extract common metadata fields
            // Extract all metadata fields
            for (String name : metadata.names()) {
                metadataMap.put(name, metadata.get(name));
            }

            // We can add more fields for any meta data we notice and want to collect

        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception
        }
        return metadataMap;
    }
	
	public String getFileNameFromStream(InputStream inputStream) throws IOException {
		Tika tika = new Tika();
        return tika.detect(inputStream);
	}
	
	
	
	public String getBase64() {
		return base64;
	}
	
	public byte[] getBytes() {
		return bytes;
	}
	
	public double getByteSize() {
		return byte_size;
	}
	
	

}
