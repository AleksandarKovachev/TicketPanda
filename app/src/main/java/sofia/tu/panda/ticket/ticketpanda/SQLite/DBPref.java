package sofia.tu.panda.ticket.ticketpanda.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import sofia.tu.panda.ticket.ticketpanda.Objects.Program;

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
            case DBConstants.MY_TICKETS:
                contentValues.put("row", value[1]);
                contentValues.put("column", value[2]);
                contentValues.put("programId", value[3]);
        }
        this.db.insert(value[0], null, contentValues);
    }

    public Cursor getVals(String... value) {
        switch (value[0]) {
            case DBConstants.LOGIN_TABLE:
                return this.db.query(value[0], new String[]{"_id", "name", "egn", "dateOfCreation"}, null, null, null, null, null);
            case DBConstants.PROGRAM_TABLE:
                return this.db.query(value[0], new String[]{"_id", "title", "smallDescription", "fullDescription",
                                "price", "author", "actors", "director", "production", "primaryImage", "secondaryImage", "thirdImage", "bought"},
                        null, null, null, null, null);
            case DBConstants.SCENE_TABLE:
                return this.db.query(value[0], new String[]{"row", "column", "data"}, "programId=?", new String[]{value[1]}, null, null, null);
            case DBConstants.MY_TICKETS:
                return this.db.query(value[0], new String[]{"row", "column", "programId"}, null, null, null, null, null);
            default:
                return null;
        }
    }

    public Program getProgramById(Long id) {
        String sql = "SELECT * FROM " + DBConstants.PROGRAM_TABLE + " WHERE _id = " + id + ";";

        Cursor c = db.rawQuery(sql, null);

        Program program = new Program();
        program.setTitle(c.getString(c.getColumnIndex("title")));
        program.setSmallDescription((c.getString(c.getColumnIndex("smallDescription"))));
        program.setDescription(c.getString(c.getColumnIndex("fullDescription")));
        program.setPrice(c.getInt(c.getColumnIndex("price")));
        program.setAuthor(c.getString(c.getColumnIndex("author")));
        program.setActors(c.getString(c.getColumnIndex("actors")));
        program.setDirector(c.getString(c.getColumnIndex("director")));
        program.setProduction(c.getString(c.getColumnIndex("production")));
        program.setPrimaryImage(c.getInt(c.getColumnIndex("primaryImage")));
        program.setSecondaryImage(c.getInt(c.getColumnIndex("secondaryImage")));
        program.setThirdImage(c.getInt(c.getColumnIndex("thirdImage")));
        program.setBought(c.getInt(c.getColumnIndex("bought")));

        return program;
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