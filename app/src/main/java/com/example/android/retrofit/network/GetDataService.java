package com.example.android.retrofit.network;
import com.example.android.retrofit.model.RetroPost;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {
    @GET("/posts")
    Call<List<RetroPost>> getAllPosts();
}
