package revamp.mocks;

import android.os.Bundle;
import android.view.View;

import revamp.android.PresenterActivity;

/**
 * Created by mrm on 4/6/15.
 */
public class MockActivity extends PresenterActivity<MockPresenter, MockViewComponent> implements MockViewComponent {

    private MockBO mockBO;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new View(this);
        setContentView(view);
    }

    public void setBusinessObject(MockBO bo) {
        this.mockBO = bo;
    }

    @Override
    public MockPresenter buildPresenter() {
        return new MockPresenter(mockBO);
    }
}
