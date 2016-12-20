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
import revamp.mocks.TestActivity;
import revamp.mocks.TestBO;
import revamp.mocks.TestPresenter;

/**
 * Tests {@link PresenterActivity}
 */
@RunWith(CustomTestRunner.class)
@Config(manifest = Config.NONE)
public class PresenterActivityTest {

    @Mock TestBO mockBO;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_activity_presenter_creation() {
        ActivityController<TestActivity> controller = createMockActivity();
        TestActivity activity = controller.get();
        controller.create().start();
        Assert.assertNotNull(activity.presenter());
    }

    @Test
    public void test_activity_view_attachment() {
        ActivityController<TestActivity> controller = createMockActivity();
        TestActivity activity = controller.get();

        TestPresenter presenter = activity.presenter();
        Assert.assertFalse(presenter.isTaken());

        controller.create().start().resume();
        Assert.assertTrue(presenter.isTaken());
        Assert.assertEquals(presenter.view(), activity);

        controller.pause().stop().destroy();
        Assert.assertFalse(presenter.isTaken());
    }

    private ActivityController<TestActivity> createMockActivity() {
        ActivityController<TestActivity> controller = Robolectric.buildActivity(TestActivity.class);

        // We want to inject the BO before calling onCreate or anything else
        TestActivity activity = controller.get();
        activity.setBusinessObject(mockBO);
        return controller;
    }

}
