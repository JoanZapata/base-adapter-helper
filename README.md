![Overview](https://raw.github.com/JoanZapata/base-adapter-helper/master/header.png)


> **BaseAdapterHelper** aims to make BaseAdapter's ```getView()``` method much more readable, getting rid of the [ViewHolder pattern](http://www.jmanzano.es/blog/?p=166) boilerplate code.

> **QuickAdapter** allows you to shorten the code of most usual ```BaseAdapter```, taking care of implementing everything for you based on your data list. You only need to focus on the mapping between your view and your model.

# Get it

BaseAdapter Helper is now **available on Maven Central**.

```xml
<dependency>
  <groupId>com.joanzapata.android</groupId>
  <artifactId>base-adapter-helper</artifactId>
  <version>1.1.0</version>
</dependency>
```

# Features

## BaseAdapterHelper

* ```setText()``` Calls ```setText(String)``` on any TextView.
* ```setAlpha()``` Calls ```setAlpha(float)``` on any View.
* ```setVisible()``` Calls ```setVisibility(int)``` on any View.
* ```linkify()``` Calls ```Linkify.addLinks(view, ALL)``` on any TextView.
* ```setImageResource()``` Calls ```setImageResource(int)``` on any ImageView.
* ```setImageDrawable()``` Calls ```setImageDrawable(Drawable)``` on any ImageView.
* ```setImageBitmap()``` Calls ```setImageBitmap(Bitmap)``` on any ImageView.
* ```setImageUrl()``` Uses [Square's Picasso](http://square.github.io/picasso/) to download the image and put it in an ImageView.
* ```setImageBuilder()``` Associates a [Square's Picasso](http://square.github.io/picasso/) RequestBuilder to an ImageView.
* If you need something else, please [report an issue](https://github.com/JoanZapata/base-adapter-helper/issues). Any contribution is welcome! In the meanwhile, you can still use ```getView(id)```on the adapter to do custom operations.

## QuickAdapter

![Overview](https://raw.github.com/JoanZapata/base-adapter-helper/master/progress_sample.png)

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
