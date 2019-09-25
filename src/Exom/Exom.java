package Exom;

import Exom.Exceptions.InvalidFlagException;
import Exom.Modules.*;
import Exom.Objects.Command.CommandArgs;
import Exom.Utils.*;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * The main class of Exom.
 *
 * @author UnexomWid
 *
 * @version 1.0
 *
 * @since 1.0
 */
@SuppressWarnings("unused")
public class Exom {

    /**
     * The Exom version.
     *
     * @since 1.0
     */
    public static final String Version = "1.0";

    /**
     * The Exom version code name.
     *
     * @since 1.0
     */
    public static final String CodeName = "Initus";

    /**
     * The operating system name.
     *
     * @since 1.0
     */
    public static final String OS = System.getProperty("os.name");

    /**
     * The current working directory.
     *
     * @since 1.0
     */
    public static final String WD = System.getProperty("user.dir");

    /**
     * A global chronometer which stores a time point in the form of nanoseconds.
     *
     * @since 1.0
     */
    public static long Chronometer;

    /**
     * The main method.
     *
     * @param args The command line arguments.
     *
     * @since 1.0
     */
    public static void main(String[] args) {
        if(args.length > 0) {
            Run(args);
        } else {
            Run();
        }
    }

    /**
     * Runs Exom.
     *
     * @since 1.0
     */
    public static void Run() {
        try {
            if(Flag.Minimal)
                RunMinimally();

            if(!Flag.NoChronometer)
                StartChronometer();

            LogLine("Exom v" + Version + "\n(C)UnexomWid 2018-2019\n");

            Info("Initializing commands...");
            Commands.Initialize();
            Info("Initializing script engines...");
            ScriptEngines.Initialize();
            Info("Exom initialized successfully.");

            if(!Flag.NoChronometer)
                Info("\n", "Initialization took " + StopChronometer(), "");

            Terminate(0);
        }
        catch(Exception ex) {
            Error(ex.getMessage());
            Terminate(1);
        }
    }

    /**
     * Runs Exom with arguments.
     *
     * @param args The arguments.
     *
     * @since 1.0
     */
    public static void Run(String[] args) {
        try {
            if(args.length == 0) {
                Run();
                return;
            }

            if(!Flag.NoChronometer)
                StartChronometer();

            String[] cmdArgs = ApplyArgs(args);

            if(cmdArgs == null) {
                Run();
                return;
            } else if(cmdArgs.length == 0) { //This eliminates the NullPointerException.
                Run();
                return;
            }

            Commands.Initialize();
            if(!Commands.Handle(cmdArgs[0], new CommandArgs(Arrays.copyOfRange(cmdArgs, 1, cmdArgs.length), System.in, System.out)))
                Terminate(1);

            if(!Flag.NoChronometer)
            Info("\n\n", "Execution took " + StopChronometer(), "");

            Terminate(0);
        }
        catch(Exception ex) {
            Error(ex.getMessage());
            Terminate(1);
        }
    }

    /**
     * Runs Exom minimally.
     *
     * @since 1.0
     */
    public static void RunMinimally() {
        try {
            if(!Flag.NoChronometer)
                StartChronometer();

            LogLine("Exom v" + Version + " '" + CodeName + "'\n(C)UnexomWid 2018-2019\n");

            Info("Initializing commands...");
            Commands.Initialize();
            Info("Initializing script engines...");
            ScriptEngines.Initialize();
            Info("Exom initialized successfully.");

            if(!Flag.NoChronometer)
                Info("\n", "Initialization took " + StopChronometer(), "");

            Scanner in = new Scanner(System.in);
            String input = "";
            String lwr = "";

            while(true) {
                StreamUtils.WriteString(System.out, "\nEXOM>");
                input = in.nextLine();
                lwr = input.toLowerCase();

                if(input.charAt(0) == '-') {
                    try {
                        Flag.Toggle(lwr);
                    } catch(InvalidFlagException ex) { Error(ex.getMessage()); }
                    continue;
                }

                if(lwr.equals("exit") || lwr.equals("quit") || lwr.equals("terminate") || lwr.equals("return")) {
                    break;
                } else if(lwr.equals("cls") || lwr.equals("clear")) {
                    try {
                        if (OS.contains("Windows"))
                            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                        else
                            Runtime.getRuntime().exec("clear");
                    } catch (IOException | InterruptedException ex) { Error(ex.getMessage()); }
                    continue;
                }

                String[] cmd = StringUtils.ToArgs(input);

                if(!Flag.NoChronometer)
                    StartChronometer();

                if(!Commands.Handle(cmd[0], new CommandArgs(Arrays.copyOfRange(cmd, 1, cmd.length), System.in, System.out)))
                    continue;

                StreamUtils.WriteString(System.out, "\n");

                if(!Flag.NoChronometer)
                    Info("\n", "Execution took " + StopChronometer(), "");
            }

            Info("Shutting down...");
            Terminate(0);
        }
        catch(Exception ex) {
            Error(ex.getMessage());
            Terminate(1);
        }
    }

    /**
     * Terminates the application with a status code.
     *
     * @param status The status code.
     *
     * @since 1.0
     */
    public static void Terminate(int status) {
        System.exit(status);
    }

    /**
     * Applies arguments and then runs Exom.
     *
     * @param args
     */
    private static String[] ApplyArgs(String[] args) throws InvalidFlagException {
        if(args[0].charAt(0) != '-')
            return args;

        for(int u = 0; u < args.length; u++) {
            String arg = args[u];

            if(arg.charAt(0) == '-') {
                Flag.Toggle(arg);
            } else {
                return Arrays.copyOfRange(args, u, args.length);
            }
        }

        return null;
    }

