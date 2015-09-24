
package com.homedesign;

import android.app.Activity;
import android.os.Bundle;

public class ActivityMain extends Activity {
    
    private HomeDesignGLSurfaceView mGLSurfaceView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGLSurfaceView = new HomeDesignGLSurfaceView(this);
        setContentView(mGLSurfaceView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGLSurfaceView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGLSurfaceView.onPause();
    }
}
