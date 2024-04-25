package com.scalablesolutionscloud.PDF;


import java.util.HashMap;
import java.util.Map;


/**
 * Utility class for processing form data extracted from PDF documents.
 */
public class FormDataProcessor {
	
	  /**
     * Merges two maps containing nested maps into one map.
     * If there are duplicate keys, the value from map2 overwrites the value from map1.
     *
     * @param map1 the first map to merge
     * @param map2 the second map to merge
     * @return a new map containing the merged data
     */
	 private static Map<String, Map<String, String>> mergeMaps(Map<String, Map<String, String>> map1, Map<String, Map<String, String>> map2) {
	        Map<String, Map<String, String>> mergedMap = new HashMap<>();

	        // Add all entries from map1
	        for (Map.Entry<String, Map<String, String>> entry : map1.entrySet()) {
	            mergedMap.put(entry.getKey(), entry.getValue());
	        }

	        // Add entries from map2, handling collisions
	        for (Map.Entry<String, Map<String, String>> entry : map2.entrySet()) {
	            String key = entry.getKey();
	            Map<String, String> value = entry.getValue();
	            // If the key already exists in mergedMap, decide how to handle the conflict
	            if (mergedMap.containsKey(key)) {
	                // Here, you can decide whether to overwrite the existing value or merge the values
	                // For simplicity, let's overwrite the value with the one from map2
	                mergedMap.put(key, value);
	            } else {
	                // If the key doesn't exist in mergedMap, simply add the entry
	                mergedMap.put(key, value);
	            }
	        }

	        return mergedMap;
	    }
	 
	 /**
	  * Prints the contents of a map containing nested maps on new lines.
	  *
	  * @param map the map to print
	  */
	 public static void printMap(Map<String, Map<String, String>> map) {
		    for (Map.Entry<String, Map<String, String>> entry : map.entrySet()) {
		        System.out.println(entry.getKey() + ":");
		        Map<String, String> valueMap = entry.getValue();
		        for (Map.Entry<String, String> valueEntry : valueMap.entrySet()) {
		            System.out.println("  " + valueEntry.getKey() + ": " + valueEntry.getValue());
		        }
		    }
		}
	 
	 
	 	/**
	     * Populates filled form fields from a map containing form fields.
	     *
	     * @param fields the map containing form fields
	     * @return a new map containing only filled form fields
	     */
	    private static Map<String, Map<String, String>> populateFilledFields(Map<String, Map<String, String>> fields) {
	        Map<String, Map<String, String>> filledFields = new HashMap<>();
	        for (Map.Entry<String, Map<String, String>> entry : fields.entrySet()) {
	            String currentValue = entry.getValue().get("Current Value");
	            if (!currentValue.isEmpty() && !currentValue.equals("Off")) {
	                filledFields.put(entry.getKey(), entry.getValue());
	            }
	        }
	        return filledFields;
	    }

	    /**
	     * Populates unfilled form fields from a map containing form fields.
	     *
	     * @param fields the map containing form fields
	     * @return a new map containing only unfilled form fields
	     */
	    private static Map<String, Map<String, String>> populateUnfilledFields(Map<String, Map<String, String>> fields) {
	        Map<String, Map<String, String>> unfilledFields = new HashMap<>();
	        for (Map.Entry<String, Map<String, String>> entry : fields.entrySet()) {
	            String currentValue = entry.getValue().get("Current Value");
	            if (currentValue.isEmpty() || currentValue.equals("Off")) {
	                unfilledFields.put(entry.getKey(), entry.getValue());
	            }
	        }
	        return unfilledFields;
	    }
	    
	    

	    /**
	     * Extracts PDF form data from two maps containing text and non-text inputs.
	     *
	     * @param text_inputs     a map containing text inputs
	     * @param non_text_input  a map containing non-text inputs
	     * @return a PDFFormData object containing the extracted data
	     */
	    public static PDFFormData extractPDFFormData(Map<String, Map<String, String>> text_inputs, Map<String, Map<String, String>> non_text_input) {
	        Map<String, Map<String, String>> merged_map = mergeMaps(text_inputs, non_text_input);
	        return new PDFFormData(populateFilledFields(merged_map), populateUnfilledFields(merged_map));
	    }
}


/*
 * 	 public static void main(String[] args) {
    String pdfFilePath = "unfilled-fafsa.pdf";

    // Open the PDF file as an InputStream and load the PDF document
    try (FileInputStream fileInputStream = new FileInputStream(new File(pdfFilePath));
         PDDocument document = PDFFormExtractor.createPdfDocument(fileInputStream)) {


        // Access the document's AcroForm
        PDAcroForm acroForm = document.getDocumentCatalog().getAcroForm();
        if (acroForm != null) {
            // Extract all text fields (filled and unfilled)
            Map<String, Map<String, String>> textFields = PDFFormExtractor.extractAllTextFields(document);

            // Extract all non-text fields (filled and unfilled)
            Map<String, Map<String, String>> nonTextInputFields = PDFFormExtractor.extractNonTextInputFields(acroForm);

            Map<String, Map<String, String>> mergedMap = mergeMaps(textFields, nonTextInputFields);
            
            Map<String, Map<String, String>> filled = populateFilledFields(mergedMap);
            Map<String, Map<String, String>> unfilled = populateUnfilledFields(mergedMap);

            // Print the merged map
            printMap(filled);
            System.out.println("\n\n");
            printMap(unfilled);

        } else {
            System.out.println("No AcroForm found in this PDF.");
        }
    } catch (IOException e) {
        System.err.println("Error occurred while handling the PDF:");
        e.printStackTrace();
    }
}
 */

