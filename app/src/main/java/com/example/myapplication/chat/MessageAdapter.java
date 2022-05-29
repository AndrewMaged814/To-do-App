package com.example.myapplication.chat;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter
{
    private static final int Type_message_sent =0;
    private static final int Type_message_received =1;
    private static final int Type_Image_sent =2;
    private static final int Type_Image_received =3;

    private LayoutInflater inflater;
    private List<JSONObject> messages = new ArrayList<>();

    public MessageAdapter ( LayoutInflater inflater)
    {
        this.inflater=inflater;

    }

    private class sentMessageholder extends RecyclerView.ViewHolder
    {

        //hold reference to text view in layout item_sent_message
        TextView messageText;
        public sentMessageholder(@NonNull View itemView)
        {
            super(itemView);

            messageText = itemView.findViewById(R.id.sentMessage);
        }
    }

    private class sentImageholder extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        public sentImageholder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

    private class receivedMessageholder extends RecyclerView.ViewHolder
    {
        TextView name,message;

        public receivedMessageholder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.senderName);
            message=itemView.findViewById(R.id.receivedMessage);
        }
    }

    private class receivedImageholder extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        TextView name;

        public receivedImageholder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.imageView);
            name = itemView.findViewById(R.id.senderName);
        }
    }

    //to know which holder to implement
    @Override
    public int getItemViewType(int position)
    {
        JSONObject message = messages.get(position);

        try {
            if(message.getBoolean("isSent"))
            {
                if(message.has("message"))
                    return Type_message_sent;
                else
                    return Type_Image_sent;

            }
            else
            {
                if(message.has("message"))
                    return Type_message_received;
                else
                    return Type_Image_received;

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return -1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;

        switch (viewType)
        {
            case Type_message_sent:
                view= inflater.inflate(R.layout.item_sent_message, parent, false);
                return new sentMessageholder(view);
            case Type_message_received:
                view= inflater.inflate(R.layout.item_received_message, parent, false);
                return new receivedMessageholder(view);
            case Type_Image_sent:
                view= inflater.inflate(R.layout.item_sent_image, parent, false);
                return new sentMessageholder(view);
            case Type_Image_received:
                view= inflater.inflate(R.layout.item_received_image, parent, false);
                return new receivedImageholder(view);

        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        JSONObject message = messages.get(position);

        try {
            if (message.getBoolean("isSent"))
            {

                if (message.has ("message"))
                {

                    sentMessageholder messageHolder = (sentMessageholder) holder;
                    messageHolder.messageText.setText(message.getString("message"));


                }

                else
                {

                    sentImageholder imageHolder = (sentImageholder) holder;
                    //since the image is B64 string (binary data) to convert must use bitmap
                    Bitmap bitmap = getBitmapFromString(message.getString("image"));

                    imageHolder.imageView.setImageBitmap(bitmap);

                }

            }
            else
            {
                if (message.has("message"))
                {
                    receivedMessageholder messageHolder = (receivedMessageholder) holder;
                    messageHolder.name.setText(message.getString("name"));
                    messageHolder.message.setText(message.getString("message"));

                }

                else
                {

                    receivedImageholder imageHolder = (receivedImageholder) holder;
                    imageHolder.name.setText(message.getString("name"));

                    Bitmap bitmap = getBitmapFromString(message.getString("image"));
                    imageHolder.imageView.setImageBitmap(bitmap);

                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private Bitmap getBitmapFromString(String image)
    {

        byte[] bytes = Base64.decode(image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    @Override
    public int getItemCount() {
        //total number of messages
        return messages.size();
    }

    public void addItem (JSONObject jsonObject)
    {
        messages.add(jsonObject);
        notifyDataSetChanged();

    }
}
