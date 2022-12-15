package com.a7mad.elsh3rawy.a90days;


import android.content.Context;
import android.content.DialogInterface;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import me.tankery.lib.circularseekbar.CircularSeekBar;

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.MyViewHolder> {
    Context context;
    ArrayList<Circle> arrayList;

    public RecAdapter(Context context, ArrayList<Circle> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.custom_days, parent, false);


        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textTitle.setText(arrayList.get(position).getTitle());
        holder.circularSeekBar.setProgress(arrayList.get(position).getValue());
        holder.textValue.setText(arrayList.get(position).getValue() + "");
        holder.textExpressions.setText(context.getResources().getStringArray(R.array.Expressions)[new Random().nextInt(90)]);
        holder.textExpressions.setMovementMethod(new ScrollingMovementMethod());
        String currentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
        String title = arrayList.get(position).getTitle();

        holder.rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("هنعيد من الاول ؟ ");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "نعم ",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                holder.circularSeekBar.setProgress(0);
                                MainActivity.cs.set(MainActivity.cs.indexOf(title) + 1, currentDate);
                                holder.textValue.setText(0 + "");

                                Toast.makeText(context, "مش مشكله .... نقطة ومن أول السطر .", Toast.LENGTH_SHORT).show();

                                MainActivity.circles = MainActivity.cs.toString().substring(1, MainActivity.cs.toString().length() - 1).replaceAll(",", "#");
                                MainActivity.editor.putString("circles", MainActivity.circles);
                                MainActivity.editor.commit();

                            }
                        });

                builder1.setNegativeButton(
                        "لأ",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("هل تريد حذف العنصر  ؟ ");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "نعم ",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                MainActivity.arrayList.remove((MainActivity.cs.indexOf(title)+1)/2);

                                MainActivity.cs.remove(MainActivity.cs.indexOf(title)+1);

                            MainActivity.cs.remove(MainActivity.cs.indexOf(title) );

                                Toast.makeText(context, "تم الحذف", Toast.LENGTH_LONG).show();

                                MainActivity.circles = MainActivity.cs.toString().substring(1, MainActivity.cs.toString().length() - 1).replaceAll(",", "#")+"#";
                                MainActivity.editor.putString("circles", MainActivity.circles);
                                MainActivity.editor.commit();
                                MainActivity.recAdapter.notifyDataSetChanged();

                            }
                        });

                builder1.setNegativeButton(
                        "لأ",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });




    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        CircularSeekBar circularSeekBar;
        TextView textTitle, textExpressions, textValue;
        Button rest,delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            circularSeekBar = itemView.findViewById(R.id.seek_bar);
            textExpressions = itemView.findViewById(R.id.textExpressions);
            textTitle = itemView.findViewById(R.id.textTitle);
            rest = itemView.findViewById(R.id.restBtn);
            delete=itemView.findViewById(R.id.deleteBtn);
            textValue = itemView.findViewById(R.id.textValue);

        }
    }
}
