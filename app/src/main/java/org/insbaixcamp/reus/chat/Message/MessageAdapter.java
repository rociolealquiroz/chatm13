package org.insbaixcamp.reus.chat.Message;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.insbaixcamp.reus.chat.R;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {
    private List<Message> messageList;
    private String userName;
    private Context context;
    private String TAG = "MessageAdapter";

    public MessageAdapter(List<Message> chatList,Context context) {
        this.messageList = chatList;
        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        userName = sharedPref.getString(context.getResources().getString(R.string.preference_user_name),"anonymous");

    }

    @NonNull
    @Override
    public MessageAdapter.MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message, parent, false);

        return new MessageViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.MessageViewHolder holder, int position) {
        holder.userid.setText(messageList.get(position).getUserId());
        holder.message.setText(messageList.get(position).getMessage());
        Log.i(TAG,userName);
        Log.i(TAG,messageList.get(position).getUserId());
        //Personalització del card si es meu o dels altres
        if(userName.equals(messageList.get(position).getUserId())){
            Log.i(TAG,"entro");
            holder.cardView.setRadius(20);
        }else{
            Log.i(TAG,"no entro");

        }

    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {
        TextView userid;
        TextView message;
        CardView cardView;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            userid = itemView.findViewById(R.id.tv_user);
            message = itemView.findViewById(R.id.tv_message);
            cardView = itemView.findViewById(R.id.cv_message);
        }
    }
}
