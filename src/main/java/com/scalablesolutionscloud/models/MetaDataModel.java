package com.scalablesolutionscloud.models;

import java.util.Map;

/**
 * Represents the metadata model used in the Spring REST service.
 * This model class interacts with the MetaDataController and MetaDataService.
 */
public class MetaDataModel {

    private Map<String, String> meta_data;
    private Map<String, String> program_execution;
    private int id;

    /**
     * Constructs a MetaDataModel object with the specified metadata, file data, program execution information, and identifier.
     *
     * @param meta_data         the map containing metadata keys and values
     * @param file_data         the map containing file data keys and values
     * @param program_execution the map containing program execution keys and values
     * @param id                the unique identifier associated with the MetaDataModel
     */
    public MetaDataModel(Map<String, String> meta_data,
                         Map<String, String> program_execution, int id) {
        this.meta_data = meta_data;
        this.program_execution = program_execution;
        this.id = id;
    }

    /**
     * Constructs an empty MetaDataModel object.
     */
    public MetaDataModel() {
    }
    
    
    /**
     * Constructs an MetaDataModel object with id
     */
    public MetaDataModel(int id) {
		super();
		this.id = id;
	}

	/**
     * Retrieves the map containing metadata keys and values.
     *
     * @return the metadata map
     */
    public Map<String, String> getMeta_data() {
        return meta_data;
    }

    /**
     * Sets the map containing metadata keys and values.
     *
     * @param meta_data the metadata map to set
     */
    public void setMeta_data(Map<String, String> meta_data) {
        this.meta_data = meta_data;
    }

    /**
     * Retrieves the map containing program execution keys and values.
     *
     * @return the program execution map
     */
    public Map<String, String> getProgram_execution() {
        return program_execution;
    }

    /**
     * Sets the map containing program execution keys and values.
     *
     * @param program_execution the program execution map to set
     */
    public void setProgram_execution(Map<String, String> program_execution) {
        this.program_execution = program_execution;
    }

    /**
     * Retrieves the unique identifier associated with the MetaDataModel.
     *
     * @return the identifier
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier associated with the MetaDataModel.
     *
     * @param id the identifier to set
     */
    public void setId(int id) {
        this.id = id;
    }

    
    
}
