package com.ttknp.bservicewebcontroller.exception;

public class ContentNotAllowed extends RuntimeException {

    // private String currentCause;

    public ContentNotAllowed(String message) {
        super(message);
        // this.currentCause = message;
    }

    /**
    public String getCurrentCause() {
        return currentCause;
    }
    */

}