package revamp.android;

public interface RetainableStore {

  boolean shouldRetain();

  void retainObject(String objectId, Object object);

  Object restoreRetained(String objectId);
}
