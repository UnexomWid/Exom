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
