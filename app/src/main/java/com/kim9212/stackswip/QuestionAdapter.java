package com.kim9212.stackswip;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class QuestionAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<QuestionItem> questionItems;

    public QuestionAdapter() {
    }

    public QuestionAdapter(Context context, ArrayList<QuestionItem> questionItems) {
        this.context = context;
        this.questionItems = questionItems;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.question_list,parent,false);
        VH holder=new VH(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh=(VH) holder;
        QuestionItem questionItem=questionItems.get(position);
        vh.title.setText(questionItem.title);
        vh.question.setText(questionItem.question);

        String imgUri="http://hkh26.dothome.co.kr/Question/"+questionItem.file;
        Glide.with(context).load(imgUri).into(vh.file);

    }

    @Override
    public int getItemCount() {
        return questionItems.size();
    }

    class VH extends RecyclerView.ViewHolder{

        TextView title;
        TextView question;
        ImageView file;
        TextView date;

        public VH(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.que_title);
            question=itemView.findViewById(R.id.que_question);
            file=itemView.findViewById(R.id.que_img);
            date=itemView.findViewById(R.id.que_date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    QuestionItem item=questionItems.get(getLayoutPosition());
                    Intent intent=new Intent(context,DeatilQuestionActivity.class);
                    intent.putExtra("Title", item.title);
                    intent.putExtra("Question", item.question);


                    context.startActivity(intent);
                }
            });
        }

    }
}//Adater


