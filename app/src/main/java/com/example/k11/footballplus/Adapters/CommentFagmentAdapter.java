package com.example.k11.footballplus.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.k11.footballplus.Helpers.SqliteHelper;
import com.example.k11.footballplus.Models.Comment;
import com.example.k11.footballplus.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by k11 on 29/11/17.
 */

public class CommentFagmentAdapter extends RecyclerView.Adapter<CommentFagmentAdapter.ViewHolder> {

    List<Comment> commentList = new ArrayList<>();
    Context context;

    public CommentFagmentAdapter(List<Comment> commentList, Context context) {
        this.commentList = commentList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comments, parent, false);
        ViewHolder viewHolder = new ViewHolder(item);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.txtTitleItemComment.setText(commentList.get(position).getTitleCommnet());

        SqliteHelper sqliteHelper;
        String nameUser = "";
        sqliteHelper = new SqliteHelper(context, "DB_CAMP_FOOTBALL", null, 1);

        SQLiteDatabase db = sqliteHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select USER .NAME FROM USER WHERE USER.ID= " + commentList.get(position).getIdUserComment(), null);


        while (cursor.moveToNext()) {

            nameUser = cursor.getString(0);
        }

        holder.txtDescriptionItemComment.setText("@" + nameUser + " " + commentList.get(position).getDescriptionComment());


        //imagen con picaso
        //Picasso.with(context).load(campFootballList.get(position).getImage()).into((holder.imgItemListFieldSoccerProfile));

    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitleItemComment, txtDescriptionItemComment;

        public ViewHolder(View item) {
            super(item);
            txtTitleItemComment = (TextView) item.findViewById(R.id.txtTitleItemComment);
            txtDescriptionItemComment = (TextView) item.findViewById(R.id.txtDescriptionItemComment);
        }


    }

}