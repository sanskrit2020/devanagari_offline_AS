package com.android.devanagari_offline;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;

import com.android.devanagari_offline.R;
import com.android.devanagari_offline.webviewclient.BlockWebClientv11;
import com.android.devanagari_offline.webviewclient.WebClient;




/**
 * Extensions to the main activity for Android 3.0+
 * @author toby
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class NewsActivityv11 extends MainActivity {
   // variables to track dragging for actionbar auto-hide
   protected float startX;
   protected float startY;
   boolean skipInitialLoad = false;

   @Override
   public void onCreate(Bundle savedInstanceState) {
      v11 = true; // prevent recursive activity redirects
      super.onCreate(savedInstanceState);

      if (getIntent().getDataString() != null) {
         skipInitialLoad = true;
      }
      
      // setup actionbar
      ActionBar ab = getActionBar();
      ab.setDisplayShowTitleEnabled(false);
      ab.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
      ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
               android.R.layout.simple_list_item_1,
               android.R.id.text1,
               getResources().getStringArray(R.array.sites));
      ab.setListNavigationCallbacks(adapter, new OnNavigationListener() {
         @Override
         public boolean onNavigationItemSelected(int arg0, long arg1) {
            String url = getResources().getStringArray(R.array.sites_url)[arg0];
            if (!skipInitialLoad) openSite(url);
            else skipInitialLoad = false;
            return true;
         }
      });
      
   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      boolean ret = super.onCreateOptionsMenu(menu);
      menu.findItem(R.id.menu_site).setVisible(false);
      return ret;
   }
   
   @Override
   protected WebClient getWebViewClient(ProgressBar pb) {
      return new BlockWebClientv11(this, wv, pb);
   }


}
