package typo.com.jalavant_2_0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.nio.channels.Channel;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private Button btn_connect;
    // Declare a WifiManager object
    private WifiManager wifiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Initialize the WifiManager object
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        //connect button | Jump to the ControlViewActivity
        btn_connect = findViewById(R.id.btn_connection);
        btn_connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Check if Wi-Fi is enabled, if not, enable it
                if (!wifiManager.isWifiEnabled()) {
                    wifiManager.setWifiEnabled(true);
                }

                // Connect to the specified Wi-Fi network
                String networkSSID = "Jalavant";
                String networkPassword = "Jalavant";
                WifiConfiguration wifiConfig = new WifiConfiguration();
                wifiConfig.SSID = String.format("\"%s\"", networkSSID);
                wifiConfig.preSharedKey = String.format("\"%s\"", networkPassword);


                int netId = wifiManager.addNetwork(wifiConfig);
                wifiManager.disconnect();
                wifiManager.enableNetwork(netId, true);
                wifiManager.reconnect();

                // Check if Wi-Fi is enabled and connected to a network
                if (wifiManager.isWifiEnabled()) {
                    // Wi-Fi is enabled, check if we are connected to a network
                    if (wifiManager.getConnectionInfo().getNetworkId() != -1) {
                        // We are connected to a network
                        String ssid = wifiManager.getConnectionInfo().getSSID();
                        Log.d("Wifi", "Connected to " + ssid);

                        // Jump to next page
                        Intent intent = new Intent(MainActivity.this, ControlViewActivity.class);
                        startActivity(intent);

                    } else {
                        // We are not connected to a network
                        Toast.makeText(MainActivity.this,"Connect failed, Please try again",Toast.LENGTH_LONG).show();
                        Log.d("Wifi", "Not connected to any network");
                    }
                } else {
                    // Wi-Fi is not enabled
                    Toast.makeText(MainActivity.this,"Wi-Fi is not enabled",Toast.LENGTH_LONG).show();
                    Log.d("Wifi", "Wi-Fi is not enabled");
                }

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        wifiManager.disconnect();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        wifiManager.disconnect();
    }




}