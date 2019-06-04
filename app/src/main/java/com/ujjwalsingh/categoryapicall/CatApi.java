package com.ujjwalsingh.categoryapicall;

import com.ujjwalsingh.categoryapicall.Category.PostList;
import com.ujjwalsingh.categoryapicall.Product.ProductList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class CatApi {

    private static final String url = "http://13.127.236.125/api/v1/";

    public static PostService postService = null;

    public static PostService getService(){
        if (postService==null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url).addConverterFactory(GsonConverterFactory.create())
                    .build();
            postService = retrofit.create(PostService.class);
        }
        return postService;
    }

    public interface PostService{
        @GET("categorytrees/")
        Call<PostList> getPostList();

        @GET("products/")
        Call<ProductList> getProductList();

    }
}
