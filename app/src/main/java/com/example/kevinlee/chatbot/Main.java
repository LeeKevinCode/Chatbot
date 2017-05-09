package com.example.kevinlee.chatbot;

import android.app.Activity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class Main extends Activity {

    private ChatArrayAdapter adp;
    private ListView list;
    private EditText chatText;
    private Button send;
    private boolean side = false;

    Intent in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Intent i = getIntent();

        send = (Button)findViewById(R.id.main_btn);
        list = (ListView)findViewById(R.id.main_listview);

        adp = new ChatArrayAdapter(getApplicationContext(),R.layout.chat);

        chatText = (EditText)findViewById(R.id.chat);

        chatText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {

                if((keyEvent.getAction() == keyEvent.ACTION_DOWN) && (i == keyEvent.KEYCODE_ENTER)){

                    return sendChatMessage();

                }

                return false;

            }
        });

        send.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        sendChatMessage();
                    }
                });

        list.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        list.setAdapter(adp);

        adp.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                list.setSelection(adp.getCount()-1);
            }

            @Override
            public void onInvalidated() {
                super.onInvalidated();
            }
        });
    }

    private boolean sendChatMessage() {

        adp.add(new ChatMessage(side,chatText.getText().toString()));
        chatText.setText("");

        side = !side;
        return true;
    }

}
