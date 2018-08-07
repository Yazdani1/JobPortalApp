package yazdaniscodelab.jobportalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import yazdaniscodelab.jobportalapp.Model.Data;

public class AllJobActivity extends AppCompatActivity {

    private Toolbar toolbar;

    //Recycler
    private RecyclerView recyclerView;

    //Firebase

    private DatabaseReference mAllJobPost;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_job);

        toolbar=findViewById(R.id.all_job_psot);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("All Job Post");

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Database

        mAuth = FirebaseAuth.getInstance();



       mAllJobPost= FirebaseDatabase.getInstance().getReference().child("Public database");

        mAllJobPost.keepSynced(true);

        recyclerView=findViewById(R.id.recycler_all_job);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);


    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Data,AllJobPostViewHolder>adapter=new FirebaseRecyclerAdapter<Data, AllJobPostViewHolder>
                (
                        Data.class,
                        R.layout.alljobpost,
                        AllJobPostViewHolder.class,
                        mAllJobPost
                ) {
            @Override
            protected void populateViewHolder(AllJobPostViewHolder viewHolder, final Data model, int position) {

                viewHolder.setJobTitle(model.getTitle());
                viewHolder.setJobDate(model.getDate());
                viewHolder.setJobDescription(model.getDescription());
                viewHolder.setJobSkills(model.getSkills());
                viewHolder.setJobSalary(model.getSalary());

                viewHolder.myview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent=new Intent(getApplicationContext(),JobDetailsActivity.class);

                        intent.putExtra("title",model.getTitle());
                        intent.putExtra("date",model.getDate());
                        intent.putExtra("description",model.getDescription());
                        intent.putExtra("skills",model.getSkills());
                        intent.putExtra("salary",model.getSalary());

                        startActivity(intent);

                    }
                });

            }
        };

        recyclerView.setAdapter(adapter);

    }

    public static class AllJobPostViewHolder extends RecyclerView.ViewHolder{

        View myview;

        public AllJobPostViewHolder(View itemView) {
            super(itemView);
            myview=itemView;
        }

        public void setJobTitle(String title){
            TextView mTitle=myview.findViewById(R.id.all_job_post_title);
            mTitle.setText(title);
        }

        public void setJobDate(String date){
            TextView mDate=myview.findViewById(R.id.all_job_post_date);
            mDate.setText(date);
        }

        public void setJobDescription(String description){
            TextView mDescription=myview.findViewById(R.id.all_job_post_description);
            mDescription.setText(description);
        }

        public void setJobSkills(String skills){
            TextView mSkills=myview.findViewById(R.id.all_job_post_skills);
            mSkills.setText(skills);
        }

        public void setJobSalary(String salary){
            TextView mSalary=myview.findViewById(R.id.all_job_post_salary);
            mSalary.setText(salary);
        }


    }

}
