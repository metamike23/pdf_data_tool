package com.scalablesolutionscloud.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.scalablesolutionscloud.models.MetaDataModel;
import com.scalablesolutionscloud.service.MetaDataService;

@RestController
public class MetaDataController {
	
	@Autowired
	private MetaDataService metaDataService;
	
	@PostMapping("/extract-metadata")
    public ResponseEntity<Optional<MetaDataModel>> extractMetaData(@RequestBody TextExtractRequest request) {
        // Use the metaDataService to extract metadata
        Optional<MetaDataModel> extractedMetaData = metaDataService.extractMetaDataFromPDF(
                request.getUrl(), request.getId());
        
        // If metadata is found, return it, otherwise return not found
        if (extractedMetaData != null) {
            return ResponseEntity.ok(extractedMetaData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Data Transfer Object (DTO) that encapsulates the data for a text extraction request.
     * It contains the URL of a PDF and a unique identifier for the extraction process.
     */
    static class TextExtractRequest {
        private String url;
        private int id;

        /**
         * Gets the URL of the PDF to be processed.
         * @return the URL of the PDF.
         */
        public String getUrl() {
            return url;
        }

        /**
         * Gets the unique identifier for the extraction request.
         * @return the ID of the extraction request.
         */
        public int getId() {
            return id;
        }

    }
}
