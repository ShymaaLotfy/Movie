package com.example.android.movie;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Shimaa on 8/28/2016.
 */
public class ImageAsyncTask extends AsyncTask<String, int[], Bitmap>{

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap = null;
            // Get your image bitmap here
            try {
            bitmap = BitmapFactory.decodeStream((InputStream)new URL(params[0]).getContent());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }            return bitmap;
        }


}

