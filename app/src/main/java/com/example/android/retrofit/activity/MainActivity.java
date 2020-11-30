package com.example.android.retrofit.activity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.example.android.retrofit.R;
import com.example.android.retrofit.adapter.CustomAdapter;
import com.example.android.retrofit.helper.RVItemClickListener;
import com.example.android.retrofit.helper.RVItemTouchListener;
import com.example.android.retrofit.model.RetroPost;
import com.example.android.retrofit.network.GetDataService;
import com.example.android.retrofit.network.RetrofitClientInstance;
import com.google.android.material.snackbar.Snackbar;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final String EXTRA_MESSAGE = "com.example.android.retrofit.extra.MESSAGE";
    private CustomAdapter adapter;
    private RecyclerView recyclerView;
    private TextView comment;
    ProgressDialog progressDialog;

    private void generateDataList(List<RetroPost> postList) {
        recyclerView = findViewById(R.id.customRecyclerView);
        adapter = new CustomAdapter(this, postList);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        comment = findViewById(R.id.comment);

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        /*Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);

        Call<List<RetroPost>> call = service.getAllPosts();
        call.enqueue(new Callback<List<RetroPost>>() {

            @Override
            public void onResponse(Call<List<RetroPost>> call, Response<List<RetroPost>> response) {
                progressDialog.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<RetroPost>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onClick(View view) {
        Log.d(LOG_TAG, "Post clicked!");
        Intent intent = new Intent(this, SecondActivity.class);
        String message = comment.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}