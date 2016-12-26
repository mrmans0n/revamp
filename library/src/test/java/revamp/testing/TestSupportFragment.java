package revamp.testing;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import revamp.android.PresenterSupportFragment;

public class TestSupportFragment extends PresenterSupportFragment<TestPresenter, TestViewComponent> implements TestViewComponent {

  private TestBO mockBO;
  private View view;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    view = new View(getActivity());
    return view;
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
