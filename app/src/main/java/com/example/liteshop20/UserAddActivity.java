package com.example.liteshop20;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;
import java.util.List;
import java.util.Locale;

public class UserAddActivity extends AppCompatActivity implements View.OnClickListener{
    EditText editT1,editT2,editT3,editT4,editT5,editT6;
    Button button;
    List<ProdCart> prodCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_add);
        //Το κουμπί που περιμένουμε να πατηθεί
        button= findViewById(R.id.button);
        button.setOnClickListener(this);
        //Πέρνουμε τις τιμές που δίνει ο χρήστης
        editT1 = findViewById(R.id.editText);
        editT2 = findViewById(R.id.editText2);
        editT3 = findViewById(R.id.editText3);
        editT4 = findViewById(R.id.editText4);
        editT5 = findViewById(R.id.editText5);
        editT6 = findViewById(R.id.editText6);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {
        boolean flag=true;
        String Var_phoneNum;
        //Χρειάζεται έλεγχος για τις εισόδους
        String Var_username = editT1.getText().toString();
        String Var_address = editT2.getText().toString();
        String Var_postCode =editT3.getText().toString();
        if(Var_postCode.length() != 5){ //Έλεγχος ταχυδρομικού κώδικα
            flag=false;
            Toast.makeText(this,"Ο ταχυδρομικός κώδικας δεν είναι σωστός",Toast.LENGTH_LONG).show();
        }
        String Var_town = editT4.getText().toString();
        Var_phoneNum =editT5.getText().toString();
        if (Var_phoneNum.length()!=10 && flag){ //Έλεγχος τηλεφώνου, και αν ειναι ήδη το flag=false τοτε δεν μπαινει γτ το ένα Toast θα σβήσει το προηγούμενο.
            flag=false;
            Toast.makeText(this,"Ο τηλεφωνικός αριθμός που έδωσες δεν είναι σωστός", Toast.LENGTH_LONG).show();
        }
        String Var_notes =""+editT6.getText().toString();
        if(Var_username.length()==0 || Var_address.length()==0 ||Var_town.length()==0){
            flag=false;
            Toast.makeText(this,"Προσοχή! Άφησες κάποιο προαπαιτούμενο πεδίο κενό.",Toast.LENGTH_LONG).show();
        }
        if(flag) {
            User user = new User(); //Δίνουμε "μορφή" στα δεδομένα για να τα εισάγουμε στη βάση.
            user.setName(Var_username);
            user.setAddress(Var_address);
            user.setPostCode(Var_postCode);
            user.setPhoneNum(Var_phoneNum);
            user.setNotes(Var_notes);
            user.setTown(Var_town);
            MainActivity.myAppDatabase.myDao().addUser(user); //Κάνουμε insert τις τιμές που πήραμε στη βάση.
            Toast.makeText(this, "Έγινε!", Toast.LENGTH_LONG).show(); //Μήνυμα πως όλα πήγαν καλά.
            try {
                prodCart = MainActivity.myAppDatabase.myDao().getProdCart();
                for(ProdCart i:prodCart) {
                    if(MainActivity.myAppDatabase.myDao().getProdCartAmount(i.getId())>0) {
                        //Ανανέωση του Stack του προϊόντος
                        int amount = MainActivity.myAppDatabase.myDao().getProductStack(i.getId());
                        MainActivity.myAppDatabase.myDao().updateProductsStack(i.getId(), (amount - i.getAmount()));
                        //Στοιχεία που θέλουμμε για τη δημιουργία της παραγγελίας
                        int Var_uid = MainActivity.myAppDatabase.myDao().getUserID(Var_username, Var_phoneNum);
                        String Var_date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                        // Δημιουργία παραγγελίας
                        Orders order = new Orders();
                        order.setUid(Var_uid);
                        order.setPid(i.getId());
                        order.setOr_Quantity(i.getAmount());
                        order.setOr_date(Var_date);
                        MainActivity.myAppDatabase.myDao().addOrder(order);
                    }
                    //Διαγραφή απ το καλάθι
                    MainActivity.myAppDatabase.myDao().deleteProdCart(i);
                }

                Toast.makeText(this,"Η παραγγελία ολοκληρώθηκε επιτυχώς!",Toast.LENGTH_LONG).show();

                finish(); //Κλείνει και καθαρίζει η διεπαφή
            }catch(Exception e){
                Toast.makeText(this,"Αποτυχία παραγγελίας. Ελένξτε ξανά όλα τα στοιχεία",Toast.LENGTH_LONG).show();
                Log.e("UserAddActivity: ",""+e);
                finish();
            }
        }
    }
}
