package com.github.jjobes.htmldialog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;

/**
 * <p>A class that asynchronously loads an HTML file, displays it
 * in a WebView and displays the WebView in a {@code DialogFragment}.</p>
 *
 * <p>{@code HtmlDialogFragment} is created and shown by {@link HtmlDialog}.</p>
 *
 * @author jjobes
 *
 */
public class HtmlDialogFragment extends DialogFragment
{
    private static final String TAG = "HtmlDialogFragment";

    public static final String TAG_HTML_DIALOG_FRAGMENT = "tagHtmlDialogFragment";

    private static final String KEY_HTML_RES_ID = "keyHtmlResId";
    private static final String KEY_TITLE = "keyTitle";
    private static final String KEY_SHOW_NEGATIVE_BUTTON = "keyShowNegativeButton";
    private static final String KEY_NEGATIVE_BUTTON_TEXT = "keyNegativeButtonText";
    private static final String KEY_SHOW_POSITIVE_BUTTON = "keyShowPositiveButton";
    private static final String KEY_POSITIVE_BUTTON_TEXT = "keyPositiveButtonText";
    private static final String KEY_CANCELABLE = "keyCancelable";

    private WebView mWebView;
    private ProgressBar mProgressBar;

    private AsyncTask<Void, Void, String> mHtmlLoader;

    // These values are originally set by the client in HtmlDialog
    private static HtmlDialogListener mListener;

    private int mHtmlResId;
    private String mTitle;
    private boolean mShowNegativeButton;
    private String mNegativeButtonText;
    private boolean mShowPositiveButton;
    private String mPositiveButtonText;
    private boolean mCancelable;

    /**
     * <p>Return a new instance of {@code HtmlDialogFragment} with its bundle
     * filled with the incoming arguments.</p>
     *
     * <p>Called by {@link HtmlDialog#show()}.</p>
     *
     * @param listener
     * @param htmlResId
     * @param title
     * @param showNegativeButton
     * @param negativeButtonText
     * @param showPositiveButton
     * @param positiveButtonText
     * @param cancelable
     * @return
     */
    public static HtmlDialogFragment newInstance(HtmlDialogListener listener,
            int htmlResId, String title,
            boolean showNegativeButton, String negativeButtonText,
            boolean showPositiveButton, String positiveButtonText,
            boolean cancelable)
    {
        mListener = listener;

        HtmlDialogFragment fragment = new HtmlDialogFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(KEY_HTML_RES_ID, htmlResId);
        bundle.putString(KEY_TITLE, title);
        bundle.putBoolean(KEY_SHOW_NEGATIVE_BUTTON, showNegativeButton);
        bundle.putString(KEY_NEGATIVE_BUTTON_TEXT, negativeButtonText);
        bundle.putBoolean(KEY_SHOW_POSITIVE_BUTTON, showPositiveButton);
        bundle.putString(KEY_POSITIVE_BUTTON_TEXT, positiveButtonText);
        bundle.putBoolean(KEY_CANCELABLE, cancelable);

        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        unpackBundle();
    }

    private void unpackBundle()
    {
        Bundle args = getArguments();

        mHtmlResId = args.getInt(KEY_HTML_RES_ID);
        mTitle = args.getString(KEY_TITLE);
        mShowNegativeButton = args.getBoolean(KEY_SHOW_NEGATIVE_BUTTON);
        mNegativeButtonText = args.getString(KEY_NEGATIVE_BUTTON_TEXT);
        mShowPositiveButton = args.getBoolean(KEY_SHOW_POSITIVE_BUTTON);
        mPositiveButtonText = args.getString(KEY_POSITIVE_BUTTON_TEXT);
        mCancelable = args.getBoolean(KEY_CANCELABLE);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();

        if (mHtmlLoader != null)
        {
            mHtmlLoader.cancel(true);
        }
    }

    @SuppressLint("InflateParams")
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        setCancelable(mCancelable);

        View content = LayoutInflater.from(getActivity()).inflate(R.layout.html_fragment, null);

        mWebView = (WebView) content.findViewById(R.id.webView);
        mProgressBar = (ProgressBar) content.findViewById(R.id.progressBar);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        if (mTitle != null)
            builder.setTitle(mTitle);

        builder.setView(content);

        if (mShowNegativeButton && mNegativeButtonText != null)
        {
            builder.setNegativeButton(mNegativeButtonText,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            if (mListener != null)
                            {
                                mListener.onNegativeButtonPressed();
                            }

                            dialog.dismiss();
                        }
                    });
        }

        if (mShowPositiveButton && mPositiveButtonText != null)
        {
            builder.setPositiveButton(mPositiveButtonText,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            if (mListener != null)
                            {
                                mListener.onPositiveButtonPressed();
                            }

                            dialog.dismiss();
                        }
                    });
        }

        return builder.create();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        loadHtml();
    }

    // Load the HTML file asynchronously in case of a very large file.
    private void loadHtml()
    {
        mHtmlLoader = new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... params)
            {
                InputStream is = getActivity().getResources().openRawResource(mHtmlResId);
                BufferedReader br = new BufferedReader(new InputStreamReader(is));

                String line;
                StringBuilder sb = new StringBuilder();

                try
                {
                    while ((line = br.readLine()) != null)
                    {
                        sb.append(line).append("\n");
                    }

                    br.close();
                }
                catch (IOException e)
                {
                    Log.d(TAG, "IOException caught in loadHtml()");
                }

                return sb.toString();
            }

            @Override
            protected void onPostExecute(String htmlString)
            {
                if (getActivity() == null || isCancelled())
                {
                    return;
                }

                mProgressBar.setVisibility(View.INVISIBLE);
                mWebView.setVisibility(View.VISIBLE);
                mWebView.loadDataWithBaseURL(null, htmlString, "text/html", "utf-8", null);
                mHtmlLoader = null;
            }

        }.execute();
    }

    /**
     * Called when the user clicks outside the dialog or presses the <b>Back</b>
     * button.
     */
    @Override
    public void onCancel(DialogInterface dialog)
    {
        super.onCancel(dialog);

        if (mListener != null)
        {
            mListener.onDialogCancel();
        }
    }
}
