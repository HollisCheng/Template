package template.cheng.hollis.template.api

import com.google.gson.JsonObject
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

/**
 * Created by hollischeng on 8/2/2018.
 */

interface APIService {

    @GET("GetPrivilegeFilterList")
    fun getPrivilegeFilterList(@Query("json") json: JsonObject): Call<Void>

    @GET("GetPrivilegeList")
    fun getPrivilegeList(@Query("json") json: JsonObject): Call<Void>

    @GET("GetEventOfferFormGenerate")
    fun getEventOfferFormGenerate(@Query("json") json: JsonObject): Call<Void>

    @Headers("Content-Type: application/json")
    @POST("CreateDevice")
    fun createDevice(@Body body: JsonObject): Call<Void>

    @Headers("Content-Type: application/json")
    @POST("CreateEventFormSubmit")
    fun createEventFormSubmit(@Body body: JsonObject): Call<Void>

    @GET("GetEvent")
    fun getEvent(@Query("json") json: JsonObject): Call<Void>

    @GET("GetCodeMaintEstate")
    fun getCodeMaintEstate(@Query("json") json: JsonObject): Call<Void>

    @GET("GetCodeMaint")
    fun getCodeMaint(@Query("json") json: JsonObject): Call<Void>

    companion object {
        fun create(): APIService {
            var logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            var httpClient = OkHttpClient.Builder()
            httpClient.interceptors().add(logging)

//            httpClient.addInterceptor { chain ->
//                val original: Request = chain.request()
//
//                val request: Request = original.newBuilder()
//                        .header("Platform", "Android")
//                        .header("Version", BuildConfig.VERSION_NAME)
//                        .header("Language", Utility.APILang())
//                        .header("MemberID", Utility.MemberID.toString())
//                        .method(original.method(), original.body())
//                        .build()
//
//                chain.proceed(request)
//            }//Add Custom Request Header

            val retrofit = Retrofit.Builder()
                    .baseUrl("https://eapproval.nwd.com.hk/NWD_Eapproval/Services/MobileServices.svc/GetWorkList")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build()
            return retrofit.create(APIService::class.java)
        }
    }
}