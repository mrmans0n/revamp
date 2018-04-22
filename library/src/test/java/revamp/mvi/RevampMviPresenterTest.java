package revamp.mvi;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.robolectric.annotation.Config;
import revamp.CustomTestRunner;
import revamp.mvp.RevampMvpPresenter;

import static junit.framework.Assert.*;

/**
 * Tests {@link RevampMvpPresenter}
 */
@RunWith(CustomTestRunner.class)
@Config(manifest = Config.NONE)
public class RevampMviPresenterTest {

  @Rule public MockitoRule mRule = MockitoJUnit.rule();
  @Mock RevampViewStateRenderer<RevampViewState> mViewComponent;

  private RevampMviPresenter<RevampViewStateRenderer<RevampViewState>> mPresenter;

  @Before
  public void setup() {
    mPresenter = new RevampMviPresenter<>();
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
    mPresenter = new RevampMviPresenter<RevampViewStateRenderer<RevampViewState>>() {
      @Override protected boolean isEmptyViewComponentEnabled() {
        return true;
      }
    };
    assertFalse(mPresenter.isTaken());
    assertTrue(mPresenter.isUsingEmptyViewComponent());
    mPresenter.takeView(mViewComponent);
    assertTrue(mPresenter.isTaken());
    assertFalse(mPresenter.isUsingEmptyViewComponent());
    assertEquals(mPresenter.view(), mViewComponent);
    mPresenter.dropView();
    assertFalse(mPresenter.isTaken());
    assertTrue(mPresenter.isUsingEmptyViewComponent());
  }
}
