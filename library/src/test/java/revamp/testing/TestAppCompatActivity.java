package revamp.testing;

import android.os.Bundle;
import android.view.View;

import revamp.android.PresenterAppCompatActivity;

public class TestAppCompatActivity extends PresenterAppCompatActivity<TestPresenter, TestViewComponent> implements TestViewComponent {

  private TestBO mockBO;
  private View view;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    view = new View(this);
    setContentView(view);
  }

  public void setBusinessObject(TestBO bo) {
    this.mockBO = bo;
  }

  @Override
  public TestPresenter buildPresenter() {
    return new TestPresenter(mockBO);
  }

  @Override
  public void displayElements(String persistedString) {
    // some viewcomponent method
  }
}
