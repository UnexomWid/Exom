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

import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

import javax.naming.InvalidNameException;

import Exom.Engines;
import Exom.Utils.*;

/**
 * Contains methods used for Exomyte interaction.
 * 
 * @author UnexomWid
 *
 * @since 1.0
 */
public class EXMY {

	/**
	 * The folder containing the EXCM files.
	 *
	 * @since 1.0
	 */
    public String Namespace;
    /**
     * The path to the EXCH file.
     *
	 * @since 1.0
     */
    public String Handler;
    /**
     * The path to the Variables file.
     *
	 * @since 1.0
     */
    public String Variables;
    /**
     * A map of all RAM Variables.
     *
	 * @since 1.0
     */
    public Map<String, String> RamVars;
    /**
     * A map of all File Variables.
     *
	 * @since 1.0
     */
    public Map<String, String> FileVars;
    /**
     * Whether or not the interpreter is currently inside an IF statement.
     *
	 * @since 1.0
     */
    public boolean InIf;
    /**
     * The source of the EXMY Script.
     *
	 * @since 1.0
     */
    public Scanner Source;
    
    /**
     * Initializes a new instance of the EXMY class.
     *
	 * @since 1.0
     */
    public EXMY() {
    	Namespace = "";
    	Handler = "";
    	Variables = "";
    	RamVars = new HashMap<String, String>();
    	FileVars = new HashMap<String, String>();
    	InIf = false;
    }
    /**
     * Initializes a new instance of the EXMY class.
     * 
     * @param handlerPath The path to the EXCH file.
     * @param varPath The path to the Variables file.
     *
	 * @since 1.0
     */
    public EXMY(String handlerPath, String varPath) {
    	Handler = handlerPath;
		Namespace = Exom.Utils.StringUtils.FolderName(Handler) + "excm/";
    	Variables = varPath;
    	RamVars = new HashMap<String, String>();
    	FileVars = new HashMap<String, String>();
    	InIf = false;
    }
    
