package com.example.hoang.bookmovietickets.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.hoang.bookmovietickets.Model.MovieModel;
import com.example.hoang.bookmovietickets.Model.UserModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hoang on 11/14/2015.
 */
public class DBHelper extends SQLiteOpenHelper{

    public static final String TAG = "MOVIE";

    //movie table
    public static final String TABLE_NAME = "movie";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_IS_BOOKED = "booked";
    public static final String COLUMN_IS_FAVORITE = "favorite";
    public static final String COLUMN_RATING = "rating";
    public static final String COLUMN_ID = "id";

    //user table
    public static final String USER_TABLE_NAME = "users";
    public static final String COLUMN_USER_NAME = "username";
    public static final String COLUMN_USER_PASS = "password";
    public static final String COLUMN_USER_ID = "id";

    public static final String DATABASE_NAME = "bookticket.db";
    public static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_MOVIE = "create table " + TABLE_NAME + "( "
                                                    + COLUMN_ID + " integer primary key autoincrement, "
                                                    + COLUMN_TITLE + " text, "
                                                    + COLUMN_DESCRIPTION + " text, "
                                                    + COLUMN_IMAGE + " text,"
                                                    + COLUMN_IS_BOOKED + " boolean,"
                                                    + COLUMN_IS_FAVORITE + " boolean,"
                                                    + COLUMN_RATING + " float default 2.0" + ")";

