package kmitl.lab03.tanayot.SimpleMyDot.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

import kmitl.lab03.tanayot.SimpleMyDot.model.Dot;

/**
 * Created by student on 8/25/2017 AD.
 */

public class DotView extends View {
    private Paint paint;
    private List<Dot> allDots;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(allDots != null){
            for(int i = 0; i < allDots.size();i++){
                paint.setColor(Color.parseColor(this.allDots.get(i).getColor()));
                canvas.drawCircle(this.allDots.get(i).getCenterX(),this.allDots.get(i).getCenterY(),this.allDots.get(i).getRadius(),paint);
            }
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


    public void setDot(List allDots) {
        this.allDots = allDots;
    }
}
