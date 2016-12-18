package revamp.base;

import android.support.annotation.NonNull;

/**
 * Provides basic functionality for all presenters.
 */
public abstract class BasePresenter<BO extends BusinessObject, V extends ViewComponent> implements Presenter<V> {

  private V viewComponent;
  private BO businessObject;

  public BasePresenter(@NonNull BO businessObject) {
    this.businessObject = businessObject;
  }

  @Override
  public void takeView(@NonNull V view) {
    this.viewComponent = view;
  }

  public boolean isTaken() {
    return viewComponent != null;
  }

  protected V view() {
    return viewComponent;
  }

  protected BO bo() {
    return businessObject;
  }

  @Override
  public void dropView() {
    if (isTaken()) {
      viewComponent = null;
    }
  }
}
