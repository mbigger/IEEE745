package cn.studyjams.s2.sj109.ieee745;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EncoderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encoder);
    }

    /**
     * 浮点转换为字节
     *
     * @param f
     * @return
     */
    public  byte[] float2byte(float f) {
        int accum = Float.floatToRawIntBits(f);
        byte[] byteRet = new byte[4];
        byteRet[0] = (byte)(accum & 0xFF);
        byteRet[1] = (byte)((accum>>8) & 0xFF);
        byteRet[2] = (byte)((accum>>16) & 0xFF);
        byteRet[3] = (byte)((accum>>24) & 0xFF);
        return byteRet;
    }

    public String toHex(byte[] b){
        String ret = "";
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = "0" + hex;
            }
            ret = ret + hex.toUpperCase() + " ";
        }
        return ret.substring(0, ret.length()-1);
    }

    public void encode(View v){
        EditText et = (EditText) findViewById(R.id.editText10);
        String str = et.getText().toString();
        if(str==null || str.isEmpty()) return;
        Float n = Float.parseFloat(str);
        if(n == null) return;
        byte[] bs = float2byte(n);
        EditText et4 = (EditText) findViewById(R.id.editText4);
        et4.setText(toHex(bs));
    }

    public void clear(View v){
        EditText et = (EditText) findViewById(R.id.editText10);
        et.setText("");
        EditText et4 = (EditText) findViewById(R.id.editText4);
        et4.setText("");
    }
}
