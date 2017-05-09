package com.example.kevinlee.chatbot;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by K on 9/5/2017.
 */
public class ChatArrayAdapter extends ArrayAdapter<ChatMessage>{

    private TextView chatText;
    private List<ChatMessage> MessageList = new ArrayList<ChatMessage>();
    private LinearLayout layout;

    public ChatArrayAdapter(Context context, int resource, ChatMessage[] objects) {
        super(context, resource, objects);
    }

    public ChatArrayAdapter(Context context, int resource) {
        super(context,resource);
    }

    @Override
    public void add(ChatMessage object){
        MessageList.add(object);
        super.add(object);
    }

    public int getCount(){
        return this.MessageList.size();
    }

    public ChatMessage getItem(int index){
        return this.MessageList.get(index);
    }

    public View getView(int position, View ConvertView, ViewGroup parent){
        View v = ConvertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.chat,parent, false);
        }

        layout = (LinearLayout) v.findViewById(R.id.chat_message);
        ChatMessage messageObj = getItem(position);
        chatText = (TextView) v.findViewById(R.id.chat_singleMessage);

        chatText.setText(messageObj.message);

        chatText.setBackgroundResource(messageObj.left ? R.drawable.bubble_green:R. drawable.bubble_yellow);

        layout.setGravity(messageObj.left? Gravity.LEFT:Gravity.RIGHT);
        return v;
    }

    public Bitmap decodeToBitmap(byte[] decodeByte) {
        return BitmapFactory.decodeByteArray(decodeByte, 0, decodeByte.length);
    }
}
