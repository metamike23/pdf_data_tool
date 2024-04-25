package com.scalablesolutionscloud.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scalablesolutionscloud.PDF.PDFFormExtractor;
import com.scalablesolutionscloud.memory.FileDownloader;
import com.scalablesolutionscloud.models.MetaDataModel;


@Service
public class MetaDataService {
	
	 @Autowired
	    private FileDownloader fileDownloader;
	    
	    /**
	     * Constructs a new TextExtractService.
	     */
	    public MetaDataService() {
	        super();
	    }
	    
	    
	    public Optional<MetaDataModel> extractMetaDataFromPDF(String url, int id) {
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


	           
	             Map<String, String> meta_data = PDFFormExtractor.getPdfMetaData(document);
	             
	             long processDocTime = System.currentTimeMillis() - startTime;
	             executionData.put("Processing document time", processDocTime + " ms");
	 
	             executionData.put("Total time taken", (downLoadTime + loadDocTime + processDocTime) + " ms");

	            

	             return Optional.of(new MetaDataModel(meta_data, executionData, id));
	         } catch (IOException e) {
//	             logger.error("Error loading the PDF document: " + e.getMessage());
	         } catch (Exception e) {
	             e.printStackTrace();
	         }
	         return Optional.of(new MetaDataModel(id));
	    	
	    }

}
