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

package Exom.Modules;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import Exom.Exceptions.InvalidCommandException;
import Exom.Exom;
import Exom.Objects.Command.CommandArgs;
import Exom.Utils.ByteUtils;
import Exom.Utils.Cryptography.Hash;
import Exom.Objects.Command.Command;
import Exom.Objects.FunctionResponse;
import Exom.Utils.StreamUtils;
import Exom.Utils.StringUtils;

/**
 * Contains Exom commands.
 *
 * @author UnexomWid
 *
 * @since 1.0
 */
public class Commands {

    /**
     * Represents a list of all commands.
     *
     * @since 1.0
     */
    public static ArrayList<Command> List;

    /**
     * Whether or not this module has been initialized.
     */
    public static boolean Initialized = false;

    /**
     * Initializes this module.
     *
     * @since 1.0
     */
    public static void Initialize() {
        if(!Initialized) {
            List = new ArrayList<>();

            List.add(new Command("MD5", new String[] { "messagedigest5" }, "Computes the MD5 hash of a string or file", (input) -> {
                try {
                    StringBuilder res = new StringBuilder();
                    String lwr = input.Args[0].toLowerCase();

                    if(lwr.equals("-h") || lwr.equals("--help")) {
                        res.append(FormatInfo("MD5", null, "Computes the MD5 hash of a string or file"));
                        res.append("\n\nArguments: ");
                        res.append("\n[FILE]\n    Computes the MD5 hash of [FILE]");
                        res.append("\n--help, -h\n    Shows information about the command");
                        res.append("\n--text [TEXT], -t [TEXT]\n    Computes the hash of [TEXT]");
                    }
                    else if (lwr.equals("-t") || lwr.equals("--text"))
                        res.append(Hash.MD5(StringUtils.ToBytes(String.join("", Arrays.copyOfRange(input.Args, 1, input.Args.length)))));
                    else {
                        boolean first = true;
                        for(String file : input.Args) {
                            File fl = new File(file);
                            if(!fl.exists() || fl.isDirectory())
                                throw new FileNotFoundException("The file '" + file + "' does not exist.");
                            if(first)
                                first = false;
                            else res.append("\n");

                            res.append(StringUtils.FileName(file));
                            res.append(" - ");
                            res.append(Hash.MD5(ByteUtils.FileToBytes(file)));
                        }
                    }

                    return new FunctionResponse(true, res.toString());
                } catch (Exception ex) {
                    return new FunctionResponse(false, ex.getMessage());
                }
            }));

            List.add(new Command("SHA1", new String[] { "securehashalgorithm1" }, "Computes the SHA1 hash of a string or file", (input) -> {
                try {
                    StringBuilder res = new StringBuilder();
                    String lwr = input.Args[0].toLowerCase();

                    if(lwr.equals("-h") || lwr.equals("--help")) {
                        res.append(FormatInfo("SHA1", null, "Computes the SHA1 hash of a string or file"));
                        res.append("\n\nArguments: ");
                        res.append("\n[FILE]\n    Computes the SHA1 hash of [FILE]");
                        res.append("\n--help, -h\n    Shows information about the command");
                        res.append("\n--text [TEXT], -t [TEXT]\n    Computes the hash of [TEXT]");
                    }
                    else if (lwr.equals("-t") || lwr.equals("--text"))
                        res.append(Hash.SHA1(StringUtils.ToBytes(String.join("", Arrays.copyOfRange(input.Args, 1, input.Args.length)))));
                    else {
                        boolean first = true;
                        for(String file : input.Args) {
                            File fl = new File(file);
                            if(!fl.exists() || fl.isDirectory())
                                throw new FileNotFoundException("The file '" + file + "' does not exist.");
                            if(first)
                                first = false;
                            else res.append("\n");

                            res.append(StringUtils.FileName(file));
                            res.append(" - ");
                            res.append(Hash.SHA1(ByteUtils.FileToBytes(file)));
                            res.append("\n");
                        }
                    }

                    return new FunctionResponse(true, res.toString());
                } catch (Exception ex) {
                    return new FunctionResponse(false, ex.getMessage());
                }
            }));

            List.add(new Command("SHA256", new String[] { "securehashalgorithm256" }, "Computes the SHA256 hash of a string or file", (input) -> {
                try {
                    StringBuilder res = new StringBuilder();
                    String lwr = input.Args[0].toLowerCase();

                    if(lwr.equals("-h") || lwr.equals("--help")) {
                        res.append(FormatInfo("SHA256", null, "Computes the SHA256 hash of a string or file"));
                        res.append("\n\nArguments: ");
                        res.append("\n[FILE]\n    Computes the SHA256 hash of [FILE]");
                        res.append("\n--help, -h\n    Shows information about the command");
                        res.append("\n--text [TEXT], -t [TEXT]\n    Computes the hash of [TEXT]");
                    }
                    else if (lwr.equals("-t") || lwr.equals("--text"))
                        res.append(Hash.SHA256(StringUtils.ToBytes(String.join("", Arrays.copyOfRange(input.Args, 1, input.Args.length)))));
                    else {
                        boolean first = true;
                        for(String file : input.Args) {
                            File fl = new File(file);
                            if(!fl.exists() || fl.isDirectory())
                                throw new FileNotFoundException("The file '" + file + "' does not exist.");
                            if(first)
                                first = false;
                            else res.append("\n");

                            res.append(StringUtils.FileName(file));
                            res.append(" - ");
                            res.append(Hash.SHA256(ByteUtils.FileToBytes(file)));
                            res.append("\n");
                        }
                    }

                    return new FunctionResponse(true, res.toString());
                } catch (Exception ex) {
                    return new FunctionResponse(false, ex.getMessage());
                }
            }));

            List.add(new Command("ToArgs", new String[] { "toarguments" }, "Coneverts a string to an array of command-line-like arguments", (input) -> {
                try {
                    StringBuilder res = new StringBuilder();
                    String lwr = input.Args[0].toLowerCase();

                    if(lwr.equals("-h") || lwr.equals("--help")) {
                        res.append(FormatInfo("ToArgs", null, "Converts a string to an array of command-line-like arguments"));
                        res.append("\n\nArguments: ");
                        res.append("\n[STRING]\n    Converts [STRING] to an array of command-line-like arguments");
                        res.append("\n--help, -h\n    Shows information about the command");
                    } else {

                        res.append("[");
                        boolean first = true;

                        for (String s : input.Args) {
                            if (first)
                                first = false;
                            else res.append(", ");
                            res.append("\"");
                            res.append(s);
                            res.append("\"");
                        }

                        res.append("]\n");
                    }

                    return new FunctionResponse(true, res.toString());
                } catch (Exception ex) {
                    return new FunctionResponse(false, ex.getMessage());
                }
            }));

            Initialized = true;
        }
    }

