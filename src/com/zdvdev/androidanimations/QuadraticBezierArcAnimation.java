package com.zdvdev.androidanimations;

import android.graphics.Point;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * To calculate the middle point:
 * http://www.newgrounds.com/portal/view/529670
 */
public class QuadraticBezierArcAnimation extends Animation {

	private final int mFromXValue;
	private final float mFromYValue;
	private final int mMidXValue;
	private final float mMidYValue;
	private final int mToXValue;
	private final float mToYValue;
	private Point start;
	private Point end;
	private Point middle;

	/**
	 * A translation along an arc defined by three points and a Bezier Curve
	 */
	public QuadraticBezierArcAnimation(long duration, int fromXValue, float fromYValue, int midXValue, float midYValue, int toXValue, float toYValue) {
		setDuration(duration);

		mFromXValue = fromXValue;
		mFromYValue = fromYValue;
		mMidXValue = midXValue;
		mMidYValue = midYValue;
		mToXValue = toXValue;
		mToYValue = toYValue;
	}

	/**
	 * Calculate the position on a quadratic bezier curve given three points
	 * and the percentage of time passed.
	 * from http://en.wikipedia.org/wiki/B%C3%A9zier_curve
	 *
	 * @param interpolatedTime
	 * 		- the fraction of the duration that has passed where 0<=time<=1
	 * @param p0
	 * 		- a single dimension of the starting point
	 * @param p1
	 * 		- a single dimension of the middle point
	 * @param p2
	 * 		- a single dimension of the ending point
	 */
	private static long calcBezier(float interpolatedTime, float p0, float p1, float p2) {
		return Math.round((Math.pow((1 - interpolatedTime), 2) * p0) + (2 * (1 - interpolatedTime) * interpolatedTime * p1) + (Math.pow(interpolatedTime, 2) * p2));
	}

	@Override
	protected void applyTransformation(float interpolatedTime, Transformation t) {
		float dx = calcBezier(interpolatedTime, start.x, middle.x, end.x);
		float dy = calcBezier(interpolatedTime, start.y, middle.y, end.y);

//		Log.d("debug", "applyTransformation t:" + interpolatedTime+ " dx: " + dx + " dy:" + dy);
		t.getMatrix().setTranslate(dx, dy);
	}

	@Override
	public void initialize(int width, int height, int parentWidth, int parentHeight) {
		super.initialize(width, height, parentWidth, parentHeight);
		int startX = Math.round(resolveSize(Animation.ABSOLUTE, mFromXValue, width, parentWidth));
		int startY = Math.round(resolveSize(Animation.ABSOLUTE, mFromYValue, height, parentHeight));
		int endX = Math.round(resolveSize(Animation.ABSOLUTE, mToXValue, width, parentWidth));
		int endY = Math.round(resolveSize(Animation.ABSOLUTE, mToYValue, height, parentHeight));
		int middleX = Math.round(resolveSize(Animation.ABSOLUTE, mMidXValue, width, parentWidth));
		int middleY = Math.round(resolveSize(Animation.ABSOLUTE, mMidYValue, height, parentHeight));
		end = new Point(0, 0);
		start = new Point(endX- startX, endY - startY);
		middle = new Point(middleX - startX, middleY - startY);
	}
}