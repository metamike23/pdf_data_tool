package x.web3central.streams.input;


import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.text.PDFTextStripper;

public class Pdf {

	private String file_name;
	private String file_owner;
	private int page_count;
	private boolean hasFormFields;
	
	
	public Pdf(String fileName, String fileOwner) {
		this.file_name = fileName;
		this.file_owner = fileOwner;
	}
	
	public Pdf(String fileName) {
		this.file_name = fileName;
		this.file_owner = "unknown";
	}
	
	public PDDocument createPdfDocument(InputStream inputStream) throws IOException {
		 try (PDDocument document = PDDocument.load(inputStream)) {
	            return document;
	        }
		 
	}
	
	
	// Small Bug. This method destoys the PDDocument object after execution causes issue with I/O stream
	 public String extractTextFromPDF(InputStream inputStream) throws IOException {
		 	PDDocument doc = PDDocument.load(inputStream);
		 	PDFTextStripper textStripper = new PDFTextStripper();
		    textStripper.setSortByPosition(true);
		    return textStripper.getText(doc);
	  }
	 
	 public Map<String, String> getPdfMetaData(PDDocument document) {
		// Get document information
         PDDocumentInformation documentInformation = document.getDocumentInformation();
     

         // Get all metadata entries
         Map<String, String> metadataMap = new LinkedHashMap<>();
         metadataMap.put("Extension", ".pdf");
         for(String entry : documentInformation.getMetadataKeys()) {
        	 metadataMap.put(entry, documentInformation.getCustomMetadataValue(entry));
         }
         
         
		return metadataMap;
	 }
	 
	 public boolean containsForm(PDDocument document) {
		 PDDocumentCatalog catalog = document.getDocumentCatalog();
	     PDAcroForm acroForm = catalog.getAcroForm();
	     
	     if(acroForm != null) {
	    	 return true;
	     }
	     else {
	    	 return false;
	     }
		 
	 }
	 
	  // Public method to extract form fields/values from the PDF
	  public Map<String, String> extractFormFields(PDDocument document) throws IOException {
		  	Map<String, String> keyVals = new LinkedHashMap<>();
	        PDDocumentCatalog catalog = document.getDocumentCatalog();
	        PDAcroForm acroForm = catalog.getAcroForm();

	        if (acroForm != null) {
	           this.hasFormFields = true;
	           keyVals = extractFormFields(acroForm);
	               
	        } else {
	            this.hasFormFields = false;
	            System.out.println("The PDF does not contain a form.");
	              
	       }
	      
		return keyVals;
	  }
	  
	// private method to extract form fields/values from the pdf
		private Map<String, String> extractFormFields(PDAcroForm acroForm) throws IOException {
			Map<String, String> keyVals = new LinkedHashMap<>();
		       for (PDField field : acroForm.getFieldTree()) {
		           String fieldName = field.getFullyQualifiedName();
		           String fieldValue = field.getValueAsString();
		           keyVals.put(fieldName, fieldValue);
		       }
		        
		   return keyVals;
		}
		        
	    
		public String getFile_name() {
			return file_name;
		}

		public String getFile_owner() {
			return file_owner;
		}

		public int getPage_count() {
			return page_count;
		}

		public boolean isAForm() {
			return hasFormFields;
		}
		
	  
	    
}
