package Challenges.Topic_0;

public class MemoryOverflowException extends Exception{

    public MemoryOverflowException() {
        super();
    }

    public MemoryOverflowException(String message) {
        super(message);
    }

    public MemoryOverflowException(String message, Throwable cause) {
        super(message, cause);
    }

    public MemoryOverflowException(Throwable cause) {
        super(cause);
    }

    public MemoryOverflowException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
