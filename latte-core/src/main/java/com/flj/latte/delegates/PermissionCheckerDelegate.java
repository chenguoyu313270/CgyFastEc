package com.flj.latte.delegates;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

import com.flj.latte.ui.camera.LatteCamera;


import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

/**
 * Created by Administrator on 2018\5\24 0024.
 */
@RuntimePermissions
public abstract class PermissionCheckerDelegate extends BaseDelegate {
    //不是直接调用方法
    @NeedsPermission(android.Manifest.permission.CAMERA)
    void start() {
        LatteCamera.start(this);
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }

    @SuppressLint("NeedOnRequestPermissionsResult")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionCheckerDelegatePermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }
}
