package com.example.yumnaasim.devfest;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import model.Organization;

public class OrganizationDetail extends AppCompatActivity {
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

        firebaseDatabase = FirebaseDatabase.getInstance();
        organization = firebaseDatabase.getReference("organizations");
        orgName = (TextView) findViewById(R.id.org_name);
        orgDetail = (TextView) findViewById(R.id.org_detail);
        orgAddress = (TextView) findViewById(R.id.org_address);
        orgDetail = (TextView) findViewById(R.id.org_detail);
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
                orgDetail.setText(currentOrg.getDetail());
                orgAddress.setText(currentOrg.getAddress());
                orgContact.setText(currentOrg.getContact());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
