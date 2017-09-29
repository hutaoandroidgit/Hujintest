package com.hu.test.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.hu.test.IMyAidlInterface;

/**
 * Created by TT on 2017/6/20.
 */

public class RemoteService extends Service {
    MyConn conn;
    MyBinder binder;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        binder =new MyBinder();
        conn = new MyConn();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        RemoteService.this.bindService(new Intent(RemoteService.this, LocalService.class), conn, Context.BIND_IMPORTANT);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //开启本地服务
        RemoteService.this.startService(new Intent(RemoteService.this, LocalService.class));
        //绑定本地服务
        RemoteService.this.bindService(new Intent(RemoteService.this, LocalService.class), conn, Context.BIND_IMPORTANT);

    }

    class MyBinder extends IMyAidlInterface.Stub {

        public String getServiceName() throws RemoteException {
            return "RemoteService";
        }
    }


    class MyConn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            //与本地服务连接失败
            RemoteService.this.startService(new Intent(RemoteService.this, LocalService.class));
            RemoteService.this.bindService(new Intent(RemoteService.this, LocalService.class), conn, Context.BIND_IMPORTANT);
        }
    }
}
