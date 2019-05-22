package heroesapi;

import java.util.List;

import model.Heroes;
import model.ImageResponse;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

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

    // For uploading image
    @Multipart
    @POST("upload")
    Call<ImageResponse> uploadImage(@Part MultipartBody.Part img);

    @GET("heroes")
    Call<List<Heroes>> getAllHeroes();


}
