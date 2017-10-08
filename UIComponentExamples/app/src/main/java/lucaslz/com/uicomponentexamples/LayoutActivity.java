package lucaslz.com.uicomponentexamples;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import lucaslz.com.component.activity.SingleFragmentActivity;

/**
 * Created by lucas on 2017/10/8.
 */
public class LayoutActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context packageContext) {
        return new Intent(packageContext, LayoutActivity.class);
    }

    @Override
    protected Fragment createFragment() {
        return LayoutFragment.newInstance();
    }
}
