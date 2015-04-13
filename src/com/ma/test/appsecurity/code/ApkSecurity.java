package com.ma.test.appsecurity.code;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

public class ApkSecurity extends Activity{
	
	String installerId = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		try {
		    installerId = this.getPackageManager().getInstallerPackageName(this.getPackageName());
		} catch (Exception e) {
		e.printStackTrace();
		    //just in case...
		}
			// For Amazon Store
		if ("com.amazon.venezia".equals(installerId)) {
		    // For Play Store
		} else if ("com.android.vending".equals(installerId)) {
			Toast.makeText(getApplicationContext(), "authorized resource", Toast.LENGTH_LONG).show();
			// continue to app
		} else {
			Toast.makeText(getApplicationContext(), "unauthorized resource", Toast.LENGTH_LONG).show();
			uninstallApp();
		}
		
		super.onCreate(savedInstanceState);
	}
	
	public void uninstallApp() {
		Intent intent = new Intent(Intent.ACTION_DELETE);
		intent.setData(Uri.parse("package:" + this.getPackageName()));		// provide your package name
		startActivity(intent);
	}	

}
