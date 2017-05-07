package sofia.tu.panda.ticket.ticketpanda.Activities;

import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sofia.tu.panda.ticket.ticketpanda.Adapters.SwipePageAdapter;
import sofia.tu.panda.ticket.ticketpanda.Objects.Program;
import sofia.tu.panda.ticket.ticketpanda.Objects.Scene;
import sofia.tu.panda.ticket.ticketpanda.R;
import sofia.tu.panda.ticket.ticketpanda.SQLite.DBConstants;
import sofia.tu.panda.ticket.ticketpanda.SQLite.DBPref;

public class ProgramItemActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager viewPager;
    private SwipePageAdapter swipePageAdapter;
    private TextView title, description, author, director, actors, production;
    private List<Integer> scene = new ArrayList<>();
    private List<Scene> data;
    private List<Button> buttons;
    private Program program;
    private static final int[] BTN_IDS = {
            R.id.a1, R.id.a2, R.id.a3, R.id.a4, R.id.a5, R.id.a6,
            R.id.b1, R.id.b1, R.id.b3, R.id.b4, R.id.b5, R.id.b6,
            R.id.v1, R.id.v2, R.id.v3, R.id.v4, R.id.v5, R.id.v6,
            R.id.g1, R.id.g2, R.id.g3, R.id.g4, R.id.g5, R.id.g6,
            R.id.d1, R.id.d2, R.id.d3, R.id.d4, R.id.d5, R.id.d6,
            R.id.e1, R.id.e2, R.id.e3, R.id.e4, R.id.e5, R.id.e6
    };

    private Button buy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.program_layout);

        Intent intent = getIntent();
        program = (Program) intent.getExtras().getSerializable("PROGRAM");

        this.setTitle(program.getTitle());

        int[] images = {program.getPrimaryImage(), program.getSecondaryImage(), program.getThirdImage()};

        viewPager = (ViewPager) findViewById(R.id.imageViewPager);
        swipePageAdapter = new SwipePageAdapter(getApplicationContext(), images);
        viewPager.setAdapter(swipePageAdapter);

        title = (TextView) findViewById(R.id.itemTitle);
        description = (TextView) findViewById(R.id.itemDescription);
        author = (TextView) findViewById(R.id.itemAuthor);
        actors = (TextView) findViewById(R.id.itemActors);
        director = (TextView) findViewById(R.id.itemDirector);
        production = (TextView) findViewById(R.id.itemProduction);

        title.setText(program.getTitle());
        description.setText(program.getDescription());
        author.setText("Автор: " + program.getAuthor());
        director.setText("Режисьор: " + program.getDirector());
        actors.setText("Участват: " + program.getActors());
        production.setText("Дата: " + program.getProduction());

        buy = (Button) findViewById(R.id.buy_button);
        buy.setText("КУПИ БИЛЕТ ЗА " + program.getPrice() + " лв.");

        data = new ArrayList<>();

        DBPref pref = new DBPref(this);
        Cursor c = pref.getVals(DBConstants.SCENE_TABLE, program.getId().toString());

        if (c.moveToFirst()) {
            do {
                Scene scene = new Scene();
                scene.setRow(c.getInt(c.getColumnIndex("row")));
                scene.setColumn(c.getInt(c.getColumnIndex("column")));
                scene.setData(c.getInt(c.getColumnIndex("data")));
                data.add(scene);
            } while (c.moveToNext());
        }

        c.close();
        pref.close();

        scene = parseToList(data);

        buy.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        LayoutInflater li = LayoutInflater.from(this);
        View dialogView = li.inflate(R.layout.tickets_layout, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setView(dialogView);

        final TextView textView = (TextView) dialogView.findViewById(R.id.tickets_price);

        buttons = new ArrayList<>();
        for (int i = 0; i < BTN_IDS.length; i++) {
            final int k = i;
            Button button = (Button) dialogView.findViewById(BTN_IDS[i]);
            if (scene.get(i) == 1) {
                button.setEnabled(false);
                button.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
            }
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    animate(v);

                    if (scene.get(k) == 2) {
                        scene.set(k, 0);
                        v.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY);
                    } else {
                        scene.set(k, 2);
                        v.getBackground().setColorFilter(Color.BLUE, PorterDuff.Mode.MULTIPLY);
                    }

                    int count = Collections.frequency(scene, 2);

                    if (count != 0) {
                        textView.setText(count * program.getPrice() + " лева");
                    } else {
                        textView.setText("");
                    }
                }
            });
            buttons.add(button);
        }

        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Купи",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                DBPref pref = new DBPref(getApplicationContext());
                                List<Scene> list = parseToArray(scene, pref);
                                for (Scene scene : list) {
                                    String sql = "UPDATE scene " +
                                            "SET data = '" + scene.getData() + "' " +
                                            "WHERE row = '" + scene.getRow() + "' " +
                                            "AND column = '" + scene.getColumn() + "' " +
                                            "AND programId = '" + program.getId() + "';";
                                    pref.execSql(sql);
                                }

                                pref.close();
                            }
                        })
                .setNegativeButton("Отмени",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    private void animate(View v) {
        ObjectAnimator objectAnimatorButton = ObjectAnimator.ofFloat(v, "rotationY", 0.0f, 360);
        objectAnimatorButton.setDuration(1000);
        objectAnimatorButton.start();
    }

    public List<Integer> parseToList(List<Scene> data) {
        Integer[][] array = new Integer[6][6];

        for (Scene scene : data) {
            array[scene.getRow()][scene.getColumn()] = scene.getData();
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 6; j++)
                list.add(array[i][j]);
        return list;
    }

    public List<Scene> parseToArray(List<Integer> data, DBPref pref) {
        Integer[][] array = new Integer[6][6];
        int row = 0;
        int col = 0;

        for (int i = 0; i < data.size(); i++) {
            if (col > 5) {
                col = 0;
                row++;
            }

            if (data.get(i) == 2) {
                pref.addRecord(DBConstants.MY_TICKETS, row + "", col + "", program.getId() + "");
                data.set(i, 1);
            }
            array[row][col] = data.get(i);

            col++;
        }

        List<Scene> list = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                Scene scene = new Scene();
                scene.setRow(i);
                scene.setColumn(j);
                scene.setData(array[i][j]);
                list.add(scene);
            }
        }
        return list;
    }
}