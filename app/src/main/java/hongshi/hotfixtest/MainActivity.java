package hongshi.hotfixtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.taobao.sophix.SophixManager;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((TextView) findViewById(R.id.bt)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SophixManager.getInstance().queryAndLoadNewPatch();
            }
        });
    }
}
