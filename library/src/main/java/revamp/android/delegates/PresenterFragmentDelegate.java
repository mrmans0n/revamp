package revamp.android.delegates;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import revamp.base.Presenter;
import revamp.base.ViewComponent;

/**
 * Created by mrm on 27/5/15.
 */
public class PresenterFragmentDelegate<V extends ViewComponent, P extends Presenter<V>> {

    private PresenterDelegateCallback<V, P> callback;

    public PresenterFragmentDelegate(@NonNull PresenterDelegateCallback<V, P> callback) {
        this.callback = callback;
    }


    public void onCreate(Bundle savedInstanceState) {
        Presenter<V> presenter = callback.presenter();
        presenter.takeView(callback.viewComponent());
    }

    public void onDestroy() {
        Presenter presenter = callback.presenter();
        presenter.dropView();
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

    }

    public void onDestroyView() {

    }

    public void onActivityCreated(Bundle savedInstanceState) {

    }

    public void onAttach(Activity activity) {

    }

    public void onDetach() {

    }

    public void onStart() {

    }

    public void onStop() {

    }

    public void onResume() {

    }

    public void onPause() {

    }

    public void onSaveInstanceState(Bundle outState) {

    }


}


