# 1.1.12
* ```setOnItemClickListener```
* ```setOnItemLongClickListener```
* ```setOnItemSelectedListener```

# 1.1.11
* BaseAdapterHelper now allows subclassing.

# 1.1.10
* ```setOnClickListener()```
* ```setOnTouchListener()```
* ```setOnLongClickListener()```
* ```setTag()```
* ```setChecked()```
* ```setAdapter()```

# 1.1.9
* Added ```EnhancedQuickAdapter``` for those who need to know if the view was bound to the same object before or not, during the view convertion.

# 1.1.8
* Automatically enable sub-pixel rendering on texts when calling ```setTypeface()```.

# 1.1.7
* Fixed bug with position not being updated in ```BaseAdapterHelper```

# 1.1.6
* Added ```replaceAll()``` to ```QuickAdapter```

# 1.1.5

* ```setTextColorRes(view, colorRes)```
* ```setTextColor(view, color)```
* ```setBackgroundColorRes(view, colorRes)```
* ```setBackgroundColor(view, color)```

# 1.1.4

* Added ability to subclass `BaseAdapterHelper` and set it in the `QuickAdapter`

# 1.1.3

* ```setProgress(view, progress)```
* ```setProgress(view, progress, max)```
* ```setMax```
* ```setRating(view, rating)```
* ```setRating(view, rating, max)```

# 1.1.2

* Updated Picasso to 2.1.1
* Added ```setTypeface()``` and ```setTypefaces()``` methods
* Fixed bug

# 1.1.1

* Added ```get()```, ```set()```, ```remove()``` and ```contains()``` to ```QuickAdapter```
* Added ```getPosition()``` to ```BaseAdapterHelper``` when used with ```QuickAdapter```

# 1.0.2

* Added ```getView(int)```
* Added ```setImageUrl(int, String)```
* Added ```setImageBuilder(int, RequestBuilder)```

# 1.0.3

* Fixed bugs
* Added ```setTypeface(int, Typeface)```
* Added ```setTypeface(Typeface, int...)```
