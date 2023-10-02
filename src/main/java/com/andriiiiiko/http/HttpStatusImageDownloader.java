package com.andriiiiiko.http;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * This class provides functionality to download HTTP status code images and save them to local files.
 */
public class HttpStatusImageDownloader {

    private static final Logger LOG = LogManager.getLogger(HttpStatusImageDownloader.class);
    private static final OkHttpClient HTTP_CLIENT = new OkHttpClient();

    /**
     * Private constructor to prevent instantiation of HttpStatusImageDownloader objects.
     */
    private HttpStatusImageDownloader() {

    }

    /**
     * Downloads the HTTP status code image for the given code and saves it to a local file.
     *
     * @param code The HTTP status code for which to download the image.
     */
    public static void downloadStatusImage(int code) {
        String url = HttpStatusChecker.getUrlImage(code);
        String filePath = "images/status-" + code + ".jpg";

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = HTTP_CLIENT.newCall(request).execute()) {
            if (response.isSuccessful()) {
                saveImageToFile(response.body(), filePath);
                LOG.info("File downloaded to: {}", filePath);
            } else {
                LOG.error("Failed to download file. Response code: {}. URL: {}", response.code(), url);
            }
        } catch (IOException e) {
            LOG.error("Error while downloading image: {}", e.getMessage(), e);
        }
    }

    /**
     * Saves the image data from the HTTP response body to a local file.
     *
     * @param responseBody The response body containing image data.
     * @param filePath     The path of the local file to save the image.
     */
    private static void saveImageToFile(ResponseBody responseBody, String filePath) {
        try {
            try (InputStream inputStream = responseBody.byteStream();
                 OutputStream outputStream = new FileOutputStream(filePath)) {
                byte[] buffer = new byte[1024];
                int bytesRead;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                outputStream.flush();
            }
        } catch (IOException e) {
            LOG.error("Error while saving image to file: {}", e.getMessage(), e);
        }
    }
}
