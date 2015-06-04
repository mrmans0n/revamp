package revamp.base;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import revamp.mocks.MockBO;
import revamp.mocks.MockPresenter;
import revamp.mocks.MockViewComponent;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

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
        assertEquals(mockPresenter.bo(), bo);
    }

    @Test
    public void test_view_attachment() {
        assertFalse(mockPresenter.isTaken());
        mockPresenter.takeView(viewComponent);
        assertTrue(mockPresenter.isTaken());
        assertEquals(mockPresenter.view(), viewComponent);
        mockPresenter.dropView();
        assertFalse(mockPresenter.isTaken());
    }
}
