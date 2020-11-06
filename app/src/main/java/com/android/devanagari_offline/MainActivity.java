package com.android.devanagari_offline;

import java.io.File;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.devanagari_offline.R;
import com.android.devanagari_offline.webviewclient.WebClient;

@SuppressLint("SetJavaScriptEnabled")
public class MainActivity extends Activity {
   private final int DIALOG_SITE = 1;

   protected boolean v11 = false; // flag to prevent recursive call to v11 activity
   public static boolean reload = false;
   private boolean load_images = false;

   WebView wv;
   WebSettings.LayoutAlgorithm la;

   /** Called when the activity is first created. */
   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);

      if (Build.VERSION.SDK_INT >= 11 && !v11) {
         // v11 flag will be set by GoogleNewsActivityv11
         Intent i = new Intent(this, NewsActivityv11.class);
         if (getIntent().getAction() != null) i.setAction(getIntent().getAction());
         if (getIntent().getData() != null) i.setData(getIntent().getData());
         startActivity(i);
         finish();
         return;
      }
      

      setContentView(R.layout.main);
//      CookieSyncManager.createInstance(this);

      wv = getWebView();
      if (wv == null) {
         finish();
         return;
      }
      
      setupWebView();
//      Toast.makeText(this,"Insecure connections" ,Toast.LENGTH_SHORT).show();
      
   }
   
   @Override
   protected void onResume() {
	   
      super.onResume();
      if (reload) {
          reload = false;
      }
   }
   
   
   @SuppressLint({ "JavascriptInterface", "SetJavaScriptEnabled" })
   protected void setupWebView() {      
      final ProgressBar pb = getProgressBar();
      if (pb != null) pb.setVisibility(View.VISIBLE);

      WebSettings settings = wv.getSettings();
      
      settings.setJavaScriptEnabled(true);
      settings.setBuiltInZoomControls(true);
      settings.setDisplayZoomControls(false);
      settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
      settings.setDatabaseEnabled(true);
      settings.setCacheMode(WebSettings.LOAD_DEFAULT);
//      settings.setBlockNetworkImage(false);
      settings.setDomStorageEnabled(true);
      settings.setUseWideViewPort(true);
      wv.getSettings().setLoadsImagesAutomatically(load_images);
      wv.setWebViewClient(getWebViewClient(pb));
      wv.setOnLongClickListener(new OnLongClickListener() {
         @Override
         public boolean onLongClick(View arg0) {
            String url = wv.getHitTestResult().getExtra();
            if (url != null) {
               Intent i = new Intent(android.content.Intent.ACTION_VIEW);
               i.setData(Uri.parse(url));
               i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               startActivity(i);
               return true;
            }

            return false;
         }
      });

      if (getIntent().getDataString() != null) {
         openSite(getIntent().getDataString());
      } else {
         openSite(getSiteUrl());
      }
   }

   /**
    * Return the title bar progress bar to indicate progress
    * 
    * @return
    */
   public ProgressBar getProgressBar() {
      return (ProgressBar) findViewById(R.id.site_progress);
   }

   /**
    * Return the web view in which to display the site
    * 
    * @return
    */
   public WebView getWebView() {
      return (WebView) findViewById(R.id.site_webview);
   }

   /**
    * Return the web view client for the web view
    * @param pb
    * @return
    */
   protected WebClient getWebViewClient(final ProgressBar pb) {
      return new WebClient(this, wv, pb);
   }

   /**
    * Return the site URL to load
    * 
    * @return
    */
   public String getSiteUrl() {
	return getResources().getStringArray(R.array.sites_url)[0];
   }

   public void openSite(String url) {
	    String uaNexus = "Mozilla/5.0 (Linux; Android 4.2.2; Galaxy Nexus Build)(KHTML, like Gecko) Mobile"; 
	    String uaIphone = "Mozilla/5.0 (iPhone; CPU iPhone OS 6_0 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Version/6.0 Mobile/10A5376e Safari/8536.25";
	    String uaFX = "Mozilla/5.0 (Mobile; rv:38.0) Gecko/32.0 Firefox/38.0"; 
	    String uaNexus5  = "Mozilla/5.0 (Linux; Android 5.0; Nexus 5 Build/LRX21O) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/37.0.0.0 Mobile Safari/537.36";
	    String uaKK  = "Mozilla/5.0 (Linux; Android 4.4; Nexus 5 Build/_BuildID_) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/30.0.0.0 Mobile Safari/537.36";
	    String uaNexus4 = "Mozilla/5.0 (Linux; Android 4.3; Nexus 4 Build/JWR66V) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.59 Mobile Safari/537.36";
	    String uaXX = "Mozilla/5.0 (Linux; Android 4.3; Galaxy Nexus Build/JWR66Y) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.92 Mobile Safari/537.36";
	    String some_file = "Some-file";
	    wv.getSettings().setUserAgentString(uaNexus);
//	    File file = new File(getExternalFilesDir(null), "DemoFile.jpg");
//	    String baseDir = Environment.getExternalStorageDirectory().getAbsolutePath();
//	    url = "file://" + Environment.getExternalStorageDirectory().getAbsolutePath() + "/s/1.html" ;
//	    url = "file:///android_asset/संतान गणपति स्तोत्र.html" ;
//	    url = "file:///android_asset/2.html" ;
	    String local_url = "file:///android_asset/" + url ;
//	    if (baseDir != null) {
//	      	   //Log.d("Devanagari", "Setting cache mode to: LOAD_DEFAULT " + baseDir);
//	    }
   	    Log.d("Devanagari_offline", "local_url: " + local_url);
	    wv.loadUrl(local_url);
	    
	    
	    // KEEP ONLY 2 FILES
	    // HTML AND *widget_css_mobile_2_bundle.css
	    
	    
   }

   @Override
   public boolean onKeyDown(int keyCode, KeyEvent event) {
	      if ((keyCode == KeyEvent.KEYCODE_BACK) && wv.canGoBack()) {
	          wv.goBack();
	          
	          return true;
	       }
      return super.onKeyDown(keyCode, event);
   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      super.onCreateOptionsMenu(menu);
      MenuInflater inflater = getMenuInflater();
      inflater.inflate(R.menu.menu, menu);
      return true;
   }



   
   @Override
