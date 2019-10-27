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

import Exom.Exom;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * Contains various script engines.
 * 
 * @author UnexomWid
 *
 * @since 1.0
 */
public class ScriptEngines {

	/**
	 * The JavaScript engine.
	 *
	 * @since 1.0
	 */
	public static ScriptEngine JavaScript;

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
			JavaScript = new ScriptEngineManager().getEngineByName("JavaScript");
			Initialized = true;
		}
	}
}
