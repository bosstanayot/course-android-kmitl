package kmitl.lab03.tanayot.SimpleMyDot.control;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import kmitl.lab03.tanayot.SimpleMyDot.R;
import kmitl.lab03.tanayot.SimpleMyDot.model.Dot;
import kmitl.lab03.tanayot.SimpleMyDot.view.DotView;

public class MainActivity extends AppCompatActivity implements Dot.OnDotChangedListener{
    private DotView dotView;
    private Dot dot;
    private List<Dot> allDots;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dotView = (DotView) findViewById(R.id.dotView);
        allDots = new ArrayList<>();

    }

    public void onRandomDot(View view) {
        Random random = new Random();
        String color = "";
        int centerX = random.nextInt(1080);
        int centerY = random.nextInt(1200);
        int radius = random.nextInt(80)+20;
        int numColor = random.nextInt(5);
        if(numColor == 0){
            color = "#FF66CC";
        }else if(numColor == 1){
            color = "#FF9900";
        }else if(numColor == 2){
            color = "#FF0000";
        }else if(numColor == 3){
            color = "#99FF00";
        }else if(numColor == 4){
            color = "#66FFFF";
        }
        dot = new Dot(this,centerX,centerY,radius,color);
        dot.setCenterX(centerX);
        dot.setCenterY(centerY);
        dot.setRadius(radius);
        dot.setColor(color);
        allDots.add(dot);
    }
    @Override
    public void onDotChanged(Dot dot) {
// TextView centerXTextView = (TextView)findViewById(R.id.centerXTextView);
// TextView centerYTextView = (TextView)findViewById(R.id.centerYTextView);
// centerXTextView.setText(String.valueOf(dot.getCenterX()));
// centerYTextView.setText(String.valueOf(dot.getCenterY()));
        dotView.setDot(allDots);
        dotView.invalidate();
    }
    public void clear(View view) {
        allDots.clear();
        dotView.setDot(allDots);
        dotView.invalidate();
    }

}






