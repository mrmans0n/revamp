package revamp.android.delegates;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;

import java.util.Map;

import revamp.android.RetainableStore;
import revamp.base.Presenter;
import revamp.base.ViewComponent;

public class PresenterActivityDelegate<V extends ViewComponent, P extends Presenter<V>> implements RetainableStore {

  private static final String PRESENTER_ID = "stored_presenter";
  private final Map<String, Object> mRetainedObjects;
  private final PresenterDelegateCallback<V, P> mCallback;

  public PresenterActivityDelegate(@NonNull PresenterDelegateCallback<V, P> callback, Object lastNonConfigurationInstance) {
    mCallback = callback;
    if (lastNonConfigurationInstance != null) {
      mRetainedObjects = (Map<String, Object>) lastNonConfigurationInstance;
      callback.setPresenter((P) mRetainedObjects.get(PRESENTER_ID));
    } else {
      mRetainedObjects = new ArrayMap<>();
    }
  }

  public void onCreate(Bundle savedInstanceState) {
    Presenter<V> presenter = mCallback.presenter();
    presenter.takeView(mCallback.viewComponent());
  }

  public void onDestroy() {
    Presenter presenter = mCallback.presenter();
    presenter.dropView();
  }

  public void onStart() {

  }

  public void onStop() {

  }

  public void onResume() {

  }

  public void onPause() {

  }

  public void onRestart() {

  }

  public void onContentChanged() {

  }

  public void onSaveInstanceState(Bundle outState) {

  }

  public void onPostCreate(Bundle savedInstanceState) {

  }

  public Object onRetainCustomNonConfigurationInstance() {
    mRetainedObjects.put(PRESENTER_ID, mCallback.presenter());
    return mRetainedObjects;
  }

  @Override
  public void retainObject(String objectId, Object object) {
    mRetainedObjects.put(objectId, object);
  }

  @Override
  public Object restoreRetained(String objectId) {
    return mRetainedObjects.get(objectId);
  }
}


