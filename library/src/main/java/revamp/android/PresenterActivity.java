package revamp.android;

import android.app.Activity;
import android.os.Bundle;

import revamp.android.delegates.PresenterActivityDelegate;
import revamp.android.delegates.PresenterDelegateCallback;
import revamp.base.Presenter;
import revamp.base.ViewComponent;

/**
 * Created by mrm on 27/5/15.
 */
public abstract class PresenterActivity<P extends Presenter<V>, V extends ViewComponent> extends Activity implements ViewComponent, PresenterDelegateCallback<V> {

    protected P presenter;
    protected PresenterActivityDelegate<V> delegate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPresenterDelegate().onCreate(savedInstanceState);
    }

    @Override

    public void onDestroy() {
        super.onDestroy();
        getPresenterDelegate().onDestroy();
    }

    @Override
    public void onStart() {
        super.onStart();
        getPresenterDelegate().onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        getPresenterDelegate().onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenterDelegate().onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        getPresenterDelegate().onPause();
    }

    @Override
    public void onRestart() {
        super.onRestart();
        getPresenterDelegate().onRestart();
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        getPresenterDelegate().onContentChanged();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getPresenterDelegate().onSaveInstanceState(outState);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        getPresenterDelegate().onPostCreate(savedInstanceState);
    }

    private PresenterActivityDelegate<V> getPresenterDelegate() {
        if (delegate == null) {
            delegate = new PresenterActivityDelegate<>(this);
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

    public abstract P buildPresenter();
}
