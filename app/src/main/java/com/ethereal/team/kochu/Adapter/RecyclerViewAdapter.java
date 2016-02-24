package com.ethereal.team.kochu.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ethereal.team.kochu.DefinitionFetcher.Definition;
import com.ethereal.team.kochu.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.DataViewHolder> {

    private List<Definition> dataList;
    Context context;


    public RecyclerViewAdapter(Context context, List<Definition> dataList) {
        this.dataList = dataList;
        this.context = context;
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public void onBindViewHolder(DataViewHolder dataViewHolder, int i) {
        Definition data = dataList.get(i);
        dataViewHolder.tvPos.setText(data.getPartsOfSpeech());
        dataViewHolder.tvMeaning.setText(data.getDefinition());
        dataViewHolder.tvUseOf.setText(data.getExample());
      //  dataViewHolder.tvSynonym.setText(data.syno);

        dataViewHolder.linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        for (String str:data.getSynonyms())
        {
            final TextView txt = new TextView(context);
            txt.setText(str);
            txt.setTextSize(17);
            txt.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            txt.setPadding(5,5,5,5);
            dataViewHolder.linearLayout.addView(txt);
        }
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.cardview_layout, viewGroup, false);
        return new DataViewHolder(itemView);
    }

    public static class DataViewHolder extends RecyclerView.ViewHolder {

        protected TextView tvPos;
        protected TextView tvMeaning;
        protected TextView tvUseOf;
        protected TextView tvSynonym;
        protected LinearLayout linearLayout;

        public DataViewHolder(View v) {
            super(v);
            tvPos = (TextView) v.findViewById(R.id.tv_pos);
            tvMeaning = (TextView) v.findViewById(R.id.tv_meaning);
            tvUseOf = (TextView) v.findViewById(R.id.tv_use_of);
            tvSynonym = (TextView) v.findViewById(R.id.tv_synonyms);
            linearLayout = (LinearLayout) v.findViewById(R.id.ll_synonyms);

        }
    }
}