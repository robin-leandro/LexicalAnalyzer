package cr.ac.ucr.ci1322.exceptions;

public class LexicalErrorException extends RuntimeException {

    public LexicalErrorException() {
    }

    public LexicalErrorException(String message) {
        super(message);
    }

    public LexicalErrorException(Throwable cause) {
        super(cause);
    }

    public LexicalErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
