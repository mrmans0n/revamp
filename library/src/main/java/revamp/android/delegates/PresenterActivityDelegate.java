package revamp.android.delegates;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import revamp.base.Presenter;
import revamp.base.ViewComponent;
import revamp.store.RetainableStore;
import revamp.store.RetainableStoreImpl;

public class PresenterActivityDelegate<V extends ViewComponent, P extends Presenter<V>> implements RetainableStore {

  @VisibleForTesting static final String PRESENTER_ID = "stored_presenter";
  private final RetainableStore mRetainableStore;
  private final PresenterActivityDelegateCallback<V, P> mCallback;

  public PresenterActivityDelegate(@NonNull PresenterActivityDelegateCallback<V, P> callback, Object lastNonConfigurationInstance) {
    mCallback = callback;
    if (lastNonConfigurationInstance != null && callback.shouldRetain()) {
      if (!(lastNonConfigurationInstance instanceof RetainableStore)) {
        throw new IllegalArgumentException("lastNonConfigurationInstance is not a RetainableStore");
      }
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

  public void onDestroy(boolean finishing) {
    Presenter presenter = mCallback.presenter();
    presenter.dropView();
    if (finishing) {
      presenter.release();
    }
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


