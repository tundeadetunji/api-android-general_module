package io.github.tundeadetunji.android;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class Device {

    //Context context;

/*
    public Machine(Context context) {
        this.context = context;
    }
*/

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void clipboardSetText(Context context, String text_to_copy) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(context.CLIPBOARD_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            clipboard.clearPrimaryClip();
        }
        ClipData clip = ClipData.newPlainText("", text_to_copy);
        clipboard.setPrimaryClip(clip);
    }

    public static String clipboardGetText(Context context) {
        String clipboardText;
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(context.CLIPBOARD_SERVICE);

        clipboardText = clipboard.getPrimaryClip().getItemAt(0)
                .coerceToText(context).toString();

        return clipboardText;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void setTextToClipboard(Context context, String text_to_copy) {clipboardSetText(context, text_to_copy);
    }

    public static String getTextFromClipboard(Context context) {
        return clipboardGetText(context);
    }

    public static boolean thereIsInternet(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        return isConnected;
    }
}
