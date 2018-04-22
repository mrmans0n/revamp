package revamp.android.delegates;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import revamp.store.RetainableStore;
import revamp.testing.TestPresenter;
import revamp.testing.TestViewComponent;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static revamp.android.delegates.PresenterActivityDelegate.PRESENTER_ID;

/**
 * Tests {@link PresenterActivityDelegate}
 */
public class PresenterActivityDelegateTest {

  @Mock private PresenterActivityDelegateCallback<TestViewComponent, TestPresenter> mDelegateCallback;
  @Mock private RetainableStore mRetainableStore;
  @Mock private TestPresenter mPresenter;
  @Mock private TestViewComponent mViewComponent;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    when(mDelegateCallback.shouldRetain()).thenReturn(true);
    when(mRetainableStore.restoreRetained(anyString())).thenReturn(mPresenter);
    when(mDelegateCallback.presenter()).thenReturn(mPresenter);
    when(mDelegateCallback.viewComponent()).thenReturn(mViewComponent);
  }

  @Test
  public void testUseCachedPresenterOnRotation() {
    PresenterActivityDelegate<TestViewComponent, TestPresenter> delegate = new PresenterActivityDelegate<>(mDelegateCallback, mRetainableStore);
    verify(mDelegateCallback).setRetainedPresenter(mPresenter);
  }

  @Test
  public void testCreatePresenterIfNotRetaining() {
    when(mDelegateCallback.shouldRetain()).thenReturn(false);
    final PresenterActivityDelegate<TestViewComponent, TestPresenter> delegate = new PresenterActivityDelegate<>(mDelegateCallback, mRetainableStore);
    verify(mDelegateCallback, never()).setRetainedPresenter(any(TestPresenter.class));
  }

  @Test
  public void testCreatePresenterIfLastNonConfigurationInstanceNull() {
    when(mDelegateCallback.shouldRetain()).thenReturn(false);
    final PresenterActivityDelegate<TestViewComponent, TestPresenter> delegate = new PresenterActivityDelegate<>(mDelegateCallback, null);
    verify(mDelegateCallback, never()).setRetainedPresenter(any(TestPresenter.class));
  }

  @Test
  public void testAttachingViewOnCreate() {
    final PresenterActivityDelegate<TestViewComponent, TestPresenter> delegate = new PresenterActivityDelegate<>(mDelegateCallback, null);
    delegate.onCreate(null);
    verify(mPresenter).takeView(mViewComponent);
  }

  @Test
  public void testDetachingViewOnDestroyAlsoReleasesIfDestroying() {
    final PresenterActivityDelegate<TestViewComponent, TestPresenter> delegate = new PresenterActivityDelegate<>(mDelegateCallback, null);
    delegate.onDestroy(true);
    verify(mPresenter).dropView();
    verify(mPresenter).release();
  }

  @Test
  public void testDetachingViewOnDestroyDoesntReleaseIfNotDestroying() {
    final PresenterActivityDelegate<TestViewComponent, TestPresenter> delegate = new PresenterActivityDelegate<>(mDelegateCallback, null);
    delegate.onDestroy(false);
    verify(mPresenter).dropView();
    verify(mPresenter, never()).release();
  }


  @Test
  public void testRetainObjectOnRotation() {
    final PresenterActivityDelegate<TestViewComponent, TestPresenter> delegate = new PresenterActivityDelegate<>(mDelegateCallback, mRetainableStore);
    delegate.onRetainCustomNonConfigurationInstance();
    verify(mRetainableStore).retainObject(PRESENTER_ID, mPresenter);
  }
}
