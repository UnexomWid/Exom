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
