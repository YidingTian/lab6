package com.example.lab6_fall;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
// to see the database, goot View > Tool Windows > Device File Explorer
//data/data/lab6_fall/databases
public class MainActivity extends AppCompatActivity {

    TextView idView;
    EditText productBox;
    EditText skuBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        idView = findViewById(R.id.productID);
        productBox = findViewById(R.id.productName);
        skuBox = findViewById(R.id.productSku);

    }


    public void newProduct (View view) {
        // add your code here...
        MyDBHandler dbHandler = new MyDBHandler(this);

        int sku = Integer.parseInt(skuBox.getText().toString());
        Product product = new Product(productBox.getText().toString(),sku);
        dbHandler.addProduct(product);

        productBox.setText("");
        skuBox.setText("");

    }


    public void lookupProduct (View view) {
        // add your code here...
        MyDBHandler dbHandler = new MyDBHandler(this);
        Product product = dbHandler.findProduct(productBox.getText().toString());

        if (product != null) {
            idView.setText(String.valueOf(product.getID()));
            skuBox.setText(String.valueOf(product.getSku()));
        } else {
            idView.setText("No Match Found");
        }
    }


    public void removeProduct (View view) {
        // add your code here...
        MyDBHandler dbHandler = new MyDBHandler(this);

        boolean result = dbHandler.deleteProduct((productBox.getText().toString()));

        if (result) {
            idView.setText("Record Deleted");
            productBox.setText("");
            skuBox.setText("");
            }
        else idView.setText("No Match Found");
        }
    }
