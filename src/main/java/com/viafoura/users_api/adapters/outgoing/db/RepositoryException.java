package com.viafoura.users_api.adapters.outgoing.db;

public class RepositoryException extends Exception {
    private int errorCode;

    public RepositoryException(String message) {
        super(message);
    }

    public RepositoryException(String message, Throwable ex) {
        super(message, ex);
    }

    public RepositoryException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
