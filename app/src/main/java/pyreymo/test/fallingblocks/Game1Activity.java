package pyreymo.test.fallingblocks;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.WindowManager;


public class Game1Activity extends AppCompatActivity {

    private GameView game_view;
    private String[] TwinkleStar = ("1 1 5 5 6 6 5 0 4 4 3 3 2 2 1 0 5 5 4 4 3 3 2 0 5 5 4" +
            " 4 3 3 2 0 1 1 5 5 6 6 5 0 4 4 3 3 2 2 1 0 0 0").split(" ");//小星星简谱


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game1);

        WindowManager manager = getWindowManager();//获取屏幕长宽
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        game_view = findViewById(R.id.game_view);
        game_view.getInfo(height, width);
        game_view.init();
        game_view.setOnClickListener(game_view);

        handler.post(task);

    }


    private Handler handler = new Handler();


    //持续计分
    private Runnable task = new Runnable() {
        public void run() {
            handler.postDelayed(this, 100);//设置循环时间100ms
            SharedPreferences sp = getSharedPreferences("Rank", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            int score = game_view.getScore();
            //TODO 重写播放钢琴音的判定
//            SharedPreferences sp2 = getSharedPreferences("Temp", MODE_PRIVATE);
//            SharedPreferences.Editor editor2 = sp2.edit();
//
//            if (sp2.getInt("fastTemp", 0) > 0) {
//                editor2.clear();
//                editor2.apply();
//            }
//
//            if (sp2.getInt("fastTemp", 0) < score) {
//                Tune(score);
//                editor2.putInt("fastTemp", score);
//                editor2.apply();
//            }

            if (sp.getInt("fast", 0) < score) {
                editor.putInt("fast", score);
                editor.apply();
            }

        }
    };


    //播放音乐
    public void Tune(int number) {
        int index = number % TwinkleStar.length;
        int resId = getResources().getIdentifier(
                "c" + TwinkleStar[index], "raw", getBaseContext().getPackageName());
        MediaPlayer.create(Game1Activity.this, resId).start();
    }


}




