package br.rnp.redesegura.exception;

public class FailedTestException extends RuntimeException {

    public FailedTestException(String message, Exception e) {
        super(message);
    }

}
