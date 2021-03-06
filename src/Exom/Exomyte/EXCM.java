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

package Exom.Exomyte;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Contains methods used for Exom Command Module interaction.
 * 
 * @author UnexomWid
 *
 * @since 1.0
 */
public class EXCM {

	/**
	 * Generates an EXCM file.
	 * 
	 * @param name The name of the EXCM.
	 * @param description The description of the EXCM.
	 * @param parameters The parameter information.
	 * @param usage The usage information.
	 * @param imports The code containing all necessary imports.
	 * @param code The code of the EXCM.
	 * @param path The path where to generate the EXCM.
	 * 
	 * @throws IOException 
	 * @throws IllegalArgumentException 
	 *
	 * @since 1.0
	 */
	public static void Generate(String name, String description, String parameters, String usage, String imports, String code, String path) throws IllegalArgumentException, IOException {
		String source = imports + "\npublic class " + name + "\n" + 
				"{\n" + 
				"	public static String GetDescription()\n" + 
				"	{\n" + 
				"		return \"" + description + "\";\n" + 
				"	}\n" + 
				"	\n" + 
				"	public static String GetParameters()\n" + 
				"	{\n" + 
				"		return \"" + parameters + "\";\n" + 
				"	}\n" + 
				"	\n" + 
				"	public static String GetUsage()\n" + 
				"	{\n" + 
				"		return \"" + usage + "\";\n" + 
				"	}\n" + 
				"	\n" + 
				"	public static String Execute(String args) throws java.lang.Exception\n" + 
				"	{\n" + 
				"		try\n" +
				"		{\n" +
				"		" + code + "\n" + 
				"		}\n" +
				"		catch(java.lang.Exception ex)\n" +
				"		{\n" +
				"			return \"Exception:\" + ex;\n" +
				"		}\n" +
				"	}\n" + 
				"}";	
			
		Exom.Utils.ClassUtils.Compile(source, path, name, "excm");
	}
	
	/**
	 * Runs an EXCM.
	 * 
	 * @param excm The EXCM to run.
	 * @param args The arguments to run the EXCM with.
	 * 
	 * @return The response of the EXCM.
	 * 
	 * @throws IllegalAccessException 
	 * @throws InvocationTargetException 
	 * @throws NoSuchMethodException 
	 * @throws ClassNotFoundException 
	 *
	 * @since 1.0
	 */
	public static String Run(String excm, String args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		return (String) Exom.Utils.ClassUtils.Run(excm, "Execute", new Object[] { args }, new Class<?>[] { String.class });
	}
	
	/**
	 * Returns the Exception from a response string, if it exists.
	 * 
	 * @param response The response string.
	 * 
	 * @return The Exception of the response string, if it exists. Otherwise, an empty string.
	 *
	 * @since 1.0
	 */
	public static String GetException(String response) {
		if(response.length() >= 10 && response.substring(0, 10).equals("Exception:"))
			return response.substring(10);
		return "";
	}
}
