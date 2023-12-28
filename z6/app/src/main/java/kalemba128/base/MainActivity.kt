package kalemba128.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.realm.Realm
import io.realm.RealmConfiguration

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Realm.init(this)
        val realmName = "Base"
        val config = RealmConfiguration.Builder().name(realmName).schemaVersion(1).build()
        val instance: Realm = Realm.getInstance(config)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}