    /**
     * Handles a line of EXMY Script input.
     * 
     * @param input The line to handle.
     *
	 * @since 1.0
     */
    public void Handle(String input) {
    	try {
			input = StringUtils.Trim(input);
			if(input.length() >= 3 && input.toLowerCase().substring(0, 3).equals("if ")) {
				String exp = FormatLine(input.substring(3), RamVars, FileVars);
	            if((boolean)Engines.JavaScript.eval(exp)) {
	            	try {
	            		input = Source.nextLine();
		            	while((!input.toLowerCase().startsWith("endif")) && (!input.toLowerCase().startsWith("else"))) {
		            		Handle(input);
		            		input = Source.nextLine();
		            	}

		            	while(!input.toLowerCase().startsWith("endif")) {
		            		input = Source.nextLine();
		            	}
	            	}
	            	catch(NoSuchElementException ex) {
	            		throw new NoSuchElementException("Syntax error: expected \"endif\" before end of file");
	            	}
	            	catch(Exception ex) {
	            		throw ex;
	            	}
	            }
	            else {
	            	input = Source.nextLine();
	            	while((!input.toLowerCase().startsWith("endif")) && (!input.toLowerCase().startsWith("else"))) {
	            		input = Source.nextLine();
	            	}

	            	if(input.toLowerCase().startsWith("else")) {
	            		input = Source.nextLine();
	            		while(!input.toLowerCase().startsWith("endif")) {
		            		Handle(input);
		            		input = Source.nextLine();
		            	}
	            	}
	            }
	            input = Source.nextLine();
			}
			if(input.length() >= 7 && input.toLowerCase().substring(0, 7).equals("--exch ")) {
				Handler = input.substring(7, input.length());
				Namespace = Exom.Utils.StringUtils.FolderName(Handler) + "excm/";
			}
			else if(input.length() >= 7 && input.toLowerCase().substring(0, 7).equals("--exvr ")) {
				Variables = input.substring(7, input.length());
			}
			else if(input.length() >= 4 && input.toLowerCase().substring(0, 4).equals("let ")) {
				String letInfo = input.substring(4, input.length());
				String let = "";
				
				while(letInfo.length() > 0) {
					char pos = letInfo.charAt(0);
					if(pos != ' ' && pos != '=') {
						let += pos;
						if(letInfo.length() > 1)
							letInfo = letInfo.substring(1, letInfo.length());
						else letInfo = "";
					}
					else break;
				}
							
				while(letInfo.length() > 0) {
					if(letInfo.charAt(0) != ' ')
						break;
					letInfo = letInfo.substring(1, letInfo.length());
				}
				if(letInfo.length() > 0) {
					if(letInfo.charAt(0) != '=')
						throw new InvalidNameException("Syntax error: variable name cannot contain spaces");
					
					letInfo = letInfo.substring(1, letInfo.length());
					while(letInfo.length() > 0 && letInfo.charAt(0) == ' ') {
						letInfo = letInfo.substring(1, letInfo.length());
					}
					
					if(letInfo.charAt(0) != '\"') {
						String[] commandInfo = EXCH.GetEXCM(Handler, letInfo);
						if(commandInfo == null) {
							String value = FormatLine(letInfo, RamVars, FileVars);
							RamVars.put(let, value);
						}
						else {
							String args = FormatLine(commandInfo[1], RamVars, FileVars);
							try {
								String response = EXCM.Run(Namespace + commandInfo[0] + ".excm", args);
								RamVars.put(let, response);
							}
							catch(Exception ex) {
								throw new FileNotFoundException("EXCM '" + commandInfo[0] + "' was not found");
							}
						}
					}
					else {
						String value = FormatLine(letInfo, RamVars, FileVars);
						RamVars.put(let, value);
					}
				}
				else RamVars.put(let, "");
			}
			else if(input.length() >= 4 && input.toLowerCase().substring(0, 4).equals("var ")) {
				String varInfo = input.substring(4, input.length());
				String var = "";
				
				while(varInfo.length() > 0) {
					char pos = varInfo.charAt(0);
					if(pos != ' ' && pos != '=') {
						var += pos;
						if(varInfo.length() > 1)
							varInfo = varInfo.substring(1, varInfo.length());
						else varInfo = "";
					}
					else break;
				}
							
				while(varInfo.length() > 0) {
					if(varInfo.charAt(0) != ' ')
						break;
					varInfo = varInfo.substring(1, varInfo.length());
				}
				if(varInfo.length() > 0) {
					if(varInfo.charAt(0) != '=')
						throw new InvalidNameException("Syntax error: variable name cannot contain spaces");
					
					varInfo = varInfo.substring(1, varInfo.length());
					while(varInfo.length() > 0 && varInfo.charAt(0) == ' ') {
						varInfo = varInfo.substring(1, varInfo.length());
					}
					
					if(varInfo.charAt(0) != '\"') {
						String[] commandInfo = EXCH.GetEXCM(Handler, varInfo);
						if(commandInfo == null) {
							String value = FormatLine(varInfo, RamVars, FileVars);
							FileVars.put(var, value);
						}
						else {
							String args = FormatLine(commandInfo[1], RamVars, FileVars);
							try {
								String response = EXCM.Run(Namespace + commandInfo[0] + ".excm", args);
								FileVars.put(var, response);
							}
							catch(Exception ex)
							{
								throw new FileNotFoundException("EXCM '" + commandInfo[0] + "' was not found");
							}
						}
					}
					else {
						String value = FormatLine(varInfo, RamVars, FileVars);
						FileVars.put(var, value);
					}
				}
				else FileVars.put(var, "");
			}
			else if(input.length() >= 1 && input.toLowerCase().substring(0, 1).equals("*")) {
				String let = "";
				String letInfo = input.substring(1, input.length());
				
				while(letInfo.length() > 0) {
					char pos = letInfo.charAt(0);
					if(pos != ' ' && pos != '=')
					{
						let += pos;
						letInfo = letInfo.substring(1, letInfo.length());
					}
					else break;
				}
				
				if(!RamVars.containsKey(let))
					throw new NullPointerException("Syntax error: \"" + let + "\" is not a variable");
					
				while(letInfo.length() > 0 && letInfo.charAt(0) == ' ') {
					letInfo = letInfo.substring(1, letInfo.length());
				}
				if(letInfo.length() == 0)
					throw new NullPointerException("Syntax error: '=' expected");

				if(letInfo.charAt(0) != '=')
					throw new InvalidNameException("Syntax error: Variable name cannot contain spaces");
					
				letInfo = letInfo.substring(1, letInfo.length());
				while(letInfo.length() > 0 && letInfo.charAt(0) == ' ') {
					letInfo = letInfo.substring(1, letInfo.length());
				}
					
				if(letInfo.charAt(0) != '\"') {
					String[] commandInfo = EXCH.GetEXCM(Handler, letInfo);
					if(commandInfo == null) {
						String value = FormatLine(letInfo, RamVars, FileVars);
						RamVars.put(let, value);
					}
					else {
						String args = FormatLine(commandInfo[1], RamVars, FileVars);
						try {
							String response = EXCM.Run(Namespace + commandInfo[0] + ".excm", args);
							RamVars.put(let, response);
						}
						catch(Exception ex) {
							throw new FileNotFoundException("EXCM '" + commandInfo[0] + "' was not found");
						}
					}
				}
				else {
					String value = FormatLine(letInfo, RamVars, FileVars);
					RamVars.put(let, value);
				}
			}
			else if(input.length() >= 1 && input.toLowerCase().substring(0, 1).equals("$")) {
				String var = "";
				String varInfo = input.substring(1, input.length());
				
				while(varInfo.length() > 0) {
					char pos = varInfo.charAt(0);
					if(pos != ' ' && pos != '=') {
						var += pos;
						varInfo = varInfo.substring(1, varInfo.length());
					}
					else break;
				}
				
				if(!RamVars.containsKey(var))
					throw new NullPointerException("Syntax error: \"" + var + "\" is not a variable");
					
				while(varInfo.length() > 0 && varInfo.charAt(0) == ' ') {
					varInfo = varInfo.substring(1, varInfo.length());
				}
				if(varInfo.length() == 0)
					throw new NullPointerException("Syntax error: '=' expected");

				if(varInfo.charAt(0) != '=')
					throw new InvalidNameException("Syntax error: Variable name cannot contain spaces");
					
				varInfo = varInfo.substring(1, varInfo.length());
				while(varInfo.length() > 0 && varInfo.charAt(0) == ' ') {
					varInfo = varInfo.substring(1, varInfo.length());
				}
					
				if(varInfo.charAt(0) != '\"') {
					String[] commandInfo = EXCH.GetEXCM(Handler, varInfo);
					if(commandInfo == null) {
						String value = FormatLine(varInfo, RamVars, FileVars);
						FileVars.put(var, value);
					}
					else {
						String args = FormatLine(commandInfo[1], RamVars, FileVars);
						try {
							String response = EXCM.Run(Namespace + commandInfo[0] + ".excm", args);
							FileVars.put(var, response);
						}
						catch(Exception ex) {
							throw new FileNotFoundException("EXCM '" + commandInfo[0] + "' was not found");
						}
					}
				}
				else {
					String value = FormatLine(varInfo, RamVars, FileVars);
					FileVars.put(var, value);
				}
			}
			else if(input.length() == 8 && input.toLowerCase().substring(0, 8).equals("savevars")) {
				try {
					FileOutputStream fo = new FileOutputStream(Variables, false);
					for(Map.Entry<String, String> entry : FileVars.entrySet()) 
					{
					    String key = entry.getKey();
					    String value = entry.getValue();

					    fo.write((byte)key.length());
					    fo.write(key.getBytes(StandardCharsets.US_ASCII));
					    fo.write((byte)value.length());
					    fo.write(value.getBytes(StandardCharsets.US_ASCII));
					}
					fo.close();
				}
				catch(Exception ex) {
					throw new FileNotFoundException("Invalid file or insufficient permissions");
				}
			}
			else if(input.length() == 8 && input.toLowerCase().substring(0, 8).equals("loadvars")) {
				try {
					FileInputStream fi = new FileInputStream(Variables);
					byte[] key;
					byte[] value;
					byte x = (byte)fi.read();
					while(x > -1) {
						key = new byte[x];
						fi.read(key);
						value = new byte[(byte)fi.read()];
						fi.read(value);
						FileVars.put(new String(key, StandardCharsets.US_ASCII), new String(value, StandardCharsets.US_ASCII));
						x = (byte)fi.read();
					}
					fi.close();
				}
				catch(Exception ex) {
					throw new FileNotFoundException(ex + ": Invalid file or insufficient permissions");
				}
			}
			else if(input.length() == 9 && input.toLowerCase().substring(0, 9).equals("clearvars")) {
				FileVars.clear();
				new File(Variables).delete();
			}
			else if(input.length() > 0) {
				String[] commandInfo = EXCH.GetEXCM(Handler, input);
				if(commandInfo == null)
					throw new NullPointerException("Command \"" + input.split(" ")[0] + "\" has no alias");
				String args = FormatLine(commandInfo[1], RamVars, FileVars);
				try {
					String response = EXCM.Run(Namespace + commandInfo[0] + ".excm", args);
					if(!response.isEmpty())
						System.out.println(response);
				}
				catch(Exception ex) {
					throw new FileNotFoundException("EXCM \"" + commandInfo[0] + "\" was not found");
				}
			}
		}
		catch(ClassNotFoundException ex) {
			System.out.println("Exception: " + ex + ": The Handler \"" + Handler + "\" was not found");
		}
		catch(Exception ex) {
			System.out.println("Exception: " + ex);
		}
    }
    
