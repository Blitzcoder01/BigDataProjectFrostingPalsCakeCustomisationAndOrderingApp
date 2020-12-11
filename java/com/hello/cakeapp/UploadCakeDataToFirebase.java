package com.hello.cakeapp;
import com.google.firebase.database.IgnoreExtraProperties;
public class UploadCakeDataToFirebase {
    public String cakename_usr;
    public String flavour_usr;
    public String weight_usr;
    public String type_usr;
    public String message_usr;
    public String detail_usr;
    public String address_usr;
    public String name_usr;
    public String phone_user;

    public String url;
    public UploadCakeDataToFirebase(String cakename_usr,String flavour_usr,String weight_usr,String type_usr,String message_usr,String detail_usr, String address_usr,String name_usr, String phone_user,String url) {
        this.cakename_usr = cakename_usr;
        this.flavour_usr=flavour_usr;
        this.weight_usr=weight_usr;
        this.type_usr=type_usr;
        this.message_usr=message_usr;
        this.detail_usr=detail_usr;
        this.address_usr=address_usr;
        this.name_usr=name_usr;
        this.phone_user=phone_user;

        this.url= url;

    }

    public String getCakename_usr() {
        return cakename_usr;
    }

    public void setCakename_usr(String cakename_usr) {
        this.cakename_usr = cakename_usr;
    }

    public String getFlavour_usr() {
        return flavour_usr;
    }

    public void setFlavour_usr(String flavour_usr) {
        this.flavour_usr = flavour_usr;
    }

    public String getWeight_usr() {
        return weight_usr;
    }

    public void setWeight_usr(String weight_usr) {
        this.weight_usr = weight_usr;
    }

    public String getType_usr() {
        return type_usr;
    }

    public void setType_usr(String type_usr) {
        this.type_usr = type_usr;
    }

    public String getMessage_usr() {
        return message_usr;
    }

    public void setMessage_usr(String message_usr) {
        this.message_usr = message_usr;
    }

    public String getAddress_usr() {
        return address_usr;
    }

    public void setAddress_usr(String address_usr) {
        this.address_usr = address_usr;
    }

    public String getName_usr() {
        return name_usr;
    }

    public void setName_usr(String name_usr) {
        this.name_usr = name_usr;
    }

    public String getPhone_user() {
        return phone_user;
    }

    public void setPhone_user(String phone_user) {
        this.phone_user = phone_user;
    }

    public String getDetail_usr() {
        return detail_usr;
    }

    public void setDetail_usr(String detail_usr) {
        this.detail_usr = detail_usr;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
