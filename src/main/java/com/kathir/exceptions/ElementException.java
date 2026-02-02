package com.kathir.exceptions;

public class ElementException extends FrameworkException {
    public ElementException(String message, Throwable cause) {
        super(message, cause);
    }

    public static class ElementNotFoundException extends ElementException {
        public ElementNotFoundException(String locator, Throwable cause) {
            super("Element not found: " + locator, cause);
        }
    }

    public static class ElementNotInteractableFrameworkException extends ElementException {
        public ElementNotInteractableFrameworkException(String locator, Throwable cause) {
            super("Element not interactable: " + locator, cause);
        }
    }

    public static class WaitTimeoutException extends ElementException {
        public WaitTimeoutException(String message, Throwable cause) {
            super("Wait timed out: " + message, cause);
        }
    }

}
