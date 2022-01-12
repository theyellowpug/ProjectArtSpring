package com.theyellowpug.projectArt.exception;

import com.theyellowpug.projectArt.entity.Profile;

public class ImgToDBException extends Exception{

    private Profile profile;

    public Profile getProfile() {
        return profile;
    }

    public ImgToDBException(Profile affectedProfile){
        this.profile = affectedProfile;
    }
}
