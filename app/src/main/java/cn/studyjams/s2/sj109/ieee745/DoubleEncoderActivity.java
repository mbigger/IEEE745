package cn.studyjams.s2.sj109.ieee745;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DoubleEncoderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_double_encoder);
    }

    /**
     * 双精度浮点数转换为字节
     *
     * @param d
     * @return
     */
    public  byte[] double2byte(double d) {
        long accum = Double.doubleToRawLongBits(d);
        byte[] byteRet = new byte[8];
        byteRet[0] = (byte)(accum & 0xFF);
        byteRet[1] = (byte)((accum>>8) & 0xFF);
        byteRet[2] = (byte)((accum>>16) & 0xFF);
        byteRet[3] = (byte)((accum>>24) & 0xFF);
        byteRet[4] = (byte)((accum>>32) & 0xFF);
        byteRet[5] = (byte)((accum>>40) & 0xFF);
        byteRet[6] = (byte)((accum>>48) & 0xFF);
        byteRet[7] = (byte)((accum>>56) & 0xFF);
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
        Double n = Double.parseDouble(str);
        if(n == null) return;
        byte[] bs = double2byte(n);
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
