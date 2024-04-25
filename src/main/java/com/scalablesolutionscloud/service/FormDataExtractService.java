package com.scalablesolutionscloud.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scalablesolutionscloud.PDF.FormDataProcessor;
import com.scalablesolutionscloud.PDF.PDFFormData;
import com.scalablesolutionscloud.PDF.PDFFormExtractor;
import com.scalablesolutionscloud.memory.FileDownloader;
import com.scalablesolutionscloud.models.FormDataModel;

@Service
public class FormDataExtractService {
	
	
	
	@Autowired
    private FileDownloader fileDownloader;
	
	
    public Optional<FormDataModel> extractFormDataFromPDF(String url, int id) {
   	 long startTime = System.currentTimeMillis();
   	 
        try (InputStream file = fileDownloader.downloadFileAsStream(url);
             PDDocument document = PDDocument.load(file)) {
        	
        	
        	Map<String, String> executionData = new LinkedHashMap<>();
        	long downLoadTime = System.currentTimeMillis() - startTime;
            executionData.put("Downloading file time", (downLoadTime) + " ms");
            
            Map<String, String> fileData = new LinkedHashMap<>();
           

            fileData.put("Number of Pages", String.valueOf(document.getNumberOfPages()));
            fileData.put("File Size", String.valueOf(file.available()));
            fileData.put("Extension", ".pdf");
            
            long loadDocTime = System.currentTimeMillis() - startTime;
            executionData.put("Loading document time", loadDocTime + " ms");


            // extract metadata
            Map<String, String> meta_data = PDFFormExtractor.getPdfMetaData(document);
            
            // extract form fields
            Map<String, Map<String, String>> text_fields = PDFFormExtractor.extractAllTextFields(document);
            Map<String, Map<String, String>> non_text_fields = PDFFormExtractor.extractNonTextInputFields(document);
            
            PDFFormData pdf_form_data = FormDataProcessor.extractPDFFormData(text_fields, non_text_fields);
            
            
            long processDocTime = System.currentTimeMillis() - startTime;
            executionData.put("Form data extraction time", processDocTime + " ms");

            executionData.put("Total time taken", (downLoadTime + loadDocTime + processDocTime) + " ms");

            return Optional.of(new FormDataModel(pdf_form_data.getFilled_form_fields(), pdf_form_data.getUnfilled_form_fields(), meta_data, executionData, id));
        } catch (IOException e) {
//            logger.error("Error loading the PDF document: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.of(new FormDataModel(id));
   	
   }


}
