package com.ujjwalsingh.categoryapicall;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.ujjwalsingh.categoryapicall.Area.AreaList;
import com.ujjwalsingh.categoryapicall.Category.PostList;
import com.ujjwalsingh.categoryapicall.Category.Result;
import com.ujjwalsingh.categoryapicall.City.CityList;
import com.ujjwalsingh.categoryapicall.Product.ProductList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.FormUrlEncoded;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    public ArrayList<String> citiesL;
    public ArrayAdapter<String> arrayAdapterCity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//            getProductData();
//            getCatData();

        spinner = findViewById(R.id.spin);


        getCitydata();
        //getAreaDe();
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

    private void getCitydata(){
        citiesL = new ArrayList<>();
        arrayAdapterCity= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,citiesL);
        spinner.setAdapter(arrayAdapterCity);
        Call<CityList> call = CatApi.getService().getCity();
        call.enqueue(new Callback<CityList>() {
            @Override
            public void onResponse(Call<CityList> call, Response<CityList> response) {
                CityList cityList = response.body();
                for (int i=0; i<cityList.getResults().size();i++) {
                    citiesL.add(cityList.getResults().get(i).getDistrict());
                    Log.i("tusha",cityList.getResults().get(i).toString());
                    arrayAdapterCity.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<CityList> call, Throwable t) {

            }
        });
    }

//    private void getCitydata(){
//        Call<CityList> call = CatApi.getService().getCity();
//        call.enqueue(new Callback<CityList>() {
//            @Override
//            public void onResponse(Call<CityList> call, Response<CityList> response) {
//                CityList cityList = response.body();
//                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<CityList> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "Failed" , Toast.LENGTH_SHORT).show();
//
//            }
//        });
//    }
//
//    private void getAreaDe(){
//        areasL = new ArrayList<>();
//        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,areasL);
//        spinner.setAdapter(arrayAdapter);
//        Call<AreaList> call = CatApi.getService().getArea();
//        call.enqueue(new Callback<AreaList>() {
//            @Override
//            public void onResponse(Call<AreaList> call, Response<AreaList> response) {
//                AreaList areaList = response.body();
//                for (int i=0; i<areaList.getResults().size();i++) {
//                    areasL.add(areaList.getResults().get(i).getArea());
//                    Log.i("killer", areasL.get(i).toString());
//                    arrayAdapter.notifyDataSetChanged();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<AreaList> call, Throwable t) {
//
//            }
//        });
//        Log.i("itra",String.valueOf(areasL.size()));
//    }

}
