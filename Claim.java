package com.college.lostfound.model;

import java.time.LocalDateTime;

public class Claim {
    private int claimId, itemId, userId;
    private String itemName, claimantName, claimDescription, status;
    private LocalDateTime claimDate;
    public int getClaimId(){return claimId;} public void setClaimId(int v){claimId=v;}
    public int getItemId(){return itemId;} public void setItemId(int v){itemId=v;}
    public int getUserId(){return userId;} public void setUserId(int v){userId=v;}
    public String getItemName(){return itemName;} public void setItemName(String v){itemName=v;}
    public String getClaimantName(){return claimantName;} public void setClaimantName(String v){claimantName=v;}
    public String getClaimDescription(){return claimDescription;} public void setClaimDescription(String v){claimDescription=v;}
    public String getStatus(){return status;} public void setStatus(String v){status=v;}
    public LocalDateTime getClaimDate(){return claimDate;} public void setClaimDate(LocalDateTime v){claimDate=v;}
}
