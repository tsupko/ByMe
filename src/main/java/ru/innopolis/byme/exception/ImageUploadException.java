package ru.innopolis.byme.exception;

public class ImageUploadException extends Exception {
    public ImageUploadException(String message) {
        super(message);
    }

    public ImageUploadException(String message, Exception exception) {
        super(message, exception);
    }
}
