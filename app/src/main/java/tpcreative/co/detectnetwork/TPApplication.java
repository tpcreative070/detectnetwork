package tpcreative.co.detectnetwork;
import android.app.Application;

public class TPApplication extends Application {

    private static String TAG = Application.class.getSimpleName();
    private static TPApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized TPApplication getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(TPReceiver.ConnectivityReceiverListener listener) {
        TPReceiver.connectivityReceiverListener = listener;
    }

}
