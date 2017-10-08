package lucaslz.com.uicomponentexamples;

import android.content.Intent;

/**
 * Created by lucas on 2017/10/8.
 */
public class Example {
    private String mName;
    private String mType;

    public Example(String name, String type) {
        mName = name;
        mType = type;
    }

    public String getName() {
        return mName;
    }

    public String getType() {
        return mType;
    }
}
