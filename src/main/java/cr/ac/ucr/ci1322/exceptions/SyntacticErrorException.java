package cr.ac.ucr.ci1322.exceptions;

public class SyntacticErrorException extends RuntimeException {

    public SyntacticErrorException() {
    }
    
    public SyntacticErrorException(String message) {
        super(message);
    }

    public SyntacticErrorException(Throwable cause) {
        super(cause);
    }

    public SyntacticErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
