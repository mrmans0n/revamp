package revamp.android.delegates;

import android.os.Bundle;
import android.support.annotation.NonNull;

import revamp.base.Presenter;
import revamp.base.ViewComponent;

/**
 * Created by mrm on 27/5/15.
 */
public class PresenterActivityDelegate<V extends ViewComponent> {

    private PresenterDelegateCallback<V> callback;

    public PresenterActivityDelegate(@NonNull PresenterDelegateCallback<V> callback) {
        this.callback = callback;
    }

    public void onCreate(Bundle savedInstanceState) {
        Presenter<V> presenter = callback.presenter();
        presenter.takeView(callback.viewComponent());
    }

    public void onDestroy() {
        Presenter presenter = callback.presenter();
        presenter.dropView();
    }

    public void onStart() {

    }

    public void onStop() {

    }

    public void onResume() {

    }

    public void onPause() {

    }

    public void onRestart() {

    }

    public void onContentChanged() {

    }

    public void onSaveInstanceState(Bundle outState) {

    }

    public void onPostCreate(Bundle savedInstanceState) {

    }
}


