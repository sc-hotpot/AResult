package sc.hotpot.aresult;

import android.app.Activity;
import android.content.Intent;

public class AResultUtil {

    private Activity activity;
    private AResult mResult;
    public AResultUtil(Activity activity)
    {
        this.activity=activity;
    }
    public void startForResult(Intent intent, AResult.Callback callback)
    {


        mResult = new AResult(activity);
        mResult.startForResult(intent, callback);
    }
}
