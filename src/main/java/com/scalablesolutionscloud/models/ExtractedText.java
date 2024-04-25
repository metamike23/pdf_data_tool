package com.scalablesolutionscloud.models;

import java.util.Map;

/**
 * Represents extracted text data along with additional metadata.
 * This model is used in conjunction with the Spring MVC framework,
 * primarily in the TextExtractController and TextExtractService components.
 */
public class ExtractedText {

	private int id;
    private Map<String, String> text_data;
    private Map<String, String> file_data;
    private Map<String, String> program_execution;

    /**
     * Default constructor for the ExtractedText class.
     */
    public ExtractedText() {

    }

    /**
     * Retrieves the text data.
     *
     * @return the text data
     */
    public Map<String, String> getTextData() {
        return text_data;
    }

    /**
     * Sets the text data.
     *
     * @param text_data the text data to set
     */
    public void setTextData(Map<String, String> text_data) {
        this.text_data = text_data;
    }

    /**
     * Retrieves the file data.
     *
     * @return the file data
     */
    public Map<String, String> getFileData() {
        return file_data;
    }

    /**
     * Sets the file data.
     *
     * @param file_data the file data to set
     */
    public void setFileData(Map<String, String> file_data) {
        this.file_data = file_data;
    }

    /**
     * Retrieves the program execution metadata.
     *
     * @return the program execution metadata
     */
    public Map<String, String> getProgramExecution() {
        return program_execution;
    }

    /**
     * Sets the program execution metadata.
     *
     * @param program_execution the program execution metadata to set
     */
    public void setProgramExecution(Map<String, String> program_execution) {
        this.program_execution = program_execution;
    }

    /**
     * Retrieves the ID associated with the extracted text.
     *
     * @return the ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID associated with the extracted text.
     *
     * @param id the ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Generates a string representation of the ExtractedText object.
     *
     * @return a string containing text data, file data, ID, and program execution metadata
     */
    @Override
    public String toString() {
        return "Text Data: " + text_data + "\n" +
                "File Data: " + file_data + "\n" +
                "ID: " + id + "\n" +
                "Program Execution: " + program_execution;
    }
}
