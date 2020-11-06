package com.android.devanagari_offline.webviewclient;

import com.android.devanagari_offline.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebClient extends WebViewClient {
   Activity activity;
   WebView wv;
   View pd;
   public static String domain_name = "http://golem.de";

   public WebClient(Activity activity, WebView wv, View pd) {
      this.activity = activity;
      this.wv = wv;
      this.pd = pd;
   }

   @Override
   public void onPageFinished(WebView view, String url) {
      if (pd != null) pd.setVisibility(View.GONE);

      // Google+ workaround to prevent opening of blank window
      wv.loadUrl("javascript:_window=function(url){ location.href=url; }");
//      Log.d("News", "Finishing " + url);
      domain_name = url;
// create    public static boolean url_parameter = something here;

      super.onPageFinished(view, url);
   }

   @Override
   public void onPageStarted(WebView view, String url, Bitmap favicon) {
// This is for the circle that indicates loading site
	   // do not remove
      if (pd != null) pd.setVisibility(View.VISIBLE);
      super.onPageStarted(view, url, favicon);
   }

   


   
   /**
    * Returns true if the site is within the acceptable domains
    * @param uri
    * @return
    */
   @SuppressLint("DefaultLocale")
protected boolean isAllowedSite(Uri uri) {
//	      Log.d("News", "My domain name " + domain_name);

      // String url = uri.toString();
      String host = uri.getHost();
      String sch = uri.getScheme();
      if (sch.startsWith("http")){
      	String[] allowedSites = activity.getResources().getStringArray(R.array.allowed_sites);
      	for (String sites : allowedSites) {
    	  	for (String site : sites.split(" ")) {
        	 if (host.toLowerCase().endsWith(site.toLowerCase())) { return true; }
         	}
      	}
   	}	
      return false;  
   }
   
   
   
   // ends class
}
