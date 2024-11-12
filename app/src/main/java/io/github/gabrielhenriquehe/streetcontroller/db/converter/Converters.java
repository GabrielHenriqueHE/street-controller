package io.github.gabrielhenriquehe.streetcontroller.db.converter;

import androidx.room.TypeConverter;
import java.util.Date;

public class Converters {

    @TypeConverter
    public static Long fromDate(Date date) {
        return date == null ? null : date.getTime(); // Converte Date para Long (milissegundos)
    }

    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp); // Converte Long para Date
    }
}