    /**
     * Returns the command that has an alias.
     *
     * @param alias The command alias.
     *
     * @return The command that has the alias, if found. Else, null.
     *
     * @since 1.0
     */
    public static Command Find(String alias) throws InvalidCommandException {
        alias = alias.toLowerCase();
        for(Command x : List) {
            if(x.Name.toLowerCase().equals(alias) || (x.Aliases != null && x.Aliases.contains(alias)))
                return x;
        }

        throw new InvalidCommandException("No command with the specified alias was found.");
    }

    /**
     * Handles a command with input.
     *
     * @param alias The command alias.
     * @param args The command input.
     *
     * @return Whether or not the execution is successful.
     *
     * @since 1.0
     */
    public static boolean Handle(String alias, CommandArgs args) throws IOException {
        try {
            Command cmd = Find(alias);
            if (cmd == null) {
                Exom.Error("'" + alias + "' is not a valid command.");
                return false;
            }
            FunctionResponse res = cmd.Execute(args);

            if (res.Response.length() > 0) {
                if (res.Success)
                    StreamUtils.WriteString(args.OutputStream, res.Response);
                else Exom.Error(res.Response);
            }

            return res.Success;
        } catch(InvalidCommandException ex) {
            Exom.Error("'" + alias + "' is not a valid command.");
            return false;
        } catch(Exception ex) {
            Exom.Error(ex.getMessage());
            return false;
        }
    }

    /**
     * Formats information about a command.
     *
     * @param name The name of the command.
     * @param aliases The aliases of the command.
     * @param description The description of the command.
     *
     * @return Formatted information about the command.
     *
     * @since 1.0
     */
    public static String FormatInfo(String name, ArrayList<String> aliases, String description) {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ");
        sb.append(name != null && name.length() > 0 ? name : "N/A");
        sb.append("\nDescription: ");
        sb.append(description != null && description.length() > 0 ? description : "N/A");
        sb.append("\nAliases: ");

        boolean first = true;

        if(aliases == null || aliases.size() == 0)
            sb.append("N/A");
        else {
            for (String als : aliases) {
                if (first) {
                    first = false;
                } else sb.append(", ");

                sb.append(als);
            }
        }

        return sb.toString();
    }
}
