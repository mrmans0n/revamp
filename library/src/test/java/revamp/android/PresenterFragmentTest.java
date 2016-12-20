package revamp.android;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.annotation.Config;

import revamp.CustomTestRunner;
import revamp.mocks.TestBO;
import revamp.mocks.TestFragment;
import revamp.mocks.TestPresenter;

import static org.robolectric.util.FragmentTestUtil.startFragment;

/**
 * Created by mrm on 4/6/15.
 */
@RunWith(CustomTestRunner.class)
@Config(manifest = Config.NONE)
public class PresenterFragmentTest {

    @Mock
    TestBO mockBO;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_fragment_presenter_creation() {
        TestFragment fragment = createMockFragment();
        startFragment(fragment);

        Assert.assertNotNull(fragment.presenter());
    }

    @Test
    public void test_fragment_view_attachment() {
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
