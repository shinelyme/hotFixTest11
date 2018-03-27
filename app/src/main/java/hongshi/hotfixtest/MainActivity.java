package hongshi.hotfixtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.taobao.sophix.SophixManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((TextView) findViewById(R.id.bt)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SophixManager.getInstance().queryAndLoadNewPatch();


                getzhou7();
            }
        });
    }

    private void getzhou7() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
        Calendar cal = Calendar.getInstance();
        Date time= null;
        try {
            time = sdf.parse("2018-03-27");
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        Date time = new Date();
        cal.setTime(time);
//        System.out.println("要计算日期为:"+sdf.format(cal.getTime())); //输出要计算日期

        //判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        if(1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        cal.setFirstDayOfWeek(Calendar.MONDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int day = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek()-day);//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
//        System.out.println("所在周星期一的日期："+sdf.format(cal.getTime()));
        Log.e("logtime",sdf.format(cal.getTime()));
        System.out.println(cal.getFirstDayOfWeek()+"-"+day+"+6="+(cal.getFirstDayOfWeek()-day+6));
        cal.add(Calendar.DATE, 6);
//        System.out.println("所在周星期日的日期："+sdf.format(cal.getTime()));
        Log.e("logtime",sdf.format(cal.getTime()));
    }


}
