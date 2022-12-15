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


        //----------------------------------------------------
        // Wifi Init
        //----------------------------------------------------

        manager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        channel = manager.initialize(this, getMainLooper(), null);
        receiver = new WiFiDirectBroadcastReceiver(manager, channel, this);

        manager.discoverPeers(channel, new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {
                Log.d("test", "Success discover" );
            }

            @Override
            public void onFailure(int reasonCode) {
                Log.d("test", ""+reasonCode );
            }
        });

        WifiP2pManager.PeerListListener myPeerListListener = null;

        if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(Context.WIFI_P2P_SERVICE)) {

            // request available peers from the wifi p2p manager. This is an
            // asynchronous call and the calling activity is notified with a
            // callback on PeerListListener.onPeersAvailable()
            if (manager != null) {
                manager.requestPeers(channel, myPeerListListener);
            }
        }

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
//        webView = (WebView) findViewById(R.id.webView);
//        webView.loadUrl("http://172.20.10.9:5000");        //webView.loadUrl("http://www.google.com");
//
//        try {
//            StringBuilder sb = new StringBuilder();
//            URL url = new URL("http://172.20.10.9:5000");
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
//            Log.d("test", "onClick data: "+ sb.toString());
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}