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

import Exom.Objects.FunctionResponse;

import java.util.Arrays;
import java.util.HashSet;
import java.util.function.Function;

/**
 * Represents an Exom command.
 *
 * @author UnexomWid
 *
 * @since 1.0
 */
public class Command {
    /**
     * Initializes a new instance of the Command class.
     *
     * @since 1.0
     */
    public Command() {
        Name = "";
        Aliases = new HashSet<>();
        Code = (CommandArgs x) -> new FunctionResponse();
    }

    /**
     * Initializes a new instance of the Command class.
     *
     * @param name The name of the command.
     * @param aliases The aliases of the command.
     * @param code The code of the command.
     *
     * @since 1.0
     */
    public Command(String name, String[] aliases, String description, Function<CommandArgs, FunctionResponse> code) {
        Name = name;
        Aliases = new HashSet<>(Arrays.asList(aliases));
        Description = description;
        Code = code;
    }

    /**
     * The name of the command.
     *
     * @since 1.0
     */
    public String Name;

    /**
     * The aliases of the command.
     *
     * @since 1.0
     */
    public HashSet<String> Aliases;

    /**
     * The description of the command.
     *
     * @since 1.0
     */
    public String Description;

    /**
     * The code of the command.
     *
     * @since 1.0
     */
    public Function<CommandArgs, FunctionResponse> Code;

    /**
     * Executes the command with arguments.
     *
     * @param args The arguments.
     *
     * @return The response of the command.
     */
    public FunctionResponse Execute(CommandArgs args) {
        return Code.apply(args);
    }
}
