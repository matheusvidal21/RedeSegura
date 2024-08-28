package br.rnp.redesegura.exceptions;

public class FailedTestException extends RuntimeException {

    public FailedTestException(String message, Exception e) {
        super(message);
    }

}
