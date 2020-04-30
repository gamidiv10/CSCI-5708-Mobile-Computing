package com.example.lab_6;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CourseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Course course);

    @Query("DELETE FROM course_table")
    void deleteAll();

    @Query("SELECT * from course_table ORDER BY course ASC")
    LiveData<List<Course>> getAlphabetizedWords();
}
