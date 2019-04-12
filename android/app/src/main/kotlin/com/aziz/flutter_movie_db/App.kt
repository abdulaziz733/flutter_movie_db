package com.aziz.flutter_movie_db

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by Leo Wirasanto Laia on 11/05/2018.
 */
class App : MultiDexApplication() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        Realm.setDefaultConfiguration(
                RealmConfiguration.Builder()
                        .name("Moviedb-realm")
//                        .encryptionKey(Const.getRealmKey())
                        .deleteRealmIfMigrationNeeded()
//                .schemaVersion(Const.realmSchemaVersion)
//                .migration(SchemaMigration())
                        .build()
        )
    }
}