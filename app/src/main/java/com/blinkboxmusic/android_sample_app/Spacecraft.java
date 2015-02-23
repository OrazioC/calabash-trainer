package com.blinkboxmusic.android_sample_app;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by orazio on 16/12/14.
 */
public class Spacecraft implements Parcelable{

    private String name;
    private String affiliation;
    private String description;
    private String imageName;

    public Spacecraft() {
    }

    public Spacecraft(String name, String affiliation, String description, String imageName) {
        this.name = name;
        this.affiliation = affiliation;
        this.description = description;
        this.imageName = imageName;
    }

    /**
     * Use when reconstructing User object from parcel
     * This will be used only by the 'CREATOR'
     * @param in a parcel to read this object
     */
    public Spacecraft(Parcel in) {
        this.name = in.readString();
        this.affiliation = in.readString();
        this.description = in.readString();
        this.imageName = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(affiliation);
        dest.writeString(description);
        dest.writeString(imageName);
    }

    /**
     * This field is needed for Android to be able to
     * create new objects, individually or as arrays
     *
     * If you don’t do that, Android framework will raises an exception
     * Parcelable protocol requires a Parcelable.Creator object
     * called CREATOR
     */
    public static final Parcelable.Creator<Spacecraft> CREATOR = new Parcelable.Creator<Spacecraft>() {

        public Spacecraft createFromParcel(Parcel in) {
            return new Spacecraft(in);
        }

        public Spacecraft[] newArray(int size) {
            return new Spacecraft[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
