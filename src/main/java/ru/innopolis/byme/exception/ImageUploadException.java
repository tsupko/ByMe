package ru.innopolis.byme.exception;

import java.io.IOException;

public class ImageUploadException extends Exception {
    public ImageUploadException(String message) {
        super(message);
    }

    public ImageUploadException(String message, Exception exception) {

    }
}
