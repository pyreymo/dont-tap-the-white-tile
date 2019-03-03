package pyreymo.test.fallingblocks;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) getSupportActionBar().hide();
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //全屏模式（已禁用）


        setContentView(R.layout.activity_main);

//        Button button0 = findViewById(R.id.button0);
//        button0.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "退出", Toast.LENGTH_SHORT).show();
//                finish();
//            }
//        });

        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Game1Activity.class);
                startActivity(intent);
                overridePendingTransition(
                        android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "街机模式敬请期待",
                        Toast.LENGTH_SHORT).show();
            }
        });
        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "鬼畜模式敬请期待",
                        Toast.LENGTH_SHORT).show();
            }
        });
        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "每个人都有手指头，尽管不一定好用。",
                        Toast.LENGTH_LONG).show();

                Intent intent = new Intent(MainActivity.this, Game4Activity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });

        handler.post(task);
//        AutoTune("0 0 0 0 0 0 1 1 5 5 6 6 5 0 4 4 3 3 2 2 1 0 5 5 4 4 3 3 2 0 5 5 4 4 3 3 2 0 1 1 5 5 6 6 5 0 4 4 3 3 2 2 1 0");//测试播放小星星
    }


    private Handler handler = new Handler();
    private Runnable task = new Runnable() {
        public void run() {
            handler.postDelayed(this, 100);//设置循环时间
            SharedPreferences sp = getSharedPreferences("Rank", MODE_PRIVATE);
            int score_fast = sp.getInt("fast", 0);
            int score_fuck = sp.getInt("fuck", 0);
            TextView rank_text = findViewById(R.id.rank_text);
            rank_text.setText("极速模式：" + score_fast
                    + "\n\n街机模式：0\n\n鬼畜模式：0"
                    + "\n\n上天模式：" + score_fuck);
        }
    };


    public void AutoTune(final String str) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String[] notation = str.split(" ");
                for (int i = 0; i < notation.length; i++) {
                    int resId = getResources().getIdentifier("c" + notation[i],
                            "raw", getBaseContext().getPackageName());
                    try {
                        MediaPlayer.create(MainActivity.this, resId).start();
                        Thread.currentThread().sleep(300);//控制播放速度
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

}