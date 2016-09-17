package date.datelogics.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import date.datelogics.DateFormatListener;
import date.datelogics.R;


/**
 * Created by CHANDRASAIMOHAN on 9/2/2016.
 */
public class DateFormatsAdapter extends RecyclerView.Adapter <DateFormatsAdapter.SourceHolder> {

    Context ctx;
    private LayoutInflater inflator;
    DateFormatListener  dateFormatListener;
    List<String> formatTypes = Collections.EMPTY_LIST;
    public DateFormatsAdapter(Context ctx, DateFormatListener  dateFormatListener, List<String> formatTypes){
        this.ctx = ctx;
        this.formatTypes = formatTypes;
        this.dateFormatListener = dateFormatListener;
        inflator = LayoutInflater.from(ctx);
    }

    @Override
    public SourceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflator.inflate(R.layout.source_row_item,parent,false);
        SourceHolder soureViewHolder = new SourceHolder(view);
        return soureViewHolder;
    }

    @Override
    public void onBindViewHolder(SourceHolder holder, int position) {
        holder.title.setText(formatTypes.get(position));
        holder.title.setTag(formatTypes.get(position));
    }

    @Override
    public int getItemCount() {
        return formatTypes.size();
    }


    public class SourceHolder  extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title;
        SourceHolder(View itemView){
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.source_row_label);
            title.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            String formatType = (String) title.getTag();
           Log.d("DateFormatsAdapter","Format Type"+formatType);
            dateFormatListener.setFormatType(formatType);
         /* if(ListUtils.checkNetworkConnectivity(ctx)){
              Intent i = new Intent(Intent.ACTION_VIEW);
              i.setData(Uri.parse(url));
              ctx.startActivity(i);
          }else{
              Toast.makeText(ctx,"Check Your internet Connectivity",Toast.LENGTH_LONG).show();
          }*/

        }
    }
}
