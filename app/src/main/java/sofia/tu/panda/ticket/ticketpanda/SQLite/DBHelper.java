package sofia.tu.panda.ticket.ticketpanda.SQLite;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    static final String DB_NAME = "pandaDB";
    static final int CURRENT_VERSION = 1;
    protected SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, CURRENT_VERSION);
        open();
    }

    private final String loginTable = "create table " + DBConstants.LOGIN_TABLE + " (" +
            "_id integer primary key autoincrement, " +
            "name text not null, " +
            "egn text unique not null, " +
            "dateOfCreation text not null);";

    private final String programTable = "create table " + DBConstants.PROGRAM_TABLE + " (" +
            "_id integer primary key autoincrement, " +
            "title text not null, " +
            "smallDescription text not null, " +
            "fullDescription text not null, " +
            "price integer not null, " +
            "author text not null, " +
            "actors text not null, " +
            "director text not null, " +
            "production text not null, " +
            "primaryImage integer not null, " +
            "secondaryImage integer, " +
            "thirdImage integer, " +
            "bought integer default null);";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(loginTable);

        db.execSQL(programTable);
        db.execSQL(DBProductions.production1);
        db.execSQL(DBProductions.production2);
        db.execSQL(DBProductions.production3);
        db.execSQL(DBProductions.production4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void open() throws SQLException {
        db = getWritableDatabase();
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            db.execSQL("PRAGMA foreign_keys=ON;");
            db.setForeignKeyConstraintsEnabled(true);
        }
    }

    public void close() {
        db.close();
    }
}