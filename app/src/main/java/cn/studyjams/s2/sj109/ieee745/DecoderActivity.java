package cn.studyjams.s2.sj109.ieee745;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class DecoderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decoder);
        EditText et4 = (EditText) findViewById(R.id.editText4);
        et4.addTextChangedListener(new CustomTextWatcher(et4));
    }

    /**
     * 字节转换为浮点
     *
     * @param b 字节（至少4个字节）
     * @param index 开始位置
     * @return
     */
    public static float byte2float(byte[] b, int index) {
        int l;
        l = b[index + 0];
        l &= 0xff;
        l |= ((long) b[index + 1] << 8);
        l &= 0xffff;
        l |= ((long) b[index + 2] << 16);
        l &= 0xffffff;
        l |= ((long) b[index + 3] << 24);
        return Float.intBitsToFloat(l);
    }

    public String toHex(byte b){
        String str = null;
        // 将每个字节与0xFF进行与运算，然后转化为10进制，然后借助于Integer再转化为16进制
        str = Integer.toHexString(0xFF & b).toUpperCase();
        if (str.length() == 1)// 每个字节8为，转为16进制标志，2个16进制位
        {
            str = "0" + str;
        }
        return  str;
    }

    public void decode(View v){
        EditText et4 = (EditText) findViewById(R.id.editText4);
        String str = et4.getText().toString();
        if(str==null || str.isEmpty()) return;
        if(str.length() < 11) return;
        str = str.replace(" ","");
        Log.d("DecoderActivity",str);
        byte[] bytes = ByteHEXUtils.hexToByte(str);
        Float f = byte2float(bytes, 0);
        if(f == null) return;
        EditText et10 = (EditText) findViewById(R.id.editText10);
        et10.setText(f.toString());
    }

    public void clear(View v){
        EditText et = (EditText) findViewById(R.id.editText10);
        et.setText("");
        EditText et4 = (EditText) findViewById(R.id.editText4);
        et4.setText("");
    }
}
