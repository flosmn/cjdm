package mergesortbug;

/**
 * This class is a special exception thrown when the indexes supplied are
 * invalid.
 */
public class InvalidInput extends Exception{

  public InvalidInput(String exception) {
    super(exception);
  }
}
