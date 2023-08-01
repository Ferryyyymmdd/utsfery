package com.FerrySaptawan.uas_202102277;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ApotekerActivity extends AppCompatActivity {
    EditText kode, nama, jk, alamat, telepon, noizin;
    Button simpan, tampil, hapus, edit;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apoteker);

        kode = findViewById(R.id.edtkode);
        nama = findViewById(R.id.edtnama);
        jk = findViewById(R.id.edtjk);
        alamat = findViewById(R.id.edtalamat);
        telepon = findViewById(R.id.edttelepon);
        noizin = findViewById(R.id.edtnoizin);
        simpan = findViewById(R.id.btnsimpan);
        tampil = findViewById(R.id.btntampil);
        hapus = findViewById(R.id.btnhapus);
        edit = findViewById(R.id.btnedit);
        db = new DBHelper(this);

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtkode = kode.getText().toString();
                String txtnama = nama.getText().toString();
                String txtjk = jk.getText().toString();
                String txtalamat = alamat.getText().toString();
                String txttelepon = telepon.getText().toString();
                String txtnoizin = noizin.getText().toString();


                if (TextUtils.isEmpty(txtkode) || TextUtils.isEmpty(txtnama) || TextUtils.isEmpty(txtjk)
                        || TextUtils.isEmpty(txtalamat) || TextUtils.isEmpty(txttelepon) || TextUtils.isEmpty(txtnoizin)) {
                    Toast.makeText(ApotekerActivity.this, "Semua Field Wajib diIsi", Toast.LENGTH_LONG).show();
                } else {
                    boolean checkkode = db.checkkode(txtkode);
                    if (!checkkode) {
                        boolean insert = db.insertDataApoteker(txtkode, txtnama, txtjk, txtalamat, txttelepon, txtnoizin);
                        if (insert) {
                            Toast.makeText(ApotekerActivity.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ApotekerActivity.this, "Data gagal disimpan", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(ApotekerActivity.this, "Data Mahasiswa Sudah Ada", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        tampil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = db.tampildataapoteker();
                if (res.getCount() == 0) {
                    Toast.makeText(ApotekerActivity.this, "Tidak Ada Data", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(getApplicationContext(), BiodataApoteker.class);
                startActivity(intent);
            }
        });


        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String kodeToDelete = kode.getText().toString();

                boolean isDeleted = db.deleteDataApoteker(kodeToDelete);

                if (isDeleted) {
                    Toast.makeText(ApotekerActivity.this, "Record deleted successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ApotekerActivity.this, "Failed to delete record", Toast.LENGTH_SHORT).show();
                }
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newkode = kode.getText().toString();
                String newnama = nama.getText().toString();
                String newjk = jk.getText().toString();
                String newalamat = alamat.getText().toString();
                String newtelepon = telepon.getText().toString();
                String newnoizin = noizin.getText().toString();

                if (TextUtils.isEmpty(newkode) || TextUtils.isEmpty(newnama) || TextUtils.isEmpty(newjk)
                        || TextUtils.isEmpty(newalamat) || TextUtils.isEmpty(newtelepon)) {
                    Toast.makeText(ApotekerActivity.this, "Semua Field Wajib diIsi", Toast.LENGTH_LONG).show();
                } else {
                    boolean isUpdated = db.updateDataApoteker(newkode, newnama, newjk, newalamat, newtelepon, newnoizin);

                    if (isUpdated) {
                        Toast.makeText(ApotekerActivity.this, "Data berhasil diupdate", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ApotekerActivity.this, "Data gagal diupdate", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}