package revamp.base;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.robolectric.annotation.Config;

import revamp.CustomTestRunner;
import revamp.testing.TestBO;
import revamp.testing.TestEmptyViewPresenter;
import revamp.testing.TestPresenter;
import revamp.testing.TestViewComponent;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests {@link BasePresenter}
 */
@RunWith(CustomTestRunner.class)
@Config(manifest = Config.NONE)
public class BasePresenterTest {

  private static final String STRING_VALUE = ":)";
  @Rule public MockitoRule mRule = MockitoJUnit.rule();
  @Mock TestViewComponent mViewComponent;
  @Mock TestBO mBusinessObject;

  private TestPresenter mPresenter;
  private TestEmptyViewPresenter mEmptyViewPresenter;

  @Before
  public void setup() {
    mPresenter = new TestPresenter(mBusinessObject);
    mEmptyViewPresenter = new TestEmptyViewPresenter(mBusinessObject);
  }

  @Test
  public void testBO() {
    assertEquals(mPresenter.bo(), mBusinessObject);
  }

  @Test
  public void testViewAttachCycle() {
    assertFalse(mPresenter.isTaken());
    mPresenter.takeView(mViewComponent);
    assertTrue(mPresenter.isTaken());
    assertEquals(mPresenter.view(), mViewComponent);
    mPresenter.dropView();
    assertFalse(mPresenter.isTaken());
  }

  @Test
  public void testEmptyViewAttachCycle() {
    assertTrue(mEmptyViewPresenter.isTaken());
    assertTrue(mEmptyViewPresenter.isEmptyViewComponent());
    mEmptyViewPresenter.takeView(mViewComponent);
    assertTrue(mEmptyViewPresenter.isTaken());
    assertFalse(mEmptyViewPresenter.isEmptyViewComponent());
    assertEquals(mEmptyViewPresenter.view(), mViewComponent);
    mEmptyViewPresenter.dropView();
    assertTrue(mEmptyViewPresenter.isEmptyViewComponent());
  }

  @Test
  public void testBasicPresenterAction() {
    when(mBusinessObject.getPersistedString()).thenReturn(STRING_VALUE);
    mPresenter.takeView(mViewComponent);
    mPresenter.loadData();
    verify(mViewComponent).displayElements(STRING_VALUE);
  }

  @Test(expected = NullPointerException.class)
  public void testViewMethodFailsWhenNotAttached() {
    mPresenter.loadData();
  }

  @Test
  public void testEmptyViewSwallowsActionsWhenNotAttached() {
    mEmptyViewPresenter.loadData();
  }
}
