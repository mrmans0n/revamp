package revamp.store;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import revamp.base.Presenter;

/**
 * Test {@link RetainableStoreImpl}
 */
public class RetainableStoreImplTest {

  @Rule public MockitoRule mRule = MockitoJUnit.rule();
  @Mock Presenter mPresenter;

  private RetainableStoreImpl mStore;

  @Before
  public void setup() {
    mStore = new RetainableStoreImpl();
  }

  @Test
  public void testStoreRetainRetrieve() {
    mStore.retainObject("presenter", mPresenter);
    Assert.assertEquals(mStore.restoreRetained("presenter"), mPresenter);
  }
}
