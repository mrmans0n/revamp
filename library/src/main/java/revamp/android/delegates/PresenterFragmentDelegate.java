package revamp.android.delegates;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.SparseArrayCompat;
import android.view.View;

import java.util.concurrent.atomic.AtomicInteger;

import revamp.base.Presenter;
import revamp.base.ViewComponent;

public class PresenterFragmentDelegate<V extends ViewComponent, P extends Presenter<V>> {
  private static final String FRAGMENT_TAG = "revamp_fragment";
  private PresenterFragmentDelegateCallback<V, P> mCallback;
  private PresenterStore mPresenterStore;

  public PresenterFragmentDelegate(@NonNull PresenterFragmentDelegateCallback<V, P> callback, int storeId, @NonNull Activity activity) {
    mCallback = callback;
    retrievePresenterIfExisting(storeId, activity);
  }

  private void retrievePresenterIfExisting(int storeId, Activity activity) {
    HasPresenterStore fragmentStore;
    if (activity instanceof FragmentActivity) {
      FragmentActivity fragmentActivity = (FragmentActivity) activity;
      fragmentStore = (HasPresenterStore) fragmentActivity.getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG);
    } else {
      fragmentStore = (HasPresenterStore) activity.getFragmentManager().findFragmentByTag(FRAGMENT_TAG);
    }

    if (fragmentStore == null) {
      fragmentStore = createHeadlessFragment(activity);
    }
    PresenterStore store = fragmentStore.store();

    Presenter presenter = store.get(storeId);
    if (presenter != null) {
      mCallback.setPresenter((P) presenter);
    }
  }

  private HasPresenterStore createHeadlessFragment(Activity activity) {
    return null;
  }


  public void onCreate(Bundle savedInstanceState) {
    Presenter<V> presenter = mCallback.presenter();
    presenter.takeView(mCallback.viewComponent());
  }

  public void onDestroy() {
    Presenter presenter = mCallback.presenter();
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

  /**
   * Common structure for holding presenters and generating their ids
   */
  private static final class PresenterStore {
    private static AtomicInteger sCount = new AtomicInteger(0);
    private SparseArrayCompat<Presenter> mRetainedObjects = new SparseArrayCompat<>();


    public void put(int key, Presenter presenter) {
      mRetainedObjects.put(key, presenter);
    }

    public Presenter get(int key) {
      return mRetainedObjects.get(key);
    }

    public int generateId() {
      return sCount.incrementAndGet();
    }

    public void release() {
      mRetainedObjects.clear();
      mRetainedObjects = null;
    }
  }

  private interface HasPresenterStore {
    PresenterStore store();
  }

  /**
   * Headless fragment for handling our cache
   */
  public static final class RetainedFragment extends android.app.Fragment implements HasPresenterStore {

    private final PresenterStore mStore;

    public RetainedFragment() {
      mStore = new PresenterStore();
    }

    @Override
    public void onDestroy() {
      mStore.release();
      super.onDestroy();
    }

    @Override
    public PresenterStore store() {
      return mStore;
    }
  }

  /**
   * Headless support fragment handling our cache
   */
  public static final class RetainedSupportFragment extends Fragment implements HasPresenterStore {

    private final PresenterStore mStore;

    public RetainedSupportFragment() {
      mStore = new PresenterStore();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setRetainInstance(true);
    }

    @Override
    public void onDestroy() {
      mStore.release();
      super.onDestroy();
    }

    @Override
    public PresenterStore store() {
      return mStore;
    }
  }
}


