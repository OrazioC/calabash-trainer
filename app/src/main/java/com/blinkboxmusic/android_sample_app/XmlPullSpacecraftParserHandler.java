package com.blinkboxmusic.android_sample_app;

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
    static final String TAG_DESCRIPTION = "description";
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

        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                case XmlPullParser.START_TAG:
                    String tagName = parser.getName();

                    if (tagName.equals(TAG_SPACECRAFT)) {
                        currentSpacecraft = new Spacecraft();
                    } else if (tagName.equals(TAG_NAME)) {
                        currentSpacecraft.setName(parser.nextText());
                    } else if (tagName.equals(TAG_AFFILIATION)) {
                        currentSpacecraft.setAffiliation(parser.nextText());
                    } else if (tagName.equals(TAG_DESCRIPTION)) {
                        currentSpacecraft.setDescription(parser.nextText());
                    } else if (tagName.equals(TAG_IMAGE)) {
                        currentSpacecraft.setImageName(parser.nextText());
                    }
                    break;
                case XmlPullParser.END_TAG:
                    String endTagName = parser.getName();
                    if (endTagName.equals(TAG_SPACECRAFT)) {
                        spacecrafts.add(currentSpacecraft);
                    }
                    break;
            }
            eventType = parser.next();
        }
        return spacecrafts;
    }
}
