package revamp.testing;

import android.support.annotation.NonNull;

import revamp.mvp.RevampMvpPresenter;

public class TestPresenter extends RevampMvpPresenter<TestModel, TestViewComponent> {
  public TestPresenter(@NonNull TestModel businessObject) {
    super(businessObject);
  }

  @Override
  public boolean isTaken() {
    return super.isTaken();
  }

  @NonNull
  @Override
  public TestViewComponent view() {
    return super.view();
  }

  @Override
  public TestModel model() {
    return super.model();
  }

  public void loadData() {
    String persistedString = model().getPersistedString();
    view().displayElements(persistedString);
  }

  @Override
  protected boolean isEmptyViewComponentEnabled() {
    return false;
  }
}
