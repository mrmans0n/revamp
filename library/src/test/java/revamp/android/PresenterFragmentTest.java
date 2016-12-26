package revamp.android;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.annotation.Config;

import revamp.CustomTestRunner;
import revamp.testing.TestBO;
import revamp.testing.TestFragment;
import revamp.testing.TestPresenter;

import static org.robolectric.util.FragmentTestUtil.startFragment;

/**
 * Tests {@link PresenterFragment}
 */
@RunWith(CustomTestRunner.class)
@Config(manifest = Config.NONE)
public class PresenterFragmentTest {

  @Mock TestBO mockBO;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testFragmentPresenterCreation() {
    TestFragment fragment = createMockFragment();
    startFragment(fragment);

    Assert.assertNotNull(fragment.presenter());
  }

  @Test
  public void testFragmentViewAttachment() {
    TestFragment fragment = createMockFragment();

    TestPresenter presenter = fragment.presenter();
    Assert.assertFalse(presenter.isTaken());

    startFragment(fragment);
    Assert.assertTrue(presenter.isTaken());
    Assert.assertEquals(presenter.view(), fragment);

    fragment.onDestroy();
    Assert.assertFalse(presenter.isTaken());

  }

  private TestFragment createMockFragment() {
    TestFragment fragment = new TestFragment();
    fragment.setBusinessObject(mockBO);
    return fragment;
  }

}
