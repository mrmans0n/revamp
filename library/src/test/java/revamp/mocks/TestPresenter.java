package revamp.mocks;

import android.support.annotation.NonNull;

import revamp.base.BasePresenter;

public class TestPresenter extends BasePresenter<TestBO, TestViewComponent> {
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
}
