package Exom;

import Exom.Exceptions.InvalidFlagException;

/**
 * Contains Exom runtime flags.
 *
 * @author UnexomWid
 *
 * @since 1.0
 */
public class Flag {

    /**
     * Whether or not to run Exom minimally.
     *
     * @since 1.0
     */
    public static boolean Minimal = false;

    /**
     * Whether or not to suppress chronometer output.
     *
     * @since 1.0
     */
    public static boolean NoChronometer = false;

    /**
     * Toggles an Exom runtime flag.
     *
     * @param flag The flag to toggle.
     */
    public static void Toggle(String flag) throws InvalidFlagException {
        if(flag.charAt(0) != '-')
            throw new InvalidFlagException("'" + flag + "' is not a valid flag.");

        if(flag.equals("-m") || flag.equals("--minimal")) {
            Minimal = !Minimal;
        } else if(flag.equals("-c") || flag.equals("--no-chronometer")) {
            NoChronometer = !NoChronometer;
        } else {
            throw new InvalidFlagException("'" + flag + "' is not a valid flag.");
        }
    }
}
