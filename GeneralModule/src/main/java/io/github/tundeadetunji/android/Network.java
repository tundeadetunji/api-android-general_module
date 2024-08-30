package io.github.tundeadetunji.android;

import android.app.Activity;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Network {


    private final Activity activity;
    private final TextView view;
    private final String url;

    private final String user_agent_string;

    public Network(String url, Activity activity, TextView showResultHere, String userAgentString) {
        this.activity = activity;
        this.view = showResultHere;
        this.url = url;
        this.user_agent_string = userAgentString;
    }

    public void readResource(){
        new Thread() {
            @Override
            public void run() {
                String path = url;
                URL u = null;
                try {
                    u = new URL(path);
                    HttpURLConnection c = (HttpURLConnection) u.openConnection();
                    c.setRequestMethod("GET");
                    c.setRequestProperty("User-Agent", user_agent_string);
                    c.connect();
                    InputStream in = c.getInputStream();
                    final ByteArrayOutputStream bo = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    in.read(buffer); // Read from Buffer.
                    bo.write(buffer); // Write Into Buffer.

                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            view.setText(bo.toString());
                            try {
                                bo.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();

    }
}
