package com.scalablesolutionscloud.PDF;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDCheckBox;
import org.apache.pdfbox.pdmodel.interactive.form.PDChoice;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.pdmodel.interactive.form.PDRadioButton;
import org.apache.pdfbox.pdmodel.interactive.form.PDTextField;


/**
 * A utility class for processing PDF documents to extract various types of form field data.
 * This class utilizes the Apache PDFBox library to load and manipulate PDF documents.
 * It includes methods to extract metadata, check for the existence of form fields, and retrieve form field values.
 *
 * Methods are provided to:
 * - Check if a PDF document contains form fields.
 * - Extract all form fields, both filled and unfilled.
 * - Extract all text fields, specifically.
 * - Extract non-text input fields such as checkboxes, radio buttons, and choice fields.
 * - Process individual form fields based on their type.
 * - Retrieve metadata about the PDF document.
 *
 * This class is designed to be used in applications that need to process and analyze form data within PDFs.
 */

public class PDFFormExtractor {

	/**
	 * Creates a PDDocument from an InputStream. This method ensures that the PDF document is loaded
	 * directly from the provided InputStream and managed within the method scope, allowing for safe closure.
	 *
	 * @param inputStream the InputStream from which to load the PDF document.
	 * @return a loaded PDDocument ready for processing.
	 * @throws IOException if an error occurs while loading the PDF document from the stream.
	 */
	public static PDDocument createPdfDocument(InputStream inputStream) throws IOException {
	    try (PDDocument document = PDDocument.load(inputStream)) {
	        return document;
	    }
	}

	/**
	 * Extracts metadata from a PDF document, including standard properties such as extension, version, and encryption state.
	 * Metadata keys are checked for null values to ensure robustness and prevent potential NullPointerExceptions.
	 *
	 * @param document the PDDocument from which metadata is to be extracted.
	 * @return a Map containing keys and values representing the document's metadata.
	 * @throws NullPointerException if any metadata keys are null (handled internally to avoid this exception).
	 */
	public static Map<String, String> getPdfMetaData(PDDocument document) {
	    PDDocumentInformation info = document.getDocumentInformation();
	    Map<String, String> metadata = new LinkedHashMap<>();
	    metadata.put("Extension", ".pdf");
	    if (info.getMetadataKeys() != null) {
	        for (String key : info.getMetadataKeys()) {
	            String value = info.getCustomMetadataValue(key);
	            if (key != null && value != null) {
	                metadata.put(key, value);
	            }
	        }
	    }
	    metadata.put("Version", String.valueOf(document.getVersion()));
	    metadata.put("Encryption", document.isEncrypted() ? "Yes" : "No");
	    return metadata;
	}
	
	/**
     * Checks if the provided PDF document contains any form fields.
     * 
     * @param document the PDDocument object to check for form fields
     * @return true if the document contains form fields, false otherwise
     */
    public static boolean hasFormFields(PDDocument document) {
        if (document != null) {
            PDDocumentCatalog catalog = document.getDocumentCatalog();
            if (catalog != null) {
                PDAcroForm acroForm = catalog.getAcroForm();
                // Check if the AcroForm is not null and it has fields
                return acroForm != null && acroForm.getFields() != null && !acroForm.getFields().isEmpty();
            }
        }
        return false;  // Return false if document or catalog is null, or no fields are present
    }

    /**
     * Extracts all text fields from the PDF's AcroForm, both filled and unfilled.
     * This method returns a map where each key is the field name and each value is another map containing
     * the current value and field type of the field.
     *
     * @param document the PDDocument containing the AcroForm to be processed.
     * @return a Map where each key is the field name, and the value is a Map with keys "Current Value" and "Field Type".
     * @throws IOException if an error occurs during field extraction.
     */
    public static Map<String, Map<String, String>> extractAllTextFields(PDDocument document) throws IOException {
        Map<String, Map<String, String>> textFields = new LinkedHashMap<>();
        PDDocumentCatalog catalog = document.getDocumentCatalog();
        PDAcroForm acroForm = catalog.getAcroForm();

        if (acroForm != null) {
            for (PDField field : acroForm.getFieldTree()) {
                if (field instanceof PDTextField) {
                    Map<String, String> fieldDetails = new LinkedHashMap<>();
                    fieldDetails.put("Current Value", field.getValueAsString());
                    fieldDetails.put("Field Type", "Text");  // Since we know these are text fields
                    textFields.put(field.getFullyQualifiedName(), fieldDetails);
                }
            }
        }

        return textFields;
    }

