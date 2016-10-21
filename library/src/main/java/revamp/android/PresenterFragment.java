package revamp.android;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.view.View;

import revamp.android.delegates.PresenterDelegateCallback;
import revamp.android.delegates.PresenterFragmentDelegate;
import revamp.base.Presenter;
import revamp.base.PresenterFactory;
import revamp.base.ViewComponent;

/**
 * Created by mrm on 27/5/15.
 */
public abstract class PresenterFragment<P extends Presenter<V>, V extends ViewComponent> extends Fragment implements ViewComponent, PresenterDelegateCallback<V>, PresenterFactory<P> {

    private P presenter;
    private PresenterFragmentDelegate<V> delegate;

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

    private PresenterFragmentDelegate<V> getPresenterDelegate() {
        if (delegate == null) {
            delegate = new PresenterFragmentDelegate<>(this);
        }
        return delegate;
    }

    @Override
    public V viewComponent() {
        return (V) this;
    }

    public P presenter() {
        if (presenter == null) {
            presenter = buildPresenter();
        }
        return presenter;
    }

    @Override
    public abstract P buildPresenter();
}
