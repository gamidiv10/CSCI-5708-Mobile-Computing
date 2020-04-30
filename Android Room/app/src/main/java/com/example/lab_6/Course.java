package com.example.lab_6;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "course_table")

public class Course {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "course")
    private String course;

    public Course(@NonNull String course)
    {
        this.course = course;
    }

    public String getCourse(){return this.course;}
}
