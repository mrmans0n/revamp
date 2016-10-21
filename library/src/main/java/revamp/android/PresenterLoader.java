// Copyright (c) 2016-present Revamp

package revamp.android;

import android.content.Context;
import android.content.Loader;

import revamp.base.Presenter;
import revamp.base.PresenterFactory;
import revamp.base.ViewComponent;

public class PresenterLoader<P extends Presenter<? extends ViewComponent>> extends Loader<P> {

    private final PresenterFactory<P> mPresenterFactory;
    private P mPresenter;

    /**
     * Stores away the application context associated with context.
     * Since Loaders can be used across multiple activities it's dangerous to
     * store the context directly; always use {@link #getContext()} to retrieve
     * the Loader's Context, don't use the constructor argument directly.
     * The Context returned by {@link #getContext} is safe to use across
     * Activity instances.
     *
     * @param context used to retrieve the application context.
     */
    public PresenterLoader(Context context, PresenterFactory<P> presenterFactory) {
        super(context);
        mPresenterFactory = presenterFactory;
    }

    @Override
    protected void onStartLoading() {
        if (mPresenter != null) {
            deliverResult(mPresenter);
            return;
        }
        forceLoad();
    }

    @Override
    protected void onForceLoad() {
        mPresenter = mPresenterFactory.buildPresenter();
        deliverResult(mPresenter);
    }

    @Override
    protected void onReset() {
        mPresenter.dropView();
        mPresenter = null;
    }
}
