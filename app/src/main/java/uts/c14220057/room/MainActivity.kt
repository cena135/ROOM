package uts.c14220057.room

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.room.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import uts.c14220057.room.database.daftarBelanjaDB

class MainActivity : AppCompatActivity() {

    private lateinit var DB : daftarBelanjaDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        DB = daftarBelanjaDB.getDatabase(this)

        val _fabAdd = findViewById<Button>(R.id.fabAdd)

        _fabAdd.setOnClickListener {
            startActivity(Intent(this,TambahDaftar::class.java))
        }

        super.onStart()
        CoroutineScope(Dispatchers.Main).async {
            val daftarBelanja = DB.fundaftarBelanjaDAO().selectAll()
            Log.d("data ROOM", daftarBelanja.toString())
        }

    }
}