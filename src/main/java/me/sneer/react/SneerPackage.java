package com.reactactoe;

import android.app.Activity;
import android.util.Log;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.ArrayList;
import java.util.List;

public class SneerPackage implements ReactPackage {
	private final Activity activity;

	public SneerPackage(Activity activity) {
		this.activity = activity;
	}

	@Override
	public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
		Log.d("TICTACTOE", "createNativeModules");

		List<NativeModule> modules = new ArrayList<>();
		modules.add(new SneerModule(reactContext, activity));
		return modules;
	}

	@Override
	public List<Class<? extends JavaScriptModule>> createJSModules() {
		return new ArrayList<>();
	}

	@Override
	public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
		return new ArrayList<>();
	}
}
