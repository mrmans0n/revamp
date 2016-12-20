package revamp.android.delegates;

import android.os.Bundle;
import android.support.annotation.NonNull;

import revamp.store.RetainableStore;
import revamp.store.RetainableStoreImpl;
import revamp.base.Presenter;
import revamp.base.ViewComponent;

public class PresenterActivityDelegate<V extends ViewComponent, P extends Presenter<V>> implements RetainableStore {

  private static final String PRESENTER_ID = "stored_presenter";
  private final RetainableStore mRetainableStore;
  private final PresenterActivityDelegateCallback<V, P> mCallback;

  public PresenterActivityDelegate(@NonNull PresenterActivityDelegateCallback<V, P> callback, Object lastNonConfigurationInstance) {
    mCallback = callback;
    if (lastNonConfigurationInstance != null) {
      mRetainableStore = (RetainableStore) lastNonConfigurationInstance;
      callback.setRetainedPresenter((P) mRetainableStore.restoreRetained(PRESENTER_ID));
    } else {
      mRetainableStore = new RetainableStoreImpl();
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
    if (!mCallback.shouldRetain()) {
      return null;
    }
    mRetainableStore.retainObject(PRESENTER_ID, mCallback.presenter());
    return mRetainableStore;
  }

  @Override
  public void retainObject(String objectId, Object object) {
    mRetainableStore.retainObject(objectId, object);
  }

  @Override
  public Object restoreRetained(String objectId) {
    return mRetainableStore.restoreRetained(objectId);
  }
}


