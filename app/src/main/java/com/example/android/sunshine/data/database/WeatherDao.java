package com.example.android.sunshine.data.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.Date;
import java.util.List;

@Dao
public interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void bulkInsert(WeatherEntry... weathers);

    @Query("SELECT * FROM weather WHERE date = :date")
    LiveData<WeatherEntry> getWeatherByDate(Date date);

    @Query("SELECT COUNT(id) from weather WHERE date >= :date")
    int countAllFutureWeather(Date date);

    @Query("DELETE FROM weather WHERE date < :date")
    void deleteOldData(Date date);

    /**
    * Selects all {@link WeatherEntry} entries after a give date, inclusive. The LiveData will
    * be kept in sync with the database, so that it will automatically notify observers when the
    * values in the table change.
    *
    * @param date A {@link Date} from which to select all future weather
    * @return {@link LiveData} list of all {@link WeatherEntry} objects after date
    */
    @Query("SELECT * FROM weather WHERE date >= :date")
    LiveData<List<WeatherEntry>> getCurrentWeatherForecasts(Date date);
}
