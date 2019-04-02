package Exom.Utils.Cryptography.Ciphers;

/**
 * Contains methods used for encrypting and decrypting text with the Morse code.
 * 
 * @author UnexomWid
 *
 * @since 1.0
 */
public class Morse {

	/**
	 * Encodes a String.
	 * 
	 * @param data The String to encode.
	 * 
	 * @return The encoded String.
	 * 
	 * @throws NullPointerException
	 *
	 * @since 1.0
	 */
	public static String Encode(String data) {
        String fin = "";

        String[] lines = data.split("\n");

        for (int u = 0; u < lines.length; u++) {
            String[] words = lines[u].split(" ");

            for (int l = 0; l < words.length; l++) {
                for (int p = 0; p < words[l].length(); p++) {
                    if (EncodeChar(words[l].charAt(p)).equals(""))
                        throw new NullPointerException("Invalid character: '" + words[l].charAt(p) + "'");
                    fin += EncodeChar(words[l].charAt(p));

                    if (p != words[l].length() - 1)
                        fin += " ";
                }

                if (l != words.length - 1)
                    fin += " / ";
            }

            if (u != lines.length - 1)
                fin += "\n";
        }

        return fin;
	}
	
	/**
	 * Decodes a String.
	 * 
	 * @param data The String to decode.
	 * 
	 * @return The decoded String.
	 * 
	 * @throws NullPointerException
	 *
	 * @since 1.0
	 */
	public static String Decode(String data) throws NullPointerException {
		String fin = "";

        String[] lines = data.split("\n");

        for (int u = 0; u < lines.length; u++) {
            String[] words = lines[u].split(" / ");

            for (int l = 0; l < words.length; l++) {
                String[] chars = words[l].split(" ");

                for (int p = 0; p < chars.length; p++) {
                    if (DecodeChar(chars[p]) == '\0')
                        throw new NullPointerException("Invalid character: '" + chars[p] + "'");
                    fin += DecodeChar(chars[p]);
                }

                if (l != words.length - 1)
                    fin += " ";
            }

            if (u != lines.length - 1)
                fin += "\n";
        }

        return fin;
	}
	
	/**
	 * Encodes a character.
	 * 
	 * @param c The character to encode.
	 * 
	 * @return The encoded character.
	 *
	 * @since 1.0
	 */
	public static String EncodeChar(char c) {
		if (c == 'a' || c == 'A') return ".-";
        if (c == 'b' || c == 'B') return "-...";
        if (c == 'c' || c == 'C') return "-.-.";
        if (c == 'd' || c == 'D') return "-..";
        if (c == 'e' || c == 'E') return ".";
        if (c == 'f' || c == 'F') return "..-.";
        if (c == 'g' || c == 'G') return "--.";
        if (c == 'h' || c == 'H') return "....";
        if (c == 'i' || c == 'I') return "..";
        if (c == 'j' || c == 'J') return ".---";
        if (c == 'k' || c == 'K') return "-.-";
        if (c == 'l' || c == 'L') return ".-..";
        if (c == 'm' || c == 'M') return "--";
        if (c == 'n' || c == 'N') return "-.";
        if (c == 'o' || c == 'O') return "---";
        if (c == 'p' || c == 'P') return ".--.";
        if (c == 'q' || c == 'Q') return "--.-";
        if (c == 'r' || c == 'R') return ".-.";
        if (c == 's' || c == 'S') return "...";
        if (c == 't' || c == 'T') return "-";
        if (c == 'u' || c == 'U') return "..-";
        if (c == 'v' || c == 'V') return "...-";
        if (c == 'w' || c == 'W') return ".--";
        if (c == 'x' || c == 'X') return "-..-";
        if (c == 'y' || c == 'Y') return "-.--";
        if (c == 'z' || c == 'Z') return "--..";

        if (c == '0') return "-----";
        if (c == '1') return ".----";
        if (c == '2') return "..---";
        if (c == '3') return "...--";
        if (c == '4') return "....-";
        if (c == '5') return ".....";
        if (c == '6') return "-....";
        if (c == '7') return "--...";
        if (c == '8') return "---..";
        if (c == '9') return "----.";

        if (c == '.') return ".-.-.-";
        if (c == ',') return "--..--";
        if (c == '?') return "..--..";
        if (c == '!') return "-.-.--";
        if (c == ':') return "---...";
        if (c == '=') return "-...-";
        if (c == '+') return ".-.-.";
        if (c == '-') return "-....-";
        if (c == '\'') return ".----.";
        if (c == '/') return "-..-.";
        if (c == '(') return "-.--.";
        if (c == ')') return "-.--.-";
        if (c == '&') return ".-...";
        if (c == ';') return "-.-.-.";
        if (c == '_') return "..--.-";
        if (c == '\"') return ".-..-.";
        if (c == '$') return "...-..-";
        if (c == '@') return ".--.-.";

        if (c == ' ') return " ";

        return "";
	}
	
	/**
	 * Decodes a character.
	 * 
	 * @param data The character to decode.
	 * 
	 * @return The decoded character.
	 *
	 * @since 1.0
	 */
	public static char DecodeChar(String data) {
		if (data.equals(".-")) return 'A';
        if (data.equals("-...")) return 'B';
        if (data.equals("-.-.")) return 'C';
        if (data.equals("-..")) return 'D';
        if (data.equals(".")) return 'E';
        if (data.equals("..-.")) return 'F';
        if (data.equals("--.")) return 'G';
        if (data.equals("....")) return 'H';
        if (data.equals("..")) return 'I';
        if (data.equals(".---")) return 'J';
        if (data.equals("-.-")) return 'K';
        if (data.equals(".-..")) return 'L';
        if (data.equals("--")) return 'M';
        if (data.equals("-.")) return 'N';
        if (data.equals("---")) return 'O';
        if (data.equals(".--.")) return 'P';
        if (data.equals("--.-")) return 'Q';
        if (data.equals(".-.")) return 'R';
        if (data.equals("...")) return 'S';
        if (data.equals("-")) return 'T';
        if (data.equals("..-")) return 'U';
        if (data.equals("...-")) return 'V';
        if (data.equals(".--")) return 'W';
        if (data.equals("-..-")) return 'X';
        if (data.equals("-.--")) return 'Y';
        if (data.equals("--..")) return 'Z';

        if (data.equals("-----")) return '0';
        if (data.equals(".----")) return '1';
        if (data.equals("..---")) return '2';
        if (data.equals("...--")) return '3';
        if (data.equals("....-")) return '4';
        if (data.equals(".....")) return '5';
        if (data.equals("-....")) return '6';
        if (data.equals("--...")) return '7';
        if (data.equals("---..")) return '8';
        if (data.equals("----.")) return '9';

        if (data.equals(".-.-.-")) return '.';
        if (data.equals("--..--")) return ',';
        if (data.equals("..--..")) return '?';
        if (data.equals("-.-.--")) return '!';
        if (data.equals("---...")) return ':';
        if (data.equals("-...-")) return '=';
        if (data.equals(".-.-.")) return '+';
        if (data.equals("-....-")) return '-';
        if (data.equals(".----.")) return '\'';
        if (data.equals("-..-.")) return '/';
        if (data.equals("-.--.")) return '(';
        if (data.equals("-.--.-")) return ')';
        if (data.equals(".-...")) return '&';
        if (data.equals("-.-.-.")) return ';';
        if (data.equals("..--.-")) return '_';
        if (data.equals(".-..-.")) return '\"';
        if (data.equals("...-..-")) return '$';
        if (data.equals(".--.-.")) return '@';

        return '\0';
	}
}
