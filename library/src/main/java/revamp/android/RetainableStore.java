package revamp.android;

public interface RetainableStore extends Retainable {

  void retainObject(String objectId, Object object);

  Object restoreRetained(String objectId);
}
