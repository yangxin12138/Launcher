package com.twd.launcher;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ApplicationListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_list);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        GridView gridView = findViewById(R.id.gridView);
        PackageManager packageManager = getPackageManager();
//        List<ApplicationInfo> installedApps = packageManager.getInstalledApplications(0);
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> installedApps = packageManager.queryIntentActivities(intent,0);

        AppAdapter adapter = new AppAdapter(this,installedApps);
        gridView.setAdapter(adapter);
    }

    public class AppAdapter extends BaseAdapter{
        private Context context;
        private List<ResolveInfo> apps;

        public AppAdapter(Context context,List<ResolveInfo> apps){
            this.context = context;
            this.apps = apps;
        }

        @Override
        public int getCount() {
            return apps.size();
        }

        @Override
        public Object getItem(int position) {
            return apps.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ResolveInfo app = apps.get(position);
            PackageManager manager = context.getPackageManager();
            ViewHold viewHold = null;
            if (convertView == null){
                convertView = View.inflate(context,R.layout.grid_item_app,null);
                viewHold = new ViewHold();
                viewHold.tv_name = convertView.findViewById(R.id.app_name);
                viewHold.iv_icon = convertView.findViewById(R.id.app_icon);
                convertView.setTag(viewHold);
            }else {
                viewHold = (ViewHold) convertView.getTag();
            }
            viewHold.iv_icon.setImageDrawable(app.activityInfo.loadIcon(context.getPackageManager()));
            viewHold.tv_name.setText(app.loadLabel(manager).toString());

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 在这里处理点击应用程序图标的逻辑
                    Intent intent = context.getPackageManager().getLaunchIntentForPackage(app.activityInfo.packageName);
                    if (intent != null) {
                        context.startActivity(intent);
                    }
                }
            });
            return convertView;
        }

        private class ViewHold {
            private TextView tv_name;
            private ImageView iv_icon;

        }
    }
}