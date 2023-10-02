package com.andriiiiiko.http;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * This class provides functionality to check and retrieve HTTP status code images from
 * <a href="https://http.cat/">Website</a>.
 */
public class HttpStatusChecker {

    private static final Logger LOG = LogManager.getLogger(HttpStatusChecker.class);
    public static final String BASE_PHOTO_URL = "https://http.cat/";
    private static final OkHttpClient HTTP_CLIENT = new OkHttpClient();

    /**
     * Private constructor to prevent instantiation of HttpStatusChecker objects.
     */
    private HttpStatusChecker() {

    }

    /**
     * Retrieves the URL of the HTTP status code image for the given code.
     *
     * @param code The HTTP status code for which to retrieve the image.
     * @return The URL of the HTTP status code image.
     */
    public static String getUrlImage(int code) {
        String photoURL = BASE_PHOTO_URL + code + ".jpg";

        Request request = new Request.Builder()
                .url(photoURL)
                .build();

        try (Response response = HTTP_CLIENT.newCall(request).execute()) {
            if (response.code() == 404) {
                LOG.error("Images for code {} do not exist", code);
            }
        } catch (IOException e) {
            LOG.error("Error making the request: {}", e.getMessage());
        }

        return photoURL;
    }
}
