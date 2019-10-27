/**
 * Exom-Legacy (https://github.com/UnexomWid/Exom-Legacy)
 *
 * This project is licensed under the MIT license.
 * Copyright (c) 2017-2019 UnexomWid (https://uw.exom.dev)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the
 * following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
 * LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

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
