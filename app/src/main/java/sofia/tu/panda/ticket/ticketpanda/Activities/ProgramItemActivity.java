package sofia.tu.panda.ticket.ticketpanda.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import sofia.tu.panda.ticket.ticketpanda.Adapters.SwipePageAdapter;
import sofia.tu.panda.ticket.ticketpanda.Objects.Program;
import sofia.tu.panda.ticket.ticketpanda.R;

public class ProgramItemActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private SwipePageAdapter swipePageAdapter;
    private TextView title, description, author, director, actors, production, price;

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
        price = (TextView) findViewById(R.id.itemPrice);

        title.setText(program.getTitle());
        description.setText(program.getDescription());
        author.setText("Автор: " + program.getAuthor());
        director.setText("Режисьор: " + program.getDirector());
        actors.setText("Участват: " + program.getActors());
        production.setText("Дата: " + program.getProduction());
        price.setText(program.getPrice() + " лв.");

    }
}