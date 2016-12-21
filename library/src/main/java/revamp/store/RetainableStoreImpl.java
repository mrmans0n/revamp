// Copyright (c) 2016-present Revamp

package revamp.store;

import android.support.v4.util.ArrayMap;

public class RetainableStoreImpl implements RetainableStore {

  private final ArrayMap<String, Object> mStore;

  public RetainableStoreImpl() {
    mStore = new ArrayMap<>();
  }

  @Override
  public void retainObject(String objectId, Object object) {
    mStore.put(objectId, object);
  }

  @Override
  public Object restoreRetained(String objectId) {
    return mStore.get(objectId);
  }
}
