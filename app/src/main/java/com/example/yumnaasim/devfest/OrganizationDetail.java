package com.example.yumnaasim.devfest;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import model.Organization;

public class OrganizationDetail extends Activity {
    TextView orgName, orgDetail,orgAddress, orgContact;
    String orgId;
    Organization currentOrg;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference organization;

    CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization_detail);

        Button button = (Button) findViewById(R.id.buttonDonate);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrganizationDetail.this,BillingInfo.class);
                startActivity(intent);
            }
        });

        firebaseDatabase = FirebaseDatabase.getInstance();
        organization = firebaseDatabase.getReference("organizations");
        orgName = (TextView) findViewById(R.id.org_name);
        orgDetail = (TextView) findViewById(R.id.org_detail);
        orgAddress = (TextView) findViewById(R.id.org_address);
        orgContact = (TextView) findViewById(R.id.org_contact);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);

        if (getIntent()!=null)
            orgId = getIntent().getStringExtra("OrgId");
        if (!orgId.isEmpty())
        {
            getOrganizationDetail(orgId);

        }
    }

    private void getOrganizationDetail(String orgId) {
        organization.child(orgId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                currentOrg = dataSnapshot.getValue(Organization.class);
                collapsingToolbarLayout.setTitle(currentOrg.getName());
                orgName.setText(currentOrg.getName());
                orgDetail.setText(currentOrg.getDetails());
                orgAddress.setText("Address: "+currentOrg.getAddress());
                orgContact.setText("Contact Number: "+currentOrg.getContact());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
