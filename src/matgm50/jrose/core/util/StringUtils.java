package matgm50.jrose.core.util;

public class StringUtils {

    public static String getStrBetweenTags(String str, String tag) {

        String region;
        int regionStart = 0, regionEnd = 0;

        int tagLen = tag.length();
        int strLen = str.length();

        boolean recording = false;

        for(int i = 0; i < strLen; i++) {

            if(str.regionMatches(i, tag, 0, tagLen)) {

                if(recording) {

                    regionEnd = i;
                    break;

                } else {

                    recording = true;
                    regionStart = i + tagLen;

                }

            }

        }

        region = str.substring(regionStart, regionEnd);

        return region;

    }

}
