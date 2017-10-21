package revamp.base;

/**
 * Marks the BusinessObject (aka Model aka Logic) classes.
 */
public interface BusinessObject {
  BusinessObject NONE = new BusinessObject() {
    @Override
    public void release() {

    }
  };

  void release();
}
