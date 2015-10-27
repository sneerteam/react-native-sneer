package me.sneer.react;

import android.app.Activity;
import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

import sneer.android.Message;
import sneer.android.PartnerSession;
import sneer.android.ui.SneerInstallation;

public class SneerModule extends ReactContextBaseJavaModule {

	private static PartnerSession session;
	private final String TAG = getClass().getSimpleName();
	private Activity activity;
	private static boolean wasStartedByMe;

	public SneerModule(ReactApplicationContext reactContext, Activity activity) {
		super(reactContext);
		this.activity = activity;
	}

	@Override
	public String getName() {
		return "Sneer";
	}

	@Override
	public Map<String, Object> getConstants() {
		Map<String, Object> constants = new HashMap<>();
		// none
		return constants;
	}

	@ReactMethod
	public void wasCalledFromConversation(Callback cb) {
		cb.invoke(SneerInstallation.checkConversationContext(activity));
	}

	@ReactMethod
	public void wasStartedByMe(Callback cb) {
		cb.invoke(wasStartedByMe);
	}

	@ReactMethod
	public void join() {
		Log.i(TAG, "SneerModule.join: " + session);
		if (session != null)
			return;
		session = PartnerSession.join(activity, new PartnerSession.Listener() {
			@Override
			public void onUpToDate() {
				sendEvent("upToDate", null);
			}

			@Override
			public void onMessage(Message message) {
				WritableMap map = Arguments.createMap();
				map.putBoolean("wasSentByMe", message.wasSentByMe());
				map.putString("payload", (String) message.payload());
				sendEvent("message", map);
			}
		});
		wasStartedByMe = session.wasStartedByMe();
	}

	@ReactMethod
	public void close() {
		Log.i(TAG, "SneerModule.close: " + session);
		if (session != null) {
			session.close();
			session = null;
		}
	}

	@ReactMethod
	public void finish() {
		activity.finish();
	}

	@ReactMethod
	public void send(final String o) {
		session.send(o);
	}

	private void sendEvent(String eventName, @Nullable WritableMap params) {
		getReactApplicationContext()
				.getJSModule(RCTDeviceEventEmitter.class)
				.emit(eventName, params);
	}

}
