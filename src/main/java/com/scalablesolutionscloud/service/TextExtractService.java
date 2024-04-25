package com.scalablesolutionscloud.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scalablesolutionscloud.memory.FileDownloader;
import com.scalablesolutionscloud.models.ExtractedText;
import com.scalablesolutionscloud.textextract.CleanedText;
import com.scalablesolutionscloud.textextract.DominantFontTextExtractor;
import com.scalablesolutionscloud.textextract.SentenceTokenizer;
import com.scalablesolutionscloud.textextract.TextContainer;

/**
 * Service for extracting text data from PDF files accessed via URLs.
 * This service utilizes the Apache PDFBox library to load and analyze PDF documents,
 * and it incorporates various text processing techniques to extract, tokenize, and clean text data.
 * It provides detailed execution data for each step of the process, including times and metadata.
 */
@Service
public class TextExtractService {
    
    private static final Logger logger = LoggerFactory.getLogger(TextExtractService.class);

    @Autowired
    private FileDownloader fileDownloader;
    
    /**
     * Constructs a new TextExtractService.
     */
    public TextExtractService() {
        super();
    }
    
    
    /**
     * Extracts text from a PDF file located at the specified URL and associates it with a given User ID.
     * The method downloads the PDF, extracts text using various tools, tokenizes the text into sentences,
     * cleans the text, and compiles execution statistics and text data into an {@link ExtractedText} object.
     * 
     * @param url The URL from where the PDF file is downloaded.
     * @param id The user identification number associated with the extracted text for tracking.
     * @return An Optional containing an {@link ExtractedText} object if extraction is successful, otherwise an empty Optional.
     */
    public Optional<ExtractedText> extractTextFromUrl(String url, int id) {
        long startTime = System.currentTimeMillis();
        ExtractedText extractedText = new ExtractedText();
        extractedText.setId(id);

        try (InputStream file = fileDownloader.downloadFileAsStream(url);
             PDDocument document = PDDocument.load(file)) {
        	
        	
        	Map<String, String> executionData = new LinkedHashMap<>();
        	long downLoadTime = System.currentTimeMillis() - startTime;
            executionData.put("Downloading file time", (downLoadTime) + " ms");
            
            Map<String, String> fileData = new LinkedHashMap<>();
            Map<String, String> textData = new LinkedHashMap<>();

            fileData.put("Number of Pages", String.valueOf(document.getNumberOfPages()));
            fileData.put("File Size", String.valueOf(file.available()));
            
            long loadDocTime = System.currentTimeMillis() - startTime;
            executionData.put("Loading document time", loadDocTime + " ms");


            DominantFontTextExtractor stripper = new DominantFontTextExtractor();
            stripper.processDocument(document);
            
            long processDocTime = System.currentTimeMillis() - startTime;
            executionData.put("Processing document time", processDocTime + " ms");
            
            
            fileData.put("Title", stripper.getTitle() != null ? stripper.getTitle() : "");
            fileData.put("Author", stripper.getAuthor() != null ? stripper.getAuthor() : "");
            fileData.put("Creator", stripper.getCreator() != null ? stripper.getCreator() : "");
            fileData.put("Producer", stripper.getProducer() != null ? stripper.getProducer() : "");
            
            
            
            List<String> sentences = SentenceTokenizer.tokenizeSentences(stripper.getDominantFontTexts());
            
            long tokenizingTime = System.currentTimeMillis() - startTime;
            executionData.put("Tokenizing sentences time", tokenizingTime + " ms");

            TextContainer tc = new TextContainer();
            sentences.forEach(tc::add);

            CleanedText ct = new CleanedText(tc.getText());
            
            long cleanTextTime = System.currentTimeMillis() - startTime;
            executionData.put("Cleaning text time", cleanTextTime + " ms");

            textData.put("Text", ct.getCleanedText() != null ? ct.getCleanedText() : "");
            textData.put("Sentence Count", String.valueOf(ct.getSentenceCount()));
            textData.put("Token Count", String.valueOf(ct.getTokenCount()));
            textData.put("Character Count", String.valueOf(ct.getCharacterCount()));

            executionData.put("Total time taken", (downLoadTime + loadDocTime + processDocTime + tokenizingTime + cleanTextTime) + " ms");

            extractedText.setProgramExecution(executionData);
            extractedText.setFileData(fileData);
            extractedText.setTextData(textData);

            return Optional.of(extractedText);
        } catch (IOException e) {
            logger.error("Error loading the PDF document: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}




