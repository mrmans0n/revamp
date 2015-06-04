package revamp.mocks;

import android.support.annotation.NonNull;

import revamp.base.BasePresenter;

/**
 * Mock class with increased visibility for testing
 */
public class MockPresenter extends BasePresenter<MockBO, MockViewComponent> {
    public MockPresenter(@NonNull MockBO businessObject) {
        super(businessObject);
    }

    @Override
    public boolean isTaken() {
        return super.isTaken();
    }

    @Override
    public MockViewComponent view() {
        return super.view();
    }

    @Override
    public MockBO bo() {
        return super.bo();
    }
}
