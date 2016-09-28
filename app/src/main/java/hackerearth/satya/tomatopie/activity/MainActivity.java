package hackerearth.satya.tomatopie.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import hackerearth.satya.tomatopie.R;
import hackerearth.satya.tomatopie.model.City;

public class MainActivity extends AppCompatActivity {

    public static void start(Context context, City city) {
        Intent starter = new Intent(context, MainActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
