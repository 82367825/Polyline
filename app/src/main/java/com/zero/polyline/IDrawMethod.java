package com.zero.polyline;

import android.graphics.Canvas;
import android.graphics.PointF;

import java.util.List;

/**
 * @author linzewu
 * @date 2017/7/21
 */

public interface IDrawMethod {
    
    void preparePoints(List<PointF> pointFList);
    
    void drawPoints(Canvas canvas, List<PointF> pointList);
    
}
