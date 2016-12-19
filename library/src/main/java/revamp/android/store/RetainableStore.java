package revamp.android.store;

public interface RetainableStore {

  void retainObject(String objectId, Object object);

  Object restoreRetained(String objectId);
}
