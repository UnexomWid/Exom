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

package Exom.Objects;

/**
 * Represents an object with a name.
 * 
 * @author UnexomWid
 *
 * @since 1.0
 */
public class NamedObject<T> {

	/**
	 * The name of the object.
	 *
	 * @since 1.0
	 */
	public String Name;
	/**
	 * The value of the object.
	 */
	public T Value;
	
	/**
	 * Initializes a new instance of the NamedObject class.
	 * 
	 * @param name The name of the object.
	 * @param value The value of the object.
	 *
	 * @since 1.0
	 */
	public NamedObject(String name, T value) {
		Name = name;
		Value = value;
	}
	/**
	 * Initializes a new instance of the NamedObject class with an empty value.
	 * 
	 * @param name The name of the object.
	 *
	 * @since 1.0
	 */
	public NamedObject(String name) {
		Name = name;
		Value = null;
	}
	/**
	 * Initializes a new instance of the NamedObject class with an empty name and value.
	 *
	 * @since 1.0
	 */
	public NamedObject() {
		Name = "";
		Value = null;
	}
}
