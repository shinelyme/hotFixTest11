package hongshi.hotfixtest;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.taobao.sophix.PatchStatus;
import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;


/**
 * @author lsbing
 * @version 1.0
 * @created 2018/3/22 09:51
 * @title 类的名称
 * @description 该类主要功能描述
 * @changeRecord：2018/3/22 09:51 modify  by lsbing
 */

public class App extends Application {
    private String appVersion;

    @Override
    public void onCreate() {
        super.onCreate();
        getAppVersion();
//        initHotFix();
    }



    private void getAppVersion() {
        try {
            appVersion = this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionName;
        } catch (Exception e) {
            appVersion = "1.0";
        }
    }

    private void initHotFix() {
        SophixManager.getInstance().setContext(this)
                .setAppVersion(appVersion+"")
                .setAesKey(null)
                .setEnableDebug(true)
                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                    @Override
                    public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {
                        String msg = new StringBuilder("").append("Mode:").append(mode)
                                .append(" Code:").append(code)
                                .append(" Info:").append(info)
                                .append(" HandlePatchVersion:").append(handlePatchVersion).toString();
                        Log.e("网络数据", msg);
                        Toast.makeText(App.this, "msg:"+msg, Toast.LENGTH_SHORT).show();
                        // 补丁加载回调通知
                        if (code == PatchStatus.CODE_LOAD_SUCCESS) {
                            // 表明补丁加载成功
                        } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                            // 表明新补丁生效需要重启. 开发者可提示用户或者强制重启;
                            // 建议: 用户可以监听进入后台事件, 然后应用自杀
                        } else if (code == PatchStatus.CODE_LOAD_FAIL) {
                            // 内部引擎异常, 推荐此时清空本地补丁, 防止失败补丁重复加载
                            // SophixManager.getInstance().cleanPatches();
                        } else {
                            // 其它错误信息, 查看PatchStatus类说明
                        }
                    }
                }).initialize();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        initHotFix();
    }
}
