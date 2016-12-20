package revamp.android.delegates;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.util.ActivityController;

import revamp.CustomTestRunner;
import revamp.mocks.TestActivity;
import revamp.mocks.TestBO;
import revamp.mocks.TestPresenter;
import revamp.mocks.TestViewComponent;
import revamp.store.RetainableStore;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests {@link PresenterFragmentDelegate}
 */
@RunWith(CustomTestRunner.class)
public class PresenterFragmentDelegateTest {

  @Mock private PresenterFragmentDelegateCallback<TestViewComponent, TestPresenter> mDelegateCallback;
  @Mock private RetainableStore mRetainableStore;
  @Mock private TestPresenter mPresenter;
  @Mock private TestViewComponent mViewComponent;
  @Mock private TestBO mBO;
  private ActivityController<TestActivity> mActivityController;
  private TestActivity mActivity;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    mActivityController = Robolectric.buildActivity(TestActivity.class);
    mActivity = mActivityController.get();
    mActivity.setBusinessObject(mBO);

    when(mDelegateCallback.shouldRetain()).thenReturn(true);
    when(mRetainableStore.restoreRetained(anyString())).thenReturn(mPresenter);
    when(mDelegateCallback.presenter()).thenReturn(mPresenter);
    when(mDelegateCallback.viewComponent()).thenReturn(mViewComponent);
  }

  @Test
  public void testAttachingViewOnCreate() {
    PresenterFragmentDelegate<TestViewComponent, TestPresenter> delegate = new PresenterFragmentDelegate<>(mDelegateCallback, mActivity);
    delegate.onCreate(null);
    verify(mPresenter).takeView(mViewComponent);
  }

  @Test
  public void testDetachingViewOnDestroy() {
    PresenterFragmentDelegate<TestViewComponent, TestPresenter> delegate = new PresenterFragmentDelegate<>(mDelegateCallback, mActivity);
    delegate.onDestroy();
    verify(mPresenter).dropView();
  }
}
