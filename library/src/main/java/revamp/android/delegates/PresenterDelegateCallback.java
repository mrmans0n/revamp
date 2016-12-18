package revamp.android.delegates;

import revamp.base.Presenter;
import revamp.base.ViewComponent;

public interface PresenterDelegateCallback<V extends ViewComponent, P extends Presenter<V>> {
    P presenter();

    void setPresenter(P presenter);

    V viewComponent();
}
