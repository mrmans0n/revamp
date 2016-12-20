package revamp.android.delegates;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import revamp.mocks.TestPresenter;
import revamp.mocks.TestViewComponent;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test {@link PresenterViewGroupDelegate}
 */
public class PresenterViewGroupDelegateTest {
  @Mock private PresenterDelegateCallback<TestViewComponent, TestPresenter> mDelegateCallback;
  @Mock private TestPresenter mPresenter;
  @Mock private TestViewComponent mViewComponent;
  private PresenterViewGroupDelegate<TestViewComponent, TestPresenter> mDelegate;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    when(mDelegateCallback.presenter()).thenReturn(mPresenter);
    when(mDelegateCallback.viewComponent()).thenReturn(mViewComponent);
    mDelegate = new PresenterViewGroupDelegate<>(mDelegateCallback);
  }

  @Test
  public void testAttachingViewOnAttachedToWindow() {
    mDelegate.onAttachedToWindow();
    verify(mPresenter).takeView(mViewComponent);
  }

  @Test
  public void testDetachingViewOnDetachedFromWindow() {
    mDelegate.onDetachedFromWindow();
    verify(mPresenter).dropView();
  }
}
