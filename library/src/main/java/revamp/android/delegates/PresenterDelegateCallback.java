package revamp.android.delegates;

import revamp.base.Presenter;
import revamp.base.ViewComponent;

/**
 * Created by mrm on 27/5/15.
 */
public interface PresenterDelegateCallback<V extends ViewComponent> {
    Presenter<V> presenter();

    V viewComponent();
}
