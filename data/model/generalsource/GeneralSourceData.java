
package com.example.sofra.data.model.generalsource;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GeneralSourceData implements Parcelable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("restaurant_id")
    @Expose
    private String restaurantId;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("photo_url")
    @Expose
    private String photoUrl;
    @SerializedName("restaurant")
    @Expose
    private GeneralSourceData restaurant;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("starting_at")
    @Expose
    private String startingAt;
    @SerializedName("ending_at")
    @Expose
    private String endingAt;
    @SerializedName("available")
    @Expose
    private Boolean available;
    @SerializedName("region_id")
    @Expose
    private String regionId;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("delivery_time")
    @Expose
    private String deliveryTime;
    @SerializedName("delivery_cost")
    @Expose
    private String deliveryCost;
    @SerializedName("minimum_charger")
    @Expose
    private String minimumCharger;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("whatsapp")
    @Expose
    private String whatsapp;
    @SerializedName("availability")
    @Expose
    private String availability;
    @SerializedName("activated")
    @Expose
    private String activated;
    @SerializedName("rate")
    @Expose
    private float rate;
    @SerializedName("region")
    @Expose
    private GeneralSourceData region;
    @SerializedName("city_id")
    @Expose
    private String cityId;
    @SerializedName("city")
    @Expose
    private GeneralSourceData city;
    @SerializedName("categories")
    @Expose
    private List<Object> categories = null;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("type_text")
    @Expose
    private String typeText;
    @SerializedName("offer_price")
    @Expose
    private String offerPrice;
    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("has_offer")
    @Expose
    private Boolean hasOffer;
    @SerializedName("category")
    @Expose
    private GeneralSourceData category;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("payment_method_id")
    @Expose
    private String paymentMethodId;
    @SerializedName("cost")
    @Expose
    private String cost;
    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("commission")
    @Expose
    private String commission;
    @SerializedName("net")
    @Expose
    private String net;
    @SerializedName("delivered_at")
    @Expose
    private Object deliveredAt;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("refuse_reason")
    @Expose
    private Object refuseReason;
    @SerializedName("client_id")
    @Expose
    private String clientId;
    @SerializedName("client")
    @Expose
    private GeneralSourceData client;
    @SerializedName("items")
    @Expose
    private List<GeneralSourceData> items = null;
    @SerializedName("profile_image")
    @Expose
    private String profileImage;
    @SerializedName("pivot")
    @Expose
    private GeneralSourceData pivot;
    @SerializedName("order_id")
    @Expose
    private String orderId;
    @SerializedName("item_id")
    @Expose
    private String itemId;
    @SerializedName("quantity")
    @Expose
    private String quantity;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("commissions")
    @Expose
    private String commissions;
    @SerializedName("payments")
    @Expose
    private String payments;
    @SerializedName("net_commissions")
    @Expose
    private Double netCommissions;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("title_en")
    @Expose
    private String titleEn;
    @SerializedName("content_en")
    @Expose
    private String contentEn;
    @SerializedName("action")
    @Expose
    private String action;
    @SerializedName("order")
    @Expose
    private GeneralSourceData order;
    @SerializedName("user")
    @Expose
    private GeneralSourceData user;
    @SerializedName("review")
    @Expose
    private GeneralSourceData review;

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public GeneralSourceData getCity() {
        return city;
    }

    public void setCity(GeneralSourceData city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public GeneralSourceData getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(GeneralSourceData restaurant) {
        this.restaurant = restaurant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStartingAt() {
        return startingAt;
    }

    public void setStartingAt(String startingAt) {
        this.startingAt = startingAt;
    }

    public String getEndingAt() {
        return endingAt;
    }

    public void setEndingAt(String endingAt) {
        this.endingAt = endingAt;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(String deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public String getMinimumCharger() {
        return minimumCharger;
    }

    public void setMinimumCharger(String minimumCharger) {
        this.minimumCharger = minimumCharger;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getActivated() {
        return activated;
    }

    public void setActivated(String activated) {
        this.activated = activated;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public GeneralSourceData getRegion() {
        return region;
    }

    public void setRegion(GeneralSourceData region) {
        this.region = region;
    }

    public List<Object> getCategories() {
        return categories;
    }

    public void setCategories(List<Object> categories) {
        this.categories = categories;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTypeText() {
        return typeText;
    }

    public void setTypeText(String typeText) {
        this.typeText = typeText;
    }

    public String getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(String offerPrice) {
        this.offerPrice = offerPrice;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Boolean getHasOffer() {
        return hasOffer;
    }

    public void setHasOffer(Boolean hasOffer) {
        this.hasOffer = hasOffer;
    }

    public GeneralSourceData getCategory() {
        return category;
    }

    public void setCategory(GeneralSourceData category) {
        this.category = category;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }

    public String getNet() {
        return net;
    }

    public void setNet(String net) {
        this.net = net;
    }

    public Object getDeliveredAt() {
        return deliveredAt;
    }

    public void setDeliveredAt(Object deliveredAt) {
        this.deliveredAt = deliveredAt;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Object getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(Object refuseReason) {
        this.refuseReason = refuseReason;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public GeneralSourceData getClient() {
        return client;
    }

    public void setClient(GeneralSourceData client) {
        this.client = client;
    }

    public List<GeneralSourceData> getItems() {
        return items;
    }

    public void setItems(List<GeneralSourceData> items) {
        this.items = items;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public GeneralSourceData getPivot() {
        return pivot;
    }

    public void setPivot(GeneralSourceData pivot) {
        this.pivot = pivot;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getCommissions() {
        return commissions;
    }

    public void setCommissions(String commissions) {
        this.commissions = commissions;
    }

    public String getPayments() {
        return payments;
    }

    public void setPayments(String payments) {
        this.payments = payments;
    }

    public Double getNetCommissions() {
        return netCommissions;
    }

    public void setNetCommissions(Double netCommissions) {
        this.netCommissions = netCommissions;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public String getContentEn() {
        return contentEn;
    }

    public void setContentEn(String contentEn) {
        this.contentEn = contentEn;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public GeneralSourceData getOrder() {
        return order;
    }

    public void setOrder(GeneralSourceData order) {
        this.order = order;
    }

    public GeneralSourceData getUser() {
        return user;
    }

    public void setUser(GeneralSourceData user) {
        this.user = user;
    }

    public GeneralSourceData getReview() {
        return review;
    }

    public void setReview(GeneralSourceData review) {
        this.review = review;
    }

    public static Creator<GeneralSourceData> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.photo);
        dest.writeValue(this.restaurantId);
        dest.writeString(this.updatedAt);
        dest.writeString(this.createdAt);
        dest.writeValue(this.id);
        dest.writeString(this.photoUrl);
        dest.writeParcelable(this.restaurant, flags);
        dest.writeString(this.description);
        dest.writeString(this.price);
        dest.writeString(this.startingAt);
        dest.writeString(this.endingAt);
        dest.writeValue(this.available);
        dest.writeString(this.regionId);
        dest.writeString(this.email);
        dest.writeString(this.deliveryTime);
        dest.writeString(this.deliveryCost);
        dest.writeString(this.minimumCharger);
        dest.writeString(this.phone);
        dest.writeString(this.whatsapp);
        dest.writeString(this.availability);
        dest.writeString(this.activated);
        dest.writeValue(this.rate);
        dest.writeParcelable(this.region, flags);
        dest.writeString(this.cityId);
        dest.writeParcelable(this.city, flags);
        dest.writeList(this.categories);
        dest.writeString(this.type);
        dest.writeString(this.content);
        dest.writeString(this.typeText);
        dest.writeString(this.offerPrice);
        dest.writeString(this.categoryId);
        dest.writeValue(this.hasOffer);
        dest.writeParcelable(this.category, flags);
        dest.writeString(this.note);
        dest.writeString(this.address);
        dest.writeString(this.paymentMethodId);
        dest.writeString(this.cost);
        dest.writeString(this.total);
        dest.writeString(this.commission);
        dest.writeString(this.net);
        dest.writeParcelable((Parcelable) this.deliveredAt, flags);
        dest.writeString(this.state);
        dest.writeParcelable((Parcelable) this.refuseReason, flags);
        dest.writeString(this.clientId);
        dest.writeParcelable(this.client, flags);
        dest.writeList(this.items);
        dest.writeString(this.profileImage);
        dest.writeParcelable(this.pivot, flags);
        dest.writeString(this.orderId);
        dest.writeString(this.itemId);
        dest.writeString(this.quantity);
    }

    private GeneralSourceData() {
    }

    private GeneralSourceData(Parcel in) {
        this.name = in.readString();
        this.photo = in.readString();
        this.restaurantId = in.readString();
        this.updatedAt = in.readString();
        this.createdAt = in.readString();
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.photoUrl = in.readString();
        this.restaurant = in.readParcelable(GeneralSourceData.class.getClassLoader());
        this.description = in.readString();
        this.price = in.readString();
        this.startingAt = in.readString();
        this.endingAt = in.readString();
        this.available = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.regionId = in.readString();
        this.email = in.readString();
        this.deliveryTime = in.readString();
        this.deliveryCost = in.readString();
        this.minimumCharger = in.readString();
        this.phone = in.readString();
        this.whatsapp = in.readString();
        this.availability = in.readString();
        this.activated = in.readString();
        this.rate = (float) in.readValue(Integer.class.getClassLoader());
        this.region = in.readParcelable(GeneralSourceData.class.getClassLoader());
        this.cityId = in.readString();
        this.city = in.readParcelable(GeneralSourceData.class.getClassLoader());
        this.categories = new ArrayList<>();
        in.readList(this.categories, Object.class.getClassLoader());
        this.type = in.readString();
        this.content = in.readString();
        this.typeText = in.readString();
        this.offerPrice = in.readString();
        this.categoryId = in.readString();
        this.hasOffer = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.category = in.readParcelable(GeneralSourceData.class.getClassLoader());
        this.note = in.readString();
        this.address = in.readString();
        this.paymentMethodId = in.readString();
        this.cost = in.readString();
        this.total = in.readString();
        this.commission = in.readString();
        this.net = in.readString();
        this.deliveredAt = in.readParcelable(Object.class.getClassLoader());
        this.state = in.readString();
        this.refuseReason = in.readParcelable(Object.class.getClassLoader());
        this.clientId = in.readString();
        this.client = in.readParcelable(GeneralSourceData.class.getClassLoader());
        this.items = new ArrayList<>();
        in.readList(this.items, GeneralSourceData.class.getClassLoader());
        this.profileImage = in.readString();
        this.pivot = in.readParcelable(GeneralSourceData.class.getClassLoader());
        this.orderId = in.readString();
        this.itemId = in.readString();
        this.quantity = in.readString();
    }

    public static final Creator<GeneralSourceData> CREATOR
            = new Creator<GeneralSourceData>() {
        @Override
        public GeneralSourceData createFromParcel(Parcel source) {
            return new GeneralSourceData(source);
        }

        @Override
        public GeneralSourceData[] newArray(int size) {
            return new GeneralSourceData[size];
        }
    };
}
