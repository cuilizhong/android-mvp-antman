package com.wmm.antman.utils;

import android.content.Context;
import android.content.Intent;

/**
 * Created by ming on 2016/4/13.
 */
public class IntentUtils {

    public static void ToStartActivity(Context context, Class activityClass) {
        Intent intent = new Intent(context, activityClass);
        context.startActivity(intent);
    }
}
