package com.college.lostfound.model;

import java.time.LocalDate;

public class Item {
    private int itemId, userId, categoryId;
    private String itemName, description, location, type, status, contactInfo, categoryName, reporterName;
    private LocalDate itemDate;

    public int getItemId(){return itemId;} public void setItemId(int v){itemId=v;}
    public int getUserId(){return userId;} public void setUserId(int v){userId=v;}
    public int getCategoryId(){return categoryId;} public void setCategoryId(int v){categoryId=v;}
    public String getItemName(){return itemName;} public void setItemName(String v){itemName=v;}
    public String getDescription(){return description;} public void setDescription(String v){description=v;}
    public String getLocation(){return location;} public void setLocation(String v){location=v;}
    public LocalDate getItemDate(){return itemDate;} public void setItemDate(LocalDate v){itemDate=v;}
    public String getType(){return type;} public void setType(String v){type=v;}
    public String getStatus(){return status;} public void setStatus(String v){status=v;}
    public String getContactInfo(){return contactInfo;} public void setContactInfo(String v){contactInfo=v;}
    public String getCategoryName(){return categoryName;} public void setCategoryName(String v){categoryName=v;}
    public String getReporterName(){return reporterName;} public void setReporterName(String v){reporterName=v;}
}
