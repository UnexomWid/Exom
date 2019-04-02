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
