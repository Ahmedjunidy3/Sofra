package com.example.sofra.data.local;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceManager {
    private static SharedPreferences sharedPreferences = null;


    private static void setSharedPreferences(Activity activity) {
        if (sharedPreferences == null) {
            sharedPreferences = activity.getSharedPreferences(
                    "SHARED_PREF", Context.MODE_PRIVATE);
        }
    }

    public static void setSharedPreferences(Application application) {
        if (sharedPreferences == null) {
            sharedPreferences = application.getSharedPreferences(
                    "SHARED_PREF", Context.MODE_PRIVATE);
        }
    }

    public static void saveData(Application application, String data_Key, String data_Value) {
        setSharedPreferences(application);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(data_Key, data_Value);
            editor.apply();
        }
    }

    public static void saveRestId(Application application, String data_Value) {
        setSharedPreferences(application);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("REST_ID", data_Value);
            editor.apply();
        }
    }

    public static void saveRestApiToken(Application application, String data_Value) {
        setSharedPreferences(application);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("REST_API_TOKEN", data_Value);
            editor.apply();
        }
    }

    public static String loadRestApiToken(Activity activity) {
        setSharedPreferences(activity);
        return sharedPreferences.getString("REST_API_TOKEN", null);
    }

    public static void saveRestEmail(Activity activity, String data_Value) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("REST_LOGIN_EMAIL", data_Value);
            editor.apply();
        }
    }

    public static String loadRestEmail(Activity activity) {
        setSharedPreferences(activity);
        return sharedPreferences.getString("REST_LOGIN_EMAIL", null);
    }

    public static void saveRestPassword(Activity activity, String data_Value) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("REST_LOGIN_PASSWORD", data_Value);
            editor.apply();
        }
    }

    public static String loadRestPassword(Activity activity) {
        setSharedPreferences(activity);
        return sharedPreferences.getString("REST_LOGIN_PASSWORD", null);
    }

    public static void removeClientEmail(Application application) {
        setSharedPreferences(application);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("CLIENT_LOGIN_EMAIL");
            editor.apply();
        }
    }

    public static void removeClientPassword(Application application) {
        setSharedPreferences(application);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("CLIENT_LOGIN_PASSWORD");
            editor.apply();
        }
    }

    public static void removeClientEmail(Activity activity) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("CLIENT_LOGIN_EMAIL");
            editor.apply();
        }
    }

    public static void removeClientPassword(Activity activity) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("CLIENT_LOGIN_PASSWORD");
            editor.apply();
        }
    }

    public static void removeRestEmail(Activity activity) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("REST_LOGIN_EMAIL");
            editor.apply();
        }
    }

    public static void removeRestPassword(Activity activity) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("REST_LOGIN_PASSWORD");
            editor.apply();
        }
    }

    public static void removeRestEmail(Application application) {
        setSharedPreferences(application);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("REST_LOGIN_EMAIL");
            editor.apply();
        }
    }

    public static void removeRestPassword(Application application) {
        setSharedPreferences(application);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("REST_LOGIN_PASSWORD");
            editor.apply();
        }
    }

    public static void saveClientEmail(Activity activity, String data_Value) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("CLIENT_LOGIN_EMAIL", data_Value);
            editor.apply();
        }
    }

    public static String loadClientEmail(Activity activity) {
        setSharedPreferences(activity);
        return sharedPreferences.getString("CLIENT_LOGIN_EMAIL", null);
    }

    public static void saveClientPassword(Activity activity, String data_Value) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("CLIENT_LOGIN_PASSWORD", data_Value);
            editor.apply();
        }
    }

    public static String loadClientPassword(Activity activity) {
        setSharedPreferences(activity);
        return sharedPreferences.getString("CLIENT_LOGIN_PASSWORD", null);
    }

    public static void saveRestDeviceToken(Application application, String data_Value) {
        setSharedPreferences(application);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("REST_DEVICE_TOKEN", data_Value);
            editor.apply();
        }
    }

    public static String loadRestDeviceToken(Activity activity) {
        setSharedPreferences(activity);
        return sharedPreferences.getString("REST_DEVICE_TOKEN", null);
    }

    public static void saveClientDeviceToken(Application application, String data_Value) {
        setSharedPreferences(application);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("CLIENT_DEVICE_TOKEN", data_Value);
            editor.apply();
        }
    }

    public static String loadClientDeviceToken(Activity activity) {
        setSharedPreferences(activity);
        return sharedPreferences.getString("CLIENT_DEVICE_TOKEN", null);
    }

    public static void saveClientApiToken(Application application, String data_Value) {
        setSharedPreferences(application);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("CLIENT_API_TOKEN", data_Value);
            editor.apply();
        }
    }

    public static String loadClientApiToken(Activity activity) {
        setSharedPreferences(activity);
        return sharedPreferences.getString("CLIENT_API_TOKEN", null);
    }

    public static String loadRestId(Activity activity) {
        setSharedPreferences(activity);
        return sharedPreferences.getString("REST_ID", null);
    }

    public static void saveSelectedRestId(Activity activity, String data_Value) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("SELECTED_REST_ID", data_Value);
            editor.apply();
        }
    }

    public static String loadSelectedRestId(Activity activity) {
        setSharedPreferences(activity);
        return sharedPreferences.getString("SELECTED_REST_ID", null);
    }

    public static void saveClientOrderSumPrice(Activity activity, String data_Value) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("CLIENT_ORDER_SUM_PRICE", data_Value);
            editor.apply();
        }
    }

    public static String loadClientOrderSumPrice(Activity activity) {
        setSharedPreferences(activity);
        return sharedPreferences.getString("CLIENT_ORDER_SUM_PRICE", null);
    }

    public static void saveSelectedRestDeliveryCost(Activity activity, String data_Value) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("SELECTED_REST_DELIVERY_COST", data_Value);
            editor.apply();
        }
    }

    public static String loadSelectedRestDeliveryCost(Activity activity) {
        setSharedPreferences(activity);
        return sharedPreferences.getString("SELECTED_REST_DELIVERY_COST", null);
    }



    public static String loadData(Activity activity, String data_Key ) {
        setSharedPreferences(activity);
        return sharedPreferences.getString(data_Key, null);
    }

    public static boolean loadBoolean(Activity activity, String data_Key) {
        setSharedPreferences(activity);

        return sharedPreferences.getBoolean(data_Key, false);
    }

    public static void clean(Activity activity) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
        }
    }

    public static void clean(Application application) {
        setSharedPreferences(application);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
        }
    }

}
