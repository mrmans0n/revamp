// Copyright (c) 2016-present Revamp

package revamp.store;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import revamp.base.Presenter;

/**
 * Test {@link RetainableStoreImpl}
 */
public class RetainableStoreImplTest {

  @Mock
  Presenter mPresenter;

  private RetainableStoreImpl mStore;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    mStore = new RetainableStoreImpl();
  }

  @Test
  public void testStoreRetainRetrieve() {
    mStore.retainObject("presenter", mPresenter);
    Assert.assertEquals(mStore.restoreRetained("presenter"), mPresenter);
  }
}
