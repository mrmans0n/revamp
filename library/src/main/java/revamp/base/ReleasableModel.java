package revamp.base;

/**
 * Marks the BusinessObject (aka Model aka Logic) classes.
 */
public interface ReleasableModel extends Model {
  ReleasableModel NONE = new ReleasableModel() {
    @Override
    public void release() {

    }
  };

  void release();
}
