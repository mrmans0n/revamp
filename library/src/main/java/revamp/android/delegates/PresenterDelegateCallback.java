package revamp.android.delegates;

import android.support.annotation.NonNull;

import revamp.base.Presenter;
import revamp.base.ViewComponent;

public interface PresenterDelegateCallback<V extends ViewComponent, P extends Presenter<V>> {
  /**
   * @return a {@link Presenter} instance. If it does not exist it should create one.
   */
  @NonNull
  P presenter();

  /**
   * Sets a {@link Presenter} obtained from the retained store. This should override your locally
   * stored presenter, as it has been recovered from the store after a rotation.
   */
  void setRetainedPresenter(@NonNull P presenter);

  /**
   * @return a {@link ViewComponent} for this presenter.
   */
  @NonNull
  V viewComponent();
}
