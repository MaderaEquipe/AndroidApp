package com.reinc.madera;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class FileDownloader extends AsyncTask<String, Void, String> {
    private ProgressDialog progress;
    private String sendMethod = "GET";
    private ArrayList<Pair> sendVariables = new ArrayList<Pair>();

    public FileDownloader( Context ctx) {
        this.progress = new ProgressDialog(ctx);
    }

    public void setMethod(String newMethod) {
        this.sendMethod = newMethod;
    }
    public void addVariable( String key, String value) {
        this.sendVariables.add( new Pair( key, value));
    }

    @Override
    protected void onPreExecute() {
        progress.setTitle("Please wait while downloading");
        progress.setMessage("Fetching remote data...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();
    }

    @Override
    protected String doInBackground(String... params) {
        String strData = "";

        HttpURLConnection httpUrlConnection = null;
        try {
            URL url = new URL( params[0]);
            httpUrlConnection = (HttpURLConnection) url.openConnection();
            httpUrlConnection.setRequestMethod( this.sendMethod);

            // Ajout des variables POST s'il y en a...
            int len = this.sendVariables.size();
            if( len > 0) {

                String reqData = "";
                for (int i = 0; i<len; i++) {
                    Pair curPair = this.sendVariables.get(i);
                    String encKey = URLEncoder.encode(curPair.getVarKey(), "UTF-8");
                    String encVal = URLEncoder.encode(curPair.getVarValue(), "UTF-8");

                    if (reqData != "") reqData += "&";
                    reqData += encKey + "=" + encVal;
                }

                httpUrlConnection.setDoOutput(true);

                OutputStreamWriter wr = new OutputStreamWriter(httpUrlConnection.getOutputStream());
                wr.write(reqData);
                wr.flush();

            }

            InputStream inStream = new BufferedInputStream(httpUrlConnection.getInputStream());
            BufferedReader buffRdr = new BufferedReader(new InputStreamReader(inStream, "UTF-8"));

            final StringBuilder readStr = new StringBuilder();
            String currentLine;
            while ((currentLine = buffRdr.readLine()) != null) {
                readStr.append(currentLine);
            }
            strData = readStr.toString();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpUrlConnection.disconnect();
        }

        return strData;
    }

    @Override
    protected void onPostExecute(String result) {
        if (progress.isShowing()) progress.dismiss();
        Log.i("HTTP GET", result);
    }

}
class Pair {
    private String varKey;
    private String varValue;

    public String getVarKey() {
        return varKey;
    }

    public String getVarValue() {
        return varValue;
    }

    public Pair( String key, String value) {
        this.varKey = key;
        this.varValue = value;
    }
}
