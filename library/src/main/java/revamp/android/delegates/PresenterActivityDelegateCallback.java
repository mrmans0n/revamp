package revamp.android.delegates;

import revamp.android.store.Retainable;
import revamp.android.store.RetainableStore;
import revamp.base.Presenter;
import revamp.base.ViewComponent;

public interface PresenterActivityDelegateCallback<V extends ViewComponent, P extends Presenter<V>> extends RetainableStore, Retainable, PresenterDelegateCallback<V, P> {
}
