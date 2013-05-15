package com.shinsegae.android.ssgosp;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {

	public static final String PREFS_NAME = "prefs";		
	SharedPreferences prefs;
	EditText et_id;
    EditText et_pw;
    TextView tv_msg;
    Button login_but;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        
        final String username = prefs.getString("edit_id", null);
        final String password = prefs.getString("edit_pw", null);

        
        if (username != null && password != null){
       	 
            setContentView(R.layout.loginview);

            et_id = (EditText) findViewById(R.id.edit_id);
            et_pw = (EditText) findViewById(R.id.edit_pw);
            login_but = (Button) findViewById(R.id.btn_login);
            
            login_but.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                try {
                    if(username.equalsIgnoreCase(et_id.getText().toString()) && password.equals(et_pw.getText().toString())) {
                        Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(LoginActivity.this,CalendarActivity.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(LoginActivity.this,"로그인 실패", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            });
        } else {
        	//Intent i = new Intent(LoginActivity.this, LoginActivity.class);
		//	startActivity(i);
        }
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
