package revamp.android;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import revamp.CustomTestRunner;
import revamp.mocks.MockActivity;
import revamp.mocks.MockBO;
import revamp.mocks.MockPresenter;

/**
 * Created by mrm on 4/6/15.
 */
@RunWith(CustomTestRunner.class)
@Config(manifest = Config.NONE)
public class PresenterActivityTest {

    @Mock
    MockBO mockBO;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_activity_presenter_creation() {
        ActivityController<MockActivity> controller = createMockActivity();
        MockActivity activity = controller.get();
        controller.create().start();
        Assert.assertNotNull(activity.presenter());
    }

    @Test
    @Ignore("TODO fix the WeakReference being gced in the assertTrue")
    public void test_activity_view_attachment() {
        ActivityController<MockActivity> controller = createMockActivity();
        MockActivity activity = controller.get();

        MockPresenter presenter = activity.presenter();
        Assert.assertFalse(presenter.isTaken());

        controller.create().start().resume();
        Assert.assertTrue(presenter.isTaken());
        Assert.assertEquals(presenter.view(), activity);

        controller.pause().stop().destroy();
        Assert.assertFalse(presenter.isTaken());
    }

    private ActivityController<MockActivity> createMockActivity() {
        ActivityController<MockActivity> controller = Robolectric.buildActivity(MockActivity.class);

        // We want to inject the BO before calling onCreate or anything else
        MockActivity activity = controller.get();
        activity.setBusinessObject(mockBO);
        return controller;
    }

}
