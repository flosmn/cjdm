/**
 * 
 */
package org.ogre4j.exceptions;

/**
 * Exception thrown by native methods when an Ogre::Exception is caught.
 *
 */
public class Ogre4jException extends Exception {

	/**
	 * Default serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see Exception#Exception()
	 */
	public Ogre4jException() {
	}

	/**
	 * @see Exception#Exception(String)
	 */
	public Ogre4jException(String message) {
		super(message);
	}

	/**
	 * @see Exception#Exception(Throwable)
	 */
	public Ogre4jException(Throwable cause) {
		super(cause);
	}

	/**
	 * @see Exception#Exception(String, Throwable)
	 */
	public Ogre4jException(String message, Throwable cause) {
		super(message, cause);
	}
}
