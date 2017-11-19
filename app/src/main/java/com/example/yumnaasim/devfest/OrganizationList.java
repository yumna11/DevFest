package com.example.yumnaasim.devfest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Interface.ItemClickListener;
import model.Organization;
import viewHolder.OrganizationViewHolder;

public class OrganizationList extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference product;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    String categoryId;
    FirebaseRecyclerAdapter<Organization, OrganizationViewHolder> firebaseRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Organizations");
        setContentView(R.layout.activity_organization_list);
        recyclerView = (RecyclerView) findViewById(R.id.recyler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        loadOrganization();
    }

    private void loadOrganization() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        product = firebaseDatabase.getReference("organizations");
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Organization, OrganizationViewHolder>
                (Organization.class,
                        R.layout.organization_item,
                        OrganizationViewHolder.class,
                        product.orderByKey()) {
            @Override
            protected void populateViewHolder(final OrganizationViewHolder viewHolder, final Organization model, final int position) {
                viewHolder.orgName.setText(model.getName());
                viewHolder.orgDetail.setText(model.getDetails());
                final Organization org = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent intent = new Intent(OrganizationList.this,OrganizationDetail.class);
                        intent.putExtra("OrgId",firebaseRecyclerAdapter.getRef(position).getKey());
                        startActivity(intent);
                    }
                });

            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }
}
