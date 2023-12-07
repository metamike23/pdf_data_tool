# Java PDF Data Extractor

Welcome to the Java PDF Data Extractor, a versatile tool that enables you to seamlessly handle PDF files. Whether you need to extract text, form data, or convert between base64 and PDF, this tool has you covered.

## Features

### Base64ToPDF
Easily convert base64 strings to PDF files with the `Base64ToPDF` class. Your converted files will be neatly stored in the `target/output/` folder for convenient access.

### PDFToText
Harness the power of `PDFToText` to extract both text and form data from your PDF files. Unstructured text is captured as a String, while form data is neatly organized into a Key-Value Pair Map for effortless analysis.

### FormData
The `FormData` class serves as a comprehensive container for all the data extracted from PDF files. Export your data as JSON, with support for additional export formats on the horizon.

### FileToBase64
Need to convert files of any type into base64 string representation? The `FileToBase64` class streamlines this process, offering versatility in handling diverse file formats.

## Improvements on the Horizon

### 1. Multithreading with java.nio
Enhance the tool's performance by implementing multithreading using `java.nio`. Unlike `java.io`, `java.nio` supports concurrent I/O streams, paving the way for more efficient and responsive operations.

### 2. Code Optimization with Class Hierarchy and Interfaces
Efficiency matters, and we're on a mission to reduce redundancy in the code. By introducing class hierarchies and interfaces, we aim to streamline features, making the codebase more elegant and maintainable. Expect a leaner and more cohesive structure in future updates.

## Dependencies

This project utilizes the following dependencies:

| Library                  | Version  |
|--------------------------|----------|
| org.apache.pdfbox:pdfbox | 2.0.30   |
| com.fasterxml.jackson.core:jackson-databind | 2.12.3 |

## Dependency Management

This project uses [Maven](https://maven.apache.org/) for efficient dependency management. Maven helps streamline the build process and ensures smooth integration of project dependencies.

## Future Plans

We plan to integrate the [Spring Boot](https://spring.io/projects/spring-boot) web framework for enhanced front-end capabilities. Stay tuned for exciting updates as we continue to evolve the project!


Thank you for choosing the Java PDF Data Extractor. We're committed to continuous improvement, and your feedback is invaluable in shaping the future of this tool. Stay tuned for more updates and enhancements!
