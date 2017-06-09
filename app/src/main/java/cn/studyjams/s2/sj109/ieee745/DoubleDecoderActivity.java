package cn.studyjams.s2.sj109.ieee745;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class DoubleDecoderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_double_decoder);
        EditText et4 = (EditText) findViewById(R.id.editText4);
        et4.addTextChangedListener(new CustomTextWatcher(et4));
    }

    /**
     * 字节转换为double
     *
     * @param array 字节（至少4个字节）
     * @param pos 开始位置
     * @return
     */
    public static double byte2double(byte[] array,int pos) {
        long accum = 0;
        accum = array[pos+0] & 0xFF;
        accum |= (long)(array[pos+1] & 0xFF)<<8;
        accum |= (long)(array[pos+2] & 0xFF)<<16;
        accum |= (long)(array[pos+3] & 0xFF)<<24;
        accum |= (long)(array[pos+4] & 0xFF)<<32;
        accum |= (long)(array[pos+5] & 0xFF)<<40;
        accum |= (long)(array[pos+6] & 0xFF)<<48;
        accum |= (long)(array[pos+7] & 0xFF)<<56;
        return Double.longBitsToDouble(accum);
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
        Double f = byte2double(bytes, 0);
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
