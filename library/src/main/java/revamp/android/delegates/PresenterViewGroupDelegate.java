package revamp.android.delegates;

import android.support.annotation.NonNull;

import revamp.base.Presenter;
import revamp.base.ViewComponent;

public class PresenterViewGroupDelegate<V extends ViewComponent, P extends Presenter<V>> {

  private PresenterDelegateCallback<V, P> mCallback;

  public PresenterViewGroupDelegate(@NonNull PresenterDelegateCallback<V, P> callback) {
    this.mCallback = callback;
  }

  public void onAttachedToWindow() {
    Presenter<V> presenter = mCallback.presenter();
    presenter.takeView(mCallback.viewComponent());
  }

  public void onDetachedFromWindow() {
    Presenter presenter = mCallback.presenter();
    presenter.dropView();
  }
}


