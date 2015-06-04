package revamp.base;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import revamp.mocks.MockBO;
import revamp.mocks.MockPresenter;
import revamp.mocks.MockViewComponent;

/**
 * Created by mrm on 4/6/15.
 */
public class BasePresenterTest {

    @Mock
    private MockViewComponent viewComponent;

    @Mock
    private MockBO bo;

    private MockPresenter mockPresenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockPresenter = new MockPresenter(bo);
    }

    @Test
    public void test_bo() {
        Assert.assertEquals(mockPresenter.bo(), bo);
    }

    @Test
    public void test_view_attachment() {
        Assert.assertFalse(mockPresenter.isTaken());
        mockPresenter.takeView(viewComponent);
        Assert.assertTrue(mockPresenter.isTaken());
        Assert.assertEquals(mockPresenter.view(), viewComponent);
        mockPresenter.dropView();
        Assert.assertFalse(mockPresenter.isTaken());
    }
}
