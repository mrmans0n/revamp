package revamp.android;

public interface RetainableStore {
  void retainObject(String objectId, Object object);

  Object restoreRetained(String objectId);
}
