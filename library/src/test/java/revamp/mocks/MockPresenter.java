package revamp.mocks;

import android.support.annotation.NonNull;

import revamp.base.BasePresenter;

/**
 * Created by mrm on 4/6/15.
 */
public class MockPresenter extends BasePresenter<MockBO, MockViewComponent> {
    public MockPresenter(@NonNull MockBO businessObject) {
        super(businessObject);
    }
}
