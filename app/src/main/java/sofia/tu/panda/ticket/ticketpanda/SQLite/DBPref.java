package sofia.tu.panda.ticket.ticketpanda.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class DBPref extends DBHelper {

    public DBPref(Context context) {
        super(context);
    }

    public void addRecord(String... value) {
        ContentValues contentValues = new ContentValues();

        switch (value[0]) {
            case DBConstants.LOGIN_TABLE:
                contentValues.put("name", value[1]);
                contentValues.put("egn", value[2]);
                contentValues.put("dateOfCreation", value[3]);
                break;
        }
        this.db.insert(value[0], null, contentValues);
    }

    public Cursor getVals(String... value) {
        switch (value[0]) {
            case DBConstants.LOGIN_TABLE:
                return this.db.query(value[0], new String[]{"_id", "name", "egn", "dateOfCreation"}, null, null, null, null, null);
            case DBConstants.PROGRAM_TABLE:
                return this.db.query(value[0], new String[]{"title", "smallDescription", "fullDescription",
                                "price", "author", "actors", "director", "production", "primaryImage", "secondaryImage", "thirdImage", "bought"},
                        null, null, null, null, null);
            default:
                return null;
        }
    }

    public void deleteRecord(String table, String... value) {
        db.execSQL("delete from  " + table +
                " where " + value[0] + "=\'" + value[2] +
                "\' and " + value[1] + " = \'" + value[3] + "\'");
    }

    public Cursor getRawQuery(String sql, String[] params) {
        return db.rawQuery(sql, params);
    }

    public void execSql(String sql) {
        db.execSQL(sql);
    }

    public void changeItem(String table, String... value) {
        ContentValues newValues = new ContentValues();
        newValues.put(value[0], value[1]);
        newValues.put(value[2], value[3]);

        db.update(table, newValues, String.format("%s = ?", value[4]), new String[]{value[5]});
    }
}