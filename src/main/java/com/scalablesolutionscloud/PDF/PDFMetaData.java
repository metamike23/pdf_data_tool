package com.scalablesolutionscloud.PDF;

import java.util.Map;

/**
 * Represents the metadata extracted from a PDF document.
 */
public class PDFMetaData {

    private Map<String, String> metadata;

    /**
     * Constructs a PDFMetaData object with the specified metadata.
     *
     * @param metadata the map containing metadata keys and values
     */
    public PDFMetaData(Map<String, String> metadata) {
        this.metadata = metadata;
    }

    /**
     * Constructs an empty PDFMetaData object.
     */
    public PDFMetaData() {
    }

    /**
     * Retrieves the map containing metadata keys and values.
     *
     * @return the metadata map
     */
    public Map<String, String> getMetadata() {
        return metadata;
    }

    /**
     * Sets the map containing metadata keys and values.
     *
     * @param metadata the metadata map to set
     */
    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }
}
