package com.adobe.plugins;

// import org.apache.cordova.CallbackContext;
// import org.apache.cordova.CordovaPlugin;
// import org.json.JSONArray;

// import com.google.analytics.tracking.android.GAServiceManager;
// import com.google.analytics.tracking.android.GoogleAnalytics;
// import com.google.analytics.tracking.android.Tracker;

// import android.util.Log;

import com.google.android.gms.analytics.*;
// import com.google.android.gms.analytics.Tracker;
// import com.google.android.gms.analytics.GoogleAnalytics;

import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

import android.util.Log;

public class GAPlugin extends CordovaPlugin {

  private static GoogleAnalytics ga;
  private static Tracker tracker;
  private final String TAG = "GAPlugin";
  private CallbackContext trackerContext;

  /**
   * Initializes the plugin
   *
   * @param cordova The context of the main Activity.
   * @param webView The associated CordovaWebView.
   */
  @Override
  public void initialize(CordovaInterface cordova, CordovaWebView webView) {
    super.initialize(cordova, webView);
    ga = GoogleAnalytics.getInstance(cordova.getActivity());
    this.trackerContext = null;
    Log.v(TAG, "Initializing GAPlugin");
  }

  // private void setTrackingId(String trackingId) {
  //   Log.v(TAG, "Setting tracking ID of: " + trackingId);
  //   tracker = ga.newTracker(trackingId);
  //   // setup uncaught exception handler
  //   tracker.enableExceptionReporting(true);
  //   this.trackerContext = tracker;
  // }

	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext)
  throws JSONException {


    if (action.equals("initGA")) {
      Log.d(TAG, "Current action is initGA");
      try {
        Log.v(TAG, "Setting tracking ID of: " + args.getString(0));
        tracker = ga.newTracker(args.getString(0));
        // setup uncaught exception handler
        tracker.enableExceptionReporting(true);

        this.trackerContext = callbackContext;
        PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, "initGA - id = " + args.getString(0) + "; interval = " + args.getInt(1) + " seconds");
        pluginResult.setKeepCallback(true);
        callbackContext.sendPluginResult(pluginResult);
        // setTrackingId(args.getString(0));

				// tracker = ga.getTracker(args.getString(0));
				// GAServiceManager.getInstance().setDispatchPeriod(args.getInt(1));
				// ga.setDefaultTracker(tracker);
				// callbackContext.success("initGA - id = " + args.getString(0) + "; interval = " + args.getInt(1) + " seconds");
				return true;
			} catch (final Exception e) {
        Log.e(TAG, e.getMessage());
				callbackContext.error(e.getMessage());
			}
		} else if (action.equals("exitGA")) {
      Log.d(TAG, "Current action is exitGA");
      return true;
			// try {
			// 	GAServiceManager.getInstance().dispatch();
			// 	callback.success("exitGA");
			// 	return true;
			// } catch (final Exception e) {
			// 	callback.error(e.getMessage());
			// }
		} else if (action.equals("trackEvent")) {
      Log.d(TAG, "Current action is trackEvent");
      return true;
			// try {
			// 	tracker.sendEvent(args.getString(0), args.getString(1), args.getString(2), args.getLong(3));
			// 	callback.success("trackEvent - category = " + args.getString(0) + "; action = " + args.getString(1) + "; label = " + args.getString(2) + "; value = " + args.getInt(3));
			// 	return true;
			// } catch (final Exception e) {
			// 	callback.error(e.getMessage());
			// }
		} else if (action.equals("trackPage")) {
      Log.d(TAG, "Current action is trackPage");
      try {
        tracker.setScreenName(args.getString(0));
        tracker.send(new HitBuilders.AppViewBuilder().build());
        Log.d(TAG, "Succesfully tracked the page: " + args.getString(0));
        // callbackContext.success("trackPage - url = " + args.getString(0));

        this.trackerContext = callbackContext;
        PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, "trackPage - url = " + args.getString(0));
        pluginResult.setKeepCallback(true);
        callbackContext.sendPluginResult(pluginResult);
        return true;
      } catch (final Exception e) {
        Log.e(TAG, e.getMessage());
        callbackContext.error(e.getMessage());
      }
			// try {
			// 	tracker.sendView(args.getString(0));
			// 	callback.success("trackPage - url = " + args.getString(0));
			// 	return true;
			// } catch (final Exception e) {
			// 	callback.error(e.getMessage());
			// }
		} else if (action.equals("setVariable")) {
      return true;
			// try {
			// 	tracker.setCustomDimension(args.getInt(0), args.getString(1));
			// 	callback.success("setVariable passed - index = " + args.getInt(0) + "; value = " + args.getString(1));
			// 	return true;
			// } catch (final Exception e) {
			// 	callback.error(e.getMessage());
			// }
		}
		else if (action.equals("setDimension")) {
      return true;
			// try {
			// 	tracker.setCustomDimension(args.getInt(0), args.getString(1));
			// 	callback.success("setDimension passed - index = " + args.getInt(0) + "; value = " + args.getString(1));
			// 	return true;
			// } catch (final Exception e) {
			// 	callback.error(e.getMessage());
			// }
		}
		else if (action.equals("setMetric")) {
      return true;
			// try {
			// 	tracker.setCustomMetric(args.getInt(0), args.getLong(1));
			// 	callback.success("setVariable passed - index = " + args.getInt(2) + "; key = " + args.getString(0) + "; value = " + args.getString(1));
			// 	return true;
			// } catch (final Exception e) {
			// 	callback.error(e.getMessage());
			// }
		}
		return false;
	}
}

