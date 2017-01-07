package com.explem.smalllemonade.tiaozhuan;

/**
 * Created by johpo on 2016/12/30 0030.
 */
import android.content.Context;
import android.content.Intent;

import com.explem.smalllemonade.WebViewActivity;

public class TiaoZhuan {
    public static void tiaoZhuan(Context context, String url) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }
}
