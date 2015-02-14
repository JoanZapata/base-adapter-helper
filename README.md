[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-base--adapter--helper-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/1331)

[![Overview](https://raw.github.com/JoanZapata/base-adapter-helper/master/header.png)](https://github.com/JoanZapata/base-adapter-helper/blob/5465bea778f098a059390c441416c501e599cc10/base-adapter-helper-sample/src/main/java/com/joanzapata/android/twitter/TwitterActivity.java#L44-L57)

[![Alt](http://developer.android.com/images/brand/en_app_rgb_wo_45.png)](https://play.google.com/store/apps/details?id=com.joanzapata.android.twitter)

> **BaseAdapterHelper** aims to make BaseAdapter's ```getView()``` method much more readable, getting rid of the [ViewHolder pattern](http://www.jmanzano.es/blog/?p=166) boilerplate code.

> **QuickAdapter** allows you to shorten the code of most usual ```BaseAdapter```, taking care of implementing everything for you based on your data list. You only need to focus on the mapping between your view and your model.

# Get it

BaseAdapter Helper is now **[available on Maven Central](http://search.maven.org/remotecontent?filepath=com/joanzapata/android/base-adapter-helper/1.1.11/base-adapter-helper-1.1.11.jar)**.

```xml
<dependency>
  <groupId>com.joanzapata.android</groupId>
  <artifactId>base-adapter-helper</artifactId>
  <version>1.1.11</version>
</dependency>
```

or

```groovy
compile 'com.joanzapata.android:base-adapter-helper:1.1.11'
```

# Features

## BaseAdapterHelper

* ```setText()``` Calls ```setText(String)``` on any TextView.
* ```setAlpha()``` Calls ```setAlpha(float)``` on any View.
* ```setVisible()``` Calls ```setVisibility(int)``` on any View.
* ```linkify()``` Calls ```Linkify.addLinks(view, ALL)``` on any TextView.
* ```setTypeface()``` Calls ```setTypeface(Typeface)``` on any TextView.
* ```setProgress()``` Calls ```setProgress(int)``` on any ProgressBar.
* ```setMax()``` Calls ```setMax(int)``` on any ProgressBar.
* ```setRating()``` Calls ```setRating(int)``` on any RatingBar.
* ```setImageResource()``` Calls ```setImageResource(int)``` on any ImageView.
* ```setImageDrawable()``` Calls ```setImageDrawable(Drawable)``` on any ImageView.
* ```setImageBitmap()``` Calls ```setImageBitmap(Bitmap)``` on any ImageView.
* ```setImageUrl()``` Uses [Square's Picasso](http://square.github.io/picasso/) to download the image and put it in an ImageView.
* ```setImageBuilder()``` Associates a [Square's Picasso](http://square.github.io/picasso/) RequestBuilder to an ImageView.
* ```setOnClickListener()```
* ```setOnTouchListener()```
* ```setOnLongClickListener()```
* ```setTag()```
* ```setChecked()```
* ```setAdapter()```
* If you need something else, please [report an issue](https://github.com/JoanZapata/base-adapter-helper/issues). Any contribution is welcome! In the meanwhile, you can still use ```getView(id)```on the adapter to do custom operations.

## QuickAdapter

[![Overview](https://raw.github.com/JoanZapata/base-adapter-helper/master/progress_sample.png)](https://github.com/JoanZapata/base-adapter-helper/blob/5465bea778f098a059390c441416c501e599cc10/base-adapter-helper-sample/src/main/java/com/joanzapata/android/twitter/TwitterActivity.java#L85)

* ```showIndeterminateProgress(boolean)``` Shows/hides an indeterminate progress at the end of the list.

# Performance

The performances using the BaseAdapter Helper are **equivalent** to the ones of the **ViewHolder pattern**.
It has been tested on **thousands items list** of **complex item structure** with more than **30 views to be adapted** on each item, and the ```getView()``` method has shown ```similar execution times```.

# License

```
Copyright 2013 Joan Zapata

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

This project uses Picasso from Square, which is licensed under the same
license as this project. You can find the project page at

    http://square.github.io/picasso/
    
The sample application uses Twitter4j by Yusuke Yamamoto, 
ActionBarSherlock by Jake Wharton, and AndroidAnnotations by Pierre-Yves Ricau, 
all of them being licensed under the Apache license version 2.0.
```
