package com.jessepiologo.DinoMeteorShower.engine;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by James on 6/16/2017.
 */

public class BitmapInverter {
    public void createFile(Bitmap res) {
        try {
            // Assume block needs to be inside a Try/Catch block.
            String path = Environment.getExternalStorageDirectory().toString();
            OutputStream fOut = null;
            Integer counter = 0;
            File file = new File(path, "inverted.png"); // the File to save , append increasing numeric counter to prevent files from getting overwritten.
            fOut = new FileOutputStream(file);

            Bitmap pictureBitmap = do18ShadesOfGray(res); // obtaining the Bitmap
            pictureBitmap.compress(Bitmap.CompressFormat.PNG, 0, fOut); // saving the Bitmap to a file compressed as a JPEG with 85% compression rate
            fOut.flush(); // Not really required
            fOut.close(); // do not forget to close the stream

            MediaStore.Images.Media.insertImage(getContentResolver(), file.getAbsolutePath(), file.getName(), file.getName());

        } catch (Exception e) {
        }
    }

    public Bitmap do18ShadesOfGray(Bitmap src) {
        int qtd = 18;
        // create new bitmap with the same settings as source bitmap
        Bitmap bmOut = Bitmap.createBitmap(src.getWidth() * qtd, src.getHeight(), src.getConfig());
        // color info
        int xx = 0;
        int yy = 0;

        int rn = 0;
        int gn = 0;
        int bn = 0;

        int factor = 255 / (qtd - 1);

        int A, R, G, B;
        int pixelColor;
        // image size
        int height = src.getHeight();
        int width = src.getWidth();

        for (int i = 0; i < qtd; i++) {
            yy = 0;
            // scan through every pixel
            for (int y = 0; y < height; y++) {
                xx = 0;

                for (int x = i * src.getWidth(); x < width + (width * i); x++) {
                    //for (int x = 0; x < width; x++) {
                    // get one pixel
                    pixelColor = src.getPixel(xx, yy);
                    // saving alpha channel
                    A = Color.alpha(pixelColor);
                    // inverting byte for each R/G/B channel
                    R = Math.abs(Color.red(pixelColor) - rn);
                    G = Math.abs(Color.green(pixelColor) - gn);
                    B = Math.abs(Color.blue(pixelColor) - bn);
                    System.out.println("i: " + i + "   y: " + y +
                            "   x: " + x + "  factor: " + factor + "  RGB: " + B);
                    // set newly-inverted pixel to output image
                    bmOut.setPixel(x, y, Color.argb(A, R, G, B));
                    xx++;
                }
                yy++;
            }
            rn += factor;
            gn += factor;
            bn += factor;
        }
        // return final bitmap
        System.out.println("OOOO   Bitmap creation Done OOOO");
        return bmOut;
    }

    public ContentResolver getContentResolver() {
        ContentResolver contentResolver = null;
        return contentResolver;
    }
}
