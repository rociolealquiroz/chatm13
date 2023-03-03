package org.insbaixcamp.reus.chat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.insbaixcamp.reus.chat.Message.Message;
import org.insbaixcamp.reus.chat.Message.MessageAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    FirebaseDatabase database;
    DatabaseReference myRef;
    List<Message> chatList;

    private MessageAdapter messageAdapter;
    private LinearLayoutManager mLayoutManager;
    EditText etMessage;

    RecyclerView rv_messages;

    FloatingActionButton fab;

    public static final String ANONYMOUS = "anonymous";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_main);

        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        rv_messages = findViewById(R.id.rv_messages);

        mLayoutManager = new LinearLayoutManager(this);
        rv_messages.setLayoutManager(mLayoutManager);

        fab = findViewById(R.id.fab);

        etMessage = findViewById(R.id.input);

        if (mFirebaseUser == null){
            signInAnonymously();
        }

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("messages");
        chatList = new ArrayList<Message>();
        messageAdapter = new MessageAdapter(chatList, this);
        rv_messages.setAdapter(messageAdapter);

        actualitzarChat();
        escriureAlChat();

    }

    @Override
    protected void onStart() {
        super.onStart();
        signInAnonymously();
    }


    private void updateUI(FirebaseUser user) {

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("UserId",user.getUid());
        editor.apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.sign_in){
            Intent intent = new Intent(this, SignIn.class);

            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }


    private void signInAnonymously(){

        mFirebaseAuth.signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInAnonymously:success");
                            FirebaseUser user = mFirebaseAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInAnonymously:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }



    private void escriureAlChat() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etMessage.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "No puedes enviar un mensaje vacío", Toast.LENGTH_SHORT).show();
                } else {
                    //Creem l'objecte amb l'informació
                    Message message = new Message(getUserName(), etMessage.getText().toString());
                    //Fem un push a la base de dades
                    myRef.push().setValue(message);
                    etMessage.setText("");
                }

            }
        });
    }



    private void actualitzarChat() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                chatList.clear(); // Limpia la lista para evitar duplicación
                for (DataSnapshot item: snapshot.getChildren()) {
                    Message message = item.getValue(Message.class);
                    chatList.add(message);
                }
                messageAdapter.notifyDataSetChanged();
                rv_messages.scrollToPosition(chatList.size()-1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private String getUserName() {
        SharedPreferences sharedPref = this.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        return sharedPref.getString(getResources().getString(R.string.preference_user_name),"anonymous");
    }

}