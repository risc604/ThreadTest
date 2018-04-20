package com.demo.tomcat.threadtest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class DownloadTask extends AsyncTask<Void, Integer, Boolean>
{
    private static final String TAG = DownloadTask.class.getSimpleName();

    ProgressDialog  progressDialog;
    @SuppressLint("StaticFieldLeak")
    Context         context;

    public DownloadTask(Activity activity)
    {
        Log.w(TAG, "DownloadTask(), activity: " + activity);
        context = activity.getBaseContext();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.w(TAG, " onPreExecute(), ");

        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
        }

        progressDialog.show();
    }

    @Override
    protected Boolean doInBackground(Void... params)
    {
        Log.w(TAG, " doInBackground(), ");
        try {
            while (true)
            {
                int downloadPercent = doDownload();
                publishProgress(downloadPercent);
                if (downloadPercent > 99)
                    break;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Log.w(TAG, " onProgressUpdate(), ");

        progressDialog.setMessage("Download " + values[0] + "%");
    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
        Log.w(TAG, " onPostExecute(), ");

        progressDialog.dismiss();

        if (result)
        {
            Toast.makeText(context, "Download succeeded", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Download failed", Toast.LENGTH_SHORT).show();
        }
    }


    //---------------- user function -----------------------//
    private int doDownload()
    {
        Log.w(TAG, "  doDownload(), ");
        return 0;
    }
}

