# Get it

```
<dependency>
  <groupId>com.joanzapata.android</groupId>
  <artifactId>base-adapter-helper</artifactId>
  <version>1.0.0</version>
</dependency>
```

# Use it

### In your ```BaseAdapter``` subclass, instead of writting the usual:

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

### You can now write

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

And that's it!

# Features

For now you can use:

* ```setText()``` Calls setText(String) on any TextView
* ```setAlpha()``` Calls setAlpha(float) on any View

# Impacts

You'll have a slight performance loss compared to the hard coded version. References to views in the ```ViewHolder``` are held in a ```SparseArray``` instead of being directly accessed as class fields.
