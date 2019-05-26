package com.poduri.manohar.whatsappclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("zd57H1YFUgGZbaA2ly34G9pwvE63TqIpqIyW5Mwa")
                // if defined
                .clientKey("v9fgzniYof9bW3rvs5502SsGusgG8Ec4wYTVEVnK")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
