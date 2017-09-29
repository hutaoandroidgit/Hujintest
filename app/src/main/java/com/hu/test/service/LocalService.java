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

public class LocalService extends Service {
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
    public int onStartCommand(Intent intent, int flags, int startId) {

        this.bindService(new Intent(this, RemoteService.class), conn, Context.BIND_IMPORTANT);

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //开启本地服务
        LocalService.this.startService(new Intent(LocalService.this, RemoteService.class));
        //绑定本地服务
        LocalService.this.bindService(new Intent(LocalService.this, RemoteService.class), conn, Context.BIND_IMPORTANT);

    }

    class MyBinder extends IMyAidlInterface.Stub {

        public String getServiceName() throws RemoteException {
            return "LocalService";
        }
    }


    class MyConn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            //与本地服务连接失败
            LocalService.this.startService(new Intent(LocalService.this, RemoteService.class));
            LocalService.this.bindService(new Intent(LocalService.this, RemoteService.class), conn, Context.BIND_IMPORTANT);
        }
    }
}
