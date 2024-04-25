package com.scalablesolutionscloud.PDF;

import java.util.Map;

/**
 * Represents the data extracted from a PDF form.
 */
public class PDFFormData {
	
    private Map<String, Map<String, String>> filled_form_fields;
    private Map<String, Map<String, String>> unfilled_form_fields;

    /**
     * Constructs a PDFFormData object with the specified filled and unfilled form fields.
     *
     * @param filled_form_fields   a map containing filled form fields
     * @param unfilled_form_fields a map containing unfilled form fields
     */
    public PDFFormData(Map<String, Map<String, String>> filled_form_fields, Map<String, Map<String, String>> unfilled_form_fields) {
        this.filled_form_fields = filled_form_fields;
        this.unfilled_form_fields = unfilled_form_fields;
    }

    /**
     * Gets the map containing filled form fields.
     *
     * @return the map containing filled form fields
     */
    public Map<String, Map<String, String>> getFilled_form_fields() {
        return filled_form_fields;
    }

    /**
     * Sets the map containing filled form fields.
     *
     * @param filled_form_fields a map containing filled form fields
     */
    public void setFilled_form_fields(Map<String, Map<String, String>> filled_form_fields) {
        this.filled_form_fields = filled_form_fields;
    }

    /**
     * Gets the map containing unfilled form fields.
     *
     * @return the map containing unfilled form fields
     */
    public Map<String, Map<String, String>> getUnfilled_form_fields() {
        return unfilled_form_fields;
    }

    /**
     * Sets the map containing unfilled form fields.
     *
     * @param unfilled_form_fields a map containing unfilled form fields
     */
    public void setUnfilled_form_fields(Map<String, Map<String, String>> unfilled_form_fields) {
        this.unfilled_form_fields = unfilled_form_fields;
    }
}
