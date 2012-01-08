package com.rmwebfx.common.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class DialogHelper {
	// Simple: Message and a simple button that closes the dialog
	public static AlertDialog simpleDialog(Activity activity, String message, String button) {
		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setMessage(message)
		       .setCancelable(false)
		       .setNegativeButton(button, new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		               dialog.cancel();
		           }
		       });
		AlertDialog finalDialog = builder.create();
		
		return finalDialog;
	}
}