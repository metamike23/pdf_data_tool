package com.scalablesolutionscloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.scalablesolutionscloud.models.FormDataModel;
import com.scalablesolutionscloud.service.FormDataExtractService;

import java.util.Optional;

@RestController
public class FormExtractController {

    @Autowired
    private FormDataExtractService formDataExtractService;

    /**
     * Handles POST requests to extract form data from a specified PDF URL.
     *
     * @param request The request containing the URL of the PDF and an identification number.
     * @return ResponseEntity containing the extracted form data or a not found status.
     */
    @PostMapping("/extract-form")
    public ResponseEntity<FormDataModel> extractForm(@RequestBody FormExtractRequest request) {
        Optional<FormDataModel> formData = formDataExtractService.extractFormDataFromPDF(request.getUrl(), request.getId());

        return formData.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Data Transfer Object to encapsulate the data for the PDF extraction request.
     */
    static class FormExtractRequest {
        private String url;
        private int id;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}