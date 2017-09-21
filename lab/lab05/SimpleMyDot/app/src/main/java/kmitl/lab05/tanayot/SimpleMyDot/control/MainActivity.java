package kmitl.lab05.tanayot.SimpleMyDot.control;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import kmitl.lab05.tanayot.SimpleMyDot.R;
import kmitl.lab05.tanayot.SimpleMyDot.model.Dot;
import kmitl.lab05.tanayot.SimpleMyDot.model.DotParcelable;
import kmitl.lab05.tanayot.SimpleMyDot.model.Dots;
import kmitl.lab05.tanayot.SimpleMyDot.view.DotView;
import kmitl.lab05.tanayot.SimpleMyDot.model.Screenshot;
import kmitl.lab05.tanayot.SimpleMyDot.model.Colors;

public class MainActivity extends AppCompatActivity  implements Dots.OnDotsChangeListener, DotView.OnDotViewPressListener {
    private DotView dotView;
    private Dot dot;

    private Dots dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dotView = (DotView) findViewById(R.id.dotView);
       dotView.setListener(this);


        dots = new Dots();
        dots.setListener(this);
    }

    public void onRandomDot(View view) {
        Random random = new Random();
        int centerX = random.nextInt(dotView.getWidth());
        int centerY = random.nextInt(dotView.getHeight());
        Dot newDot = new Dot(centerX, centerY, 70, new Colors().getColor());
        dots.addDot(newDot);
    }
    @Override
    public void onDotsChanged(Dots dots) {
// TextView centerXTextView = (TextView)findViewById(R.id.centerXTextView);
// TextView centerYTextView = (TextView)findViewById(R.id.centerYTextView);
// centerXTextView.setText(String.valueOf(dot.getCenterX()));
// centerYTextView.setText(String.valueOf(dot.getCenterY()));
        dotView.setDots(dots);
        dotView.invalidate();
    }
    public void onRemoveAll(View view) {
        dots.clearAll();
    }
    @Override
    public void onDotViewPressed(int x, int y) {
        final int dotPosition = dots.findDot(x, y);
        if (dotPosition == -1) {
            Dot newDot = new Dot(x, y, 70, new Colors().getColor());
            dots.addDot(newDot);
        } else {
            dots.removeBy(dotPosition);
        }
    }

    @Override
    public void onDotViewLongPressed(int x, int y) {
        int dotPosition = dots.findDot(x, y);
        if (dotPosition == -1) {
            return;
        } else {
            alertDialog(dotPosition);
        }
    }

    public void alertDialog(final int dotPosition) {

        final DotParcelable dotParcelable = new DotParcelable(dotPosition, dots.getAllDot().get(dotPosition).getColor(), dots.getAllDot().get(dotPosition).getRadius());
        Intent editActIntent = new Intent(MainActivity.this, EditActivity.class);
        editActIntent.putExtra("dotParcelable", dotParcelable);
        startActivityForResult(editActIntent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            DotParcelable dotParcelable = data.getParcelableExtra("reDotParcelable");
            if (resultCode == Activity.RESULT_OK) {
                dots.getAllDot().get(dotParcelable.getDotPosition()).setColor(dotParcelable.getColor());
            } else {
                dots.getAllDot().get(dotParcelable.getDotPosition()).setRadius(dotParcelable.getRadius());
            }
        }
    }

    public void onShare(View view) {
        View main;
        ImageView imageView;
        main = findViewById(R.id.main);
        imageView = (ImageView) findViewById(R.id.imageView);
        Bitmap b = Screenshot.takescreenshotOfRottView(imageView);
        saveBitmap(b);
        File imagePath = new File(this.getCacheDir(), "images");
        File newFile = new File(imagePath, "image.png");
        Uri contentUri = FileProvider.getUriForFile(this, "kmitl.lab05.tanayot.SimpleMyDot.fileprovider", newFile);
        if (contentUri != null) {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); // temp permission for receiving app to read this file
            shareIntent.setDataAndType(contentUri, getContentResolver().getType(contentUri));
            shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri);
            startActivity(Intent.createChooser(shareIntent, "Choose an app"));

        }
    }

    private Bitmap createBitmapFromView(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bitmap);
        view.layout(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        view.draw(c);
        return bitmap;
    }

    private void saveBitmap(Bitmap bitmap) {
        // save bitmap to cache directory
        try {
            File cachePath = new File(this.getCacheDir(), "images");
            cachePath.mkdirs(); // don't forget to make the directory
            FileOutputStream stream = new FileOutputStream(cachePath + "/image.png"); // overwrites this image every time
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            stream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}






