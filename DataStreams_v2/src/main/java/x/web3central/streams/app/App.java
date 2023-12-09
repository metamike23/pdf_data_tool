package x.web3central.streams.app;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.pdfbox.pdmodel.PDDocument;

import x.web3central.streams.input.Base64Stream;
import x.web3central.streams.input.Pdf;
import x.web3central.streams.middle.Forms;
import x.web3central.streams.middle.MetaData;
import x.web3central.streams.middle.Pairs;
import x.web3central.streams.middle.SingleUpload;

public class App {

	
	
	
public static void main(String[] args) throws IOException {	
		long startTime = System.nanoTime();
		// Fafsa App B64
		String base64Data = "base64_string";
		
		Base64Stream b64Stream = new Base64Stream(base64Data);
		Base64Stream b64Stream2 = new Base64Stream(base64Data);
		
		InputStream is = b64Stream.toInputStream(b64Stream.getBytes());
		InputStream is2 = b64Stream.toInputStream(b64Stream2.getBytes());
	
		
		Pdf pdf = new Pdf("fafsaApp");
		
		PDDocument doc = pdf.createPdfDocument(is);
		int pages = doc.getNumberOfPages();
		
		
		Map<String, String> formdata = pdf.extractFormFields(doc);
		String txt = pdf.extractTextFromPDF(is2);
		Map<String, String> metadata2 = pdf.getPdfMetaData(doc);
		
		
		Forms form = new Forms(formdata);
		MetaData metadata = new MetaData(metadata2);
		
		Pairs[] pairs = new Pairs[2];
		pairs[0] = form;
		pairs[1] = metadata;
		
		SingleUpload single = new SingleUpload(pairs);
		Map<String, String> md = single.getMetaData();
		Map<String, String> fd = single.getFormData();
		System.out.println("\nMETA DATA");
		for(Map.Entry<String, String> entry: md.entrySet()) {
			String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + ": " + value);
		}
		
		System.out.println("\nFORM DATA");
		
		int i = 0;
		for(Map.Entry<String, String> entry: fd.entrySet()) {
			String key = entry.getKey();
            String value = entry.getValue();
            if(!value.equals("Off") && value.length() > 0) {
            	System.out.println(key + ": " + value);
                i++;
            }
            
            if(i == 20) break;
		}
		
		
		System.out.println("\nTEXT DATA");
		System.out.println("Character Count: " + txt.length());
		System.out.println("Token Count: " + txt.split(" ").length);
		System.out.println("Page Count: " + pages);
		System.out.println("File Size: " + b64Stream.getByteSize() + " bytes");
		
		long endTime = System.nanoTime();

        // Calculate and print the elapsed time in milliseconds
        long elapsedTimeInMillis = (endTime - startTime) / 1_000_000;
        
        System.out.println("\nBENCHMARK");
        System.out.println("Execution Time: " + elapsedTimeInMillis + " milliseconds");
		
	       
	}

}
