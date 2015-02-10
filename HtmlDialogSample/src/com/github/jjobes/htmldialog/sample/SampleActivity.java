package com.github.jjobes.htmldialog.sample;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

import com.github.jjobes.htmldialog.HtmlDialog;
import com.github.jjobes.htmldialog.HtmlDialogListener;

/**
 * Sample test class for HtmlDialog.
 *
 * @author jjobes
 *
 */
public class SampleActivity extends FragmentActivity
{
    private HtmlDialogListener listener = new HtmlDialogListener() {

        @Override
        public void onNegativeButtonPressed()
        {
            Toast.makeText(
                    SampleActivity.this,
                    "onNegativeButtonPressed()",
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPositiveButtonPressed()
        {
            Toast.makeText(
                    SampleActivity.this,
                    "onPositiveButtonPressed()",
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onDialogCancel()
        {
            Toast.makeText(
                    SampleActivity.this,
                    "onDialogCancel()",
                    Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
    }

    public void onClick(View view)
    {
        // Traditional method:

//        HtmlDialog dialog = new HtmlDialog(getFragmentManager());
//        dialog.setListener(listener);
//        dialog.setHtmlResId(R.raw.licenses);
//        dialog.setTitle(getResources().getString(R.string.title));
//        dialog.setShowNegativeButton(true);
//        dialog.setNegativeButtonText(getResources().getString(R.string.cancel));
//        dialog.setShowPositiveButton(true);
//        dialog.setPositiveButtonText(getResources().getString(R.string.ok));
//        //dialog.setCancelable(false);
//        dialog.show();

        // Builder method:

        new HtmlDialog.Builder(getFragmentManager())
            .setListener(listener)
            .setHtmlResId(R.raw.licenses)
            .setTitle(getResources().getString(R.string.title))
            .setShowNegativeButton(true)
            .setNegativeButtonText(getResources().getString(R.string.cancel))
            .setShowPositiveButton(true)
            .setPositiveButtonText(getResources().getString(R.string.ok))
            //.setCancelable(false)
            .build()
            .show();
    }
}
