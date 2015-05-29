package revamp.base;

import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;

/**
 * Provides basic functionality for all presenters.
 */
public abstract class BasePresenter<BO extends BusinessObject, V extends ViewComponent> implements Presenter<V> {

    private WeakReference<V> weakView;
    private BO businessObject;

    public BasePresenter(@NonNull BO businessObject) {
        this.businessObject = businessObject;
    }

    @Override
    public void takeView(@NonNull V view) {
        weakView = new WeakReference<>(view);
    }

    protected boolean isTaken() {
        return weakView != null && weakView.get() != null;
    }

    protected V view() {
        return weakView.get();
    }

    protected BO bo() {
        return businessObject;
    }

    @Override
    public void dropView() {
        if (isTaken()) {
            weakView.clear();
            weakView = null;
        }
    }
}
