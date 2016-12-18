// Copyright (c) 2016-present Revamp

package revamp.android;

public interface CanRetainObjects {
    void retainObject(String objectId, Object object);

    Object restoreRetained(String objectId);
}
