package xyz.yluo.ruisiapp.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.yluo.ruisiapp.PublicData;
import xyz.yluo.ruisiapp.R;
import xyz.yluo.ruisiapp.View.MyHtmlTextView;
import xyz.yluo.ruisiapp.utils.RequestSendMail;


/**
 * Created by yluo on 2015/10/5 0005.
 * 关于页面
 */
public class AboutActivity extends BaseActivity {

    @BindView(R.id.html_text)
    MyHtmlTextView myHtmlTextView;
    @BindView(R.id.version)
    TextView version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        String ss = "<b>西电睿思手机客户端</b><br />目前可见bug较多，最近比较忙修复bug速度会很慢的。。。<br />" +
                "bug反馈,或者有什么好的建议点击按钮给我发邮件吧<br />或者 <a href=\"home.php?mod=space&uid=252553&do=profile&mobile=2\">" +
                "@谁用了FREEDOM</a>或者<a href=\"home.php?mod=space&uid=261098&do=profile&mobile=2\">@wangfuyang</a><br />"+
                "也可以到我的github上留言<a href=\"https://github.com/freedom10086/Ruisi\">点击这儿</a>";
        myHtmlTextView.mySetText(this,ss);


        PackageInfo info = null;
        PackageManager manager = getPackageManager();
        try {
            info = manager.getPackageInfo(getPackageName(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int version_code = 1;
        String version_name = "1.0";
        if(info!=null){
            version_code = info.versionCode;
            version_name =  info.versionName;
        }
        String a = "版本号："+version_code+"\n版本："+version_name;
        version.setText(a);
    }


    @OnClick(R.id.fab)
    protected void fab_clidk(View view){
        Snackbar.make(view, "你要提交bug或者建议吗?", Snackbar.LENGTH_LONG)
                .setAction("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String user = PublicData.USER_NAME;
                        if(user!=null){
                            user = "by:"+user;
                        }
                        RequestSendMail.sendMail(getApplicationContext(),user);
                    }
                }).show();
    }

}
