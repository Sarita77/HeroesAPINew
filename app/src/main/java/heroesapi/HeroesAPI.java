package heroesapi;

import java.util.List;

import model.Heroes;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface HeroesAPI {
////    1. Using object
//    @POST("heroes")
//    Call<Void> addHero(@Body Heroes heroes);

//    2. Using @Field
    @FormUrlEncoded
    @POST("heroes")
    Call<Void> addhero(@Field("name") String name, @Field("desc") String desc);

    @GET("heroes")
    Call<List<Heroes>> getHeroes();


}
