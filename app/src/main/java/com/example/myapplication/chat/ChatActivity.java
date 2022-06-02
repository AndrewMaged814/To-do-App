package com.example.myapplication.chat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class ChatActivity extends AppCompatActivity implements TextWatcher {

    private String name;
    private WebSocket webSocket;
    //on this link a webSocket server runs
    private String SERVER_PATH="ws://192.168.1.103:3000";
    private EditText messageBox;
    private View sendButton, pickImage;
    private RecyclerView recyclerView;
    private int IMAGE_REQUEST_ID=1;
    private MessageAdapter MessageAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        name = getIntent().getStringExtra("name");
        initiateSocketConnection();

    }

    private void initiateSocketConnection()
    {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(SERVER_PATH).build();
        webSocket = client.newWebSocket(request, new SocketListener());

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s)
    {
        //will hold the string in message box and trim will remove all leading and trailing empty
        //spaces from a string
        String string = s.toString().trim();
        //condition to change buttons based on message box
        if(string.isEmpty())
        {
            resetMessageBox();
        }
        else
        {
            sendButton.setVisibility(View.VISIBLE);
            pickImage.setVisibility(View.INVISIBLE);

        }

    }

    private void resetMessageBox()
    {
        //removes the string "message..." not enter an infinite loop
        messageBox.removeTextChangedListener(this);
        messageBox.setText(" ");

        sendButton.setVisibility(View.INVISIBLE);
        pickImage.setVisibility(View.VISIBLE);

        messageBox.addTextChangedListener(this);
    }

    //nested class
    private class SocketListener extends WebSocketListener
    {

        //when client successful connects with server
        @Override
        public void onOpen(WebSocket webSocket, Response response)
        {

            super.onOpen(webSocket, response);

            //onOpen called from background thread and can't touch Ui from background thread
            runOnUiThread(() ->
            {
                Toast.makeText(ChatActivity.this, "Socket Connection is Successful!",
                        Toast.LENGTH_SHORT).show();

                initializeView();


            });
        }

        //when client receives any message in form of string
        @Override
        public void onMessage(WebSocket webSocket, String text)
        {
            super.onMessage(webSocket, text);

            //message sent to everyone on server except the sender
            //accept message and show it in recycler view
            runOnUiThread(()->{

                try {
                    JSONObject jsonObject = new JSONObject(text);
                    jsonObject.put("isSent",false );
                    MessageAdapter.addItem(jsonObject);

                    //for the recycler view to automatically scroll to new message
                    recyclerView.smoothScrollToPosition(MessageAdapter.getItemCount() - 1);

                } catch (JSONException e)
                {
                    e.printStackTrace();
                }

            });
        }
    }

    //store reference items to all the things in chat Activity Layout
    private void initializeView()
    {
        messageBox = findViewById(R.id.messageBox);
        sendButton = findViewById(R.id.sendButton);
        pickImage = findViewById(R.id.pickImage);
        recyclerView = findViewById(R.id.recyclerView);

        MessageAdapter = new MessageAdapter(getLayoutInflater());
        recyclerView.setAdapter(MessageAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //if message box empty the image button will be visible else wise send button will be visible
        messageBox.addTextChangedListener(this);

        //functionalities of send button
        //take message in message box and place it in a json object which then sends it to server
        sendButton.setOnClickListener( v -> {

            JSONObject jsonObject = new JSONObject();


            try {
                //name of sender
                jsonObject.put("name", name);
                //sender's message
                jsonObject.put("message",messageBox.getText().toString());

                //sends the json object to server
                webSocket.send(jsonObject.toString());
                jsonObject.put("isSent", true);
                MessageAdapter.addItem(jsonObject);

                recyclerView.smoothScrollToPosition(MessageAdapter.getItemCount()-1);

                //empty the message box after sending the message
                resetMessageBox();


            } catch (JSONException e) {
                e.printStackTrace();
            }


        });

        //pick image button functionality
        //gallery will open and user can select any image
        pickImage.setOnClickListener(v -> {

            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");

            //This API was replaced by android but couldn't find alternative
            //however deprecated APIs can still be used
            startActivityForResult (Intent.createChooser(intent, "Pick image"),
                    IMAGE_REQUEST_ID);

        });

    }

    //to accept the selected image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode ==IMAGE_REQUEST_ID && resultCode == RESULT_OK)
        {
            try {
                //to obtain the selected image from user
                InputStream is = getContentResolver().openInputStream(data.getData());
                Bitmap image = BitmapFactory.decodeStream(is);

                sendImage(image);

            } catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }

        }
    }

    private void sendImage(Bitmap image)
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        //format of compression also the quality
        //output stream is where we want to compress the image
        image.compress(Bitmap.CompressFormat.JPEG,50,outputStream);

        //image will be sent in a form of string
        //to convert image into string
        String base64string = Base64.encodeToString(outputStream.toByteArray(),
                Base64.DEFAULT);
        //To send the image to server
        JSONObject jsonObject = new JSONObject();
        try {
            //name of sender
            jsonObject.put("name",name);
            //the image
            jsonObject.put("image",base64string);
            //send name and image to server
            webSocket.send(jsonObject.toString());

            //show messages in recyclerview
            jsonObject.put("isSent", true);

            MessageAdapter.addItem(jsonObject);

            recyclerView.smoothScrollToPosition(MessageAdapter.getItemCount()-1);

        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}




