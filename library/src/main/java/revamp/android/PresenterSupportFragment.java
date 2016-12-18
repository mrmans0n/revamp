package revamp.android;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import revamp.android.delegates.PresenterDelegateCallback;
import revamp.android.delegates.PresenterFragmentDelegate;
import revamp.base.Presenter;
import revamp.base.ViewComponent;

public abstract class PresenterSupportFragment<P extends Presenter<V>, V extends ViewComponent> extends Fragment implements ViewComponent, PresenterDelegateCallback<V, P> {

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

  private PresenterFragmentDelegate<V, P> getPresenterDelegate() {
    if (mDelegate == null) {
      mDelegate = new PresenterFragmentDelegate<>(this);
    }
    return mDelegate;
  }

  @Override
  public V viewComponent() {
    return (V) this;
  }

  @Override
  public P presenter() {
    if (mPresenter == null) {
      mPresenter = buildPresenter();
    }
    return mPresenter;
  }

  @Override
  public void setPresenter(P presenter) {
    mPresenter = presenter;
  }

  public abstract P buildPresenter();
}
