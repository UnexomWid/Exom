package Exom.Objects.Command;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Represents the arguments of a command.
 *
 * @author UnexomWid
 *
 * @since 1.0
 */
public class CommandArgs {

    /**
     * Initializes a new instance of the CommandArgs class.
     *
     * @since 1.0
     */
    public CommandArgs() {
        Args = null;
        OutputStream = null;
    }

    /**
     * Initializes a new instance of the CommandArgs class.
     *
     * @param args The command arguments.
     * @param inputStream The command input stream.
     * @param outputStream The command output stream.
     *
     * @since 1.0
     */
    public CommandArgs(String[] args, InputStream inputStream, OutputStream outputStream) {
        Args = args;
        InputStream = inputStream;
        OutputStream = outputStream;
    }

    /**
     * The command string arguments.
     *
     * @since 1.0
     */
    public String[] Args;

    /**
     * The command output stream.
     */
    public OutputStream OutputStream;

    /**
     * The command input stream.
     */
    public java.io.InputStream InputStream;
}
