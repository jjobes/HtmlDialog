HtmlDialog
===================

HtmlDialog is an Android library that simplifies the display of HTML in a DialogFragment. It is useful for displaying open source licenses, EULAs, terms of service pages, etc. Tested on Android 4.0+.

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
    .setTitle(getResources().getString(R.string.title))
    .setShowCloseButton(true)
    .setCloseButtonText(getResources().getString(R.string.close))
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
By default, the dialog will have no title. You must provide one using `.setTitle()`.

**To show a Close button at the bottom of the dialog:**

``` java
.setShowCloseButton(true)
```

By default, no Close button is shown. You must call `.setShowCloseButton(true)` to show one. 

**To specify the text to display on the Close button:**

``` java
.setCloseButtonText(String)
```

Make sure to call this if you are showing the Close button.


Contributing
============
Contributions are welcome. Please open up an issue in GitHub or submit a PR.

Changelog
=========

### v1.0.0

* First release

License
=======
Licensed under the [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html)

Acknowledgements
================
This project is based off of Adam Speakman's [AndroidLicensesPage](https://github.com/adamsp/AndroidLicensesPage).

