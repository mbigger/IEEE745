package cn.studyjams.s2.sj109.ieee745;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity {

    private FirebaseAnalytics analytics;

    private  void record(String item, String content){
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME,item);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE,content);
        analytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        analytics = FirebaseAnalytics.getInstance(this);
    }

    public void float_encoder(View v){
        Intent intent = new Intent(MainActivity.this,EncoderActivity.class);
        startActivity(intent);
        record("encode","float");
    }

    public void float_decoder(View v){
        Intent intent = new Intent(MainActivity.this,DecoderActivity.class);
        startActivity(intent);
        record("decode","float");
    }

    public void double_encode(View v){
        Intent intent = new Intent(MainActivity.this,DoubleEncoderActivity.class);
        startActivity(intent);
        record("encode","double");
    }

    public void double_decode(View v){
        Intent intent = new Intent(MainActivity.this,DoubleDecoderActivity.class);
        startActivity(intent);
        record("decode","double");
    }

    public void about(View v){
        Intent intent = new Intent(MainActivity.this,AboutActivity.class);
        startActivity(intent);
        record("about","come in");
    }

}
