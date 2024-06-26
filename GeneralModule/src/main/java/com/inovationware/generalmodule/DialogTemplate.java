package com.inovationware.generalmodule;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public abstract class DialogTemplate {

    private String title = "";
    public void SetTitle(String title){
        this.title = title;
    }
    private String message = "Proceed?";
    public void setMessage(String message){
        this.message = message;
    }
    private String positiveButtonText = "Yes";
    public void setPositiveButtonText(String positiveButtonText){
        this.positiveButtonText = positiveButtonText;
    }
    private String negativeButtonText = "No";
    public void setNegativeButtonText(String negativeButtonText){
        this.negativeButtonText = negativeButtonText;
    }
    private boolean negativeButton = true;


    public abstract void positiveButtonAction();
    public abstract void negativeButtonAction();

    public void show(Context context) {
        buildDialog(context).create().show();
    }
    public void showNegativeButton(boolean value){
        negativeButton = value;
    }

    private AlertDialog.Builder buildDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(positiveButtonText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                positiveButtonAction();
            }
        });
        if (negativeButton)
            builder.setNegativeButton(negativeButtonText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    negativeButtonAction();
                }
            });
        return builder;
    }

}
