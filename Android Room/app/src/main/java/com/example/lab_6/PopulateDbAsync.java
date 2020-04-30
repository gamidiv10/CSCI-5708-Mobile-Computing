package com.example.lab_6;

import android.os.AsyncTask;

class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
    private final CourseDao mDao;


    public PopulateDbAsync(CourseRoomDatabase db) {
        mDao = db.courseDao();

    }

    @Override
    protected Void doInBackground(final Void... params) {
        mDao.deleteAll();
        Course course = new Course("DMWA");
        mDao.insert(course);
        course = new Course("MC");
        mDao.insert(course);
        return null;
    }
}
