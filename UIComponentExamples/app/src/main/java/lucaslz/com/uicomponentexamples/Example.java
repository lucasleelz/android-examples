package lucaslz.com.uicomponentexamples;

import android.content.Intent;

/**
 * Created by lucas on 2017/10/8.
 */
public class Example {
    private String mName;
    private Intent mIntent;

    public Example(String name, Intent intent) {
        mName = name;
        mIntent = intent;
    }

    public String getName() {
        return mName;
    }

    public Intent getIntent() {
        return mIntent;
    }
}
