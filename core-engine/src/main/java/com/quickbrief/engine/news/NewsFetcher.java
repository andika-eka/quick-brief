package com.quickbrief.engine.news;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class NewsFetcher {
    private final NewsApi api;
    private final String apiKey;

    public NewsFetcher(String apiKey) {
        this.apiKey = apiKey;
        
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
            
        this.api = retrofit.create(NewsApi.class);
    }

    public CompletableFuture<List<Article>> fetchTopHeadlines() {
        CompletableFuture<List<Article>> future = new CompletableFuture<>();

        // We default to "us" for English headlines, but you can change this to "id" for Indonesia
        api.getTopHeadlines("us", apiKey).enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    future.complete(response.body().articles());
                } else {
                    future.completeExceptionally(
                        new RuntimeException("API Error: " + response.code())
                    );
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                future.completeExceptionally(t);
            }
        });

        return future;
    }
}
