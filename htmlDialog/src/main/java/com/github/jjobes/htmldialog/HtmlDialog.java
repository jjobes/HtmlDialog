package com.github.jjobes.htmldialog;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

/**
 * <p>This class contains methods for the library client to create
 * a new {@code HtmlDialog}.</p>
 *
 * <p>It also implements a Builder API that offers more convenient
 * object creation.</p>
 *
 * @author jjobes
 *
 */
public class HtmlDialog
{
    private FragmentManager mFragmentManager;
    private HtmlDialogListener mListener;
    private int mHtmlResId;
    private String mTitle;
    private boolean mShowNegativeButton;
    private String mNegativeButtonText;
    private boolean mShowPositiveButton;
    private String mPositiveButtonText;
    private boolean mCancelable = true;

    /**
     * The sole constructor for {@code HtmlDialog}.
     *
     * @param fm  The {@code FragmentManager} from the calling activity that is used
     *            internally to show the {@code DialogFragment}.
     */
    public HtmlDialog(FragmentManager fm)
    {
        // See if there are any DialogFragments from the FragmentManager
        FragmentTransaction ft = fm.beginTransaction();
        Fragment prev = fm.findFragmentByTag(HtmlDialogFragment.TAG_HTML_DIALOG_FRAGMENT);

        // Remove if found
        if (prev != null)
        {
            ft.remove(prev);
            ft.commit();
        }

        mFragmentManager = fm;
    }

    /**
     * <p>Sets the listener that will inform the client of the user's
     * action.</p>
     *
     * <p>This is not required.</p>
     *
     * @param listener
     */
    public void setListener(HtmlDialogListener listener)
    {
        mListener = listener;
    }

    /**
     * Sets the resource Id of the html file to be shown.
     *
     * @param htmlResId
     */
    public void setHtmlResId(int htmlResId)
    {
        mHtmlResId = htmlResId;
    }

    /**
     * Sets the title of the dialog.
     *
     * @param title
     */
    public void setTitle(String title)
    {
        mTitle = title;
    }

    /**
     * <p>Sets whether or not to show the negative button at
     * the bottom of the dialog.</p>
     *
     * <p>By default, no negative button is shown.</p>
     *
     * <p><b>Note:</b> If you show the negative button, make sure
     * to call {@link #setNegativeButtonText(String)}.</p>
     *
     * @param showNegativeButton
     */
    public void setShowNegativeButton(boolean showNegativeButton)
    {
        mShowNegativeButton = showNegativeButton;
    }

    /**
     * Sets the text to be displayed on the negative button.
     *
     * @param negativeButtonText
     */
    public void setNegativeButtonText(String negativeButtonText)
    {
        mNegativeButtonText = negativeButtonText;
    }

    /**
     * <p>Sets whether or not to show the positive button at
     * the bottom of the dialog.</p>
     *
     * <p>By default, no positive button is shown.</p>
     *
     * <p><b>Note:</b> If you show the positive button, make sure
     * to call {@link #setPositiveButtonText(String)}.</p>
     *
     * @param showPositiveButton
     */
    public void setShowPositiveButton(boolean showPositiveButton)
    {
        mShowPositiveButton = showPositiveButton;
    }

    /**
     * Sets the text to be displayed on the positive button.
     *
     * @param positiveButtonText
     */
    public void setPositiveButtonText(String positiveButtonText)
    {
        mPositiveButtonText = positiveButtonText;
    }

    /**
     * Sets whether or not the dialog is cancelable.
     *
     * @param cancelable
     */
    public void setCancelable(boolean cancelable)
    {
        mCancelable = cancelable;
    }

    /**
     * Shows the dialog to the user. Make sure to call
     * {@link #setHtmlResId(int)} before calling this.
     */
    public void show()
    {
        HtmlDialogFragment dialogFragment =
                HtmlDialogFragment.newInstance(
                        mListener,
                        mHtmlResId,
                        mTitle,
                        mShowNegativeButton,
                        mNegativeButtonText,
                        mShowPositiveButton,
                        mPositiveButtonText,
                        mCancelable);

        dialogFragment.show(mFragmentManager,
                HtmlDialogFragment.TAG_HTML_DIALOG_FRAGMENT);
    }

    /**
     * The following implements the builder API to simplify
     * creation and display of the dialog by allowing for
     * chaining of its set methods.
     */
    public static class Builder
    {
        private FragmentManager fm;

        private HtmlDialogListener listener;
        private int htmlResId;
        private String title;
        private boolean showNegativeButton;
        private String negativeButtonText;
        private boolean showPositiveButton;
        private String positiveButtonText;
        private boolean cancelable = true;

        public Builder(FragmentManager fm)
        {
            this.fm = fm;
        }

        /**
         * @see HtmlDialog#setListener(HtmlDialogListener)
         */
        public Builder setListener(HtmlDialogListener listener)
        {
            this.listener = listener;

            return this;
        }

        /**
         * @see HtmlDialog#setHtmlResId(int)
         */
        public Builder setHtmlResId(int htmlResId)
        {
            this.htmlResId = htmlResId;

            return this;
        }

        /**
         * @see HtmlDialog#setTitle(String)
         */
        public Builder setTitle(String title)
        {
            this.title = title;

            return this;
        }

        /**
         * @see HtmlDialog#setShowNegativeButton(boolean)
         */
        public Builder setShowNegativeButton(boolean showNegativeButton)
        {
            this.showNegativeButton = showNegativeButton;

            return this;
        }

        /**
         * @see HtmlDialog#setNegativeButtonText(String)
         */
        public Builder setNegativeButtonText(String negativeButtonText)
        {
            this.negativeButtonText = negativeButtonText;

            return this;
        }

        /**
         * @see HtmlDialog#setShowPositiveButton(boolean)
         */
        public Builder setShowPositiveButton(boolean showPositiveButton)
        {
            this.showPositiveButton = showPositiveButton;

            return this;
        }

        /**
         * @see HtmlDialog#setPositiveButtonText(String)
         */
        public Builder setPositiveButtonText(String positiveButtonText)
        {
            this.positiveButtonText = positiveButtonText;

            return this;
        }

        /**
         * @see HtmlDialog#setCancelable(boolean)
         */
        public Builder setCancelable(boolean cancelable)
        {
            this.cancelable = cancelable;

            return this;
        }

        /**
         * <p>Build and return an {@code HtmlDialog} object based on the previously
         * supplied parameters.</p>
         *
         * <p>You should call {@code show()} immediately after this.</p>
         *
         * @return
         */
        public HtmlDialog build()
        {
            HtmlDialog dialog = new HtmlDialog(fm);
            dialog.setListener(listener);
            dialog.setHtmlResId(htmlResId);
            dialog.setTitle(title);
            dialog.setShowNegativeButton(showNegativeButton);
            dialog.setNegativeButtonText(negativeButtonText);
            dialog.setShowPositiveButton(showPositiveButton);
            dialog.setPositiveButtonText(positiveButtonText);
            dialog.setCancelable(cancelable);

            return dialog;
        }
    }
}
