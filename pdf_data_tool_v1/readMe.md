# Java PDF Data Extractor
- Can be uploaded as files or converted from their base64 String representation

-------------------------------------------------------------------------------------

### Base64ToPDF
- The Base64ToPDF class converts the base64 String to a pdf file and stores it in the target/output/ folder

------------------------------------------------------------------------------------------------

### PDFToText
- The PDFToText class takes a pdf file and extracts both its text and form data
- The unstrunctured text is extracted as a String
- Form data is extracted as a Key Value Pair Map

--------------------------------------------------------------------------------------------------

### FormData
- The FormData class holds all the data from the pdf file in text form
- The data can be exorted as JSON but more export formats will be added

-------------------------------------------------------------------------------------------------------

### FileToBase64
- The FileToBase64 class is used to convert a file of any type into its base64 String representation

------------------------------------------------------------------------------------------------------

## Improvements 

#### 1
- I am using the java.io library to handle the Input/Output for the Application
- The standard java.io library does not support multithreading
- So I would need to use java.nio instead to make the tool be able to use multiple threads
- java.nio can handle concurrent IO streams

#### 2
- Some of the code is redundant so I could use class hierachy's and interfaces to reduce this
- A lot of features will share behavior so this can help reduce the amount of code needed to be written
