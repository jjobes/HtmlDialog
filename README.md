HtmlDialog
===================

HtmlDialog is an Android library that simplifies the display of HTML in a DialogFragment. It is useful for displaying Open Source Licenses, EULAs, Terms of Service pages, etc. Tested on Android 4.0+.

<img src="https://raw.github.com/jjobes/HtmlDialog/master/screenshots/1.png" width="270" style="margin-right:10px;">

Setup
=====

**Eclipse/ADT**:
From your main project, simply reference the HtmlDialog library:

Right-click on your main project name and select Properties.

Select Android from the left column.

Click Add.

Select HtmlDialog.

Click Apply and then OK.

**Android Studio**:
Coming soon.

How to Use
==========
(See [SampleActivity](https://github.com/jjobes/HtmlDialog/blob/master/HtmlDialogSample/src/com/github/jjobes/htmldialog/sample/SampleActivity.java) for a more complete example)

``` java
new HtmlDialog.Builder(getFragmentManager())
    .setHtmlResId(R.raw.licenses)
    .build()
    .show();
```

**To set the resource Id of your HTML file:**

``` java
.setHtmlResId(int)
```

This is required. Pass in the resource Id of an HTML file that resides /res/raw/ in your main project. In the above example, `R.raw.licenses` refers to /res/raw/licenses.html in the main project.

**To set the title of the dialog:**

``` java
.setTitle(String)
```
By default, the dialog will have no title.

**To show a negative button at the bottom of the dialog:**

``` java
.setShowNegativeButton(true)
```

By default, no negative button is shown. 

**To specify the negative button text:**

``` java
.setNegativeButtonText(String)
```

Make sure to call this if you are showing the negative button.

**To show a positive button at the bottom of the dialog:**

``` java
.setShowPositiveButton(true)
```
By default, no positive button is shown.

**To specify the positive button text:**

``` java
.setPositiveButtonText(String)
```
Make sure to call this if you are showing the positive button.

**To force the user to click a button:**

``` java
.setCancelable(false)
```
This is useful if you are displaying a license agreement.

**To specify a listener:**

``` java
.setListener(new HtmlDialogListener() {

    @Override
    public void onNegativeButtonPressed()
    {
        
    }

    @Override
    public void onPositiveButtonPressed()
    {
        
    }

    @Override
    public void onDialogCancel()
    {
        // This override is optional.
        // Called when the user presses the Back button
        // or touches outside the dialog.
    }
})
```

Contributing
============
Contributions are welcome. Please open up an issue in GitHub or submit a PR.

Changelog
=========

### v1.1.0

* removed setShowCloseButton() and setCloseButtonText()
* added setShowNegativeButton(), setNegativeButtonText()
* added setShowPositiveButton(), setPositiveButtonText()
* added setCancelable()
* added setListener()
* updated SampleActivity

### v1.0.0

* First release

License
=======
Licensed under the [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html)

Acknowledgements
================
This project is based off of Adam Speakman's [AndroidLicensesPage](https://github.com/adamsp/AndroidLicensesPage).