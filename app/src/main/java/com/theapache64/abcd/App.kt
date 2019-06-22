package com.theapache64.abcd


import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import androidx.multidex.MultiDexApplication
import com.theapache64.abcd.di.components.DaggerAppComponent
import com.theapache64.twinkill.TwinKill
import com.theapache64.twinkill.googlefonts.GoogleFonts
import com.theapache64.twinkill.network.di.modules.BaseNetworkModule
import com.theapache64.twinkill.network.utils.retrofit.interceptors.CurlInterceptor
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class App : MultiDexApplication(), HasActivityInjector, HasSupportFragmentInjector {


    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector
    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector

    override fun onCreate() {
        super.onCreate()

        // Dagger
        DaggerAppComponent.builder()
            .baseNetworkModule(BaseNetworkModule(""))
            .build()
            .inject(this)

        // TwinKill
        TwinKill.init(
            TwinKill
                .builder()
                .addOkHttpInterceptor(CurlInterceptor())
                .setDefaultFont(GoogleFonts.GoogleSansRegular)
                .build()
        )
    }

}
