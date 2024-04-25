package com.scalablesolutionscloud.memory;

import org.springframework.stereotype.Component;


import java.io.*;
import java.net.*;

/**
 * This component provides functionality to download files from a given URL and return them as InputStreams.
 */
@Component
public class FileDownloader {
	
	
	 /**
     * Constructs a FileDownloader object.
     */
    public FileDownloader() {
        super();
        // No-arg constructor
    }

    /**
     * Downloads a file from the specified URL and returns it as an InputStream.
     *
     * @param fileURL the URL of the file to download
     * @return an InputStream representing the downloaded file content
     * @throws IOException if an I/O error occurs while downloading the file
     */
    public InputStream downloadFileAsStream(String fileURL) throws IOException {
        URL url = new URL(fileURL);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        int responseCode = httpConn.getResponseCode();

        // Check if the response code is OK (200)
        if (responseCode == HttpURLConnection.HTTP_OK) {
            InputStream inputStream = httpConn.getInputStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            byte[] fileContent = outputStream.toByteArray();

            // Close the streams
            outputStream.close();
            inputStream.close();
            httpConn.disconnect();

            // Return a new InputStream for the downloaded file content
            return new ByteArrayInputStream(fileContent);

        } else {
            httpConn.disconnect();
            throw new IOException("Error: HTTP response code " + responseCode);
        }
    }
}
