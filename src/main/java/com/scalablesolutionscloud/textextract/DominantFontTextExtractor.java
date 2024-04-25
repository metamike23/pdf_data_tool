package com.scalablesolutionscloud.textextract;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Extends {@link PDFTextStripper} to process a PDF document to identify and extract text that uses the most
 * frequently occurring font style and size. This class is intended to filter text based on its visual importance
 * as inferred from the font usage within the document.
 *
 * The process involves two main passes over the document:
 * 1) The first pass identifies the dominant font style and size.
 * 2) The second pass extracts text that matches the dominant font style and size.
 *
 * Metadata from the document is also extracted, including title, author, creator, and producer.
 */
public class DominantFontTextExtractor extends PDFTextStripper {
	
	// Instance Variables
    private Map<String, Integer> fontCounter = new HashMap<>();
    private List<String> dominantFontTexts = new ArrayList<>();
    private String dominantFont = null;
    private float dominantFontSize = 0;
    private String title;
    private String author;
    private String creator;
    private String producer;
    
    /**
     * Constructs a new DominantFontTextExtractor with necessary initializations.
     * @throws IOException if an error occurs in the base PDFTextStripper initialization.
     */
    public DominantFontTextExtractor() throws IOException {
    }
    

    /**
     * Processes the entire document to extract text using the dominant font.
     * Initially reads metadata and sets up for two-phase text extraction.
     * 
     * @param document the PDDocument to process.
     * @throws IOException if an error occurs during PDF processing.
     */
    public void processDocument(PDDocument document) throws IOException {
    	
    	readMetadata(document);
    	
        // First pass to determine the dominant font and size
        stripPage(document);
        determineDominantFontAndSize();
//        System.out.println("Dominant Font and Size: " + dominantFont + ", " + dominantFontSize);
        
        // Clear previous data for the second pass
        dominantFontTexts.clear();
        fontCounter.clear();
        // Second pass to collect text with the dominant font and size
        stripPage(document, true);
    }
    
    /**
     * Overrides {@link PDFTextStripper#writeString} to customize text extraction logic.
     * In the first pass, it populates font usage statistics. In the second pass, it collects texts that use the dominant font.
     *
     * @param text the text to process.
     * @param textPositions list of {@link TextPosition} objects for the text.
     * @throws IOException if an error occurs during text processing.
     */
    @Override
    protected void writeString(String text, List<TextPosition> textPositions) throws IOException {
        if (dominantFont != null) {
            // Processing for the second pass
            TextPosition firstPosition = textPositions.get(0);
            String fontName = firstPosition.getFont().getName();
            float fontSize = firstPosition.getFontSizeInPt();
            if (fontName.equals(dominantFont) && fontSize == dominantFontSize) {
                dominantFontTexts.add(text);
            }
        } else {
            // Processing for the first pass
            for (TextPosition position : textPositions) {
                String fontName = position.getFont().getName();
                float fontSize = position.getFontSizeInPt();
                String fontKey = fontName + "#" + fontSize;
                fontCounter.merge(fontKey, 1, Integer::sum);
            }
        }
    }
    
    /**
     * Performs a single page text stripping pass. Used for both the first and second passes.
     *
     * @param document the document to strip.
     * @param isSecondPass true if this is the second pass (collecting dominant text), false if first (determining font).
     * @throws IOException if an error occurs during stripping.
     */
    private void stripPage(PDDocument document, boolean isSecondPass) throws IOException {
        for (int page = 0; page < document.getNumberOfPages(); ++page) {
            setStartPage(page + 1);
            setEndPage(page + 1);
            writeText(document, new NullWriter()); // This will either update fontCounter or collect texts
        }
    }
    
    
    /**
     * Performs the first pass over the document to populate the font usage statistics.
     * This method calls the {@link #stripPage(PDDocument, boolean)} method with a flag set to false,
     * indicating that this is the first pass, which should only gather data about font usage without extracting text.
     *
     * @param document the PDDocument to process for the first pass.
     * @throws IOException if an error occurs during the text stripping process.
     */
    private void stripPage(PDDocument document) throws IOException {
        stripPage(document, false);
    }
    
    /**
     * Determines the most frequently used font and its size from collected data.
     */
    private void determineDominantFontAndSize() {
        String dominantKey = fontCounter.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
        if (dominantKey != null) {
            String[] parts = dominantKey.split("#");
            dominantFont = parts[0];
            dominantFontSize = Float.parseFloat(parts[1]);
        }
    }
    
    
    /**
     * Prints the texts extracted using the dominant font and size to the standard output.
     * This method iterates through all texts collected during the second pass and prints them with an index.
     * It is used for debugging or demonstration purposes to show how text has been selectively extracted based
     * on font style and size criteria.
     */
    public void printDominantFontTexts() {
        System.out.println("Texts with the Dominant Font and Size:\n");
       int j = 1;
        for (String text : dominantFontTexts) {
           
            System.out.println(j++ + ") " + text); // Print the text
        }
    }
    
    /**
     * Reads metadata from the document and stores it in class variables.
     * @param document the document from which to read metadata.
     */
    public void readMetadata(PDDocument document) {
        // Get the document information
		PDDocumentInformation info = document.getDocumentInformation();
		
		// Save metadata
		this.title = info.getTitle();
		this.author = info.getAuthor();
		this.creator = info.getCreator();
		this.producer = info.getProducer();
    }
    
    
    /**
     * Returns the texts extracted using the dominant font and size.
     * @return a List of strings containing the extracted text.
     */
    public List<String> getDominantFontTexts() {
		return dominantFontTexts;
	}
    
    /**
     * Returns the title extracted from the document's metadata.
     * @return the document's title.
     */
    public String getTitle() {
    	return this.title;
    }
    
    /**
     * Returns the author extracted from the document's metadata.
     * @return the document's author.
     */
    public String getAuthor() {
    	return this.author;
    }
    
    /**
     * Returns the creator extracted from the document's metadata.
     * @return the document's creator.
     */
    public String getCreator() {
    	return this.creator;
    }
    
    /**
     * Returns the producer extracted from the document's metadata.
     * @return the document's producer.
     */
    public String getProducer() {
    	return this.producer;
    }


    /**
     * A private inner class that serves as a writer which discards its output.
     * Used to facilitate text stripping without actual output.
     */
    private static class NullWriter extends java.io.Writer {
        @Override
        public void write(char[] cbuf, int off, int len) {
            // Do nothing
        }
        @Override
        public void flush() {
            // Do nothing
        }
        @Override
        public void close() {
            // Do nothing
        }
    }
}
