package Exom.Exceptions;

/**
 * Represents an invalid flag exception.
 *
 * @author UnexomWid
 *
 * @since 1.0
 */
public class InvalidFlagException extends Exception {

    /**
     * Initializes a new instance of the InvalidFlagException class.
     *
     * @since 1.0
     */
    public InvalidFlagException() {

    }

    /**
     * Initializes a new instance of the InvalidFlagException class.
     *
     * @param message The exception message.
     *
     * @since 1.0
     */
    public InvalidFlagException(String message) {
        super(message);
    }

    /**
     * Initializes a new instance of the InvalidFlagException class.
     *
     * @param cause The exception cause.
     *
     * @since 1.0
     */
    public InvalidFlagException(Throwable cause) {
        super(cause);
    }

    /**
     * Initializes a new instance of the InvalidFlagException class.
     *
     * @param message The exception message.
     * @param cause The exception cause.
     *
     * @since 1.0
     */
    public InvalidFlagException(String message, Throwable cause) {
        super(message, cause);
    }
}
