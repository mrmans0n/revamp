package revamp.android.delegates;

import android.support.annotation.NonNull;

import revamp.base.Presenter;
import revamp.base.ViewComponent;

/**
 * Created by mrm on 27/5/15.
 */
public class PresenterViewGroupDelegate<V extends ViewComponent, P extends Presenter<V>> {

    private PresenterDelegateCallback<V, P> callback;

    public PresenterViewGroupDelegate(@NonNull PresenterDelegateCallback<V, P> callback) {
        this.callback = callback;
    }


    public void onAttachedToWindow() {
        Presenter<V> presenter = callback.presenter();
        presenter.takeView(callback.viewComponent());
    }

    public void onDetachedFromWindow() {
        Presenter presenter = callback.presenter();
        presenter.dropView();
    }

}


