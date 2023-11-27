package com.example.vacationsrus.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vacationsrus.R;

public class ProductList extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

//        Button button = findViewById(R.id.gotoproductdetails);
//        button.setOnClickListener(new View.OnClickListener(){;
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(ProductList.this, ProductDetails.class);
//                intent.putExtra("test", "Information Sent");
//                startActivity(intent);
//            }
//        });
//
//        System.out.println(getIntent().getStringExtra("test"));
//    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_product_list, menu);
//        return true;
//    }
//
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if(item.getItemId()==R.id.mysample) {
//            Toast.makeText(ProductList.this, "put in sample data", Toast.LENGTH_LONG).show();
//            return true;
//        }
//        if(item.getItemId()==android.R.id.home){
//            this.finish();
////            Intent intent = new Intent(ProductList.this, ProductDetails.class);
////            startActivity(intent);
//            return true;
//        }
//        return true;
    }
}
