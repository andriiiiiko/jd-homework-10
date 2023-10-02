package com.andriiiiiko.http;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

/**
 * This class provides a command-line interface for obtaining HTTP status code images.
 */
public class HttpImageStatusCli {

    private static final Logger LOG = LogManager.getLogger(HttpImageStatusCli.class);

    /**
     * The main method to start the HTTP status code image retrieval process.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        HttpImageStatusCli.askStatus();
    }

    /**
     * Prompt the user to enter an HTTP status code and retrieve the corresponding status image.
     */
    public static void askStatus() {
        try (Scanner scanner = new Scanner(System.in)) {
            LOG.info("Enter HTTP status code: ");
            String input = scanner.nextLine();

            try {
                int statusCode = Integer.parseInt(input);
                HttpStatusImageDownloader.downloadStatusImage(statusCode);
            } catch (NumberFormatException e) {
                LOG.error("Please enter a valid number");
            }
        }
    }
}
