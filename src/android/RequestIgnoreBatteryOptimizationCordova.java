package br.com.fabrica704.permission;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RequestIgnoreBatteryOptimizationCordova extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("requestPermission")) {
            requestPermission();
            return true;
        }

        if (action.equals("checkPermission")) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("status", checkPermission());
                callbackContext.success(jsonObject);
            } catch (JSONException e) {
                callbackContext.error("Ocorreu um erro ao obter status");
            }
            return true;
        }

        return false;
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            String pkg = cordova.getContext().getPackageName();
            PowerManager pm = cordova.getContext().getSystemService(PowerManager.class);

            if (!pm.isIgnoringBatteryOptimizations(pkg)) {
                Intent i =
                        new Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS)
                                .setData(Uri.parse("package:" + pkg));

                cordova.getContext().startActivity(i);
            }
        }
    }

    private boolean checkPermission(){
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            String pkg = cordova.getContext().getPackageName();
            PowerManager pm = cordova.getContext().getSystemService(PowerManager.class);

            return pm.isIgnoringBatteryOptimizations(pkg);
        }

        return false;
    }


}
