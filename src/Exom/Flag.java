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
