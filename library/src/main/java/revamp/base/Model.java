package revamp.base;

/**
 * Marks the BusinessObject (aka Model aka Logic) classes.
 */
public interface Model {
  Model NONE = new Model() {
    @Override
    public void release() {

    }
  };

  void release();
}
