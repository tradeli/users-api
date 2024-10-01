package com.viafoura.users_api.adapters.outgoing.http;

public class AvatarServiceException extends Exception {
    private int errorCode;

    public AvatarServiceException(String message) {
        super(message);
    }

    public AvatarServiceException(String message, Throwable ex) {
        super(message, ex);
    }

    public AvatarServiceException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
