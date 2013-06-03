> **BaseAdapter Helper** aims to make BaseAdapter's ```getView()``` method much more readable, getting rid of the [ViewHolder pattern](http://www.jmanzano.es/blog/?p=166) boilerplate code.

# Get it

BaseAdapter Helper is now **available on Maven Central**.

```
<dependency>
  <groupId>com.joanzapata.android</groupId>
  <artifactId>base-adapter-helper</artifactId>
  <version>1.0.1</version>
</dependency>
```

# Before

```java
@Override
public View getView(int position, View v, ViewGroup parent) {
    // Keeps reference to avoid future findViewById()
    ContactsViewHolder viewHolder;

    if (v == null) {
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        v = li.inflate(R.layout.contact_row, parent, false);

        viewHolder = new ContactsViewHolder();
        viewHolder.txName = (TextView) v.findViewById(R.id.tvName);
        viewHolder.txEmails = (TextView) v.findViewById(R.id.tvEmails);
        viewHolder.txPhones = (TextView) v.findViewById(R.id.tvNumbers);

        v.setTag(viewHolder);
    } else {
        viewHolder = (ContactsViewHolder) v.getTag();
    }

    Contact contact = contacts.get(position);
    if (contact != null) {
        viewHolder.txName.setText(contact.getName());
        viewHolder.txEmails.setText(contact.getEmails().toString());
        viewHolder.txPhones.setText(contact.getNumbers().toString());
    }
    return v;
}

static class ContactsViewHolder {
    TextView txName;
    TextView txEmails;
    TextView txPhones;
}
```

> [Source](http://www.jmanzano.es/blog/?p=166)

# After

```java
@Override
public View getView(int position, View convertView, ViewGroup parent) {
    Contact contact = contacts.get(position);
    return BaseAdapterHelper.get(context, convertView, parent, R.layout.contact_row)
            .setText(R.id.tvName, contact.getName())
            .setText(R.id.tvEmails, contact.getEmails().toString())
            .setText(R.id.tvNumbers, contact.getNumbers().toString())
            .getView();
}
```

# Features

For now you can use:

* ```setText()``` Calls ```setText(String)``` on any TextView.
* ```setAlpha()``` Calls ```setAlpha(float)``` on any View.
* ```setImageResource()``` Calls ```setImageResource(int)``` on any ImageView.
* ```setImageDrawable()``` Calls ```setImageDrawable(Drawable)``` on any ImageView.
* ```setImageBitmap()``` Calls ```setImageBitmap(Bitmap)``` on any ImageView.
* ```setImageUrl()``` Uses [Square's Picasso](http://square.github.io/picasso/) to download the image and put it in an ImageView.
* ```setImageBuilder()``` Associates a [Square's Picasso](http://square.github.io/picasso/) RequestBuilder to an ImageView.
* If you need something else, please [report an issue](https://github.com/JoanZapata/base-adapter-helper/issues). Any contribution is welcome!

Complete sample:

```java
@Override
public View getView(int position, View convertView, ViewGroup parent) {
    return BaseAdapterHelper.get(context, convertView, parent, R.layout.contact_row)
            .setText(R.id.tvName, contact.getName())
            .setAlpha(R.id.ivIcon, contact.isEnabled() ? 1f : 0f)
            .setImageUrl(R.id.ivIcon, contact.getPictureUrl()) // or
            .setImageBuilder(R.id.ivIcon, Picasso.with(context).load(model.getUrl()).resize(100, 100))
            .getView();
}
```

# Not covered features

If you need a feature which is not yet covered by BaseAdapterHelper, there is workaround.

```java
 @Override
public View getView(int position, View convertView, ViewGroup parent) {
    ImageListModel model = getItem(position);
    BaseAdapterHelper helper = BaseAdapterHelper.get(context, convertView, parent, R.layout.list_item)
            .set...
            .set...;
    TextView textView = helper.getView(R.id.listText);
    textView.setText("Use this as a last resort");
    return helper.getView();
}
```

Use it as the very last resort, and please create [an issue](https://github.com/JoanZapata/base-adapter-helper/issues).

# Limitations

There is a slight performance loss compared to the hard coded version, references to views in the ```ViewHolder``` are held in a ```SparseArray``` instead of being directly accessed as class fields.

# Licence

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
```
