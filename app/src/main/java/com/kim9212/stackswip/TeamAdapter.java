package com.kim9212.stackswip;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TeamAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<TeamItem> teamItems;

    public TeamAdapter() {
    }

    public TeamAdapter(Context context, ArrayList<TeamItem> teamItems) {
        this.context = context;
        this.teamItems = teamItems;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.team_list,parent,false);
        VH holder=new VH(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh=(VH) holder;
        TeamItem teamItem=teamItems.get(position);

        vh.title.setText(teamItem.title);
        vh.area.setText(teamItem.area);
        vh.msg.setText(teamItem.msg);
        vh.email.setText(teamItem.email);
        vh.member.setText(teamItem.member);
        vh.period.setText(teamItem.period);
    }

    @Override
    public int getItemCount() {
        return teamItems.size();
    }

    class VH extends RecyclerView.ViewHolder{

        TextView title;
        TextView area;
        TextView msg;
        TextView member;
        TextView period;
        TextView email;

        public VH(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.team_title);
            area=itemView.findViewById(R.id.team_area);
            msg=itemView.findViewById(R.id.team_msg);
            email=itemView.findViewById(R.id.team_email);
            member=itemView.findViewById(R.id.team_member);
            period=itemView.findViewById(R.id.team_time);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TeamItem item=teamItems.get(getLayoutPosition());
                    Intent intent=new Intent(context,DetailTeamActivity.class);
                    intent.putExtra("Title", item.title);
                    intent.putExtra("Area", item.area);
                    intent.putExtra("Msg", item.msg);
                    intent.putExtra("Email", item.email);
                    intent.putExtra("Member", item.member);
                    intent.putExtra("Period", item.period);
                    context.startActivity(intent);
                }
            });
        }




    }
}//Adater


