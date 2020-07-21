package com.jayapps.hello.lostandfound;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {



    private EditText emailtxt;
    private EditText passwordtxt;
    private Button loginbtn;
    private FirebaseAuth mAuth;
private TextView signuplink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mAuth = FirebaseAuth.getInstance();

        emailtxt =(EditText) findViewById(R.id.emailtxt);

        passwordtxt =(EditText) findViewById(R.id.passwordtxt);

        loginbtn =(Button) findViewById(R.id.loginbtn);

        signuplink=(TextView) findViewById(R.id.registerlink);






        signuplink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(login.this,register.class);
                startActivity(intent);
                finish();
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loginemail;
                String loginpassword;
                loginemail=emailtxt.getText().toString();

                loginpassword=passwordtxt.getText().toString();
                if(!TextUtils.isEmpty(loginemail)&&!TextUtils.isEmpty(loginpassword))
                {
                    mAuth.signInWithEmailAndPassword(loginemail,loginpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(login.this, "Authentication Success.",
                                        Toast.LENGTH_SHORT).show();
                                sendtomain();

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("B", "signInWithEmail:failure", task.getException());
                                Toast.makeText(login.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

                }


            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        sendtomain();
    }
    public void sendtomain(){
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null)
        {
            Intent intent=new Intent(login.this,MainActivity.class);
            startActivity(intent);
            finish();
            //sending user to login if not logged in
        }
    }
}
