package com.scalablesolutionscloud.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.scalablesolutionscloud.models.ExtractedText;
import com.scalablesolutionscloud.service.TextExtractService;

import java.util.Optional;

/**
 * A REST controller for handling requests to extract text from PDF documents located at specific URLs.
 * This controller leverages {@link TextExtractService} to process PDFs and extract relevant text data,
 * which includes text itself, metadata, and statistics about the extraction process.
 */
@RestController
public class TextExtractController {
	
	private static final Logger logger = LoggerFactory.getLogger(TextExtractController.class);

    @Autowired
    private TextExtractService textExtractService;
    
    
    /**
     * Handles a POST request to extract text from a PDF file accessible via a specified URL.
     * The request should include the URL of the PDF and an identification number associated with the extraction request.
     *
     * @param request A {@link TextExtractRequest} object containing the URL of the PDF and a unique identifier.
     * @return A {@link ResponseEntity} containing the {@link ExtractedText} if extraction is successful,
     *         otherwise returns an HTTP 404 Not Found response.
     */
    @PostMapping("/extract-text")
    public ResponseEntity<ExtractedText> extractText(@RequestBody TextExtractRequest request) {
 
        
        Optional<ExtractedText> extractedText = textExtractService.extractTextFromUrl(
                request.getUrl(), request.getId());
        
        return extractedText
                .map(text -> {
                    return ResponseEntity.ok(text);
                })
                .orElseGet(() -> {
                	logger.error("No text extracted for URL: {}", request.getUrl());
                    return ResponseEntity.notFound().build();
                });
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
