package revamp.android.delegates;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;

import java.util.Map;

import revamp.android.CanRetainObjects;
import revamp.base.Presenter;
import revamp.base.ViewComponent;

/**
 * Created by mrm on 27/5/15.
 */
public class PresenterActivityDelegate<V extends ViewComponent, P extends Presenter<V>> implements CanRetainObjects {

    private static final String PRESENTER_ID = "presenter";
    private final Map<String, Object> retainedObjects;
    private final PresenterDelegateCallback<V, P> callback;
    private Presenter<V> presenter;

    public PresenterActivityDelegate(@NonNull PresenterDelegateCallback<V, P> callback, Object lastNonConfigurationInstance) {
        this.callback = callback;
        if (lastNonConfigurationInstance != null) {
            retainedObjects = (Map<String, Object>) lastNonConfigurationInstance;
            callback.setPresenter((P) retainedObjects.get(PRESENTER_ID));
        } else {
            retainedObjects = new ArrayMap<>();
        }
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

    public Object onRetainCustomNonConfigurationInstance() {
        retainedObjects.put("presenter", callback.presenter());
        return retainedObjects;
    }

    @Override
    public void retainObject(String objectId, Object object) {
        retainedObjects.put(objectId, object);
    }

    @Override
    public Object restoreRetained(String objectId) {
        return retainedObjects.get(objectId);
    }
}