@SuppressWarnings("deprecation")
public boolean onOptionsItemSelected(MenuItem item) {
      switch (item.getItemId()) {     
         case R.id.menu_site:
            showDialog(DIALOG_SITE);
            return true;
/*         case R.id.menu_toggle:
             load_images = !load_images;
             item.setIcon(load_images ? R.drawable.ic_action_image
                     : R.drawable.ic_action_broken_image);
             wv.getSettings().setLoadsImagesAutomatically(load_images);
        	 return true;*/
         case R.id.menu_exit:
//        	 wv.clearCache(true);
//        	 Toast.makeText(this,"App clear " + "and exit",Toast.LENGTH_SHORT).show();
        	    try {
        	        // clearing app data
        	        Runtime runtime = Runtime.getRuntime();
        	        runtime.exec("pm clear com.android.devanagari_offline");
        	    } catch (Exception e) {
        	        e.printStackTrace();
        	    }
//        	 wv.stopLoading();
//        	 WebStorage.getInstance().deleteAllData();
//        	 CookieSyncManager.createInstance(this);
//        	 CookieManager cookieManager = CookieManager.getInstance();
//        	 cookieManager.removeAllCookie();
//        	 Toast.makeText(this,"Clearing cookies",Toast.LENGTH_SHORT).show();
        	 finish();
            return true;
      }
      return false;
   }
   
   @Override
   protected Dialog onCreateDialog(int id) {
      Dialog dialog = null;

      switch (id) {
         case DIALOG_SITE:
            dialog = new AlertDialog.Builder(this).setTitle("Select Site").setItems(R.array.sites, new OnClickListener() {
               @Override
               public void onClick(DialogInterface arg0, int arg1) {
                  arg0.dismiss();
                  String url = getResources().getStringArray(R.array.sites_url)[arg1];
                  openSite(url);
               }
            }).create();
            return dialog;

      }

      return super.onCreateDialog(id);
   }
}