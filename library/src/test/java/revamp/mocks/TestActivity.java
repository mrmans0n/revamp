package revamp.mocks;

import android.os.Bundle;
import android.view.View;

import revamp.android.PresenterActivity;

public class TestActivity extends PresenterActivity<TestPresenter, TestViewComponent> implements TestViewComponent {

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
}
