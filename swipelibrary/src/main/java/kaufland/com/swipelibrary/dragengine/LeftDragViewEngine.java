package kaufland.com.swipelibrary.dragengine;

import android.graphics.Rect;
import android.view.View;

import kaufland.com.swipelibrary.DragView;
import kaufland.com.swipelibrary.SurfaceView;
import kaufland.com.swipelibrary.SwipeDirectionDetector;
import kaufland.com.swipelibrary.SwipeLayout;
import kaufland.com.swipelibrary.SwipeResult;
import kaufland.com.swipelibrary.SwipeState;
import kaufland.com.swipelibrary.SwipeViewLayouter;

import static kaufland.com.swipelibrary.SwipeDirectionDetector.SWIPE_DIRECTION_LEFT;
import static kaufland.com.swipelibrary.SwipeState.DragViewState.CLOSED;

/**
 * Created by sbra0902 on 29.03.17.
 */

public class LeftDragViewEngine implements DraggingEngine {

    private SurfaceView mSurfaceView;
    private DragView mDragView;

    private int mInitialXPos;

    private int mDragDistance;

    private int mIntermmediateDistance;

    private SwipeViewLayouter mLayouter;

    public LeftDragViewEngine(SwipeViewLayouter layouter) {
        mLayouter = layouter;
    }

    @Override
    public void moveView(float offset, SurfaceView view, View changedView) {
        if (!mDragView.equals(changedView)) {
            mDragView.setX(view.getX() - mDragView.getWidth());
        }
    }

    @Override
    public void initializePosition(SwipeViewLayouter.DragDirection orientation) {

        mSurfaceView = mLayouter.getSurfaceView();
        mDragView = mLayouter.getLeftDragView();

        mInitialXPos = (int) (mSurfaceView.getX() - mDragView.getWidth());
        mDragDistance = mDragView.getWidth();
        mIntermmediateDistance = mDragView.getSettlePointResourceId() != -1 ? mDragView.findViewById(mDragView.getSettlePointResourceId()).getRight() : mDragView.getWidth();

        moveToInitial();
    }

    private void moveToInitial() {
        mDragView.setX(mInitialXPos);
    }

    @Override
    public int clampViewPositionHorizontal(View child, int left) {

        if (mDragView != null && child.equals(mDragView)) {


            if (left > 0 && !mDragView.isBouncePossible()) {
                return 0;
            }

            return left;
        }

        return 0;
    }

    @Override
    public void restoreState(SwipeState.DragViewState state, SurfaceView view) {
        switch (state) {
            case LEFT_OPEN:

                mDragView.setX(0);
                break;
            case RIGHT_OPEN:
                mDragView.setX((int) (mLayouter.getRightDragView().getX() - mSurfaceView.getWidth() - mDragView.getWidth()));
                break;
            case TOP_OPEN:
                //TODO Implementation
                break;
            case BOTTOM_OPEN:
                //TODO Implementation
                break;
            default:
                mDragView.setX(-mDragDistance);
                break;
        }
    }


    @Override
    public int getDragDistance() {
        return mDragDistance;
    }

    @Override
    public int getIntermmediateDistance() {
        return mIntermmediateDistance;
    }

    @Override
    public int getOpenOffset() {
        return mDragDistance;
    }

    @Override
    public SwipeResult determineSwipeHorizontalState(float velocity, SwipeDirectionDetector swipeDirectionDetector, SwipeState swipeState, final SwipeLayout.SwipeListener swipeListener, View releasedChild) {
        if (mDragView.equals(releasedChild) && swipeDirectionDetector.getSwipeDirection() == SWIPE_DIRECTION_LEFT && Math.abs(swipeDirectionDetector.getDifX()) > (getDragDistance() / 2)) {
            swipeState.setState(SwipeState.DragViewState.CLOSED);
            return new SwipeResult(-mDragView.getWidth(), new Runnable() {
                @Override
                public void run() {
                    swipeListener.onSwipeClosed(CLOSED);
                }
            });
        }

        return null;
    }

    @Override
    public DragView getDragView() {
        return mDragView;
    }
}
