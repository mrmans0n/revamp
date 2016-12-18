package revamp.android.delegates;

import revamp.android.RetainableStore;
import revamp.base.Presenter;
import revamp.base.ViewComponent;

public interface PresenterActivityDelegateCallback<V extends ViewComponent, P extends Presenter<V>> extends RetainableStore, PresenterDelegateCallback<V, P> {
}
