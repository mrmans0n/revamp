package revamp.mvp;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.robolectric.annotation.Config;
import revamp.CustomTestRunner;
import revamp.testing.TestEmptyViewPresenter;
import revamp.testing.TestModel;
import revamp.testing.TestPresenter;
import revamp.testing.TestViewComponent;

import static junit.framework.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests {@link RevampMvpPresenter}
 */
@RunWith(CustomTestRunner.class)
@Config(manifest = Config.NONE)
public class RevampMvpPresenterTest {

  private static final String STRING_VALUE = ":)";
  @Rule public MockitoRule mRule = MockitoJUnit.rule();
  @Mock TestViewComponent mViewComponent;
  @Mock TestModel mModel;

  private TestPresenter mPresenter;
  private TestEmptyViewPresenter mEmptyViewPresenter;

  @Before
  public void setup() {
    mPresenter = new TestPresenter(mModel);
    mEmptyViewPresenter = new TestEmptyViewPresenter(mModel);
  }

  @Test
  public void testModel() {
    assertEquals(mPresenter.model(), mModel);
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
    assertFalse(mEmptyViewPresenter.isTaken());
    assertTrue(mEmptyViewPresenter.isUsingEmptyViewComponent());
    mEmptyViewPresenter.takeView(mViewComponent);
    assertTrue(mEmptyViewPresenter.isTaken());
    assertFalse(mEmptyViewPresenter.isUsingEmptyViewComponent());
    assertEquals(mEmptyViewPresenter.view(), mViewComponent);
    mEmptyViewPresenter.dropView();
    assertFalse(mEmptyViewPresenter.isTaken());
    assertTrue(mEmptyViewPresenter.isUsingEmptyViewComponent());
  }

  @Test
  public void testBasicPresenterAction() {
    when(mModel.getPersistedString()).thenReturn(STRING_VALUE);
    mPresenter.takeView(mViewComponent);
    mPresenter.loadData();
    verify(mViewComponent).displayElements(STRING_VALUE);
  }

  @Test
  public void testPresenterReleasePropagatesToBusinessObject() {
    mPresenter.release();
    verify(mModel).release();
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
