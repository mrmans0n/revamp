package revamp.android;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.v7.app.AppCompatActivity;

import revamp.android.delegates.PresenterActivityDelegate;
import revamp.android.delegates.PresenterDelegateCallback;
import revamp.base.Presenter;
import revamp.base.ViewComponent;

/**
 * Created by mrm on 27/5/15.
 */
public abstract class PresenterAppCompatActivity<P extends Presenter<V>, V extends ViewComponent> extends AppCompatActivity implements ViewComponent, PresenterDelegateCallback<V, P>, CanRetainObjects {

    protected P presenter;
    protected PresenterActivityDelegate<V, P> delegate;

    @Override
    @CallSuper
    protected void onCreate(Bundle savedInstanceState) {
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
    public void onRestart() {
        super.onRestart();
        getPresenterDelegate().onRestart();
    }

    @Override
    @CallSuper
    public void onContentChanged() {
        super.onContentChanged();
        getPresenterDelegate().onContentChanged();
    }

    @Override
    @CallSuper
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getPresenterDelegate().onSaveInstanceState(outState);
    }

    @Override
    @CallSuper
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        getPresenterDelegate().onPostCreate(savedInstanceState);
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return getPresenterDelegate().onRetainCustomNonConfigurationInstance();
    }

    @Override
    public void retainObject(String objectId, Object object) {
        getPresenterDelegate().retainObject(objectId, object);
    }

    @Override
    public Object restoreRetained(String objectId) {
        return getPresenterDelegate().restoreRetained(objectId);
    }

    private PresenterActivityDelegate<V, P> getPresenterDelegate() {
        if (delegate == null) {
            delegate = new PresenterActivityDelegate<>(this, getLastNonConfigurationInstance());
        }
        return delegate;
    }

    @Override
    public V viewComponent() {
        return (V) this;
    }

    @Override
    public P presenter() {
        if (presenter == null) {
            presenter = buildPresenter();
        }
        return presenter;
    }

    @Override
    public void setPresenter(P presenter) {
        this.presenter = presenter;
    }

    public abstract P buildPresenter();
}
