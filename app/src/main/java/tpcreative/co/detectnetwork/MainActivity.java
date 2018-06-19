package tpcreative.co.detectnetwork;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements TPReceiver.ConnectivityReceiverListener ,CompoundButton.OnCheckedChangeListener{

    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView textView ;
    private SwitchCompat aSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.tvStatus);
        aSwitch = findViewById(R.id.switchWifi);
        aSwitch.setOnCheckedChangeListener(this);
        /*Initialize Broadcast Receiver*/
        TPApplication.getInstance().setConnectivityListener(this);
        final  boolean status = TPReceiver.isConnected();
        onDetectStatus(status);
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        onDetectStatus(isConnected);
    }

    public void onDetectStatus(boolean isConnected){
        aSwitch.setChecked(isConnected);
        if (isConnected){
            textView.setText("Connected network");
            textView.setTextColor(getResources().getColor(R.color.ColorBlue));
        }
        else{
            textView.setText("Disconnected network");
            textView.setTextColor(getResources().getColor(R.color.ColorRed));
        }
    }



    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        Log.d(TAG,"" + b);
        WifiManager wifiManager = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiManager.setWifiEnabled(b);
    }

}
