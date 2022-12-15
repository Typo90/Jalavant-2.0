package typo.com.jalavant_2_0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.Button;

import java.io.*;
import java.net.*;
import android.net.wifi.p2p.WifiP2pManager.Channel;

public class DataViewActivity extends AppCompatActivity {

    private WebView webView;

    WifiP2pManager manager;
    Channel channel;
    BroadcastReceiver receiver;
    WifiP2pManager.PeerListListener myPeerListListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_view);



        //obtain a peer from the WifiP2pDeviceList
//        WifiP2pDevice device;
//        WifiP2pConfig config = new WifiP2pConfig();
//        config.deviceAddress = device.deviceAddress;
//        manager.connect(channel, config, new ActionListener() {
//
//            @Override
//            public void onSuccess() {
//                //success logic
//            }
//
//            @Override
//            public void onFailure(int reason) {
//                //failure logic
//            }
//        });

        //----------------------------------------------------
        // WebView Init
        //----------------------------------------------------
        webView = (WebView) findViewById(R.id.webView);
        webView.loadUrl("http://192.168.4.1:80");        //webView.loadUrl("http://www.google.com");

//        try {
//            StringBuilder sb = new StringBuilder();
//            URL url = new URL("http://192.168.4.1:80");
//
//            BufferedReader in;
//            in = new BufferedReader(
//                    new InputStreamReader(
//                            url.openStream()));
//
//            String inputLine;
//            while ((inputLine = in.readLine()) != null)
//                sb.append(inputLine);
//
//            in.close();
//
//            Log.d("Wifi", "onClick data: "+ sb.toString());
//
//        } catch (Exception e) {
//            Log.d("Wifi", "fail"+ e.toString());
//            e.printStackTrace();
//        }


//        Thread thread = new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                URL url = null;
//                try {
//                    url = new URL("http://192.168.4.1:80");
//
//                    try{
//                        Log.d("Wifi", "read success!");
//                        BufferedReader in = new BufferedReader(
//                                new InputStreamReader(
//                                        url.openStream()));
//
//                        String inputLine;
//                        while ((inputLine = in.readLine()) != null)
//                            Log.d("Wifi", "read success: "+ inputLine);
//
//                        in.close();
//                    }catch (Exception e2){
//                        Log.d("Wifi", "buffer read fail"+ e2.toString());
//                    }
//
//                } catch (MalformedURLException e) {
//                    Log.d("Wifi", "create URL fail"+ e.toString());
//                    e.printStackTrace();
//                }
//
//            }
//        });

        //thread.start();
        new Thread(new ClientThread()).start();




    }
    class ClientThread implements Runnable {
        private String msg;
        Socket s;
        PrintWriter pw;
        String type;
        BufferedReader bufferedReader;


        public void run() {
            try {
                Log.d("Wifi", "Enter Try");
                s = new Socket("192.168.4.1", 80);
                Log.d("Wifi", "Socket Create");
                //pw = new PrintWriter(s.getOutputStream());
                //pw.write(msg);
                //pw.flush();
//                bufferedReader = new BufferedReader(new InputStreamReader(s.getInputStream()));
                bufferedReader = new BufferedReader(new InputStreamReader(s.getInputStream()));
                Log.d("Wifi", "Buffer create");
                //String msg2 = bufferedReader.readLine();

                String inputLine;
                while ((inputLine = bufferedReader.readLine()) != null)
                    Log.d("Wifi", "read success: "+ inputLine);

                bufferedReader.close();
                //pw.close();
                s.close();
            } catch (UnknownHostException e) {
                Log.d("Wifi", "UnknownHost: "+ e.toString());
                e.printStackTrace();
            } catch (IOException e) {
                Log.d("Wifi", "IOException: "+ e.toString());
                e.printStackTrace();
            } catch (Exception e){
                Log.d("Wifi", "Exception: "+ e.toString());
                e.printStackTrace();
            }
        }
    }
}