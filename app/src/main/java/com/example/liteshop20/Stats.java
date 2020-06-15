package com.example.liteshop20;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class Stats extends Fragment {
    private TextView textResult;
    public Stats() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_stats, container, false);
        //Spinner με τα ερωτήματα
        Spinner spinner = view.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(Objects.requireNonNull(getActivity()), R.array.spinner_names, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                displayResult(i+1);
                //Στέλνουμε ποιό ερώτημα πάτησε ο χρήστης και εμφανίζει το αποτέλεσμα η displayResult.
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        textResult=view.findViewById(R.id.SpinnerShowResultstextView);


        return view;
    }
    private void displayResult(int choice){
        StringBuilder result = new StringBuilder();
        //Ανάλογα ποιό στατιστικό ζητηθεί, στέλνουμε το αντίστοιχό Query στη βάση.
        switch (choice) {
            case 1: //Υπόλοιπες ποσόστητες προϊόντων.
                List<Product> product = MainActivity.myAppDatabase.myDao().getProducts();
                for (Product i : product) {
                    String title = i.getTitle();
                    int amount = i.getStack();
                    result.append(" Τίτλος προϊόντος: ").append(title).append("\n Απόθεμα: ").append(amount).append("\n-------------------------\n");
                }
                textResult.setText(result.toString());
                break;
            case 2: //Συνολικές πωλήσεις προϊόντων.
                int sum = 0;
                List<Integer> poliseisTotal = MainActivity.myAppDatabase.myDao().query2();
                for (Integer i : poliseisTotal) {
                    sum += i;
                }
                String sumTxt=" Έχουν πωληθεί συνολικά "+sum+" προϊόντα.";
                textResult.setText(sumTxt);
                break;
            case 3: //Συνολικές πωλήσεις ανα προϊον.
                List<Poliseis> poliseis = MainActivity.myAppDatabase.myDao().query3();
                for (Poliseis i : poliseis) {
                    String title = i.getTitlos();
                    int amount = i.getPosotita();
                    result.append(" Τίτλος προϊόντος: ").append(title).append("\n Πωλήσεις: ").append(amount).append("\n-------------------------\n");
                }
                textResult.setText(result.toString());
                break;
        }
    }
}
