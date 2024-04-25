package com.scalablesolutionscloud.models;

import java.util.Map;

/**
 * Model class representing form data in a Spring REST service.
 */
public class FormDataModel {
	
	private Map<String, Map<String, String>> filled_form_fields;
	private Map<String, Map<String, String>> unfilled_form_fields;
	private Map<String, String> meta_data;
    private Map<String, String> program_execution;
    private int id;
    
    /**
     * Constructs a FormDataModel object with the specified filled form fields, unfilled form fields,
     * metadata, program execution information, and identifier.
     *
     * @param filled_form_fields   a map containing filled form fields with their respective data
     * @param unfilled_form_fields a map containing unfilled form fields with their respective data
     * @param meta_data            a map containing metadata keys and values
     * @param program_execution    a map containing program execution keys and values
     * @param id                   the unique identifier for the form data
     */
	public FormDataModel(Map<String, Map<String, String>> filled_form_fields,
			Map<String, Map<String, String>> unfilled_form_fields, Map<String, String> meta_data,
			Map<String, String> program_execution, int id) {
		super();
		this.filled_form_fields = filled_form_fields;
		this.unfilled_form_fields = unfilled_form_fields;
		this.meta_data = meta_data;
		this.program_execution = program_execution;
		this.id = id;
	}
	
	/**
     * Constructs a FormDataModel object with the specified identifier.
     *
     * @param id the unique identifier for the form data
     */
    public FormDataModel(int id) {
        this.id = id;
    }

    /**
     * Default constructor for FormDataModel.
     */

	public FormDataModel() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
     * Retrieves the map containing filled form fields.
     *
     * @return a map containing filled form fields with their respective data
     */
    public Map<String, Map<String, String>> getFilled_form_fields() {
        return filled_form_fields;
    }

    /**
     * Sets the map containing filled form fields.
     *
     * @param filled_form_fields a map containing filled form fields with their respective data
     */
    public void setFilled_form_fields(Map<String, Map<String, String>> filled_form_fields) {
        this.filled_form_fields = filled_form_fields;
    }

    /**
     * Retrieves the map containing unfilled form fields.
     *
     * @return a map containing unfilled form fields with their respective data
     */
    public Map<String, Map<String, String>> getUnfilled_form_fields() {
        return unfilled_form_fields;
    }

    /**
     * Sets the map containing unfilled form fields.
     *
     * @param unfilled_form_fields a map containing unfilled form fields with their respective data
     */
    public void setUnfilled_form_fields(Map<String, Map<String, String>> unfilled_form_fields) {
        this.unfilled_form_fields = unfilled_form_fields;
    }

    /**
     * Retrieves the map containing metadata.
     *
     * @return a map containing metadata keys and values
     */
    public Map<String, String> getMeta_data() {
        return meta_data;
    }

    /**
     * Sets the map containing metadata.
     *
     * @param meta_data a map containing metadata keys and values
     */
    public void setMeta_data(Map<String, String> meta_data) {
        this.meta_data = meta_data;
    }

    /**
     * Retrieves the map containing program execution information.
     *
     * @return a map containing program execution keys and values
     */
    public Map<String, String> getProgram_execution() {
        return program_execution;
    }

    /**
     * Sets the map containing program execution information.
     *
     * @param program_execution a map containing program execution keys and values
     */
    public void setProgram_execution(Map<String, String> program_execution) {
        this.program_execution = program_execution;
    }

    /**
     * Retrieves the unique identifier for the form data.
     *
     * @return the unique identifier for the form data
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the form data.
     *
     * @param id the unique identifier for the form data
     */
    public void setId(int id) {
        this.id = id;
    }
}