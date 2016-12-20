package revamp.mocks;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import revamp.android.PresenterFragment;

public class TestFragment extends PresenterFragment<TestPresenter, TestViewComponent> implements TestViewComponent {

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
}
