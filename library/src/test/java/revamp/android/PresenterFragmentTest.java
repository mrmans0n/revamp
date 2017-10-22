package revamp.android;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.annotation.Config;

import revamp.CustomTestRunner;
import revamp.testing.TestModel;
import revamp.testing.TestFragment;
import revamp.testing.TestPresenter;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.robolectric.util.FragmentTestUtil.startFragment;

/**
 * Tests {@link PresenterFragment}
 */
@RunWith(CustomTestRunner.class)
@Config(manifest = Config.NONE)
public class PresenterFragmentTest {

  @Mock TestModel mModel;

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
    final TestFragment fragment = createMockFragment();

    final TestPresenter presenter = fragment.presenter();
    assertFalse(presenter.isTaken());

    startFragment(fragment);
    assertTrue(presenter.isTaken());
    assertEquals(presenter.view(), fragment);

    fragment.onDestroy();
    assertFalse(presenter.isTaken());
  }

  private TestFragment createMockFragment() {
    TestFragment fragment = new TestFragment();
    fragment.setBusinessObject(mModel);
    return fragment;
  }

}
