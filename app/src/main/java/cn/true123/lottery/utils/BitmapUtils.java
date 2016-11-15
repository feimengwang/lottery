package cn.true123.lottery.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class BitmapUtils {

    public static final int LEFT = 0;

    public static final int RIGHT = 1;

    public static final int TOP = 3;

    public static final int BOTTOM = 4;




    public static Bitmap toGrayscale(Bitmap bmpOriginal) {

        int width, height;

        height = bmpOriginal.getHeight();

        width = bmpOriginal.getWidth();

        Bitmap bmpGrayScale = Bitmap.createBitmap(width, height,

                Config.RGB_565);

        Canvas c = new Canvas(bmpGrayScale);

        Paint paint = new Paint();

        ColorMatrix cm = new ColorMatrix();

        cm.setSaturation(0);

        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);

        paint.setColorFilter(f);

        c.drawBitmap(bmpOriginal, 0, 0, paint);

        return bmpGrayScale;

    }




    public static Bitmap toGrayscale(Bitmap bmpOriginal, int pixels) {

        return toRoundCorner(toGrayscale(bmpOriginal), pixels);

    }




    public static Bitmap toRoundCorner(Bitmap bitmap, int pixels) {

        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap

                .getHeight(), Config.ARGB_8888);

        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;

        final Paint paint = new Paint();

        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        final RectF rectF = new RectF(rect);

        final float roundPx = pixels;

        paint.setAntiAlias(true);

        canvas.drawARGB(0, 0, 0, 0);

        paint.setColor(color);

        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));

        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;

    }




    public static BitmapDrawable toRoundCorner(BitmapDrawable bitmapDrawable,

            int pixels) {

        Bitmap bitmap = bitmapDrawable.getBitmap();

        bitmapDrawable = new BitmapDrawable(toRoundCorner(bitmap, pixels));

        return bitmapDrawable;

    }




    public static void saveBefore(String path) {

        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inJustDecodeBounds = true;


        Bitmap bitmap = BitmapFactory.decodeFile(path, options);
        options.inJustDecodeBounds = false;


        int be = (int) (options.outHeight / (float) 200);

        if (be <= 0)

            be = 1;

        options.inSampleSize = 2;

        bitmap = BitmapFactory.decodeFile(path, options);

        int w = bitmap.getWidth();

        int h = bitmap.getHeight();

        System.out.println(w + " " + h);


        saveJPGE_After(bitmap, path);

    }


    /**
     * 
     * @param bitmap
     * @param name
     */

    public static void savePNG_After(Bitmap bitmap, String name) {

        File file = new File(name);

        try {

            FileOutputStream out = new FileOutputStream(file);

            if (bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)) {

                out.flush();

                out.close();

            }

        } catch (FileNotFoundException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }




    public static void saveJPGE_After(Bitmap bitmap, String path) {

        File file = new File(path);

        try {

            FileOutputStream out = new FileOutputStream(file);

            if (bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)) {

                out.flush();

                out.close();

            }

        } catch (FileNotFoundException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }


    /**
     *
     * @param src
     * @param watermark
     * @return
     */

    public static Bitmap createBitmapForWatermark(Bitmap src, Bitmap watermark) {

        if (src == null) {

            return null;

        }

        int w = src.getWidth();

        int h = src.getHeight();

        int ww = watermark.getWidth();

        int wh = watermark.getHeight();

        // create the new blank bitmap
        Bitmap newb = Bitmap.createBitmap(w, h, Config.ARGB_8888);
        Canvas cv = new Canvas(newb);

        // draw src into
        cv.drawBitmap(src, 0, 0, null);
        // draw watermark into
        cv.drawBitmap(watermark, w - ww + 5, h - wh + 5, null);
        // save all clip
        cv.save(Canvas.ALL_SAVE_FLAG);
        // store
        cv.restore();
        return newb;

    }



    public static Bitmap potoMix(int direction, Bitmap... bitmaps) {

        if (bitmaps.length <= 0) {

            return null;

        }

        if (bitmaps.length == 1) {

            return bitmaps[0];

        }

        Bitmap newBitmap = bitmaps[0];

        // newBitmap = createBitmapForFotoMix(bitmaps[0],bitmaps[1],direction);
        for (int i = 1; i < bitmaps.length; i++) {

            newBitmap = createBitmapForFotoMix(newBitmap, bitmaps[i], direction);

        }

        return newBitmap;

    }


    private static Bitmap createBitmapForFotoMix(Bitmap first, Bitmap second,

            int direction) {

        if (first == null) {

            return null;

        }

        if (second == null) {

            return first;

        }

        int fw = first.getWidth();

        int fh = first.getHeight();

        int sw = second.getWidth();

        int sh = second.getHeight();

        Bitmap newBitmap = null;

        if (direction == LEFT) {

            newBitmap = Bitmap.createBitmap(fw + sw, fh > sh ? fh : sh,

                    Config.ARGB_8888);

            Canvas canvas = new Canvas(newBitmap);

            canvas.drawBitmap(first, sw, 0, null);

            canvas.drawBitmap(second, 0, 0, null);

        } else if (direction == RIGHT) {

            newBitmap = Bitmap.createBitmap(fw + sw, fh > sh ? fh : sh,

                    Config.ARGB_8888);

            Canvas canvas = new Canvas(newBitmap);

            canvas.drawBitmap(first, 0, 0, null);

            canvas.drawBitmap(second, fw, 0, null);

        } else if (direction == TOP) {

            newBitmap = Bitmap.createBitmap(sw > fw ? sw : fw, fh + sh,

                    Config.ARGB_8888);

            Canvas canvas = new Canvas(newBitmap);

            canvas.drawBitmap(first, 0, sh, null);

            canvas.drawBitmap(second, 0, 0, null);

        } else if (direction == BOTTOM) {

            newBitmap = Bitmap.createBitmap(sw > fw ? sw : fw, fh + sh,

                    Config.ARGB_8888);

            Canvas canvas = new Canvas(newBitmap);

            canvas.drawBitmap(first, 0, 0, null);

            canvas.drawBitmap(second, 0, fh, null);

        }

        return newBitmap;

    }




    public static Bitmap createBitmapBySize(Bitmap bitmap, int width, int height) {

        return Bitmap.createScaledBitmap(bitmap, width, height, true);

    }



    public static Bitmap drawableToBitmapByBD(Drawable drawable) {

        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;

        return bitmapDrawable.getBitmap();

    }



    public static Drawable bitmapToDrawableByBD(Resources res,Bitmap bitmap) {

        Drawable drawable = new BitmapDrawable(res, bitmap);

        return drawable;

    }



    public static Bitmap bytesToBimap(byte[] b) {

        if (b.length != 0) {

            return BitmapFactory.decodeByteArray(b, 0, b.length);

        } else {

            return null;

        }

    }




    public static byte[] bitmapToBytes(Bitmap bm) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);

        return baos.toByteArray();

    }

}