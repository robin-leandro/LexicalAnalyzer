package cr.ac.ucr.ci1322.exceptions;

public class SemanticErrorException extends RuntimeException{
        public SemanticErrorException() {
    }
    
    public SemanticErrorException(String message) {
        super(message);
    }

    public SemanticErrorException(Throwable cause) {
        super(cause);
    }

    public SemanticErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
