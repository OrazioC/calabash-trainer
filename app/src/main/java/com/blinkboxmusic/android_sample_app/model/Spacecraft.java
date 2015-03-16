package com.blinkboxmusic.android_sample_app.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by orazio on 16/12/14.
 */
public class Spacecraft implements Parcelable{

    private String name;
    private String affiliation;
    private String spacecraftClass;
    private String armaments;
    private String defences;
    private String size;
    private String imageName;

    public Spacecraft() {
    }

    public Spacecraft(String name, String affiliation, String spacecraftClass, String armaments, String defences, String size, String imageName) {
        this.name = name;
        this.affiliation = affiliation;
        this.spacecraftClass = spacecraftClass;
        this.armaments = armaments;
        this.defences = defences;
        this.size = size;
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
        this.spacecraftClass = in.readString();
        this.armaments = in.readString();
        this.defences = in.readString();
        this.size = in.readString();
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
        dest.writeString(spacecraftClass);
        dest.writeString(armaments);
        dest.writeString(defences);
        dest.writeString(size);
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

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String NEW_LINE = System.getProperty("line.separator");

        result.append(this.getClass().getName() + " Object {" + NEW_LINE);
        result.append(" Name: ").append(name).append( NEW_LINE);
        result.append(" Affiliation: " + affiliation + NEW_LINE);
        result.append(" Class: " + spacecraftClass + NEW_LINE );
        result.append(" Armaments: " + armaments + NEW_LINE );
        result.append(" Defences: " + defences + NEW_LINE );
        result.append(" Size: " + size + NEW_LINE );
        result.append(" Image: " + imageName + NEW_LINE);
        result.append("}");

        return result.toString();
    }

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

    public String getSpacecraftClass() {
        return spacecraftClass;
    }

    public void setSpacecraftClass(String spacecraftClass) {
        this.spacecraftClass = spacecraftClass;
    }

    public String getArmaments() {
        return armaments;
    }

    public void setArmaments(String armaments) {
        this.armaments = armaments;
    }

    public String getDefences() {
        return defences;
    }

    public void setDefences(String defences) {
        this.defences = defences;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