    private static final String SQL_CREATE_USER = "create table " + USER_TABLE_NAME + "( "
                                                    + COLUMN_USER_ID + " integer primary key autoincrement, "
                                                    + COLUMN_USER_NAME + " text, "
                                                    + COLUMN_USER_PASS + " text" + ")";
    private static final String SQL_DELETE_MOVIE = "drop table if exists " + TABLE_NAME;
    private static final String SQL_DELETE_USER = "drop table if exists " + USER_TABLE_NAME;

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_MOVIE);
        db.execSQL(SQL_CREATE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_MOVIE);
        db.execSQL(SQL_DELETE_USER);
        onCreate(db);
    }

    private MovieModel getModel(Cursor c){
        MovieModel model = new MovieModel();
        model.setId(c.getInt(c.getColumnIndex(COLUMN_ID)));
        model.setTitle(c.getString(c.getColumnIndex(COLUMN_TITLE)));
        model.setDescription(c.getString(c.getColumnIndex(COLUMN_DESCRIPTION)));
        model.setImage(c.getString(c.getColumnIndex(COLUMN_IMAGE)));
        model.setBooked(c.getInt(c.getColumnIndex(COLUMN_IS_BOOKED)) == 0 ? false : true);
        model.setFavorited(c.getInt(c.getColumnIndex(COLUMN_IS_FAVORITE)) == 0 ? false : true);
        model.setRating(c.getFloat(c.getColumnIndex(COLUMN_RATING)));
        return model;
    }

    private UserModel getUserModel(Cursor c){
        UserModel userModel = new UserModel();
        userModel.setId(c.getInt(c.getColumnIndex(COLUMN_USER_ID)));
        userModel.setUsername(c.getString(c.getColumnIndex(COLUMN_USER_NAME)));
        userModel.setPassword(c.getString(c.getColumnIndex(COLUMN_USER_PASS)));
        return userModel;
    }

    private ContentValues getContent(MovieModel model){
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, model.getTitle());
        values.put(COLUMN_DESCRIPTION, model.getDescription());
        values.put(COLUMN_IMAGE, model.getImage());
        values.put(COLUMN_IS_BOOKED, model.isBooked());
        values.put(COLUMN_IS_FAVORITE, model.isFavorited());

        return values;
    }

    private ContentValues getUserContent(UserModel userModel){
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, userModel.getUsername());
        values.put(COLUMN_USER_PASS, userModel.getPassword());

        return values;
    }

    public void createMovie(MovieModel model){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = getContent(model);
        long n = database.insert(TABLE_NAME, null, values);
        if(n < 0){
            Log.d(TAG, "Create Movie failed");
        }else {
            Log.d(TAG, "Create Movie successful");
        }
    }

    public void createUser(UserModel model){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = getUserContent(model);
        long n = database.insert(USER_TABLE_NAME, null, values);
        if(n < 0){
            Log.d(TAG, "Create User failed");
        }else {
            Log.d(TAG, "Create User successful");
        }
    }

    public void updateMovie(MovieModel model){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = getContent(model);
        long n = database.update(TABLE_NAME, values, COLUMN_ID + " = ?", new String[]{String.valueOf(model.getId())});
        if(n < 0){
            Log.d(TAG, "Updated Movie failed");
        }else {
            Log.d(TAG, "Updated Movie successful");
        }
    }

    public void updateRatingMovie(MovieModel model){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = getContent(model);
        values.put(COLUMN_RATING, model.getRating());
        long n = database.update(TABLE_NAME, values, COLUMN_ID + " = ?", new String[]{String.valueOf(model.getId())});
        if(n < 0){
            Log.d(TAG, "Updated Movie failed");
        }else {
            Log.d(TAG, "Updated Movie successful");
        }
    }

    public void updateUser(UserModel model){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = getUserContent(model);
        long n = database.update(USER_TABLE_NAME, values, COLUMN_ID + " = ?", new String[]{String.valueOf(model.getId())});
        if(n < 0){
            Log.d(TAG, "Updated User failed");
        }else {
            Log.d(TAG, "Updated User successful");
        }
    }

    public MovieModel getMovie(int id){
        SQLiteDatabase database = getReadableDatabase();
        String query = "select * from " + TABLE_NAME + " where " + COLUMN_ID + " = " + id;
        Cursor c = database.rawQuery(query, null);
        if (c.moveToNext()){
            return getModel(c);
        }
        return null;
    }

    public UserModel getUserModel(int id){
        SQLiteDatabase database = getReadableDatabase();
        String query = "select * from " + USER_TABLE_NAME + " where " + COLUMN_ID + " = " + id;
        Cursor c = database.rawQuery(query, null);
        if (c.moveToNext()){
            return getUserModel(c);
        }
        return null;
    }

    public List<MovieModel> getListMovie(){
        SQLiteDatabase database = getReadableDatabase();
        String query = "select * from " + TABLE_NAME;
        Cursor c = database.rawQuery(query, null);
        List<MovieModel> list = new ArrayList<MovieModel>();
        c.moveToFirst();
        while (!c.isAfterLast()){
            list.add(getModel(c));
            c.moveToNext();
        }
        database.close();
        return list;
    }


    public List<UserModel> getListUser(){
        SQLiteDatabase database = getReadableDatabase();
        String query = "select * from " + USER_TABLE_NAME;
        Cursor c = database.rawQuery(query, null);
        List<UserModel> list = new ArrayList<UserModel>();
        c.moveToFirst();
        while (!c.isAfterLast()){
            list.add(getUserModel(c));
            c.moveToNext();
        }
        database.close();
        return list;
    }

    public List<MovieModel> getListBookedMovie(int isBooked){
        SQLiteDatabase database = getReadableDatabase();
        String query = "select * from " + TABLE_NAME + " where " + COLUMN_IS_BOOKED + " = " + isBooked;
        Cursor c = database.rawQuery(query, null);
        List<MovieModel> list = new ArrayList<MovieModel>();
        c.moveToFirst();
        while (!c.isAfterLast()){
            list.add(getModel(c));
            c.moveToNext();
        }
        database.close();
        return list;
    }

    public List<MovieModel> getListFavoriteMovie(int isFavorite){
        SQLiteDatabase database = getReadableDatabase();
        String query = "select * from " + TABLE_NAME + " where " + COLUMN_IS_FAVORITE + " = " + isFavorite;
        Cursor c = database.rawQuery(query, null);
        List<MovieModel> list = new ArrayList<MovieModel>();
        c.moveToFirst();
        while (!c.isAfterLast()){
            list.add(getModel(c));
            c.moveToNext();
        }
        database.close();
        return list;
    }

}
