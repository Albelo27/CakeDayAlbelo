package cs301.birthdaycake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;

public class CakeView extends SurfaceView {

    /* These are the paints we'll use to draw the birthday cake below */
    Paint cakePaint = new Paint();
    Paint frostingPaint = new Paint();
    Paint candlePaint = new Paint();
    Paint outerFlamePaint = new Paint();
    Paint innerFlamePaint = new Paint();
    Paint wickPaint = new Paint();
    Paint textPaint = new Paint();

    //added
    Paint checkeredPaint = new Paint();

    /* These constants define the dimensions of the cake.  While defining constants for things
        like this is good practice, we could be calculating these better by detecting
        and adapting to different tablets' screen sizes and resolutions.  I've deliberately
        stuck with hard-coded values here to ease the introduction for CS371 students.
     */
    public static final float cakeTop = 400.0f;
    public static final float cakeLeft = 100.0f;
    public static final float cakeWidth = 1200.0f;
    public static final float layerHeight = 200.0f;
    public static final float frostHeight = 50.0f;
    public static final float candleHeight = 300.0f;
    public static final float candleWidth = 40.0f;
    public static final float wickHeight = 30.0f;
    public static final float wickWidth = 6.0f;
    public static final float outerFlameRadius = 30.0f;
    public static final float innerFlameRadius = 15.0f;
    public static final float textX = 1500f;

    private CakeModel cakeModel;
    private float balloonX;
    private float balloonY;

    /**
     * ctor must be overridden here as per standard Java inheritance practice.  We need it
     * anyway to initialize the member variables
     */
    public CakeView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //This is essential or your onDraw method won't get called
        setWillNotDraw(false);

        //Setup our palette
        cakePaint.setColor(0x87CEEB32);  //lime green
        cakePaint.setStyle(Paint.Style.FILL);
        frostingPaint.setColor(0xFFFFFACD);  //pale yellow
        frostingPaint.setStyle(Paint.Style.FILL);
        candlePaint.setColor(0xFF32CD32);  //lime green
        candlePaint.setStyle(Paint.Style.FILL);
        outerFlamePaint.setColor(0xFFFFD700);  //gold yellow
        outerFlamePaint.setStyle(Paint.Style.FILL);
        innerFlamePaint.setColor(0xFFFFA500);  //orange
        innerFlamePaint.setStyle(Paint.Style.FILL);
        wickPaint.setColor(Color.BLACK);
        wickPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(Color.RED);
        textPaint.setTextSize(60);

        setBackgroundColor(Color.WHITE);  //better than black default

        cakeModel = new CakeModel();
    }

    /**
     * draws a candle at a specified position.  Important:  the left, bottom coordinates specify
     * the position of the bottom left corner of the candle
     */
    public void drawCandle(Canvas canvas, float left, float bottom) {
        if (cakeModel.hasCandle) {
            canvas.drawRect(left, bottom - candleHeight, left + candleWidth + candleWidth, bottom, candlePaint);
            //draw the wick
            float wickLeft = left + candleWidth/2 - wickWidth/2;
            float wickTop = bottom - wickHeight - candleHeight;
            canvas.drawRect(wickLeft, wickTop, wickLeft + wickWidth, wickTop + wickHeight, wickPaint);

            if (cakeModel.candleLit) {
                //draw the outer flame
                float flameCenterX = left + candleWidth/2;
                float flameCenterY = bottom - wickHeight - candleHeight - outerFlameRadius/3;
                canvas.drawCircle(flameCenterX, flameCenterY, outerFlameRadius, outerFlamePaint);

                //draw the inner flame
                flameCenterY += outerFlameRadius/3;
                canvas.drawCircle(flameCenterX, flameCenterY, innerFlameRadius, innerFlamePaint);
            }
        }
    }

    /**
     * onDraw is like "paint" in a regular Java program.  While a Canvas is
     * conceptually similar to a Graphics in javax.swing, the implementation has
     * many subtle differences.  Show care and read the documentation.
     *
     * This method will draw a birthday cake
     */
    @Override
    public void onDraw(Canvas canvas)
    {
        //top and bottom are used to keep a running tally as we progress down the cake layers
        float top = cakeTop;
        float bottom = cakeTop + frostHeight;

        //Frosting on top
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, frostingPaint);
        top += frostHeight;
        bottom += layerHeight;

        //Then a cake layer
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, cakePaint);
        top += layerHeight;
        bottom += frostHeight;

        //Then a second frosting layer
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, frostingPaint);
        top += frostHeight;
        bottom += layerHeight;

        //Then a second cake layer
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, cakePaint);

        //draw candles
        for (int k = 1; k <= cakeModel.numCandle; k++) {
            drawCandle(canvas, cakeLeft + (k * cakeWidth / (cakeModel.numCandle + 1)) - candleWidth / 2, cakeTop);
        }
        if (cakeModel.displayCords) {
            canvas.drawText("(" + Integer.toString(cakeModel.xPos) + "," + Integer.toString(cakeModel.yPos) + ")", textX, 200f, textPaint);
        }

        if (balloonX >= 0 && balloonY >= 0) {
            // Draw the balloon
            Paint paint = new Paint();
            paint.setColor(Color.BLUE);
            canvas.drawCircle(balloonX, balloonY - 100, 100, paint);
            // Draw the balloon string
            canvas.drawLine(balloonX, balloonY, balloonX, balloonY + 200, paint);
        }
        //added
        drawCheckered(canvas);
    }
        public void setBalloonLocation(float x, float y) {
        balloonX = x;
        balloonY = y;
        invalidate(); // Redraw the View with the new balloon location



    }

    public CakeModel getModel() {
        return cakeModel;
    }

    //added
    public void drawCheckered(Canvas canvas){
        float x = cakeModel.xGrid;
        float y = cakeModel.yGrid;
        for(int i = 1; i<=2; i++){
            //draws a green rectangle
            if(i%2 != 0){
                checkeredPaint.setColor(Color.GREEN);
                checkeredPaint.setStyle(Paint.Style.FILL);

                //green 1
                canvas.drawRect(x-10, y-5, x, y, checkeredPaint);
                //green 2
                canvas.drawRect(x, y, x+10, y+5, checkeredPaint);
            }
            //draws a red rectangle
            if(i%2 == 0){
                checkeredPaint.setColor(Color.RED);
                checkeredPaint.setStyle(Paint.Style.FILL);

                //red 1
                canvas.drawRect(x-10, y, x, y+5, checkeredPaint);
                //red2
                canvas.drawRect(x, y-5, x+10, y, checkeredPaint);
            }
        }
    }

}//class CakeView

