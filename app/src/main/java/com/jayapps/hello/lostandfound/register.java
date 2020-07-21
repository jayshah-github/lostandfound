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

public class register extends AppCompatActivity {
    private EditText regemail;
    private EditText regpass;
    private EditText regconpass;
    private Button registerbtn;
    private TextView signuplink;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();



        regemail=(EditText) findViewById(R.id.regemailtxt);
        regconpass=(EditText) findViewById(R.id.confirmpasswordtxt);
        regpass=(EditText) findViewById(R.id.regpasswordtxt);
        registerbtn=(Button) findViewById(R.id.registerbtn);
        signuplink=(TextView)findViewById(R.id.linktosignup);

        signuplink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(register.this,login.class);
                startActivity(intent);
                finish();
            }
        });


        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String regemailtxt=regemail.getText().toString();
                String regpasstxt=regpass.getText().toString();

                String regconpasstxt=regconpass.getText().toString();

                if(!TextUtils.isEmpty(regemailtxt)&&!TextUtils.isEmpty(regpasstxt)&&!TextUtils.isEmpty(regconpasstxt)){
                    if(regconpasstxt.equals(regpasstxt)){

                        mAuth.createUserWithEmailAndPassword(regemailtxt,regconpasstxt)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            // Sign in success, update UI with the signed-in user's information
                                            Log.d("A", "createUserWithEmail:success");
                                            FirebaseUser user = mAuth.getCurrentUser();

                                            Toast.makeText(register.this, "Registeration Success.",
                                                    Toast.LENGTH_SHORT).show();
                                            Intent intent=new Intent(register.this,login.class);
                                            startActivity(intent);
                                            finish();


                                        } else {
                                            // If sign in fails, display a message to the user.
                                            Log.w("B", "createUserWithEmail:failure", task.getException());
                                            Toast.makeText(register.this, "Registeration failed.",
                                                    Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                });









                    }



                }

            }
        });




    }

}
