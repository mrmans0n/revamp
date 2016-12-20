package revamp.android.delegates;

import revamp.store.Retainable;
import revamp.base.Presenter;
import revamp.base.ViewComponent;

public interface PresenterActivityDelegateCallback<V extends ViewComponent, P extends Presenter<V>> extends Retainable, PresenterDelegateCallback<V, P> {
}
