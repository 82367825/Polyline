package com.zero.polyline;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;

import java.util.List;

/**
 * @author linzewu
 * @date 2017/7/21
 */

public class BezierDrawMethod implements IDrawMethod {
    
    private Path mPath;
    
    @Override
    public void preparePoints(List<PointF> pointFList) {
        pointFList.add(0, new PointF(pointFList.get(0).x, pointFList.get(0).y));
        pointFList.add(new PointF(pointFList.get(pointFList.size() - 1).x, 
                pointFList.get(pointFList.size() - 1).y));
        pointFList.add(new PointF(pointFList.get(pointFList.size() - 1).x,
                pointFList.get(pointFList.size() - 1).y));
        
        mPath = new Path();
        mPath.moveTo(pointFList.get(0).x, pointFList.get(0).y);
        
        for (int i = 1; i < pointFList.size() - 3; i++) {
            PointF ctrlPointA = new PointF();
            PointF ctrlPointB = new PointF();
            getCtrlPoint(pointFList, i, ctrlPointA, ctrlPointB);
            mPath.cubicTo(ctrlPointA.x, ctrlPointA.y, ctrlPointB.x, ctrlPointB.y, 
                    pointFList.get(i + 1).x, pointFList.get(i + 1).y);
        }
    }

    @Override
    public void drawPoints(Canvas canvas, List<PointF> pointList) {
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(5);
        canvas.drawPath(mPath, paint);
    }

    private static final float CTRL_VALUE_A = 0.2f;
    private static final float CTRL_VALUE_B = 0.2f;
    
    /**
     * 根据已知点获取第i个控制点的坐标
     * @param pointFList
     * @param currentIndex
     * @param ctrlPointA
     * @param ctrlPointB
     */
    private void getCtrlPoint(List<PointF> pointFList, int currentIndex, 
                              PointF ctrlPointA, PointF ctrlPointB) {
        ctrlPointA.x = pointFList.get(currentIndex).x + 
                (pointFList.get(currentIndex + 1).x - pointFList.get(currentIndex - 1).x) * CTRL_VALUE_A;
        ctrlPointA.y = pointFList.get(currentIndex).y + 
                (pointFList.get(currentIndex + 1).y - pointFList.get(currentIndex - 1).y) * CTRL_VALUE_A;
        ctrlPointB.x = pointFList.get(currentIndex + 1).x - 
                (pointFList.get(currentIndex + 2).x - pointFList.get(currentIndex).x) * CTRL_VALUE_B;
        ctrlPointB.y = pointFList.get(currentIndex + 1).y - 
                (pointFList.get(currentIndex + 2).y - pointFList.get(currentIndex).y) * CTRL_VALUE_B;
    }
}
