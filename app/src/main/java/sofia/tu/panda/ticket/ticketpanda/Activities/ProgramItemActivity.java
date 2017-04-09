package sofia.tu.panda.ticket.ticketpanda.Activities;

import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import sofia.tu.panda.ticket.ticketpanda.Adapters.SwipePageAdapter;
import sofia.tu.panda.ticket.ticketpanda.Objects.Program;
import sofia.tu.panda.ticket.ticketpanda.R;

public class ProgramItemActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager viewPager;
    private SwipePageAdapter swipePageAdapter;
    private TextView title, description, author, director, actors, production;

    private Button buy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.program_layout);

        Intent intent = getIntent();
        Program program = (Program) intent.getExtras().getSerializable("PROGRAM");

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

        buy.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        LayoutInflater li = LayoutInflater.from(this);
        View dialogView = li.inflate(R.layout.tickets_layout, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setView(dialogView);

        final Button a1, a2, a3, a4, a5, a6;
        final Button b1, b2, b3, b4, b5, b6, b7;
        final Button v1, v2, v3, v4, v5, v6, v7, v8;
        final Button g1, g2, g3, g4, g5, g6, g7, g8, g9;
        final Button d1, d2, d3, d4, d5, d6, d7, d8, d9, d10;
        final Button e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11;

        final TextView textView = (TextView) dialogView.findViewById(R.id.tickets_price);

        a1 = (Button) dialogView.findViewById(R.id.a1);

        boolean clicked = false;

        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animate(v);
                textView.setText(6 + " лв.");
            }
        });

        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Добави",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

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
}