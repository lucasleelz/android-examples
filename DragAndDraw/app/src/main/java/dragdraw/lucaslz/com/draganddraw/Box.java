package dragdraw.lucaslz.com.draganddraw;

import android.graphics.PointF;

/**
 * Created by lucas on 2017/9/21.
 */
public class Box {
    private PointF mOrigin;
    private PointF mCurrent;

    public Box(PointF origin) {
        this.mOrigin = origin;
        this.mCurrent = origin;
    }

    public PointF getCurrent() {
        return mCurrent;
    }

    public void setCurrent(PointF current) {
        mCurrent = current;
    }

    public PointF getOrigin() {
        return mOrigin;
    }
}
