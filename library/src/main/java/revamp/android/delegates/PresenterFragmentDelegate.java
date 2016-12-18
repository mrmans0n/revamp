package revamp.android.delegates;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import revamp.base.Presenter;
import revamp.base.ViewComponent;

public class PresenterFragmentDelegate<V extends ViewComponent, P extends Presenter<V>> {

  private PresenterDelegateCallback<V, P> mCallback;

  public PresenterFragmentDelegate(@NonNull PresenterDelegateCallback<V, P> callback) {
    mCallback = callback;
  }


  public void onCreate(Bundle savedInstanceState) {
    Presenter<V> presenter = mCallback.presenter();
    presenter.takeView(mCallback.viewComponent());
  }

  public void onDestroy() {
    Presenter presenter = mCallback.presenter();
    presenter.dropView();
  }

  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

  }

  public void onDestroyView() {

  }

  public void onActivityCreated(Bundle savedInstanceState) {

  }

  public void onAttach(Activity activity) {

  }

  public void onDetach() {

  }

  public void onStart() {

  }

  public void onStop() {

  }

  public void onResume() {

  }

  public void onPause() {

  }

  public void onSaveInstanceState(Bundle outState) {

  }
}


