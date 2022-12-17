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







    }

}