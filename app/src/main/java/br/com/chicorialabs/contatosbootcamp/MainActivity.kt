package br.com.chicorialabs.contatosbootcamp

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    val REQUEST_CONTACT = 42
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_CONTACTS),
                REQUEST_CONTACT
            )
        } else {
            setContacts()
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode == REQUEST_CONTACT) setContacts()

    }
    private fun setContacts(){

        val listaRecuperada: ArrayList<Contact> = arrayListOf()

        val cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
        null, null, null, null)

        if (cursor != null) {
            while (cursor.moveToNext()) {
                listaRecuperada.add(Contact(cursor.getString(cursor.getColumnIndex(ContactsContract
                    .CommonDataKinds.Phone.DISPLAY_NAME)),
                    cursor.getString(cursor.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.NUMBER)),
                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds
                        .Phone.DISPLAY_NAME_ALTERNATIVE))
                ))
            }
            cursor.close()
        }

        adapter = ContactAdapter(listaRecuperada)
        recyclerView = findViewById(R.id.main_recyclerview)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = adapter
    }


}

