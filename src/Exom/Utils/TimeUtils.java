package Exom.Utils;

/**
 * Contains methods used for time manipulation.
 * 
 * @author UnexomWid
 *
 * @since 1.0
 */
public class TimeUtils {

	/**
	 * Returns a time string from a number of nanoseconds.
	 * 
	 * @param nanoseconds The number of nanoseconds.
	 * 
	 * @return The time string.
	 *
	 * @since 1.0
	 */
	public static String GetTimeString(long nanoseconds) {
		StringBuilder exTime = new StringBuilder();
		long tmp;

		tmp = nanoseconds / 86400000000000L;
        if (tmp != 0) {
            exTime.append(tmp);
            if(tmp != 1)
                exTime.append(" days");
            else exTime.append(" day");
        }
        nanoseconds -= 86400000000000L * tmp;

        tmp = nanoseconds / 3600000000000L;
        if (tmp != 0) {
            if(exTime.length() > 0)
                exTime.append(", ");
            exTime.append(tmp);
            if(tmp != 1)
                exTime.append(" hours");
            else exTime.append(" hour");
        }
        nanoseconds -= 3600000000000L * tmp;

        tmp = nanoseconds / 60000000000L;
        if (tmp != 0) {
            if(exTime.length() > 0)
                exTime.append(", ");
            exTime.append(tmp);
            if(tmp != 1)
                exTime.append(" minutes");
            else exTime.append(" minute");
        }
        nanoseconds -= 60000000000L * tmp;

        tmp = nanoseconds / 1000000000L;
        if (tmp != 0) {
            if(exTime.length() > 0)
                exTime.append(", ");
            exTime.append(tmp);
            if(tmp != 1)
                exTime.append(" seconds");
            else exTime.append(" second");
        }
        nanoseconds -= 1000000000L * tmp;

        tmp = nanoseconds / 1000000L ;
        if (tmp != 0) {
            if(exTime.length() > 0)
                exTime.append(", ");
            exTime.append(tmp);
            if(tmp != 1)
                exTime.append(" milliseconds");
            else exTime.append(" millisecond");
        }
        nanoseconds -= 1000000L * tmp;

        tmp = nanoseconds / 1000L ;
        if (tmp != 0) {
            if(exTime.length() > 0)
                exTime.append(", ");
            exTime.append(tmp);
            if(tmp != 1)
                exTime.append(" microseconds");
            else exTime.append(" microsecond");
        }
        nanoseconds -= 1000L * tmp;

        tmp = nanoseconds;
        if (tmp != 0) {
            if(exTime.length() > 0)
                exTime.append(", ");
            exTime.append(tmp);
            if(tmp != 1)
                exTime.append(" nanoseconds");
            else exTime.append(" nanosecond");
        }
        
        return exTime.toString();
	}

    /**
     * Returns a string representing the elapsed time from a specified time point.
     *
     * @param nanoseconds The time point in nanoseconds.
     *
     * @return The time string.
     *
     * @since 1.0
     */
	public static String GetElapsedTime(long nanoseconds) {
        return GetTimeString(System.nanoTime() - nanoseconds);
    }
}
