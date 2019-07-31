package com.interview.client_rs;

import com.interview.client_rs.ws.PostResponse;
import com.interview.client_rs.ws.Value;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import java.util.List;

public interface RequestInterface {
    @GET("/insert")
    Call<PostResponse> insert(
            @Query("name") String name,
            @Query("jndi_name")String jndi_name,
            @Query("description")String description,
            @Query("category")int category,
            @Query("url")String url,
            @Query("nickname")String nickname,
            @Query("password")String password,
            @Query("timeout")float timeout,
            @Query("max_connects")int max_connects,
            @Query("min_connects")int mix_connects
    );

    @GET("/selectbyid")
    Call<PostResponse> selectbyid(@Query("id") long id);

    @GET("/selectbyfew")
    Call <List<Value>> selectFew(@Query("name") String name, @Query("category") int category, @Query("description") String description);

    @GET("/selectall")
    Call <List<Value>> selectall ();

    @GET("/deleteall")
    Call<PostResponse> deleteall ();
}
