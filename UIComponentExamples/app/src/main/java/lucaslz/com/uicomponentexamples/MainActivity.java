package lucaslz.com.uicomponentexamples;

import android.support.v4.app.Fragment;

import lucaslz.com.component.activity.SingleFragmentActivity;

public class MainActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return MainFragment.newInstance();
    }
}
