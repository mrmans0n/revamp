package revamp.testing;

import android.support.annotation.NonNull;

import revamp.base.RevampPresenter;

public class TestPresenter extends RevampPresenter<TestBO, TestViewComponent> {
  public TestPresenter(@NonNull TestBO businessObject) {
    super(businessObject);
  }

  @Override
  public boolean isTaken() {
    return super.isTaken();
  }

  @Override
  public TestViewComponent view() {
    return super.view();
  }

  @Override
  public TestBO bo() {
    return super.bo();
  }

  public void loadData() {
    String persistedString = bo().getPersistedString();
    view().displayElements(persistedString);
  }

  @Override
  protected boolean isEmptyViewComponentEnabled() {
    return false;
  }
}
