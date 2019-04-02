package Exom.Objects;

/**
 * Represents a function response.
 *
 * @author UnexomWid
 *
 * @since 1.0
 */
public class FunctionResponse {

    public FunctionResponse() {
        Success = true;
        Response = "";
    }

    public FunctionResponse(boolean success, String response) {
        Success = success;
        Response = response;
    }

    /**
     * Whether or not the function executed successfully.
     *
     * @since 1.0
     */
    public boolean Success;

    /**
     * The response of the function.
     *
     * @since 1.0
     */
    public String Response;
}
