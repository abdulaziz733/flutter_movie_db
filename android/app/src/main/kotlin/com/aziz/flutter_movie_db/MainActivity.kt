package com.aziz.flutter_movie_db

import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import com.aziz.flutter_movie_db.model.MovieLocalDb
import com.aziz.flutter_movie_db.utils.RealmHelper
import com.google.gson.Gson
import io.flutter.app.FlutterActivity
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugins.GeneratedPluginRegistrant
import io.realm.Realm

class MainActivity : FlutterActivity() {
    private final var CHANNEL = "movie-db"
//    var listResult: MutableList<MovieLocalDb> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GeneratedPluginRegistrant.registerWith(this)
        Realm.init(this)
//    Realm.setDefaultConfiguration(
//            RealmConfiguration.Builder()
//                    .name("com.aziz.flutter_movie_db")
////                        .encryptionKey(Const.getRealmKey())
//                    .deleteRealmIfMigrationNeeded()
////                .schemaVersion(Const.realmSchemaVersion)
////                .migration(SchemaMigration())
//                    .build()
//    )


        var realm = Realm.getDefaultInstance()
        MethodChannel(flutterView, CHANNEL).setMethodCallHandler { call, result ->
            if (call?.method.equals("movieAddFavorite")) {
                val data: Any? = call?.argument("data")
//                Log.i("datadata", data.toString())

                val movieData: MovieLocalDb = Gson().fromJson(data.toString(), MovieLocalDb::class.java)
                val list: MutableList<MovieLocalDb> = mutableListOf()
                list.add(movieData)
                RealmHelper().updateAll(list)

                var listResult = realm.copyFromRealm(RealmHelper().getAll(realm, MovieLocalDb::class.java))

//                Log.i("datadata", "hasil realm  ${Gson().toJson(listResult)}")
                result?.success(Gson().toJson(listResult))

            } else if (call?.method.equals("getDataLocal")) {
                var listResult = realm.copyFromRealm(RealmHelper().getAll(realm, MovieLocalDb::class.java))

//                Log.i("datadata", "hasil realm  ${Gson().toJson(listResult)}")
                result?.success(Gson().toJson(listResult))
            } else if (call?.method.equals("getBatteryLevel")) {
                val batteryLevel = getBatteryLevel()

                if (batteryLevel != -1){
                    result?.success(batteryLevel)
                } else {
                    result?.error("UNAVAILABLE", "Could not fetch battery level", null)
                }
            } else {
                result?.notImplemented()
            }
        }

    }

    fun getBatteryLevel(): Int {
        var batteryLevel = -1;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            val batteryManager = getSystemService(Context.BATTERY_SERVICE) as BatteryManager
            batteryLevel = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
        } else {
            val intent = ContextWrapper(applicationContext).registerReceiver(null, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
            batteryLevel = (intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -2) * 100) /
                    intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
        }

        return batteryLevel
    }

}
