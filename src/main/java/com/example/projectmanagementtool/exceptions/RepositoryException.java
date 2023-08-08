package com.example.projectmanagementtool.exceptions;

public class RepositoryException extends RuntimeException {

    public RepositoryException(String message) {
        super(message);
    }

    public RepositoryException(String message, Throwable throwable) {
        super(message, throwable);
    }
    public RepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
    public RepositoryException(Throwable cause) {
        super(cause);
    }

}
