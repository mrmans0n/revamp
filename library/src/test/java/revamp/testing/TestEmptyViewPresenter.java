package revamp.testing;

import android.support.annotation.NonNull;

public class TestEmptyViewPresenter extends TestPresenter {
  public TestEmptyViewPresenter(@NonNull TestBO businessObject) {
    super(businessObject);
  }

  @Override
  protected boolean isEmptyViewComponentEnabled() {
    return true;
  }
}
