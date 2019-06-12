package com.yanxing.base

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle

import com.yanxing.commonlibrary.view.LoadDialog
import com.yanxing.util.StatusBarColorUtil
import com.yanxing.util.StatusBarUtil
import io.flutter.app.FlutterActivityDelegate

import io.flutter.app.FlutterActivityDelegate.ViewFactory
import io.flutter.app.FlutterActivityEvents
import io.flutter.plugin.common.PluginRegistry
import io.flutter.plugin.common.PluginRegistry.Registrar
import io.flutter.view.FlutterNativeView
import io.flutter.view.FlutterView
import io.flutter.view.FlutterView.Provider
import io.flutter.plugins.GeneratedPluginRegistrant

/**
 * 基类
 * Created by lishuangxiang on 2016/1/21.
 */
abstract class BaseActivity: com.yanxing.baselibrary.BaseActivity,Provider, PluginRegistry, ViewFactory {

    private val delegate = FlutterActivityDelegate(this, this)
    private var eventDelegate: FlutterActivityEvents
    private  var viewProvider: Provider
    private var pluginRegistry: PluginRegistry

    constructor(){
        this.eventDelegate = this.delegate
        this.viewProvider = this.delegate
        this.pluginRegistry = this.delegate
    }

    override fun getFlutterView(): FlutterView {
        return this.viewProvider.getFlutterView()
    }

    override fun createFlutterView(context: Context): FlutterView? {
        return null
    }

    override fun createFlutterNativeView(): FlutterNativeView? {
        return null
    }

    override fun retainFlutterNativeView(): Boolean {
        return false
    }

    override fun hasPlugin(key: String): Boolean {
        return this.pluginRegistry.hasPlugin(key)
    }

    override fun <T> valuePublishedByPlugin(pluginKey: String): T {
        return this.pluginRegistry.valuePublishedByPlugin(pluginKey)
    }

    override fun registrarFor(pluginKey: String): Registrar {
        return this.pluginRegistry.registrarFor(pluginKey)
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        this.eventDelegate.onCreate(savedInstanceState)
        super.onCreate(savedInstanceState)
        StatusBarUtil.setStatusBarDarkMode(true, this)
        StatusBarColorUtil.setStatusBarDarkIcon(this,true)
    }

    /**
     * 显示加载框
     */
    protected fun showLoadingDialog() {
        showLoadingDialog(null)
    }

    /**
     * 显示加载框,带文字提示
     */
    fun showLoadingDialog(msg: String?) {
        val fragmentTransaction = mFragmentManager.beginTransaction()
        val fragment = mFragmentManager.findFragmentByTag(LoadDialog.TAG)
        if (fragment != null) {
            fragmentTransaction.remove(fragment).commit()
        } else {
            val loadDialog = LoadDialog()
            if (msg != null) {
                val bundle = Bundle()
                bundle.putString("tip", msg)
                loadDialog.arguments = bundle
            }
            loadDialog.show(fragmentTransaction, LoadDialog.TAG)
        }
    }

    /**
     * 隐藏加载框
     */
    fun dismissLoadingDialog() {
        val fragmentTransaction = mFragmentManager.beginTransaction()
        val fragment = mFragmentManager.findFragmentByTag(LoadDialog.TAG)
        if (fragment != null) {
            //移除正在显示的对话框
            fragmentTransaction.remove(fragment).commitNow()
        }
    }

    override protected fun onStart() {
        super.onStart()
        this.eventDelegate.onStart()
    }

    override protected fun onResume() {
        super.onResume()
        this.eventDelegate.onResume()
    }

    override protected fun onDestroy() {
        this.eventDelegate.onDestroy()
        super.onDestroy()
    }

    override fun onBackPressed() {
        if (!this.eventDelegate.onBackPressed()) {
            super.onBackPressed()
        }

    }

    override protected fun onStop() {
        this.eventDelegate.onStop()
        super.onStop()
    }

    override protected fun onPause() {
        super.onPause()
        this.eventDelegate.onPause()
    }

    override protected fun onPostResume() {
        super.onPostResume()
        this.eventDelegate.onPostResume()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        this.eventDelegate.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (!this.eventDelegate.onActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override protected fun onNewIntent(intent: Intent) {
        this.eventDelegate.onNewIntent(intent)
    }

    override fun onUserLeaveHint() {
        this.eventDelegate.onUserLeaveHint()
    }

    override fun onTrimMemory(level: Int) {
        this.eventDelegate.onTrimMemory(level)
    }

    override fun onLowMemory() {
        this.eventDelegate.onLowMemory()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        this.eventDelegate.onConfigurationChanged(newConfig)
    }
}
