package com.example.petrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.realm.Realm
import io.realm.RealmConfiguration

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Realm.init(this)
        Realm.setDefaultConfiguration(
            RealmConfiguration.Builder()
            /*.initialData { realm: Realm ->  //вызывается когда нет файла реалм, надо всё удалить сначала
                // Load from file "cities.json" first time

                val cities: List<Note?>? = loadCities()
                if (cities != null) {
                    // Use insertOrUpdate() to convert the objects into proper RealmObjects managed by Realm.
                    realm.insertOrUpdate(cities)//конкретно эта строчка не работает
                }
            }

             */
            .allowWritesOnUiThread(true)
            .allowQueriesOnUiThread(true)
            .deleteRealmIfMigrationNeeded()
            .build()
        )
        Realm.getDefaultInstance()

    }
}