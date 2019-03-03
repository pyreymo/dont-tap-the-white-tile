package pyreymo.test.fallingblocks;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class Game4Activity extends AppCompatActivity {

    private GameView4 game_view4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game4);

        WindowManager manager = getWindowManager();
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        game_view4 = findViewById(R.id.game_view4);
        game_view4.getInfo(height, width);
        game_view4.init();
        game_view4.setOnClickListener(game_view4);

        handler.post(task);
    }


    private Handler handler = new Handler();
    private Runnable task = new Runnable() {
        public void run() {
            handler.postDelayed(this, 100);//设置循环时间
            int score = game_view4.getScore();
            SharedPreferences sp = getSharedPreferences("Rank", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            int score_fuck = sp.getInt("fuck", 0);
            if (score_fuck > score) editor.putInt("fuck", score_fuck);
            else editor.putInt("fuck", score);
            editor.apply();

        }
    };
}
