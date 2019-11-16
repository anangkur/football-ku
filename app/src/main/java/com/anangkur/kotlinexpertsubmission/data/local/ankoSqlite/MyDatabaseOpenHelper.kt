package com.anangkur.kotlinexpertsubmission.data.local.ankoSqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.anangkur.kotlinexpertsubmission.util.Const
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(context: Context): ManagedSQLiteOpenHelper(context, Const.DATABASE_NAME, null, 1){
    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(
            EventFavourite.TABLE_EVENT, true,
            EventFavourite.COLUMN_ID to TEXT + PRIMARY_KEY,
            EventFavourite.COLUMN_HOME_SCORE to INTEGER,
            EventFavourite.COLUMN_AWAY_SCORE to INTEGER,
            EventFavourite.COLUMN_HOME_TEAM to TEXT,
            EventFavourite.COLUMN_AWAY_TEAM to TEXT,
            EventFavourite.COLUMN_TIME to TEXT,
            EventFavourite.COLUMN_DATE to TEXT,
            EventFavourite.COLUMN_HOME_GOAL to TEXT,
            EventFavourite.COLUMN_AWAY_GOAL to TEXT,
            EventFavourite.COLUMN_HOME_SHOOT to INTEGER,
            EventFavourite.COLUMN_AWAY_SHOOT to INTEGER,
            EventFavourite.COLUMN_HOME_FORMATION to TEXT,
            EventFavourite.COLUMN_AWAY_FORMATION to TEXT,
            EventFavourite.COLUMN_HOME_YELLOW to TEXT,
            EventFavourite.COLUMN_AWAY_YELLOW to TEXT,
            EventFavourite.COLUMN_HOME_RED to TEXT,
            EventFavourite.COLUMN_AWAY_RED to TEXT,
            EventFavourite.COLUMN_HOME_KEEPER to TEXT,
            EventFavourite.COLUMN_AWAY_KEEPER to TEXT,
            EventFavourite.COLUMN_HOME_DEFENSE to TEXT,
            EventFavourite.COLUMN_AWAY_DEFENSE to TEXT,
            EventFavourite.COLUMN_HOME_MIDFIELD to TEXT,
            EventFavourite.COLUMN_AWAY_MIDFIELD to TEXT,
            EventFavourite.COLUMN_HOME_FORWARD to TEXT,
            EventFavourite.COLUMN_AWAY_FORWARD to TEXT,
            EventFavourite.COLUMN_HOME_SUBTITUTE to TEXT,
            EventFavourite.COLUMN_AWAY_SUBTITUTE to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(EventFavourite.TABLE_EVENT, true)
    }

    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

}