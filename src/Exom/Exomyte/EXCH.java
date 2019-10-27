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
import java.util.Map;

/**
 * Contains methods used for Exom Command Handler interaction.
 * 
 * @author UnexomWid
 *
 * @since 1.0
*/
public class EXCH {

	//public static Map<String, String> x;
	
	/**
	 * Generates an EXCH file.
	 * 
	 * @param name The name of the EXCH.
	 * @param list The list of aliases.
	 * @param path The path where to generate the EXCH file.
	 * 
	 * @throws IOException 
	 * @throws IllegalArgumentException 
	 *
	 * @since 1.0
	*/
	public static void Generate(String name, Map<String, String> list, String path) throws IllegalArgumentException, IOException {
		String source = "import java.util.Map;\n" + 
				"import java.util.HashMap;\n" +
				"public class " + name + "\n" + 
				"{\n" + 
				"	public static Map<String, String> GetMap()\n" + 
				"	{\n" + 
				"		Map<String, String> x = new HashMap<String, String>();";
		for(Map.Entry<String, String> entry : list.entrySet()) {
			source += "\n		x.put(\"" + entry.getKey() + "\", \"" + entry.getValue() + "\");";
		}
		
		source += "\n		return x;\n" + 
				"	}\n" + 
				"	\n" + 
				"	public static String[] Handle(String command)\n" + 
				"	{\n" + 
				"		String[] split = command.split(\" \");\n" + 
				"\n" + 
				"		String args = \"\";\n" + 
				"\n" + 
				"		for (int u = 1; u < split.length; u++)\n" + 
				"		{\n" + 
				"			if (u > 1)\n" + 
				"				args += \" \" + split[u];\n" + 
				"		else args += split[u];\n" + 
				"		}\n" + 
				"		\n" + 
				"		Map<String, String> dictionary = GetMap();\n" + 
				"		\n" + 
				"		for(Map.Entry<String, String> entry : dictionary.entrySet()) \n" + 
				"		{\n" + 
				"			String key = entry.getKey();\n" + 
				"			String value = entry.getValue();\n" + 
				"\n" + 
				"			if(split[0].toLowerCase().equals(key))\n" + 
				"				return new String[] { value, args };\n" + 
				"		}\n" + 
				"		\n" + 
				"		return null;\n" + 
				"	}\n" + 
				"}";
		
		Exom.Utils.ClassUtils.Compile(source, path, name, "exch");
	}
	
	/**
	 * Splits a command in an array containing the EXCM to execute and the arguments to execute with.
	 * 
	 * @param exch The EXCH file.
	 * @param command The command to split.
	 * 
	 * @return An array containing the EXCM to execute and the arguments to execute with.
	 * 
	 * @throws IllegalAccessException 
	 * @throws InvocationTargetException 
	 * @throws NoSuchMethodException 
	 * @throws ClassNotFoundException 
	 *
	 * @since 1.0
	*/
	public static String[] GetEXCM(String exch, String command) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		return (String[]) Exom.Utils.ClassUtils.Run(exch, "Handle", new String[] { command }, new Class<?>[] { String.class });
	}
}
