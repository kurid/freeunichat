package com.example.freeunichat.ui;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.freeunichat.R;
import com.example.freeunichat.model.Contact;

import java.util.ArrayList;

public class ContactListAdapter extends BaseAdapter {
    private ArrayList<Contact> contacts;
    private Context context;
    private SparseBooleanArray selectedItemIds;

    public ContactListAdapter(Context context, ArrayList<Contact> contacts) {
        this.contacts = contacts;
        this.context = context;
        this.selectedItemIds = new SparseBooleanArray();
    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int position) {
        return contacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("MyApp", "getView was called: position = " + position + "convertView ?= " + convertView);
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.contact_list_item, null);
            Holder h = new Holder();

            h.imgId = (ImageView)convertView.findViewById(R.id.img);
            h.name = (TextView)convertView.findViewById(R.id.name);
            h.phoneNumber= (TextView)convertView.findViewById(R.id.phone_number);
            convertView.setTag(h);
        }
        Holder h = (Holder)convertView.getTag();
        Contact contact = contacts.get(position);

        byte [] img = contact.getImg();
        if(img != null){
            Drawable image = new BitmapDrawable(BitmapFactory.decodeByteArray(img, 0, img.length));
            h.imgId.setImageDrawable(image);
        }else{
            h.imgId.setImageResource(R.drawable.ic_launcher);
        };
        h.name.setText(contact.getDisplayName());
        h.phoneNumber.setText(contact.getPhoneNumber());

        convertView.setBackgroundColor(Color.TRANSPARENT);
        if (selectedItemIds.get(position)) {
            convertView.setBackgroundColor(0x9934B5E4);
        }
        return convertView;
    }

    public static class Holder {
        ImageView imgId;
        TextView name;
        TextView phoneNumber;
    }
}
