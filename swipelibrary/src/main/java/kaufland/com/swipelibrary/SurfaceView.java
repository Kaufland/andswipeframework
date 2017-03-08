package kaufland.com.swipelibrary;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import org.androidannotations.annotations.EViewGroup;

/**
 * Created by sbra0902 on 06.03.17.
 */
@EViewGroup
public class SurfaceView extends FrameLayout {

    private Rect mSurfaceRectHit;

    private int mSurfaceViewOffsetX;

    private int mSurfaceViewOffsetY;

    public SurfaceView(Context context) {
        super(context);
    }

    public SurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SurfaceView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    private void initSwipe() {

        mSurfaceRectHit = new Rect();
        getHitRect(mSurfaceRectHit);

    }

    public void moveView(float offset) {
        setTranslationX(offset);
    }

    public void moveToInitial() {
        setTranslationX(0);
    }

    public int getSurfaceViewOffsetX() {
        return mSurfaceViewOffsetX;
    }

    public void setSurfaceViewOffsetX(int surfaceViewOffsetX) {
        mSurfaceViewOffsetX = surfaceViewOffsetX;
    }

    public int getSurfaceViewOffsetY() {
        return mSurfaceViewOffsetY;
    }

    public void setSurfaceViewOffsetY(int surfaceViewOffsetY) {
        mSurfaceViewOffsetY = surfaceViewOffsetY;
    }
}