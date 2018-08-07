package yazdaniscodelab.jobportalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    private Button btnAllJob;
    private Button btnPostJob;

    //Toolbar

    private Toolbar toolbar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar=findViewById(R.id.toolbar_home);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Job Portal App");

        mAuth=FirebaseAuth.getInstance();


        btnAllJob=findViewById(R.id.btn_allJob);
        btnPostJob=findViewById(R.id.btn_PostJob);


        btnAllJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),AllJobActivity.class));

            }
        });

        btnPostJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),PostJobActivity.class));

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.logout:
                mAuth.signOut();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
