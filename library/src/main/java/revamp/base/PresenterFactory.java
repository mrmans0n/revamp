// Copyright (c) 2016-present Revamp

package revamp.base;

public interface PresenterFactory<P extends Presenter<? extends ViewComponent>> {
    P buildPresenter();
}
