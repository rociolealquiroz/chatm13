package org.insbaixcamp.reus.chat;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity{

    private FirebaseAuth mFirebaseAuth;
    private static final int RC_SIGN_IN = 9001;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sing_in);

        // Initialize FirebaseAuth
        mFirebaseAuth = FirebaseAuth.getInstance();

    }
//
//    private void signIn() {
//        Intent signInIntent = mSignInClient.getSignInIntent();
//        startActivityForResult(signInIntent, RC_SIGN_IN);
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        // Result returned from launching the Intent in signIn()
//        if (requestCode == RC_SIGN_IN) {
//            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//            try {
//                // Google Sign In was successful, authenticate with Firebase
//                GoogleSignInAccount account = task.getResult(ApiException.class);
//                firebaseAuthWithGoogle(account);
//            } catch (ApiException e) {
//                // Google Sign In failed, update UI appropriately
//                Log.w(TAG, "Google sign in failed", e);
//            }
//        }
//    }
//    public void openSomeActivityForResult() {
//        Intent intent = new Intent(this, SomeActivity.class);
//        someActivityResultLauncher.launch(intent);
//    }
//
//    // You can do the assignment inside onAttach or onCreate, i.e, before the activity is displayed
//    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
//            new ActivityResultContracts.StartActivityForResult(),
//            new ActivityResultCallback<ActivityResult>() {
//                @Override
//                public void onActivityResult(ActivityResult result) {
//                    if (result.getResultCode() == Activity.RESULT_OK) {
//                        // There are no request codes
//                        Intent data = result.getData();
//                        doSomeOperations();
//                    }
//                }
//            });
}
