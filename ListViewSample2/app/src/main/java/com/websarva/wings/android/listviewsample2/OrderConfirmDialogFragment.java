package com.websarva.wings.android.listviewsample2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

public class OrderConfirmDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Create Dialog Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Set Title of dialog
        builder.setTitle(R.string.dialog_title);
        // set message of dialog
        builder.setMessage(R.string.dialog_msg);
        // set positive button
        builder.setPositiveButton(R.string.dialog_btn_ok, new DialogButtonClickListener());
        // set negative button
        builder.setNegativeButton(R.string.dialog_btn_ng, new DialogButtonClickListener());
        // set neutral button
        builder.setNeutralButton(R.string.dialog_btn_nu, new DialogButtonClickListener());
        // create dialog object
        AlertDialog dialog = builder.create();
        return dialog;
    }

    // Member class that describes the behavior when dialog button is tapped
    private class DialogButtonClickListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            // prepare string variable for Toast
            String msg = "";
            // branch by tapped button
            switch (which) {
                // positive button
                case DialogInterface.BUTTON_POSITIVE:
                    // set message for order
                    msg = getString(R.string.dialog_ok_toast);
                    break;
                // negative button
                case DialogInterface.BUTTON_NEGATIVE:
                    // set message for cancel
                    msg = getString(R.string.dialog_ng_toast);
                    break;
                // neutral button
                case DialogInterface.BUTTON_NEUTRAL:
                    // set message for query
                    msg = getString(R.string.dialog_nu_toast);
                    break;
            }
            // show toast
            Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
        }
    }
}
