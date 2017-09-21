package kmitl.lab05.tanayot.SimpleMyDot.model;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by Barjord on 12/9/2560.
 */

public class Colors {

    public int getColor() {
        int red = new Random().nextInt(255);
        int green = new Random().nextInt(255);
        int blue = new Random().nextInt(255);
        return Color.rgb(red, green, blue);
    }
}
