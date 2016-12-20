package revamp.android.delegates;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import revamp.mocks.TestPresenter;
import revamp.mocks.TestViewComponent;

/**
 * Tests {@link PresenterActivityDelegate}
 */
public class PresenterActivityDelegateTest {

  @Mock PresenterActivityDelegateCallback<TestViewComponent, TestPresenter> mDelegateCallback;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);

  }
}
