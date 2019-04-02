package Exom.Exceptions;

/**
 * Represents an invalid command exception.
 *
 * @author UnexomWid
 *
 * @since 1.0
 */
public class InvalidCommandException extends Exception {

    /**
     * Initializes a new instance of the InvalidCommandException class.
     *
     * @since 1.0
     */
    public InvalidCommandException() {

    }

    /**
     * Initializes a new instance of the InvalidCommandException class.
     *
     * @param message The exception message.
     *
     * @since 1.0
     */
    public InvalidCommandException(String message) {
        super(message);
    }

    /**
     * Initializes a new instance of the InvalidCommandException class.
     *
     * @param cause The exception cause.
     *
     * @since 1.0
     */
    public InvalidCommandException(Throwable cause) {
        super(cause);
    }

    /**
     * Initializes a new instance of the InvalidCommandException class.
     *
     * @param message The exception message.
     * @param cause The exception cause.
     *
     * @since 1.0
     */
    public InvalidCommandException(String message, Throwable cause) {
        super(message, cause);
    }
}
