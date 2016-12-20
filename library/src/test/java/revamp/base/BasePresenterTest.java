package revamp.base;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import revamp.mocks.TestBO;
import revamp.mocks.TestPresenter;
import revamp.mocks.TestViewComponent;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by mrm on 4/6/15.
 */
public class BasePresenterTest {

    @Mock
    private TestViewComponent viewComponent;

    @Mock
    private TestBO bo;

    private TestPresenter mPresenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mPresenter = new TestPresenter(bo);
    }

    @Test
    public void test_bo() {
        assertEquals(mPresenter.bo(), bo);
    }

    @Test
    public void test_view_attachment() {
        assertFalse(mPresenter.isTaken());
        mPresenter.takeView(viewComponent);
        assertTrue(mPresenter.isTaken());
        assertEquals(mPresenter.view(), viewComponent);
        mPresenter.dropView();
        assertFalse(mPresenter.isTaken());
    }
}
