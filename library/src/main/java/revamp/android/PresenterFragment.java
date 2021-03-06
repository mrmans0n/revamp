package revamp.android;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import revamp.android.delegates.PresenterFragmentDelegate;
import revamp.android.delegates.PresenterFragmentDelegateCallback;
import revamp.base.Presenter;
import revamp.base.ViewComponent;
import revamp.store.RetainableStore;

public abstract class PresenterFragment<P extends Presenter<V>, V extends ViewComponent> extends Fragment implements ViewComponent, PresenterFragmentDelegateCallback<V, P>, RetainableStore {

  protected P mPresenter;
  protected PresenterFragmentDelegate<V, P> mDelegate;

  @Override
  @CallSuper
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getPresenterDelegate().onCreate(savedInstanceState);
  }

  @Override
  @CallSuper
  public void onDestroy() {
    super.onDestroy();
    getPresenterDelegate().onDestroy();
  }

  @Override
  @CallSuper
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    getPresenterDelegate().onViewCreated(view, savedInstanceState);
  }

  @Override
  @CallSuper
  public void onDestroyView() {
    super.onDestroyView();
    getPresenterDelegate().onDestroyView();
  }

  @Override
  @CallSuper
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    getPresenterDelegate().onActivityCreated(savedInstanceState);
  }

  @Override
  @CallSuper
  public void onAttach(Activity activity) {
    super.onAttach(activity);
    getPresenterDelegate().onAttach(activity);
  }

  @Override
  @CallSuper
  public void onDetach() {
    super.onDetach();
    getPresenterDelegate().onDetach();
  }

  @Override
  @CallSuper
  public void onStart() {
    super.onStart();
    getPresenterDelegate().onStart();
  }

  @Override
  @CallSuper
  public void onStop() {
    super.onStop();
    getPresenterDelegate().onStop();
  }

  @Override
  @CallSuper
  public void onResume() {
    super.onResume();
    getPresenterDelegate().onResume();
  }

  @Override
  @CallSuper
  public void onPause() {
    super.onPause();
    getPresenterDelegate().onPause();
  }

  @Override
  @CallSuper
  public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    getPresenterDelegate().onSaveInstanceState(outState);
  }

  @Override
  public boolean shouldRetain() {
    return true;
  }

  @Override
  public void retainObject(String objectId, Object object) {
    mDelegate.retainObject(objectId, object);
  }

  @Override
  public Object restoreRetained(String objectId) {
    return mDelegate.restoreRetained(objectId);
  }

  private PresenterFragmentDelegate<V, P> getPresenterDelegate() {
    if (mDelegate == null) {
      mDelegate = new PresenterFragmentDelegate<>(this, getActivity());
    }
    return mDelegate;
  }

  @NonNull
  @Override
  public V viewComponent() {
    return (V) this;
  }

  @NonNull
  @Override
  public P presenter() {
    if (mPresenter == null) {
      mPresenter = buildPresenter();
    }
    return mPresenter;
  }

  @Override
  public void setRetainedPresenter(@NonNull P presenter) {
    mPresenter = presenter;
  }

  public abstract P buildPresenter();
}
