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
    private int mHtmlResId;
    private String mTitle;
    private boolean mShowCloseButton;
    private String mCloseButtonText;

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
     * <p>Sets whether or not to show the Close button at
     * the bottom of the dialog.</p>
     *
     * <p>By default, no Close button is shown.</p>
     *
     * <p><b>Note:</b> If you show the Close button, make sure
     * to call {@link #setCloseButtonText(String)}.</p>
     *
     * @param showCloseButton
     */
    public void setShowCloseButton(boolean showCloseButton)
    {
        mShowCloseButton = showCloseButton;
    }

    /**
     * Sets the text to be displayed on the Close button.
     *
     * @param closeButtonText
     */
    public void setCloseButtonText(String closeButtonText)
    {
        mCloseButtonText = closeButtonText;
    }

    /**
     * Shows the dialog to the user. Make sure to call
     * {@link #setHtmlResId(int)} before calling this.
     */
    public void show()
    {
        HtmlDialogFragment dialogFragment =
                HtmlDialogFragment.newInstance(
                        mHtmlResId,
                        mTitle,
                        mShowCloseButton,
                        mCloseButtonText);

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

        private int htmlResId;
        private String title;
        private boolean showCloseButton;
        private String closeButtonText;

        public Builder(FragmentManager fm)
        {
            this.fm = fm;
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
         * @see HtmlDialog#setShowCloseButton(boolean)
         */
        public Builder setShowCloseButton(boolean showCloseButton)
        {
            this.showCloseButton = showCloseButton;

            return this;
        }

        /**
         * @see HtmlDialog#setCloseButtonText(String)
         */
        public Builder setCloseButtonText(String closeButtonText)
        {
            this.closeButtonText = closeButtonText;

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
            dialog.setHtmlResId(htmlResId);
            dialog.setTitle(title);
            dialog.setShowCloseButton(showCloseButton);
            dialog.setCloseButtonText(closeButtonText);

            return dialog;
        }
    }
}
