package com.scalablesolutionscloud.PDF;


public class PDFProcessor {
	

	
	/*
	 *    public static void main(String[] args) {
        String pdfFilePath = "unfilled-fafsa.pdf";

        // Open the PDF file as an InputStream and load the PDF document
        try (FileInputStream fileInputStream = new FileInputStream(new File(pdfFilePath));
             PDDocument document = PDFFormExtractor.createPdfDocument(fileInputStream)) {

            // Retrieve and print PDF metadata
            Map<String, String> metaData = PDFFormExtractor.getPdfMetaData(document);
            System.out.println("PDF Metadata:");
            metaData.forEach((key, value) -> System.out.println(key + ": " + value));

            // Check if the PDF contains form fields and print the result
            boolean containsFormFields = PDFFormExtractor.hasFormFields(document);
            System.out.println("\nDoes the PDF contain form fields? " + containsFormFields);

            // Access the document's AcroForm
            PDAcroForm acroForm = document.getDocumentCatalog().getAcroForm();
            if (acroForm != null) {
                // Extract all text fields (filled and unfilled)
                Map<String, Map<String, String>> textFields = PDFFormExtractor.extractAllTextFields(document);
                System.out.println("\nAll Text Fields:");
                textFields.forEach((fieldName, details) -> {
                    System.out.println("Field: " + fieldName);
                    details.forEach((detailName, detailValue) -> System.out.println("  " + detailName + ": " + detailValue));
                });

                Map<String, Map<String, String>> nonTextInputFields = PDFFormExtractor.extractNonTextInputFields(acroForm);
                System.out.println("\nNon-Text Input Fields:");
                nonTextInputFields.forEach((fieldName, values) -> {
                    System.out.println("Field: " + fieldName);  // Always print the field name
                    values.forEach((key, value) -> {
                        System.out.println("  " + key + ": " + value);  // Print all details of the field
                    });
                });
            } else {
                System.out.println("No AcroForm found in this PDF.");
            }
        } catch (IOException e) {
            System.err.println("Error occurred while handling the PDF:");
            e.printStackTrace();
        }
    }
	

	 */
	

}
