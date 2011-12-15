//-----------------------------------------------------------------------------
// CouponTable
//-----------------------------------------------------------------------------

package com.tiktok.consumerapp;

//-----------------------------------------------------------------------------
// imports
//-----------------------------------------------------------------------------

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

//-----------------------------------------------------------------------------
// class implementation
//-----------------------------------------------------------------------------

public class CouponTable
{
    
    /**
     * Runs if table needs to be created.
     */
    public static void onCreate(SQLiteDatabase database)
    {
        database.execSQL(getCreateSQL());
    }

    //-------------------------------------------------------------------------

    /**
     * Runs if table version has changed and the database needs to be upgraded.
     */
    public static void onUpgrade(SQLiteDatabase database, 
                                 int oldVersion, int newVersion)
    {
        // this is obviously the worst implementation ever
       
        Log.w(CouponTable.class.getName(), String.format(
            "Upgrading database from version %d to %d " +
            "which will destroy all data.", oldVersion, newVersion));

        // drop the table 
        dropTable(database, sName);

        // create the new table
        onCreate(database);
    }

    //-------------------------------------------------------------------------

    /**
     * @return SQL statement used to create the database table.
     */
    private static String getCreateSQL()
    {
        String createSQL = 
            "create table " + sName + "("                            +
            sKeyRowId       + " integer primary key autoincrement, " +
            sKeyId          + " integer not null,                  " +
            sKeyTitle       + " text    not null,                  " +
            sKeyImageUrl    + " text    not null,                  " +
            sKeyStartTime   + " integer not null,                  " + 
            sKeyEndTime     + " integer not null,                  " + 
            sKeyIcon        + " integer not null                   " + ");";
        return createSQL;
    }

    //-------------------------------------------------------------------------

    /**
     * Drop the given table from the database.
     */
    public static void dropTable(SQLiteDatabase database, String tableName)
    {
        database.execSQL(getTableDropSQL(tableName));
    }

    //-------------------------------------------------------------------------

    /**
     * @return SQL statement used to drop a table if it exists.
     */
    public static String getTableDropSQL(String tableName)
    {
        String tableDropSQL = 
            String.format("drop table if exists %s", tableName); 
        return tableDropSQL;
    }

    //-------------------------------------------------------------------------
    // fields
    //-------------------------------------------------------------------------

    public static String sName         = "Coupon";
    public static String sKeyRowId     = "_id";
    public static String sKeyId        = "coupon_id";
    public static String sKeyTitle     = "title";
    public static String sKeyImageUrl  = "image_url";
    public static String sKeyStartTime = "start_time";
    public static String sKeyEndTime   = "end_time";
    public static String sKeyIcon      = "icon";

}