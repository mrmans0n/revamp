package revamp.base;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import net.jodah.typetools.TypeResolver;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Provides basic functionality for all presenters.
 * <p>
 * Automatically creates an empty {@link ViewComponent} implementation that performs no action
 * when the presenter hasn't got an attached one, thus minimizing the possible {@link NullPointerException}
 * when something is called in a an empty {@link ViewComponent} reference.
 * <p>
 * This behavior can be disabled overriding {@link #isEmptyViewComponentEnabled()} in your presenter.
 */
@SuppressWarnings("unchecked")
public abstract class RevampPresenter<BO extends BusinessObject, V extends ViewComponent> implements Presenter<V> {

  @Nullable private V mViewComponent;
  @NonNull private final BO mBusinessObject;
  @NonNull private final Class<V> mViewComponentClass;
  @NonNull private final Class<BO> mBusinessObjectClass;
  @Nullable private final V mEmptyViewComponent;
  @NonNull private final InvocationHandler mViewComponentInvocationHandler = new InvocationHandler() {
    @Override
    public Object invoke(Object o, Method method, Object[] args) throws Throwable {
      Log.d("Revamp", "EmptyViewComponent -> " + method.getName() + "(" + TextUtils.join(", ", args) + ")");
      return null;
    }
  };

  public RevampPresenter(@NonNull BO businessObject) {
    mBusinessObject = businessObject;
    Class<?>[] classes = TypeResolver.resolveRawArguments(RevampPresenter.class, getClass());
    mBusinessObjectClass = (Class<BO>) classes[0];
    mViewComponentClass = (Class<V>) classes[1];
    mEmptyViewComponent = createEmptyViewComponent();
    mViewComponent = mEmptyViewComponent;
  }

  private V createEmptyViewComponent() {
    if (!isEmptyViewComponentEnabled()) {
      return null;
    }
    return (V) Proxy.newProxyInstance(mViewComponentClass.getClassLoader(),
            new Class<?>[]{mViewComponentClass},
            mViewComponentInvocationHandler);
  }

  @CallSuper
  @Override
  public void takeView(@NonNull V view) {
    mViewComponent = view;
  }

  /**
   * @return true if there is an attached {@link ViewComponent}
   */
  public boolean isTaken() {
    return mViewComponent != null && mViewComponent != mEmptyViewComponent;
  }

  /**
   * @return true if the current {@link ViewComponent} is an EmptyViewComponent
   */
  protected boolean isUsingEmptyViewComponent() {
    return isEmptyViewComponentEnabled() && mViewComponent != null && mViewComponent == mEmptyViewComponent;
  }

  protected V view() {
    return mViewComponent;
  }

  protected BO bo() {
    return mBusinessObject;
  }

  /**
   * Override if you prefer to handle nulls yourself on {@link #view()} invocations.
   *
   * @return true for automatic empty {@link ViewComponent}, false to handle {@link #view()} nullity by yourself.
   */
  protected boolean isEmptyViewComponentEnabled() {
    return true;
  }

  @CallSuper
  @Override
  public void dropView() {
    mViewComponent = mEmptyViewComponent;
  }

  @CallSuper
  @Override
  public void release() {
    bo().release();
  }
}
