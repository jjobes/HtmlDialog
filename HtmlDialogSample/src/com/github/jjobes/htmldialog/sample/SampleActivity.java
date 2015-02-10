package com.github.jjobes.htmldialog.sample;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.github.jjobes.htmldialog.HtmlDialog;

/**
 * Sample test class for HtmlDialog.
 *
 * @author jjobes
 *
 */
public class SampleActivity extends FragmentActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
    }

    public void onClick(View view)
    {
        // Traditional method:

//        HtmlDialog dialog = new HtmlDialog(getSupportFragmentManager());
//        dialog.setHtmlResId(R.raw.licenses);
//        dialog.setTitle(getResources().getString(R.string.title));
//        dialog.setShowCloseButton(true);
//        dialog.setCloseButtonText(getResources().getString(R.string.close));
//        dialog.show();

        // Builder method:

        new HtmlDialog.Builder(getFragmentManager())
            .setHtmlResId(R.raw.licenses)
            .setTitle(getResources().getString(R.string.title))
            .setShowCloseButton(true)
            .setCloseButtonText(getResources().getString(R.string.close))
            .build()
            .show();
    }
}
