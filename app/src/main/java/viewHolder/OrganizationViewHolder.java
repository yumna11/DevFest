package viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yumnaasim.devfest.R;

import Interface.ItemClickListener;

/**
 * Created by YumnaAsim on 11/19/2017.
 */

public class OrganizationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView orgName;
    public TextView orgDetail;
    ItemClickListener itemClickListener;

    public OrganizationViewHolder(View itemView) {
        super(itemView);
        this.orgName = (TextView) itemView.findViewById(R.id.org_name);
        this.orgDetail = (TextView) itemView.findViewById(R.id.org_detail);
        itemView.setOnClickListener(this);
    }
    public void setItemClickListener(ItemClickListener itemClickListener)
    {
        this.itemClickListener = itemClickListener;
    }



    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);

    }
}
