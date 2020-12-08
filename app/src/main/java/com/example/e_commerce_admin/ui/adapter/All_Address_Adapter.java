package com.example.e_commerce_admin.ui.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce_admin.R;
import com.example.e_commerce_admin.model.Address;
import com.example.e_commerce_admin.utils.FirebaseConstants;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class All_Address_Adapter extends FirebaseRecyclerAdapter<Address, All_Address_Adapter.Order_Detail_Adapter_View> {

    String selectedPosition = "";

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     * @param add_type
     */
    public All_Address_Adapter(@NonNull FirebaseRecyclerOptions<Address> options, String  add_type) {
        super(options);
        selectedPosition = add_type;

    }

    @NonNull
    @Override
    public Order_Detail_Adapter_View onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.all_adress, parent, false);
        return new Order_Detail_Adapter_View(view);
    }


    @Override
    protected void onBindViewHolder(@NonNull Order_Detail_Adapter_View holder, final int position, @NonNull Address model) {

        final String id = getRef(position).getKey();

        holder.user_name.setText(model.getName());
        holder.alladd_mob_no.setText(model.getMob_no());
        holder.tv_addtype.setText(model.getAddress_type());
        holder.user_add.setText(model.getAddress());


        holder.rb.setChecked(selectedPosition.equals(id));

        holder.rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Map<String, Object> map = new HashMap<>();
                map.put(FirebaseConstants.Address.default_address_index, id);
                FirebaseDatabase.getInstance().getReference().child("Admin")
                        .child(FirebaseAuth.getInstance().getUid())
                        .updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.i("sedcsdt", "onComplete: " + task.isSuccessful());
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("sedcsdt", "onFailure: " + e.getMessage());
                    }
                });


                selectedPosition = id;
                notifyDataSetChanged();
            }
        });

    }


    class Order_Detail_Adapter_View extends RecyclerView.ViewHolder {
        RadioButton rb;
        LinearLayout ll_radio;
        TextView user_name, user_add, alladd_mob_no, tv_addtype;

        public Order_Detail_Adapter_View(@NonNull View itemView) {
            super(itemView);
            rb = itemView.findViewById(R.id.rb);
            ll_radio = itemView.findViewById(R.id.ll_radio);
            user_name = itemView.findViewById(R.id.user_name);
            user_add = itemView.findViewById(R.id.user_add);
            alladd_mob_no = itemView.findViewById(R.id.alladd_mob_no);
            tv_addtype = itemView.findViewById(R.id.tv_addtype);
        }
    }
}
