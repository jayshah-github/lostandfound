package com.jayapps.hello.lostandfound;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();





    }


 //   public void ReplaceFragment(Fragment fragment)
   // {
//
  //      android.support.v4.app.FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
    //    fragmentTransaction.replace(R.id.containerframe,fragment);
      //  fragmentTransaction.commit();
    //}
  @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser==null)
        {
            Intent intent=new Intent(MainActivity.this,login.class);
            startActivity(intent);
            finish();
            //sending user to login if not logged in
        }
    }

}
