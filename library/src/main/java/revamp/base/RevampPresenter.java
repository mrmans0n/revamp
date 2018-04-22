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
public abstract class RevampPresenter<MODEL extends Model, VIEW extends ViewComponent> implements Presenter<VIEW> {

  @Nullable private VIEW mViewComponent;
  @NonNull private final MODEL mModel;
  @NonNull private final Class<VIEW> mViewComponentClass;
  @NonNull private final Class<MODEL> mBusinessObjectClass;
  @Nullable private final VIEW mEmptyViewComponent;
  @NonNull private final InvocationHandler mViewComponentInvocationHandler = new InvocationHandler() {
    @Override
    public Object invoke(Object o, Method method, Object[] args) throws Throwable {
      Log.d("Revamp", "EmptyViewComponent -> " + method.getName() + "(" + TextUtils.join(", ", args) + ")");
      return null;
    }
  };

  public RevampPresenter(@NonNull MODEL model) {
    mModel = model;
    Class<?>[] classes = TypeResolver.resolveRawArguments(RevampPresenter.class, getClass());
    mBusinessObjectClass = (Class<MODEL>) classes[0];
    mViewComponentClass = (Class<VIEW>) classes[1];
    mEmptyViewComponent = createEmptyViewComponent();
    mViewComponent = mEmptyViewComponent;
  }

  @Nullable
  private VIEW createEmptyViewComponent() {
    if (!isEmptyViewComponentEnabled()) {
      return null;
    }
    return (VIEW) Proxy.newProxyInstance(mViewComponentClass.getClassLoader(),
            new Class<?>[]{mViewComponentClass},
            mViewComponentInvocationHandler);
  }

  @CallSuper
  @Override
  public void takeView(@NonNull VIEW view) {
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

  @NonNull
  protected VIEW view() {
    return mViewComponent;
  }

  @Deprecated
  protected MODEL bo() {
    return model();
  }

  @NonNull
  protected MODEL model() {
    return mModel;
  }

  /**
   * Override if you prefer to handle nulls yourself on {@link #view()} invocations.
   *
   * @return true for automatic empty {@link ViewComponent}, false to handle {@link #view()} nullity by yourself.
   */
  protected boolean isEmptyViewComponentEnabled() {
    return false;
  }

  @CallSuper
  @Override
  public void dropView() {
    mViewComponent = mEmptyViewComponent;
  }

  @CallSuper
  @Override
  public void release() {
    if (model() instanceof ReleasableModel) {
      ((ReleasableModel)model()).release();
    }
  }
}
