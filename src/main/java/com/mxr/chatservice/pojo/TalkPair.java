package com.mxr.chatservice.pojo;

public class TalkPair {
    private String ip1;
    private String ip2;
    private String username1;
    private String username2;
    private boolean isEstablished;

    public String getIp1() {
        return ip1;
    }

    public void setIp1(String ip1) {
        this.ip1 = ip1;
    }

    public String getIp2() {
        return ip2;
    }

    public void setIp2(String ip2) {
        this.ip2 = ip2;
    }

    public String getUsername1() {
        return username1;
    }

    public void setUsername1(String username1) {
        this.username1 = username1;
    }

    public String getUsername2() {
        return username2;
    }

    public void setUsername2(String username2) {
        this.username2 = username2;
    }

    public boolean isEstablished() {
        return isEstablished;
    }

    public void setEstablished(boolean established) {
        isEstablished = established;
    }

    public String getAnotherIp(String ip){
        if(ip.equals(ip1)){
            return ip2;
        }else{
            return ip1;
        }
    }

    public String getAnotherUsername(String username){
        if(username.equals(username1)){
            return username2;
        }else{
            return username1;
        }
    }
}
