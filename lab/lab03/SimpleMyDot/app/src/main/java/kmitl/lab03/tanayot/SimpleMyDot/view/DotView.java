package kmitl.lab03.tanayot.SimpleMyDot.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import kmitl.lab03.tanayot.SimpleMyDot.model.Dot;

/**
 * Created by student on 8/25/2017 AD.
 */

public class DotView extends View {
    private Paint paint;
    private Dot dot;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(dot != null){
            paint.setColor(Color.RED);
            canvas.drawCircle(this.dot.getCenterX(),this.dot.getCenterY(),this.dot.getRadius(),paint);
        }

    }

    public DotView(Context context) {
        super(context);
        paint = new Paint();
    }

    public DotView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }

    public DotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
    }


    public void setDot(Dot dot) {
        this.dot = dot;
    }
}
