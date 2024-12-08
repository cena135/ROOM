package uts.c14220057.room

import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import uts.c14220057.room.database.daftarBelanja
import uts.c14220057.room.database.daftarBelanjaDAO
import uts.c14220057.room.database.daftarBelanjaDB
import uts.c14220057.room.helper.DateHelper.getCurrentDate

class TambahDaftar : AppCompatActivity() {

    var DB = daftarBelanjaDB.getDatabase(this)
    var tanggal = getCurrentDate()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tambah_daftar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val _etItem = findViewById<EditText>(R.id.etItem)
        val _etJumlah = findViewById<EditText>(R.id.etJumlah)

        CoroutineScope(Dispatchers.IO).async {
            DB.fundaftarBelanjaDAO().insert(
                daftarBelanja(
                    tanggal = tanggal,
                    item = _etItem.text.toString(),
                    jumlah = _etJumlah.text.toString()
                )
            )
        }

    }
}