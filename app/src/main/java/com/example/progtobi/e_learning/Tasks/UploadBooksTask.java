package com.example.progtobi.e_learning.Tasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by PROG. TOBI on 07-Jul-16.
 */
public class UploadBooksTask extends AsyncTask<String, String, String> {
    Context ctx;
    Activity activity;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ProgressDialog progressDialog;

    public UploadBooksTask(Context ctx) {
        this.ctx = ctx;
        activity = (Activity) ctx;
    }


    @Override
    protected String doInBackground(String... params) {
        String selectedPath = params[0];
        String bookname = params[1];
        String bookauthor = params[2];
        String bookdesc = params[3];


        try {
            HttpURLConnection conn = null;
            DataOutputStream dos = null;
            DataInputStream inStream = null;
            String lineEnd = "rn";
            String twoHyphens = "--";
            String boundary = "*****";
            int bytesRead, bytesAvailable, bufferSize;
            byte[] buffer;
            int maxBufferSize = 1 * 1024 * 1024;
            String responseFromServer = "";
            String urlString = "http://elearningapp.eu5.org/uploadbook.php";
            try {
                //------------------ CLIENT REQUEST
                FileInputStream fileInputStream = new FileInputStream(new File(selectedPath));
                // open a URL connection to the Servlet
                URL url = new URL(urlString);
                // Open a HTTP connection to the URL
                conn = (HttpURLConnection) url.openConnection();
                // Allow Inputs
                conn.setDoInput(true);
                // Allow Outputs
                conn.setDoOutput(true);
                // Don't use a cached copy.
                conn.setUseCaches(false);
                // Use a post method.
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Connection", "Keep-Alive");
                conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                conn.setRequestProperty("uploadedfile", selectedPath);

                dos = new DataOutputStream(conn.getOutputStream());
                dos.writeBytes(twoHyphens + boundary + lineEnd);
                dos.writeBytes("Content-Disposition: form-data; name=\"uploadedfile\";filename=\"" + selectedPath + "\"" + lineEnd);
                dos.writeBytes(lineEnd);
                // create a buffer of maximum size
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                buffer = new byte[bufferSize];
                // read file and write it into form...
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                while (bytesRead > 0) {
                    dos.write(buffer, 0, bufferSize);
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                }
                // send multipart form data necesssary after file data...
                dos.writeBytes(lineEnd);
                dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
                // close streams
                Log.e("Debug", "File is written");
                fileInputStream.close();
                dos.flush();
                dos.close();
                OutputStream outputStream = conn.getOutputStream();
                /*BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("bookname", "UTF-8") + "=" + URLEncoder.encode(bookname, "UTF-8") + "&"
                        + URLEncoder.encode("bookauthor", "UTF-8") + "=" + URLEncoder.encode(bookauthor, "UTF-8") + "&"
                        + URLEncoder.encode("bookdesc", "UTF-8") + "=" + URLEncoder.encode(bookdesc, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();*/
            } catch (MalformedURLException ex) {
                Log.e("Debug", "error: " + ex.getMessage(), ex);
            } catch (IOException ioe) {
                Log.e("Debug", "error: " + ioe.getMessage(), ioe);
            }
            //------------------ read the SERVER RESPONSE
            try {
                inStream = new DataInputStream(conn.getInputStream());
                String str;

                while ((str = inStream.readLine()) != null) {
                    Log.e("Debug", "Server Response " + str);
                }
                inStream.close();

            } catch (IOException ioex) {
                Log.e("Debug", "error: " + ioex.getMessage(), ioex);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPreExecute() {

        progressDialog = new ProgressDialog(ctx);
        progressDialog.setTitle("Please Wait...");
        progressDialog.setMessage("Uploading Books...");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();

    }

    @Override
    protected void onProgressUpdate(String... values) {

    }

    @Override
    protected void onPostExecute(String o) {
        progressDialog.dismiss();

    }
}

