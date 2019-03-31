package baemin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by hong2 on 30/03/2019
 * Time : 5:10 PM
 */

public class Problem_2 {
    public static final String SEPARATOR_ENTER = "\n";
    public static final String SEPARATOR_COMMA = ",";
    public static final int BASE_NUMBER = 10;

    public static String photo = "photo.jpg, Warsaw, 2013-09-05 14:08:15\n" +
            "john.png, London, 2015-06-20 15:13:22\n" +
            "myFriends.png, Warsaw, 2013-09-05 14:07:13\n" +
            "Eiffel.jpg, Paris, 2015-07-23 08:03:02\n" +
            "pisatower.jpg, Paris, 2015-07-22 23:59:59\n" +
            "BOB.jpg, London, 2015-08-05 00:02:03\n" +
            "notredame.png, Paris, 2015-09-01 12:00:00\n" +
            "me.jpg, Warsaw, 2013-09-06 15:40:22\n" +
            "a.png, Warsaw, 2016-02-13 13:33:50\n" +
            "b.jpg, Warsaw, 2016-01-02 15:12:22\n" +
            "c.jpg, Warsaw, 2016-01-02 14:34:30\n" +
            "d.jpg, Warsaw, 2016-01-02 15:15:01\n" +
            "e.png, Warsaw, 2016-01-02 09:49:09\n" +
            "f.png, Warsaw, 2016-01-02 10:55:32\n" +
            "g.jpg, Warsaw, 2016-02-29 22:13:11";

    public static void main(String[] args) {
        Problem_2 problem_2 = new Problem_2();
        System.out.println(problem_2.solution(photo));
    }
    public String solution(String S) {
        StringBuilder stringBuffer = new StringBuilder();
        String[] photoBundle = S.split(SEPARATOR_ENTER);
        List<Photo> photoList = makePhotoList(photoBundle);
        Album album = new Album(makePhotoAlbum(photoList));

        for (Photo photo : photoList) {
            stringBuffer.append(album.getPhoto(photo)).append("\n");
        }

        return stringBuffer.toString();
    }


    private Map<String, List<Photo>> makePhotoAlbum(List<Photo> photoList) {
        Map<String, List<Photo>> album = new HashMap<>();
        photoList.forEach(photo -> {
            List<Photo> temp;
            if (album.get(photo.getRegion()) == null) {
                temp = new ArrayList<>();
            } else {
                temp = album.get(photo.getRegion());
            }
            temp.add(photo);
            album.put(photo.getRegion(), temp.stream()
                    .sorted((e1,e2) -> e1.getTimeLine().compareTo(e2.timeLine))
                    .collect(Collectors.toList()));
        });
        return album;
    }

    private List<Photo> makePhotoList(String[] photoBundle) {
        List<Photo> photoList = new ArrayList<>();

        for (String fileList : photoBundle) {
            String[] photo = fileList.split(SEPARATOR_COMMA);
            photoList.add(new Photo(photo[0].trim(), photo[0].substring(photo[0].length()-4).trim()
                    , photo[1].trim(), photo[2].trim()));

        }
        return photoList;
    }

    public class Album {
        // <지역 , List<포토>>
        private Map<String, List<Photo>> album;

        public Album(Map<String, List<Photo>> album) {
            this.album = album;
        }

        public String getPhoto(Photo photo) {
            List<Photo> photos = album.get(photo.getRegion());
            int photoNumber = 0;
            Photo findPhoto;
            for (int i=0; i < photos.size(); i++) {
                if (photo.equals(photos.get(i))) {
                    photoNumber = i;
                    break;
                }
            }

            findPhoto = photos.get(photoNumber);
            return getFormattedFileName(photos, photoNumber, findPhoto);
        }

        private String getFormattedFileName(List<Photo> photos, int photoNumber, Photo findPhoto) {
            int format = ((photos.size() / BASE_NUMBER) + 1);
            String formatString = makeFileFormat(format);
            return findPhoto.getRegion() + String.format(formatString, photoNumber+1) + findPhoto.getExtension();
        }

        private String makeFileFormat(int format) {
            return "%0" + String.valueOf(format) + "d";
        }
    }

    public class Photo {
        private String flieName;
        private String extension;
        private String region;
        private Date timeLine;

        public String getExtension() {
            return extension;
        }

        public String getRegion() {
            return region;
        }

        public Date getTimeLine() {
            return timeLine;
        }

        public Photo(String fileName, String extension, String region, String timeLine) {
            this.flieName = fileName;
            this.extension = extension;
            this.region = region;
            try {
                this.timeLine = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timeLine);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}

