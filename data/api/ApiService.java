package com.example.sofra.data.api;


import com.example.sofra.data.model.generalsource.GeneralSource;
import com.example.sofra.data.model.generalsource.GeneralSource2;
import com.example.sofra.data.model.generalsource.GeneralSource3;
import com.example.sofra.data.model.login.Login;
import com.example.sofra.data.model.register.Register;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiService {


    /*START** **Restaurant***/
    @POST("restaurant/login")
    @FormUrlEncoded
    Call<Login> getLogin (@Field("email") String email,
                          @Field("password") String password);
    /*START** **Restaurant** - Login/Register/ResetPass/NewPass/*/
    @Multipart
    @POST("restaurant/sign-up")
    Call<Login> setSignedUpDetails(@Part("name") RequestBody name, @Part("email") RequestBody email
            , @Part("password") RequestBody password, @Part("password_confirmation") RequestBody passwordConfirm
            , @Part("phone") RequestBody phone, @Part("whatsapp") RequestBody whatsApp
            , @Part("region_id") RequestBody regionId, @Part("delivery_cost") RequestBody deliveryCost
            , @Part("minimum_charger") RequestBody minimumCharger, @Part MultipartBody.Part photo
            , @Part("delivery_time") RequestBody deliveryTime);

    @POST("restaurant/reset-password")
    @FormUrlEncoded
    Call<Login> setRestResetPassword(@Field("email") String email);

    @POST("restaurant/new-password")
    @FormUrlEncoded
    Call<Login> setRestNewPassword(@Field("code") String pinCode, @Field("password") String newPassword
            , @Field("password_confirmation") String passwordConfirm);

    @GET("cities")
    Call<GeneralSource> getCities();

    @GET("regions-not-paginated")
    Call<GeneralSource3> getRegions(@Query("city_id") String cityId);
    /*End** **Restaurant**/
    /*START** **Restaurant** - Home/FoodCategories/MyCateg/NewCateg/updateCateg/NewFoodItem/
    updateFoodItem/deleteFoodItem*/
    @GET("categories")
    Call<GeneralSource3> getSelectedRestCategories(@Query("restaurant_id") String restaurantId);

    @GET("items")
    Call<GeneralSource> getSelectedCategFoodItems(@Query("restaurant_id") String restaurantId
            , @Query("category_id") String categoryId);

    @GET("restaurant/my-categories")
    Call<GeneralSource> getRestCategories(@Query("api_token") String apiToken);

    @Multipart
    @POST("restaurant/new-category")
    Call<GeneralSource2> setNewCategory(@Part("name") RequestBody name, @Part MultipartBody.Part photo
            , @Part("api_token") RequestBody apiToken);


    @Multipart
    @POST("restaurant/update-category")
    Call<GeneralSource> updateCategory(@Part("name") RequestBody name, @Part MultipartBody.Part photo
            , @Part("api_token") RequestBody apiToken, @Part("category_id") RequestBody categoryId);

    @POST("restaurant/delete-category")
    @FormUrlEncoded
    Call<GeneralSource> deleteCategory(@Field("api_token") String apiToken
            , @Field("category_id") Integer categoryId);


    @Multipart
    @POST("restaurant/new-item")
    Call<GeneralSource2> setRestNewFoodItem(@Part("description") RequestBody description, @Part("price") RequestBody price
            , @Part("name") RequestBody name
            , @Part MultipartBody.Part photo, @Part("api_token") RequestBody apiToken
            , @Part("offer_price") RequestBody offerPrice, @Part("category_id") RequestBody categoryId);

    @GET("restaurant/my-items")
    Call<GeneralSource> getRestFoodItems(@Query("api_token") String apiToken
            , @Query("category_id") String categoryId);

    @Multipart
    @POST("restaurant/update-item")
    Call<GeneralSource2> updateRestFoodItem(@Part("description") RequestBody description, @Part("price") RequestBody price
            , @Part("name") RequestBody name, @Part MultipartBody.Part photo
            , @Part("api_token") RequestBody apiToken, @Part("offer_price") RequestBody offerPrice
            , @Part("item_id") RequestBody itemId, @Part("category_id") RequestBody categoryId);

    @POST("restaurant/delete-item")
    @FormUrlEncoded
    Call<GeneralSource2> deleteRestFoodItem(@Field("api_token") String apiToken
            , @Field("item_id") Integer itemId);

    @POST("contact")
    @FormUrlEncoded
    Call<GeneralSource2> contactManagement(@Field("name") String name, @Field("email") String email
            , @Field("phone") String phone, @Field("type") String type, @Field("content") String content);

    @GET("restaurant/commissions")
    Call<GeneralSource2> getRestCommission(@Query("api_token") String apiToken);

    @GET("restaurant/notifications")
    Call<GeneralSource> getRestNotifications(@Query("api_token") String apiToken);

    @GET("client/notifications")
    Call<GeneralSource> getClientNotifications(@Query("api_token") String apiToken);


    @POST("restaurant/register-token")
    @FormUrlEncoded
    Call<GeneralSource2> registerRestToken(@Field("token") String token
            , @Field("type") String type, @Field("api_token") String apiToken);

    @POST("restaurant/remove-token")
    @FormUrlEncoded
    Call<GeneralSource2> removeRestToken(@Field("token") String token, @Field("api_token") String apiToken);
    @POST("client/signup-token")
    @FormUrlEncoded
    Call<GeneralSource2> registerClientToken(@Field("token") String token
            , @Field("type") String type, @Field("api_token") String apiToken);

    @POST("client/remove-token")
    @FormUrlEncoded
    Call<GeneralSource2> removeClientToken(@Field("token") String token, @Field("api_token") String apiToken);
    /*End** **Restaurant** - Home/FoodCategories/MyCateg/NewCateg/updateCateg/deleteCateg/*/



    /*Start* Restaurant - NavBottom/More/ChangePass/NewOffers/updateOffer/deleteOffer/getMyOffersRest*/
    @POST("restaurant/change-password")
    @FormUrlEncoded
    Call<Login> setRestChangedPassword(@Field("api_token") String apiToken, @Field("old_password") String oldPassword
            , @Field("password") String newPassword, @Field("password_confirmation") String passwordConfirm);

    @POST("client/change-password")
    @FormUrlEncoded
    Call<Login> setClientChangedPassword(@Field("api_token") String apiToken, @Field("old_password") String oldPassword
            , @Field("password") String newPassword, @Field("password_confirmation") String passwordConfirm);

    @Multipart
    @POST("restaurant/new-offer")
    Call<GeneralSource2> setNewOffer(@Part("description") RequestBody description
            , @Part("price") RequestBody price, @Part("starting_at") RequestBody startingAt
            , @Part("name") RequestBody name, @Part MultipartBody.Part photo
            , @Part("ending_at") RequestBody endingAt, @Part("offer_price") RequestBody offerPrice
            , @Part("api_token") RequestBody apiToken);

    @GET("restaurant/my-offers")
    Call<GeneralSource> getRestOffers(@Query("api_token") String apiToken);

    @Multipart
    @POST("restaurant/update-offer")
    Call<GeneralSource2> updateOffer(@Part("description") RequestBody description
            , @Part("price") RequestBody price, @Part("starting_at") RequestBody startingAt
            , @Part("name") RequestBody name, @Part MultipartBody.Part photo
            , @Part("ending_at") RequestBody endingAt, @Part("offer_id") RequestBody offerId
            , @Part("api_token") RequestBody apiToken);

    @POST("restaurant/delete-offer")
    @FormUrlEncoded
    Call<GeneralSource2> deleteOffer(@Field("api_token") String apiToken, @Field("offer_id") Integer offerId);

    @GET("offers")
    Call<GeneralSource> getSelectedRestOffers(@Query("restaurant_id") String restaurantId);

    @GET("offer")
    Call<GeneralSource2> getSelectedOfferDetails(@Query("offer_id") Integer offerId);

    //Restuarant Profile
    @Multipart
    @POST("restaurant/profile")
    Call<GeneralSource2> setProfileDetails(@Part("email") RequestBody email
            , @Part("name") RequestBody name, @Part("phone") RequestBody phone
            , @Part("region_id") RequestBody regionId, @Part("delivery_cost") RequestBody deliveryCost
            , @Part("minimum_charger") RequestBody minimumCharger, @Part("availability") RequestBody availability
            , @Part MultipartBody.Part photo, @Part("api_token") RequestBody apiToken
            , @Part("delivery_time") RequestBody deliveryTime);

    @GET("restaurant")
    Call<GeneralSource2> getAvailabilityState(@Query("restaurant_id") Integer state);

    @GET("restaurants")
    Call<GeneralSource> getRestaurants(@Query("page") Integer page);

    @GET("restaurants")
    Call<GeneralSource> getRestFiltered(@Query("keyword") String keyword
            , @Query("region_id") String regionId);

    @GET("restaurant")
    Call<GeneralSource2> getRestInfo(@Query("restaurant_id") Integer restaurantId);

    @POST("restaurant/change-state")
    @FormUrlEncoded
    Call<GeneralSource2> changeState(@Field("api_token") String apiToken
            , @Field("state") String state);

    //Client Profile
    @POST("client/profile")
    @FormUrlEncoded
    Call<GeneralSource2> getClientProfileData(@Field("api_token") String apiToken);

    @Multipart
    @POST("client/profile")
    Call<GeneralSource2> editClientProfile(@Part("api_token") RequestBody apiToken, @Part("name") RequestBody name
            , @Part("phone") RequestBody phone, @Part("email") RequestBody email
            , @Part("region_id") RequestBody regionId, @Part MultipartBody.Part profileImage);

    @GET("restaurant/my-orders")
    Call<GeneralSource> getRestOrders(@Query("api_token") String apiToken, @Query("state") String state);

    @GET("restaurant/show-order")
    Call<GeneralSource2> showRestOrder(@Query("api_token") String apiToken, @Query("order_id") Integer orderId);

    @POST("restaurant/accept-order")
    @FormUrlEncoded
    Call<GeneralSource2> restAcceptOrder(@Field("api_token") String apiToken
            , @Field("order_id") Integer orderId);

    @POST("restaurant/reject-order")
    @FormUrlEncoded
    Call<GeneralSource2> restRejectOrder(@Field("api_token") String apiToken
            , @Field("order_id") Integer orderId, @Field("refuse_reason") String refuseReason);

    @POST("restaurant/confirm-order")
    @FormUrlEncoded
    Call<GeneralSource2> restConfirmOrderDelivery(@Field("api_token") String apiToken
            , @Field("order_id") Integer orderId);

    @GET("payment-methods")
    Call<GeneralSource3> getPaymentMethod(@Query("api_token") String apiToken);

    @GET("restaurant/reviews")
    Call<GeneralSource> getMyReviews(@Query("api_token") String apiToken
            , @Query("restaurant_id") Integer restaurantId);

    @POST("client/new-order")
    @FormUrlEncoded
    Call<GeneralSource2> setClientNewOrder(@Field("restaurant_id") String restaurantId
            , @Field("note") String note, @Field("address") String address
            , @Field("payment_method_id") String paymentMethodId, @Field("phone") String phone
            , @Field("name") String name, @Field("api_token") String apiToken, @Field("items[0]") Integer item0
            , @Field("quantities[0]") String quantities0, @Field("notes[0]") String notes0
            , @Field("items[1]") Integer item1, @Field("quantities[1]") String quantities1
            , @Field("notes[1]") String notes1, @Field("items[2]") Integer item2
            , @Field("quantities[2]") String quantities2, @Field("notes[2]") String notes2);

    @GET("client/my-orders")
    Call<GeneralSource> getClientOrders(@Query("api_token") String apiToken, @Query("state") String state);

    @GET("client/show-order")
    Call<GeneralSource2> showClientOrderDetails(@Query("api_token") String apiToken, @Query("order_id") Integer orderId);

    @POST("client/confirm-order")
    @FormUrlEncoded
    Call<GeneralSource> setClientOrderDeliveryConfirm(@Field("api_token") String apiToken, @Field("order_id") Integer orderId);

    @POST("client/decline-order")
    @FormUrlEncoded
    Call<GeneralSource> setClientDeclineOrder(@Field("api_token") String apiToken, @Field("order_id") Integer orderId);



    @POST("client/restaurant/review")
    @FormUrlEncoded
    Call<GeneralSource2> setClientReview(@Field("rate") String rate, @Field("comment") String comment
            , @Field("restaurant_id") String restaurantId, @Field("api_token") String apiToken);
    /*End* Restaurant - NavBottom/More/ChangePass/NewOffers/updateOffer/deleteOffer/getMyOffersRest*/

    /**START** **Client***/
   @Multipart
   @POST("client/sign-up")
   Call<Login> setClientSignedUpDetails(@Part("name") RequestBody name, @Part("email") RequestBody email
           , @Part("password") RequestBody password, @Part("password_confirmation") RequestBody passwordConfirm
           , @Part("phone") RequestBody phone, @Part("region_id") RequestBody regionId
           , @Part MultipartBody.Part profileImage);

    @POST("client/login")
    @FormUrlEncoded
    Call<Login> checkClientLoginDetails(@Field("email") String email, @Field("password") String password);

    @POST("client/reset-password")
    @FormUrlEncoded
    Call<Login> setClientResetPassword(@Field("email") String email);

    @POST("client/new-password")
    @FormUrlEncoded
    Call<Login> setClientNewPassword(@Field("code") String pinCode, @Field("password") String newPassword
            , @Field("password_confirmation") String passwordConfirm);
    /*End** **Client***/


}
