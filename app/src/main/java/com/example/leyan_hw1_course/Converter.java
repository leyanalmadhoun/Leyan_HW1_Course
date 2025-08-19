package com.example.leyan_hw1_course;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.room.TypeConverter;
import java.io.ByteArrayOutputStream;
import java.util.Date;
public class Converter {
    @TypeConverter
    public static Long toLong(Date date) {
        return date == null ? null : date.getTime();
    }
    @TypeConverter
    public static Date toDate(Long time) {
        return time == null ? null : new Date(time);
    }
    @TypeConverter
    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        if (bitmap == null) return null;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }
    @TypeConverter
    public static Bitmap getByteAsBitmap(byte[] bytes) {
        return (bytes == null || bytes.length == 0) ? null
                : BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}
