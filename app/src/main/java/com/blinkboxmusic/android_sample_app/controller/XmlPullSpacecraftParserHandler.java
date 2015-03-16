package com.blinkboxmusic.android_sample_app.controller;

import com.blinkboxmusic.android_sample_app.model.Spacecraft;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oraziocotroneo on 15/01/2015.
 */
public class XmlPullSpacecraftParserHandler {

    static final String TAG_SPACECRAFT = "spacecraft";
    static final String TAG_NAME = "name";
    static final String TAG_AFFILIATION = "affiliation";
    static final String TAG_CLASS = "class";
    static final String TAG_ARMAMENT = "armament";
    static final String TAG_DEFENCE = "defence";
    static final String TAG_SIZE = "size";
    static final String TAG_IMAGE = "image";

    List<Spacecraft> spacecrafts = null;


    public XmlPullSpacecraftParserHandler() {
        this.spacecrafts = new ArrayList<>();
    }

    public List<Spacecraft> getSpacecrafts() {
        return spacecrafts;
    }

    public List<Spacecraft> parseXML(final InputStream inputStream) throws XmlPullParserException,IOException
    {
        XmlPullParserFactory pullParserFactory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = pullParserFactory.newPullParser();

        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        parser.setInput(inputStream, null);

        int eventType = parser.getEventType();

        Spacecraft currentSpacecraft = null;
        List<String> armaments = null;
        List<String> defences = null;

        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                case XmlPullParser.START_TAG:
                    String tagName = parser.getName();

                    if (tagName.equals(TAG_SPACECRAFT)) {
                        currentSpacecraft = new Spacecraft();
                        armaments = new ArrayList<String>();
                        defences = new ArrayList<String>();
                    } else if (tagName.equals(TAG_NAME)) {
                        currentSpacecraft.setName(parser.nextText());
                    } else if (tagName.equals(TAG_AFFILIATION)) {
                        currentSpacecraft.setAffiliation(parser.nextText());
                    } else if (tagName.equals(TAG_CLASS)) {
                        currentSpacecraft.setSpacecraftClass(parser.nextText());
                    } else if (tagName.equals(TAG_ARMAMENT)) {
                        armaments.add(parser.nextText());
                    } else if (tagName.equals(TAG_DEFENCE)) {
                        defences.add(parser.nextText());
                    } else if (tagName.equals(TAG_SIZE)) {
                        currentSpacecraft.setSize(parser.nextText());
                    } else if (tagName.equals(TAG_IMAGE)) {
                        currentSpacecraft.setImageName(parser.nextText());
                    }
                    break;
                case XmlPullParser.END_TAG:
                    String endTagName = parser.getName();
                    if (endTagName.equals(TAG_SPACECRAFT)) {
                        StringBuilder armamentsStringBuilder = new StringBuilder();
                        for (String armament: armaments) {
                            armamentsStringBuilder.append(armament).append(". ");
                        }
                        currentSpacecraft.setArmaments(armamentsStringBuilder.toString());

                        StringBuilder defencesStringBuilder = new StringBuilder();
                        for (String defence: defences) {
                            defencesStringBuilder.append(defence).append(". ");
                        }
                        currentSpacecraft.setDefences(defencesStringBuilder.toString());

                        spacecrafts.add(currentSpacecraft);
                    }
                    break;
            }
            eventType = parser.next();
        }
        return spacecrafts;
    }
}
