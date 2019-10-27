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
