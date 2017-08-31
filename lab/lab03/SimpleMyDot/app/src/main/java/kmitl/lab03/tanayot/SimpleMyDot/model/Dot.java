package kmitl.lab03.tanayot.SimpleMyDot.model;

/**
 * Created by student on 8/25/2017 AD.
 */

public class Dot {
    public interface OnDotChangedListener{
        void onDotChanged(Dot dot);
    }
    private OnDotChangedListener listener;
    public void setListener(OnDotChangedListener listener) {
        this.listener = listener;
    }
    public Dot(OnDotChangedListener listener, int centerX, int centerY, int radius,String color) {
        this.listener = listener;
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.color = color;
    }
    public Dot(int centerX, int centerY, int radius, String color) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.color = color;
    }
    public int getCenterX() {
        return centerX;
    }
    public void setCenterX(int centerX) {
        this.centerX = centerX;
        this.listener.onDotChanged(this);
    }
    public int getCenterY() {
        return centerY;
    }
    public void setCenterY(int centerY) {
        this.centerY = centerY;
        this.listener.onDotChanged(this);
    }
    public int getRadius() {
        return radius;
    }
    public void setRadius(int radius) {
        this.radius = radius;
    }
    private int centerX;
    private int centerY;
    private int radius;
    private String color;
    public OnDotChangedListener getListener() {
        return listener;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
        this.listener.onDotChanged(this);
    }
}
