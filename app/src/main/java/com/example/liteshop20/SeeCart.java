package com.example.liteshop20;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SeeCart extends Fragment implements ProductAdapter.MyClickListener {

    public SeeCart() {
        // Required empty public constructor
    }

    private ArrayList<Integer> ids = new ArrayList<>();
    private ArrayList<String> products = new ArrayList<>();
    private List<ProdCart> products2;
    private TextView sumText;
    private RecyclerView.Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_see_cart, container, false);
        TextView textView = view.findViewById(R.id.cartDesc);
        sumText = view.findViewById(R.id.textView29);
        Button orderButton = view.findViewById(R.id.OrderButton);
        try{
            textView.setText("Αυτά είναι τα προϊόντα που έχεις στο καλάθι σου:");


            products2 =MainActivity.myAppDatabase.myDao().getProdCart();
            for (ProdCart i: products2){
                int id = i.getId();
                int stack = i.getAmount();

                if(stack>0){
                    String ptitle=MainActivity.myAppDatabase.myDao().getProductTitle(id);
                    ids.add(id);
                    products.add("Τίτλος: "+ptitle+"\n Πήρες: "+stack);
                }
            }
            }catch(Exception e){
                textView.setText("Το καλάθι σου είναι άδειο.");
                Log.e("SeeCart:",""+e);
            }
            setSum();

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewCart);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            adapter = new ProductAdapter(products, this);
            recyclerView.setAdapter(adapter);
            //Όταν πατηθεί το κουμπί για παραγγελία ελέγχουμε αν όλα είναι εντάξει και προχωράμε στα στοιχεία του χρήστη.
            orderButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean flag=true;
                    for(ProdCart i:products2){
                        int amount = MainActivity.myAppDatabase.myDao().getProdCartAmount(i.getId());
                        //Αν μπεί στην παρακάτω if σημαίνει πως ή το καλάθι είναι άδειο ή υπάρχουν παραπάνω παραγγελίες απ ότι προϊόντα.
                        if(amount>MainActivity.myAppDatabase.myDao().getProductStack(i.getId()) || amount==0){
                            String title=MainActivity.myAppDatabase.myDao().getProductTitle(i.getId());
                            if(amount==0) //Άδειο καλάθι
                                Toast.makeText(getActivity(),"Το καλάθι σου είναι άδειο",Toast.LENGTH_SHORT).show();
                            else //Παραπάνω παραγγελίες απ ότι προϊόντα.
                                Toast.makeText(getActivity(),"Έχεις βάλει παραπάνω προϊόντα στο καλάθι από ότι υπάρχουν στο απόθεμα για: "+title,Toast.LENGTH_LONG).show();
                            flag=false;
                            break;
                        }
                    }
                    if(flag){
                        Intent intent = new Intent(getActivity(),UserAddActivity.class);
                        startActivity(intent);
                        MainActivity.fragmentManager.popBackStack();

                    }
                }
            });


        return view;
    }
    private void setSum(){ //Μετράμε τα προϊόντα και βρίσκουμε τη συνολική τιμή.
        int sum = 0;
        try {
            for (ProdCart i : products2) {
                int id = i.getId();
                int price = MainActivity.myAppDatabase.myDao().getProductPrice(id);
                int amount = MainActivity.myAppDatabase.myDao().getProdCartAmount(id);

                if(amount>0)
                sum += price * amount;
                Log.d("Sum= ",""+sum);
            }
        }catch(Exception e){
            Log.d("SeeCart","Κάτι πήγε στραβά");
        }
        String sumTxt="Σύνολο: "+sum +" Ευρώ";
        sumText.setText(sumTxt);
    }

    @Override
    public void onMyClickListener(int position) {
        Log.d("OnButtonClick: ",position+" button clicked.");
        try{
            MainActivity.delCart(ids.get(position));
            String ptitle=MainActivity.myAppDatabase.myDao().getProductTitle(ids.get(position));
            int stack = MainActivity.myAppDatabase.myDao().getProdCartAmount(ids.get(position));
            products.set(position,"Τίτλος: "+ptitle+"\n Πήρες: "+stack);
            adapter.notifyItemChanged(position);
            if(stack!=0)
            Toast.makeText(getActivity(),"Ένα αντικείμενο αφαιρέθηκε επιτυχώς!",Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            Toast.makeText(getActivity(),"Ούψς! Κάποιο σφάλμα συνέβει και δεν προσθέθηκε η παραγγελία σου.",Toast.LENGTH_SHORT).show();
            Log.e("onSeeCart",""+e);
        }
        setSum();
    }
}