    /**
     * Logs text to STDOUT.
     *
     * @param text The text to log.
     *
     * @since 1.0
     */
    public static void Log(String text) throws IOException {
        StreamUtils.WriteString(System.out, text);
    }
    /**
     * Logs text to a stream.
     *
     * @param text The text to log.
     * @param output The output stream.
     *
     * @since 1.0
     */
    public static void Log(String text, OutputStream output) throws IOException {
        StreamUtils.WriteString(output, text);
    }

    /**
     * Logs text to STDOUT, and the new line character.
     *
     * @param text The text to log.
     *
     * @since 1.0
     */
    public static void LogLine(String text) throws IOException {
        StreamUtils.WriteString(System.out, text + "\n");
    }
    /**
     * Logs text to a stream, and the new line character.
     *
     * @param text The text to log.
     * @param output The output stream.
     *
     * @since 1.0
     */
    public static void LogLine(String text, OutputStream output) throws IOException {
        StreamUtils.WriteString(output, text + "\n");
    }

    /**
     * Outputs an information to STDOUT.
     *
     * @param message The information message.
     *
     * @since 1.0
     */
    public static void Info(String message) throws IOException {
        StreamUtils.WriteString(System.out, "[Info] " + message + "\n");
    }
    /**
     * Outputs an information to STDOUT, and a string before it.
     *
     * @param pre The string before the information.
     * @param message The information message.
     *
     * @since 1.0
     */
    public static void Info(String pre, String message) throws IOException {
        StreamUtils.WriteString(System.out, pre + "[Info] " + message + "\n");
    }
    /**
     * Outputs an information to STDOUT, a string before it and a string after it.
     *
     * @param pre The string before the information.
     * @param message The information message.
     * @param post The string after the information.
     *
     * @since 1.0
     */
    public static void Info(String pre, String message, String post) throws IOException {
        StreamUtils.WriteString(System.out, pre + "[Info] " + message + post);
    }
    /**
     * Outputs an information to a stream.
     *
     * @param message The information message.
     * @param output The output Stream
     *
     * @since 1.0
     */
    public static void Info(String message, OutputStream output) throws IOException {
        StreamUtils.WriteString(output, "[Info] " + message + "\n");
    }
    /**
     * Outputs an information to a stream, and a string before it.
     *
     * @param pre The string before the information.
     * @param message The information message.
     * @param output The output Stream
     *
     * @since 1.0
     */
    public static void Info(String pre, String message, OutputStream output) throws IOException {
        StreamUtils.WriteString(output, pre + "[Info] " + message + "\n");
    }
    /**
     * Outputs an information to a stream, a string before it and a string after it.
     *
     * @param pre The string before the information.
     * @param message The information message.
     * @param post The string after the information.
     * @param output The output Stream
     *
     * @since 1.0
     */
    public static void Info(String pre, String message, String post, OutputStream output) throws IOException {
        StreamUtils.WriteString(output, pre + "[Info] " + message + post);
    }

    /**
     * Outputs an error to STDERR.
     *
     * @param message The error message.
     *
     * @since 1.0
     */
    public static void Error(String message) {
        try {
            StreamUtils.WriteString(System.err, "[Error] " + message + "\n");
        } catch(Exception ex){ }
    }
    /**
     * Outputs an error to STDERR, and a string before it.
     *
     * @param pre The string before the error.
     * @param message The error message.
     *
     * @since 1.0
     */
    public static void Error(String pre, String message) {
        try {
            StreamUtils.WriteString(System.err, pre + "[Error] " + message + "\n");
        } catch(Exception ex){ }
    }
    /**
     * Outputs an error to STDERR, a string before it and a string after it.
     *
     * @param pre The string before the error.
     * @param message The error message.
     * @param post The string after the error.
     *
     * @since 1.0
     */
    public static void Error(String pre, String message, String post) {
        try {
            StreamUtils.WriteString(System.err, pre + "[Error] " + message + post);
        } catch(Exception ex){ }
    }
    /**
     * Outputs an error to a stream.
     *
     * @param message The error message.
     * @param output The output stream.
     *
     * @since 1.0
     */
    public static void Error(String message, OutputStream output) {
        try {
            StreamUtils.WriteString(output, "[Error] " + message + "\n");
        } catch(Exception ex){ }
    }
    /**
     * Outputs an error to a stream, and a string before it.
     *
     * @param pre The string before the error.
     * @param message The error message.
     * @param output The output stream.
     *
     * @since 1.0
     */
    public static void Error(String pre, String message, OutputStream output) {
        try {
            StreamUtils.WriteString(output, pre + "[Error] " + message + "\n");
        } catch(Exception ex){ }
    }
    /**
     * Outputs an error to a stream, a string before it and a string after it.
     *
     * @param pre The string before the error.
     * @param message The error message.
     * @param post The string after the error
     * @param output The output stream.
     *
     * @since 1.0
     */
    public static void Error(String pre, String message, String post, OutputStream output) {
        try {
            StreamUtils.WriteString(output, pre + "[Error] " + message + post);
        } catch(Exception ex){ }
    }

    /**
     * Starts the global chronometer.
     *
     * @since 1.0
     */
    public static void StartChronometer() {
        Chronometer = System.nanoTime();
    }

    /**
     * Stops the global chronometer and returns the elapsed time as a formatted string.
     *
     * @return The elapsed time as a formatted string.
     *
     * @since 1.0
     */
    public static String StopChronometer() {
        return TimeUtils.GetElapsedTime(Chronometer);
    }
}
