package com.kathir.exceptions;

public class TestDataException extends FrameworkException {
    public TestDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public static class ExcelReadException extends TestDataException {
        public ExcelReadException(String file, Throwable cause) {
            super("Failed to read Excel file: " + file, cause);
        }
    }

    public static class ExcelWriteException extends TestDataException {
        public ExcelWriteException(String file, Throwable cause) {
            super("Failed to write to Excel file: " + file, cause);
        }
    }

}
