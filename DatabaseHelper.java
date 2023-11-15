package com.froilanhuar.listaapp_1;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper{
    // The Android's default system path
    // of your application database.
    private static String DB_PATH = "";
    private static String DB_NAME = "ListaOasis.db";
    private SQLiteDatabase myDataBase;
    private final Context myContext;
    private SQLiteOpenHelper sqLiteOpenHelper;

    // Table name in the database.
    public static final String
            ALGO_TOPICS
            = "deportReplica";
    public static final String laTabla = "deportSublimada";
    /**
     * Constructor
     * Takes and keeps a reference of
     * the passed context in order
     * to access the application assets and resources. */
    public DatabaseHelper(Context context)
    {

        super(context, DB_NAME, null, 1);
        this.myContext = context;
        DB_PATH = myContext.getDatabasePath(DB_NAME)
                .toString();
    }

    // Creates an empty database
    // on the system and rewrites it
    // with your own database.
    public void createDataBase()
            throws IOException
    {

        boolean dbExist = checkDataBase();

        if (dbExist) {
            // do nothing - database already exist
        }
        else {

            this.getReadableDatabase ();
            this.close ();
            try {
                copyDataBase ();
            } catch (IOException e) {
                throw new RuntimeException (e);

            }
            // By calling this method and
            // the empty database will be
            // created into the default system
            // path of your application
            // so we are gonna be able
            // to overwrite that database
            // with our database.


            /*this.getWritableDatabase();

            try {
                copyDataBase();
            }
            catch (IOException e) {
                throw new Error(
                        "Error copying database");
            }*/


        }
    }
    // Check if the database already exist
    // to avoid re-copying the file each
    // time you open the application
    // return true if it exists
    // false if it doesn't.
    private boolean checkDataBase()
    {


        SQLiteDatabase checkDB = null;
        try {
            String myPath = myContext.getDatabasePath(DB_NAME)
                    .toString();

            checkDB
                    = SQLiteDatabase
                    .openDatabase(
                            myPath, null,
                            SQLiteDatabase.OPEN_READONLY);
        }
        catch (SQLiteException e) {

            // database doesn't exist yet.
            Log.e("message", "" + e);
        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null;
    }
    public class DBTablePrinter extends Object{

    }
    /**
     * Copies your database from your
     * local assets-folder to the just
     * created empty database in the
     * system folder, from where it
     * can be accessed and handled.
     * This is done by transferring bytestream.
     * */
    private void copyDataBase()
            throws IOException
    {
        // Open your local db as the input stream

        InputStream myInput = myContext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH;

        // Open the empty db as the output stream

        OutputStream myOutput = new FileOutputStream(outFileName);//replace this line by next line
        //OutputStream myOutput = new FileOutputStream(myDataBase.getPath());
        // transfer bytes from the
        // inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        // Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void openDataBase()
            throws SQLException
    {
        // Open the database
        String myPath = DB_PATH;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close()
    {
        // close the database.
        if (myDataBase != null)
            myDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        try {
            copyDataBase();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //String createTableQuery = "CREATE TABLE tbl_geeksCourses (id INTEGER PRIMARY KEY, name TEXT)";
        //db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)

    {

        db.execSQL("DROP TABLE IF EXISTS deportReplica");
        db.execSQL("DROP TABLE IF EXISTS deportSublimada");
        onCreate(db);
    }

    // This method is used to get the
    // algorithm topics from the database.
    public List<String> getListOfTable(Activity activity)
    {
        sqLiteOpenHelper = new DatabaseHelper(activity);
        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
        int databaseVersion = db.getVersion();
        int DATABASE_VERSION = 1;
        if (databaseVersion == DATABASE_VERSION) {
            // The database needs to be updated.

            onUpgrade(db,DATABASE_VERSION,databaseVersion);
        }
        System.out.println("database version: " + databaseVersion);
        Cursor cursor1 = db.rawQuery("SELECT name FROM sqlite_master WHERE type = 'table'", null);

        while (cursor1.moveToNext()) {
            String tableName = cursor1.getString(0);

            System.out.println("Table name: " + tableName);
        }

        cursor1.close();

        Cursor cursor2 = db.rawQuery("SELECT COUNT(*) FROM deportSublimada;", null);

        while (cursor2.moveToNext()) {
            String count1 = cursor2.getString(0);

            System.out.println("Is emty?: " + count1);
        }

        cursor2.close();

        List<String> list = new ArrayList<>();

        // query help us to return all data
        // the present in the ALGO_TOPICS table.
       // String query = "SELECT * FROM " + ALGO_TOPICS + " EXCEPT SELECT * FROM new_masterCourses;";
        //String query = "SELECT name FROM " + ALGO_TOPICS;
        //String query = "SELECT * FROM tbl_geeksCourses;";
        //String query = "SELECT * FROM " + ALGO_TOPICS;
       // String query = "SELECT * FROM " +  TABLE;
        String query = "SELECT * FROM polerasCerrada;";
        //Cursor cursor = getReadableDatabase().rawQuery(query,null);
        //String query = "CREATE TABLE new_masterCourses AS SELECT * FROM tbl_geeksCourses WHERE id > 5;";
        //String query = "SELECT * FROM new_masterCourses;";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                // list.add(cursor.getString(1));
                //list.add(cursor.getString(0) + " | " + cursor.getString(1));
                String ResultQuery = cursor.getString(0) + " | " + cursor.getString(1) + " | " + cursor.getString(2) ;
                //String ResultQuery = cursor.getString(0);//el resultado consiste de una lista de un solo indice.
                list.add(ResultQuery);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return list;
    }
}
