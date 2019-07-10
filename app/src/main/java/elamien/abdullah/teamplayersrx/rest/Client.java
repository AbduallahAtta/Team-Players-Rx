package elamien.abdullah.teamplayersrx.rest;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by AbdullahAtta on 7/10/2019.
 */
public class Client {
    private static Retrofit sRetrofit = null;

    public static TeamApi getsRetrofit() {
        if (sRetrofit == null) {
            sRetrofit = new Retrofit.Builder()
                    .baseUrl("https://www.thesportsdb.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

                    .build();
        }
        return sRetrofit.create(TeamApi.class);
    }
}
