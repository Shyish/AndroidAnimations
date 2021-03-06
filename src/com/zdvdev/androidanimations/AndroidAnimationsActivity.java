package com.zdvdev.androidanimations;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

public class AndroidAnimationsActivity extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image_parabola_test);

		findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				// The image to move (should be positioned at the end of the animation)
				View image = findViewById(R.id.imageView3);
				final int startx = image.getLeft();
				final int starty = image.getBottom();

				// The start point
				View end = findViewById(R.id.point_end);
				final int endx = end.getLeft();
				final int endy = end.getBottom();

				// The middle point of the Bezier ecuation
				View middle = findViewById(R.id.point_mid);
				final int middlex = middle.getLeft();
				final int middley = middle.getBottom();

				QuadraticBezierArcAnimation anim = new QuadraticBezierArcAnimation(4000, startx, starty, middlex, middley, endx, endy);
				anim.setInterpolator(new AccelerateInterpolator(1.5f));

				image.startAnimation(anim);
			}
		});
	}
}