package revamp.android;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.annotation.Config;

import revamp.CustomTestRunner;
import revamp.mocks.MockBO;
import revamp.mocks.MockFragment;
import revamp.mocks.MockPresenter;

import static org.robolectric.util.FragmentTestUtil.startFragment;

/**
 * Created by mrm on 4/6/15.
 */
@RunWith(CustomTestRunner.class)
@Config(manifest = Config.NONE)
public class PresenterFragmentTest {

    @Mock
    MockBO mockBO;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_fragment_presenter_creation() {
        MockFragment fragment = createMockFragment();
        startFragment(fragment);

        Assert.assertNotNull(fragment.presenter());
    }

    @Test
    public void test_fragment_view_attachment() {
        MockFragment fragment = createMockFragment();

        MockPresenter presenter = fragment.presenter();
        Assert.assertFalse(presenter.isTaken());

        startFragment(fragment);
        Assert.assertTrue(presenter.isTaken());
        Assert.assertEquals(presenter.view(), fragment);

        fragment.onDestroy();
        Assert.assertFalse(presenter.isTaken());

    }

    private MockFragment createMockFragment() {
        MockFragment fragment = new MockFragment();
        fragment.setBusinessObject(mockBO);
        return fragment;
    }

}
