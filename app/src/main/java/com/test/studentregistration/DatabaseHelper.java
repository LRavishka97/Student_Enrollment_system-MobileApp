package com.test.studentregistration;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Database Information
    private static final String DB_NAME = "Std_details.db";
    private static final int DB_VERSION = 1;

    // Table Names
    private static final String TABLE_NAME = "users";
    private static final String TABLE_NAME_STUDENT = "student";

    // Columns user table
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";

    // Columns Student table
    private static final String COLUMN_STUDENT_ID = "_id";
    private static final String COLUMN_STUDENT_NAME = "name";
    private static final String COLUMN_STUDENT_EMAIL = "email";
    //private static final String COLUMN_STUDENT_ID_NUMBER = "id_number";
    private static final String COLUMN_STUDENT_DOB = "dob";
    private static final String COLUMN_STUDENT_PHONE = "phone";

    // Create tables
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NAME + " TEXT,"
            + COLUMN_EMAIL + " TEXT,"
            + COLUMN_USERNAME + " TEXT,"
            + COLUMN_PASSWORD + " TEXT"
            + ")";
    private static final String CREATE_TABLE_STUDENT = "CREATE TABLE " + TABLE_NAME_STUDENT + "("
            + COLUMN_STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_STUDENT_NAME + " TEXT,"
            + COLUMN_STUDENT_EMAIL + " TEXT,"
            + COLUMN_STUDENT_DOB + " TEXT,"
            + COLUMN_STUDENT_PHONE + " TEXT"
            + ")";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE_STUDENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_STUDENT);
        onCreate(db);
    }

    public void insertUser(String name, String email, String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public boolean validateLogin(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {COLUMN_ID};
        String selection = COLUMN_USERNAME + " = ? AND " + COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};

        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        boolean loginSuccessful = cursor.getCount() > 0;

        cursor.close();
        db.close();

        return loginSuccessful;
    }
      //insert student table
      public void insertStudent(String name, String email,  String dob, String phone) {
          SQLiteDatabase db = this.getWritableDatabase();

          ContentValues values = new ContentValues();
          values.put(COLUMN_STUDENT_NAME, name);
          values.put(COLUMN_STUDENT_EMAIL, email);
          values.put(COLUMN_STUDENT_DOB, dob);
          values.put(COLUMN_STUDENT_PHONE, phone);

          db.insert(TABLE_NAME_STUDENT, null, values);
          db.close();
      }

    public Student getStudentData(int studentId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {
                COLUMN_STUDENT_NAME,
                COLUMN_STUDENT_EMAIL,
                COLUMN_STUDENT_DOB,
                COLUMN_STUDENT_PHONE
        };
        String selection = COLUMN_STUDENT_ID + " = ?";
        String[] selectionArgs = {String.valueOf(studentId)};

        Cursor cursor = db.query(TABLE_NAME_STUDENT, columns, selection, selectionArgs, null, null, null);
        Student student = null;

        if (cursor != null && cursor.moveToFirst()) {
            try {
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STUDENT_NAME));
                String email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STUDENT_EMAIL));
                String dob = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STUDENT_DOB));
                String phone = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STUDENT_PHONE));

                student = new Student(name, email, dob, phone);
            } catch (IllegalArgumentException e) {
                // Handle the case where the column does not exist
            } finally {
                cursor.close(); // Close the cursor when done
            }
        } else {
            Log.e("DatabaseHelper", "Database Query error");
        }

        return student; // Return the Student object or null
    }

    public void deleteStudent(int studentId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = COLUMN_STUDENT_ID + " = ?";
        String[] whereArgs = {String.valueOf(studentId)};

        // Delete the student record
        db.delete(TABLE_NAME_STUDENT, whereClause, whereArgs);

        // Close the database connection
        db.close();
    }

    public void updateStudent(int studentId, String name, String email, String dob, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_STUDENT_NAME, name);
        values.put(COLUMN_STUDENT_EMAIL, email);
        values.put(COLUMN_STUDENT_DOB, dob);
        values.put(COLUMN_STUDENT_PHONE, phone);

        String whereClause = COLUMN_STUDENT_ID + " = ?";
        String[] whereArgs = {String.valueOf(studentId)};

        // Update the student record
        int rowsAffected = db.update(TABLE_NAME_STUDENT, values, whereClause, whereArgs);

        // Check if the update was successful
        if (rowsAffected > 0) {
            Log.d("DatabaseHelper", "Student record updated successfully");
        } else {
            Log.e("DatabaseHelper", "Failed to update student record");
        }


        db.close();
    }



}

