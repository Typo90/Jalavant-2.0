package typo.com.jalavant_2_0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.wifi.WifiConfiguration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ControlViewActivity extends AppCompatActivity {

    private Button btn_jump_data_view;
    private Button btn_send_data;
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_view);

        //jump button | Jump to the ControlViewActivity
        btn_jump_data_view = findViewById(R.id.btn_jump_data_view);
        btn_jump_data_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Jump to next page
                Intent intent = new Intent(ControlViewActivity.this, DataViewActivity.class);
                startActivity(intent);
            }
        });

        //send data
        btn_send_data = findViewById(R.id.btn_send_data);
        btn_send_data.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                //thread.start();
                new Thread(new ClientThread(String.valueOf(1))).start();
                count++;
            }
        });



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

    }
    class send extends AsyncTask<Void, Void, Void>{

        Socket s;
        PrintWriter pw;
        String msg;
        BufferedReader bufferedReader;

        @Override


        protected Void doInBackground(Void... voids) {
            try {
                Log.d("Wifi", "Enter Try");
                s = new Socket("192.168.4.1", 80);
                Log.d("Wifi", "Socket Create");
                pw = new PrintWriter(s.getOutputStream());
                pw.write(msg);
                pw.flush();
                bufferedReader = new BufferedReader(new InputStreamReader(s.getInputStream()));
                Log.d("Wifi", "Buffer create");
                //String msg2 = bufferedReader.readLine();

                String inputLine;
                while ((inputLine = bufferedReader.readLine()) != null)
                    Log.d("Wifi", "read success: "+ inputLine);

                bufferedReader.close();
                pw.close();
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
            return null;
        }
    }

    class ClientThread implements Runnable {
        private String msg;
        Socket s;
        PrintWriter pw;
        BufferedReader bufferedReader;

        ClientThread(String msg){
            this.msg = msg;
        }

        public void run() {
            try {
                Log.d("Wifi", "Enter Try");
                s = new Socket("192.168.4.1", 80);
                Log.d("Wifi", "Socket Create");
                pw = new PrintWriter(s.getOutputStream());
                pw.write(msg);
                pw.flush();
                bufferedReader = new BufferedReader(new InputStreamReader(s.getInputStream()));
                Log.d("Wifi", "Buffer create");
                //String msg2 = bufferedReader.readLine();

                String inputLine;
                while ((inputLine = bufferedReader.readLine()) != null)
                Log.d("Wifi", "read success: "+ inputLine);

                bufferedReader.close();
                pw.close();
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