    /**
     * Runs an EXMY Script.
     * 
     * @param scriptFile The EXMY Script to run.
     *
	 * @since 1.0
     */
	public void Run(String scriptFile) {
		if(Handler.isEmpty()) {
			String scriptPath = Exom.Utils.StringUtils.FolderName(scriptFile);
			if(scriptPath.equals(scriptFile)) {
				Namespace = "excm/"; 
				Handler = "handler.exch";
			}
			else {
				Namespace = scriptPath + "excm/";
				Handler = scriptPath + "Handler.exch";
			}
		}
		
		if(Variables.isEmpty()) {
			String scriptPath = Exom.Utils.StringUtils.FolderName(scriptFile);
			if(scriptPath.equals(scriptFile)) {
				Variables = "variables.exvr";
			}
			else
			{
				Variables = scriptPath + "variables.exvr";
			}
		}
		
		try {
			Source = new Scanner(new FileReader(scriptFile));
			String input;
			while(Source.hasNextLine()) {
				input = Source.nextLine();
				Handle(input);
			}
			Source.close();
		}
		catch (Exception ex) {
			System.out.println("Exception: " + ex);
		}
	}
	
	/**
	 * Formats a line of EXMY Script.
	 * 
	 * @param line The line to format.
	 * @param ram The map of RAM Variables.
	 * @param file The map of File Variables.
	 * 
	 * @return The formatted line.
	 *
	 * @since 1.0
	 */
	public static String FormatLine(String line, Map<String, String> ram, Map<String, String> file) {
		try {
			boolean mode = false;
			boolean esc = false;
			boolean before = false;
			String fin = "";
			String var = "";
			
			for(int u = 0; u < line.length(); u++) {
				if(u > 0 && line.charAt(u - 1) != '\\')
					esc = false;
				
				char c = line.charAt(u);
				
				if(c == '\\') {
					esc = !esc;
					if(!esc) {
						if(!mode)
							throw new Exception("Syntax error: '\\\\'");
						else fin += "\\";
					}
				}
				else if(c == '\"') {
					if(!esc) {
						mode = !mode;
						if(!mode) {
							before = true;	
						}
						else if(var.length() > 0) {
							int index = 0;
							if(before) {
								if(var.charAt(index) != '+')
									throw new Exception("Syntax error: '+' expected between string and variable");
								index++;
							}
							else {
								if(var.charAt(index) == '+')
									throw new Exception("Syntax error: '+' was not expected");
							}
							if(var.charAt(var.length() - 1) != '+')
								throw new Exception("Syntax error: '+' expected between variable and string");
							
							if(var.charAt(index) == '*') {
								index++;
								String varName = "";
								for(int l = index; l < var.length(); l++) {
									char current = var.charAt(l);
									int code = (int) current;
									if(code < 48 || (code > 57 && code < 65) || (code > 90 && code < 97) || code > 122)
										break;
									varName += current;
								}
								if(varName.length() == 0)
									throw new Exception("Syntax error: \"\" is not a variable");

								if(!ram.containsKey(varName))
									throw new Exception("Syntax error: \"" + varName + "\" is not a variable");
								fin += ram.get(varName);
							}
							else if(var.charAt(index) == '$') {
								index++;
								int end = index;
								String varName = "";
								for(int l = index; l < var.length(); l++) {
									int code = (int) var.charAt(l);
									if(code < 48 || (code > 57 && code < 65) || (code > 90 && code < 97) || code > 122)
										break;
									end++;
								}
								if(end == index)
									throw new Exception("Syntax error: \"\" is not a variable");
								if(var.charAt(var.length() - 1) != '+')
									throw new Exception("Syntax error: '+' expected between variable and string");
								varName = var.substring(index, end);
								if(!file.containsKey(varName))
									throw new Exception("Syntax error: \"" + varName + "\" is not a variable");
								fin += file.get(varName);
							}
							else throw new Exception("Syntax error: expected * or $");		
							var = "";
						}
					}
					else {
						if(!mode)
							throw new Exception("Syntax error: '\\\"'");
						else fin += "\"";
					}
				}
				else if(mode) {
					if(esc) {
						if(c == 'n')
							fin += "\n";
						else if(c == 'r')
							fin += "\r";
						else if(c == 'b')
							fin += "\b";
						else if(c == 't')
							fin += "\t";
						else if(c == 'f')
							fin += "\f";
						else throw new Exception("Syntax error: '\\" + c + "' is not a valid escape");
					}
					else fin += c;
				}
				else {
					if(c != ' ')
						var += c;
				}
			}
			
			if(mode)
				throw new Exception("Syntax error: expected \" at end of string");
			if(var.length() > 0) {
				int index = 0;
				if(before) {
					if(var.charAt(index) != '+')
						throw new Exception("Syntax error: '+' expected between string and variable");
					index++;
				}
				else {
					if(var.charAt(index) == '+')
						throw new Exception("Syntax error: '+' was not expected");
				}
				
				if(var.charAt(index) == '*') {
					index++;
					int end = index;
					String varName = "";
					for(int l = index; l < var.length(); l++) {
						int code = (int) var.charAt(l);
						if(code < 48 || (code > 57 && code < 65) || (code > 90 && code < 97) || code > 122)
							break;
						end++;
					}
					if(end == index)
						throw new Exception("Syntax error: \"\" is not a variable");
					varName = var.substring(index, end);
					if(!ram.containsKey(varName))
						throw new Exception("Syntax error: \"" + varName + "\" is not a variable");
					fin += ram.get(varName);
				}
				else if(var.charAt(index) == '$') {
					index++;
					int end = index;
					String varName = "";
					for(int l = index; l < var.length(); l++) {
						int code = (int) var.charAt(l);
						if(code < 48 || (code > 57 && code < 65) || (code > 90 && code < 97) || code > 122)
							break;
						end++;
					}
					if(end == index)
						throw new Exception("Syntax error: \"\" is not a variable");
					varName = var.substring(index, end);
					if(!file.containsKey(varName))
						throw new Exception("Syntax error: \"" + varName + "\" is not a variable");
					fin += file.get(varName);
				}
				else throw new Exception("Syntax error: expected * or $");
			}
			return fin;
		}
		catch(Exception ex) {
			throw new NullPointerException(ex.getMessage());
		}
	}
}