    /**
     * Extracts all form fields from a PDF document, regardless of whether they are filled or not.
     * This method ensures that all form fields are captured, providing a comprehensive view of the form's state.
     *
     * @param document the PDDocument containing the AcroForm from which fields are extracted.
     * @return a Map containing the fully qualified names of the fields and their respective values,
     *         with null values replaced by an empty string to ensure uniformity.
     * @throws IOException if an error occurs during field extraction.
     */
    public static Map<String, String> extractAllFormFields(PDDocument document) throws IOException {
        Map<String, String> allFields = new LinkedHashMap<>();
        PDDocumentCatalog catalog = document.getDocumentCatalog();
        PDAcroForm acroForm = catalog.getAcroForm();

        if (acroForm != null) {
            for (PDField field : acroForm.getFieldTree()) {
                String fieldValue = field.getValueAsString();
                allFields.put(field.getFullyQualifiedName(), fieldValue == null ? "" : fieldValue);
            }
        }

        return allFields;
    }	 	 
 
    /**
     * Extracts data for all non-text input fields within a PDF's AcroForm, including field types.
     * This method processes various field types excluding text fields, 
     * collecting data in a structured map format where each field's type is also recorded.
     *
     * @param document The PDDocument containing the AcroForm to process.
     * @return A map where each key is a field name and each value is another map
     *         containing key-value pairs representing field properties and their values along with the field type.
     * @throws IOException If there is an error processing the PDF fields.
     */
    public static Map<String, Map<String, String>> extractNonTextInputFields(PDDocument document) throws IOException {
        Map<String, Map<String, String>> nonTextInputFields = new HashMap<>();
        PDDocumentCatalog catalog = document.getDocumentCatalog();
        PDAcroForm acroForm = catalog.getAcroForm();
        if (acroForm != null) {
            for (PDField field : acroForm.getFieldTree()) {
                processField(nonTextInputFields, field);
            }
        }
        return nonTextInputFields;
    }
	/**
	 * Processes a single field from the PDF's AcroForm and determines the type of field.
	 * If it's a checkbox, radio button, or choice field, it processes them accordingly.
	 *
	 * @param nonTextInputFields The map to store field data.
	 * @param field The field to process.
	 * @throws IOException If there is an error extracting data from the field.
	 */
    private static void processField(Map<String, Map<String, String>> nonTextInputFields, PDField field) throws IOException {
        String fieldType = field instanceof PDCheckBox ? "CheckBox" :
                           field instanceof PDRadioButton ? "RadioButton" :
                           field instanceof PDChoice ? "Choice" : "Unknown";
        
        Map<String, String> fieldValues = field instanceof PDCheckBox ? extractValues((PDCheckBox) field, ((PDCheckBox) field).getOnValues()) :
                              field instanceof PDRadioButton ? extractValues((PDRadioButton) field, ((PDRadioButton) field).getOnValues()) :
                              field instanceof PDChoice ? extractValues((PDChoice) field, new HashSet<>(((PDChoice) field).getOptionsDisplayValues())) :
                              new HashMap<>();

        if (!fieldValues.isEmpty()) {
            fieldValues.put("Field Type", fieldType);
            nonTextInputFields.put(field.getFullyQualifiedName(), fieldValues);
        }
    }
    
    /**
     * Extracts values for a given field and compares them against a set of options to determine current and potential values.
     * This method is specialized to handle checkboxes and radio buttons correctly by checking their actual state,
     * not just the value reported by `getValueAsString()`, which can sometimes misleadingly return "Off" for checked fields.
     *
     * @param field The field to extract values from.
     * @param options A set of possible options for this field, particularly relevant for checkboxes and radio buttons.
     * @return A map containing the current value and potential values of the field.
     * @throws IOException If there is an error during value extraction.
     */
    private static Map<String, String> extractValues(PDField field, Set<String> options) throws IOException {
        Map<String, String> fieldValues = new HashMap<>();
        String currentValue = getFieldCurrentValue(field, options);

        fieldValues.put("Current Value", currentValue);

        // Adding potential values for all fields, including those with 'Off' current value
        options.stream()
            .filter(option -> !option.equals(currentValue)) // Filter out the current value from potential options
            .forEach(option -> fieldValues.put("Potential Value", option));

        return fieldValues;
    }

    /**
     * Determines the current value of the field based on its type.
     * This method ensures the currentValue is not changed once set, allowing it to be effectively final.
     *
     * @param field The field whose value is to be determined.
     * @param options A set of valid options for the field, used to validate the current value.
     * @return The current value of the field.
     */
    private static String getFieldCurrentValue(PDField field, Set<String> options) {
        if (field instanceof PDCheckBox) {
            PDCheckBox checkBox = (PDCheckBox) field;
            return checkBox.isChecked() ? checkBox.getOnValue() : "Off";
        } else if (field instanceof PDRadioButton) {
            PDRadioButton radioButton = (PDRadioButton) field;
            String value = radioButton.getValueAsString();
            return options.contains(value) ? value : "Off";
        }
        return field.getValueAsString();  // Default case for other field types
    }

 
}

