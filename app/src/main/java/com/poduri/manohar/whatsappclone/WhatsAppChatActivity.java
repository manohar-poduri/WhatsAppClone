package com.poduri.manohar.whatsappclone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;

public class WhatsAppChatActivity extends AppCompatActivity  implements View.OnClickListener{

    private ListView chatListView;
    private ArrayList<String> chatsList;
    private ArrayAdapter adapter;
    private String selectedUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whats_app_chat);

        selectedUsers = getIntent().getStringExtra("selectedUsers");
        FancyToast.makeText(this,"chat with " + selectedUsers + " now",FancyToast.LENGTH_LONG,FancyToast.INFO,true).show();

        findViewById(R.id.btnSendMessage).setOnClickListener(this);

        chatListView = findViewById(R.id.chatListView);
        chatsList = new ArrayList();
        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, chatsList);
        chatListView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {

        final EditText edtMessage = findViewById(R.id.edtSend);

        ParseObject chat = new ParseObject("Chat");
        chat.put("waSender",ParseUser.getCurrentUser().getUsername());
        chat.put("waTargetRecipient",selectedUsers);
        chat.put("waMessage",edtMessage.getText().toString());
        chat.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                    if (e == null) {
                        FancyToast.makeText(WhatsAppChatActivity.this,"message from " + ParseUser.getCurrentUser().getUsername() + "sent to " + selectedUsers,FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                        chatsList.add(ParseUser.getCurrentUser().getUsername() + ": " + edtMessage.getText().toString());
                        adapter.notifyDataSetChanged();
                        edtMessage.setText("");


                    }
            }
        });

    }
}
