package com.zero.polyline;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * @author linzewu
 * @date 2017/7/20
 */

public class PolylineView extends View {
    
    private IDrawMethod mIDrawMethod;
    private List<PointF> mPointFList;
    
    public PolylineView(Context context) {
        super(context);
        init();
    }

    public PolylineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    
    
    private void init() {
        mPointFList = new ArrayList<>();
        mPointFList.add(new PointF(0, 500));
        mPointFList.add(new PointF(100, 330));
        mPointFList.add(new PointF(200, 280));
        mPointFList.add(new PointF(300, 460));
        mPointFList.add(new PointF(400, 560));
        mPointFList.add(new PointF(500, 200));
        mPointFList.add(new PointF(600, 300));
        mPointFList.add(new PointF(700, 340));
        mIDrawMethod = new BezierDrawMethod();
        mIDrawMethod.preparePoints(mPointFList);
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        mIDrawMethod.drawPoints(canvas, mPointFList);
    }
}
