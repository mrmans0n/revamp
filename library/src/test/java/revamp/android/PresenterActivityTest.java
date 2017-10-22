package revamp.android;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import revamp.CustomTestRunner;
import revamp.testing.TestActivity;
import revamp.testing.TestModel;
import revamp.testing.TestPresenter;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.verify;

/**
 * Tests {@link PresenterActivity}
 */
@RunWith(CustomTestRunner.class)
@Config(manifest = Config.NONE)
public class PresenterActivityTest {

  @Mock TestModel mModel;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testActivityPresenterCreation() {
    ActivityController<TestActivity> controller = createMockActivity();
    TestActivity activity = controller.get();
    controller.create().start();
    Assert.assertNotNull(activity.presenter());
  }

  @Test
  public void testActivityViewAttachment() {
    ActivityController<TestActivity> controller = createMockActivity();
    TestActivity activity = controller.get();

    final TestPresenter presenter = activity.presenter();
    assertFalse(presenter.isTaken());

    controller.create().start().resume();
    assertTrue(presenter.isTaken());
    Assert.assertEquals(presenter.view(), activity);

    controller.pause().stop().destroy();
    assertFalse(presenter.isTaken());
  }

  private ActivityController<TestActivity> createMockActivity() {
    ActivityController<TestActivity> controller = Robolectric.buildActivity(TestActivity.class);

    // We want to inject the BO before calling onCreate or anything else
    TestActivity activity = controller.get();
    activity.setBusinessObject(mModel);
    return controller;
  }

}
