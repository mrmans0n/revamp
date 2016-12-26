package revamp.base;

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
 */
public abstract class BasePresenter<BO extends BusinessObject, V extends ViewComponent> implements Presenter<V> {

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

  public BasePresenter(@NonNull BO businessObject) {
    mBusinessObject = businessObject;
    Class<?>[] classes = TypeResolver.resolveRawArguments(BasePresenter.class, getClass());
    mBusinessObjectClass = (Class<BO>) classes[0];
    mViewComponentClass = (Class<V>) classes[1];
    mEmptyViewComponent = createEmptyViewComponent();
    mViewComponent = mEmptyViewComponent;
  }

  private V createEmptyViewComponent() {
    if (!isEmptyViewComponentEnabled()) {
      return null;
    }
    V proxy = (V) Proxy.newProxyInstance(mViewComponentClass.getClassLoader(),
            new Class<?>[]{mViewComponentClass},
            mViewComponentInvocationHandler);
    return proxy;
  }


  @Override
  public void takeView(@NonNull V view) {
    mViewComponent = view;
  }

  public boolean isTaken() {
    return mViewComponent != null && mViewComponent != mEmptyViewComponent;
  }

  public boolean isUsingEmptyViewComponent() {
    return isEmptyViewComponentEnabled() && mViewComponent != null && mViewComponent == mEmptyViewComponent;
  }

  protected V view() {
    return mViewComponent;
  }

  protected BO bo() {
    return mBusinessObject;
  }

  protected boolean isEmptyViewComponentEnabled() {
    return true;
  }

  @Override
  public void dropView() {
    mViewComponent = mEmptyViewComponent;
  }
}
