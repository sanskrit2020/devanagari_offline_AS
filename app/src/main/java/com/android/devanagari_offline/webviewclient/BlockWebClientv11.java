package com.android.devanagari_offline.webviewclient;

import java.io.ByteArrayInputStream;
import android.annotation.TargetApi;
import android.app.Activity;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;

/**
 * WebViewClient for Android 3.0+
 * 
 * @author toby
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class BlockWebClientv11 extends WebClient {
   public BlockWebClientv11(Activity activity, WebView wv, View pd) {
      super(activity, wv, pd);
   }
   
   @Override
   public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
      // Block 3rd party requests (i.e. scripts/iframes/etc. outside Google's domains)
      // and also any unencrypted connections
//      if ((!url.startsWith("data:") && (!isAllowedSite(Uri.parse(url))))
	   if (!url.contains("asset")
    		  ){
         Uri uri = Uri.parse(url);
//  here is the blocking logging EXTERNAL
         Log.d("Devanagari", "External URL Blocking " + url);
         return new WebResourceResponse("text/plain", "utf-8", 
                  new ByteArrayInputStream(("[URL blocked \n" + uri.getHost() + "]").getBytes()));
         
      }
      
      return super.shouldInterceptRequest(view, url);
   }
}
