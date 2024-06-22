package com.vsgaa.aplikasisqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vsgaa.aplikasisqlite.helper.DbHelper;

public class AddEditActivity extends AppCompatActivity {

    private DbHelper helper;
    private EditText etName, etHarga, etJumlah;
    private String id, name, harga, jumlah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        etName = findViewById(R.id.et_name);
        etHarga = findViewById(R.id.et_harga);
        etJumlah = findViewById(R.id.et_jumlah);

        //terima data jika ada
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getString("id");
            name = extras.getString("name");
            harga = extras.getString("harga");
            jumlah = extras.getString("jumlah");


            etName.setText(name);
            etHarga.setText(harga);
            etJumlah.setText(jumlah);
        }

        Button btn_submit = findViewById(R.id.btn_submit);
        Button btn_cancel = findViewById(R.id.btn_cancel);

        helper = new DbHelper(this);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (id != null)
                    edit();
                else
                    save();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void save() {
        name = etName.getText().toString();
        harga = etHarga.getText().toString();
        jumlah = etJumlah.getText().toString();
        if (name.equals("") || harga.equals("") || jumlah.equals("")) {
            Toast.makeText(this, "Nama, Harga, atau Jumlah tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else {
            long rowId = helper.insert(name, harga, jumlah);
            if (rowId == -1) {
                Toast.makeText(this, "Insert gagal", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Insert berhasil", Toast.LENGTH_SHORT).show();
                blank();
            }
        }
    }

    private void blank() {
        etName.setText(null);
        etHarga.setText(null);
        etJumlah.setText(null);
    }

    private void edit() {
        if (name.equals("") || harga.equals("") || jumlah.equals("")) {
            Toast.makeText(this, "Nama, Harga, atau Jumlah tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else {
            String newName = etName.getText().toString();
            String newHarga = etHarga.getText().toString();
            String newJumlah = etJumlah.getText().toString();
            long rowAffected = helper.update(Integer.parseInt(id), newName, newHarga, newJumlah);
            if (rowAffected <= 0) {
                Toast.makeText(this, "Update gagal", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Update berhasil", Toast.LENGTH_SHORT).show();
            }
        }
    }
}