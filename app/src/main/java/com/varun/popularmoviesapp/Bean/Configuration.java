package com.varun.popularmoviesapp.Bean;

/**
 * Created by VarunBarve on 10/8/2015.
 */
public class Configuration {

    private String baseUrl, secureBaseUrl, backdropSizes, logoSizes, posterSizes, profileSizes, stillSizes, changeKeys;

    public Configuration(String baseUrl, String secureBaseUrl, String backdropSizes, String logoSizes, String posterSizes, String profileSizes, String stillSizes, String changeKeys) {
        this.baseUrl = baseUrl;
        this.secureBaseUrl = secureBaseUrl;
        this.backdropSizes = backdropSizes;
        this.logoSizes = logoSizes;
        this.posterSizes = posterSizes;
        this.profileSizes = profileSizes;
        this.stillSizes = stillSizes;
        this.changeKeys = changeKeys;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getSecureBaseUrl() {
        return secureBaseUrl;
    }

    public void setSecureBaseUrl(String secureBaseUrl) {
        this.secureBaseUrl = secureBaseUrl;
    }

    public String getBackdropSizes() {
        return backdropSizes;
    }

    public void setBackdropSizes(String backdropSizes) {
        this.backdropSizes = backdropSizes;
    }

    public String getLogoSizes() {
        return logoSizes;
    }

    public void setLogoSizes(String logoSizes) {
        this.logoSizes = logoSizes;
    }

    public String getPosterSizes() {
        return posterSizes;
    }

    public void setPosterSizes(String posterSizes) {
        this.posterSizes = posterSizes;
    }

    public String getProfileSizes() {
        return profileSizes;
    }

    public void setProfileSizes(String profileSizes) {
        this.profileSizes = profileSizes;
    }

    public String getStillSizes() {
        return stillSizes;
    }

    public void setStillSizes(String stillSizes) {
        this.stillSizes = stillSizes;
    }

    public String getChangeKeys() {
        return changeKeys;
    }

    public void setChangeKeys(String changeKeys) {
        this.changeKeys = changeKeys;
    }
}
