package com.FerrySaptawan.uas_202102277;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BiodataApoteker extends AppCompatActivity {
    private DBHelper dbHelper;
    private TextView textViewData;

    Button btnhapus;

    EditText hapus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biodata_apoteker);

        dbHelper = new DBHelper(this);
        textViewData = findViewById(R.id.textViewData);
        btnhapus = findViewById(R.id.btnhapus);
        hapus = findViewById(R.id.hapus);

        btnhapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String noperToDelete = hapus.getText().toString();

                boolean isDeleted = dbHelper.deleteDataApoteker(noperToDelete);

                if (isDeleted) {
                    Toast.makeText(BiodataApoteker.this, "Record deleted successfully", Toast.LENGTH_SHORT).show();
                    refreshData();
                } else {
                    Toast.makeText(BiodataApoteker.this, "Failed to delete record", Toast.LENGTH_SHORT).show();
                }
            }
        });


        Cursor res = dbHelper.tampildataapoteker();
        if (res.getCount() == 0) {
            Toast.makeText(BiodataApoteker.this, "Tidak Ada Data", Toast.LENGTH_SHORT).show();
        } else {
            StringBuilder data = new StringBuilder();
            while (res.moveToNext()) {
                String kode = res.getString(0);
                String nama = res.getString(1);
                String jk = res.getString(2);
                String alamat = res.getString(3);
                String telepon = res.getString(4);
                String noizin = res.getString(5);

                data.append("Kode: ").append(kode).append("\n");
                data.append("Nama: ").append(nama).append("\n");
                data.append("Jenis Kelamin: ").append(jk).append("\n");
                data.append("Alamat: ").append(alamat).append("\n");
                data.append("Telepon: ").append(telepon).append("\n");
                data.append("NO Izin: ").append(noizin).append("\n\n\n");
            }
            textViewData.setText(data.toString());
        }

        res.close();
    }
    private void loadData() {
        Cursor res = dbHelper.tampildataapoteker();
        if (res.getCount() == 0) {
            Toast.makeText(BiodataApoteker.this, "Tidak Ada Data", Toast.LENGTH_SHORT).show();
        } else {
            StringBuilder data = new StringBuilder();
            while (res.moveToNext()) {
                String kode = res.getString(0);
                String nama = res.getString(1);
                String jk = res.getString(2);
                String alamat = res.getString(3);
                String telepon = res.getString(4);
                String noizin = res.getString(5);

                data.append("Kode: ").append(kode).append("\n");
                data.append("Nama: ").append(nama).append("\n");
                data.append("Jenis Kelamin: ").append(jk).append("\n");
                data.append("Alamat: ").append(alamat).append("\n");
                data.append("Telepon: ").append(telepon).append("\n");
                data.append("NO Izin: ").append(noizin).append("\n\n\n");
            }

            textViewData.setText(data.toString());
            res.close();
        }
    }

    private void refreshData() {
        textViewData.setText("");
        loadData();
    }
}