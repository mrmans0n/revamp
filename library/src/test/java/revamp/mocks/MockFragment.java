package revamp.mocks;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import revamp.android.PresenterFragment;

/**
 * Created by mrm on 4/6/15.
 */
public class MockFragment extends PresenterFragment<MockPresenter, MockViewComponent> implements MockViewComponent {

    private MockBO mockBO;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = new View(getActivity());
        return view;
    }


    public void setBusinessObject(MockBO bo) {
        this.mockBO = bo;
    }

    @Override
    public MockPresenter buildPresenter() {
        return new MockPresenter(mockBO);
    }
}
