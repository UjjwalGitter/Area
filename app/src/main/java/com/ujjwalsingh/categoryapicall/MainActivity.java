package com.ujjwalsingh.categoryapicall;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.ujjwalsingh.categoryapicall.Category.PostList;
import com.ujjwalsingh.categoryapicall.Category.Result;
import com.ujjwalsingh.categoryapicall.Product.ProductList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            getProductData();
            getCatData();
    }

    private void getCatData(){
        Call<PostList> result = CatApi.getService().getPostList();
        result.enqueue(new Callback<PostList>() {
            @Override
            public void onResponse(Call<PostList> call, Response<PostList> response) {
                PostList ujju = response.body();
                List<Result> main = ujju.getResults();
                Log.i("retr",ujju.toString());
                Log.i("retr","sd");
                for (int i=0;i<main.size();i++){
                    Log.i("humble",main.get(i).getName().toString());
                }
                Toast.makeText(MainActivity.this, "SUCCESS", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<PostList> call, Throwable t) {
                Toast.makeText(MainActivity.this, "FAILED", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getProductData(){
        Call<ProductList> proResult = CatApi.getService().getProductList();
        proResult.enqueue(new Callback<ProductList>() {
            @Override
            public void onResponse(Call<ProductList> call, Response<ProductList> response) {
                ProductList productList = response.body();
                Toast.makeText(MainActivity.this, "SUCCESS", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<ProductList> call, Throwable t) {
                Toast.makeText(MainActivity.this, "FAILED", Toast.LENGTH_SHORT).show();

            }
        });
    }

}
