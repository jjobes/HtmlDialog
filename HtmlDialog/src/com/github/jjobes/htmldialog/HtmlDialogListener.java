package com.github.jjobes.htmldialog;

/**
 * This listener class informs the library client when the
 * user presses the negative button, presses the positive
 * button, or cancels the dialog.
 *
 * @author jjobes
 *
 */
public abstract class HtmlDialogListener
{
    /**
     * Informs the client when the user presses the
     * negative button.
     */
    public abstract void onNegativeButtonPressed();

    /**
     * Informs the client when the user presses the
     * positive button.
     */
    public abstract void onPositiveButtonPressed();

    /**
     * <p>Informs the client when the user touches outside
     * the dialog or presses the Back button.</p>
     *
     * <p>This override is optional.</p>
     */
    public void onDialogCancel() {};
}
