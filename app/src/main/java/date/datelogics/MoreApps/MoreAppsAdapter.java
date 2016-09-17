package date.datelogics.MoreApps;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import date.datelogics.R;
import date.datelogics.Utils;


/**
 * Created by CHANDRASAIMOHAN on 9/2/2016.
 */
public class MoreAppsAdapter extends RecyclerView.Adapter <MoreAppsAdapter.SourceHolder> {

    List<String> sourceData = Collections.EMPTY_LIST;
    Context ctx;
    private LayoutInflater inflator;
    HashMap<Integer,String> urlHash;
    public MoreAppsAdapter(Context ctx, List<String> sourceData, HashMap<Integer,String> urlHash){
        this.ctx = ctx;
        this.sourceData = sourceData;
        this.urlHash = urlHash;
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
        holder.title.setText(sourceData.get(position));
        holder.title.setTag(position);
    }

    @Override
    public int getItemCount() {
        return sourceData.size();
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
            int position = (int) title.getTag();
            String url = urlHash.get(position); //"https://github.com/AndroidTemplates/AllAboutLists";
          if(Utils.checkNetworkConnectivity(ctx)){
              Intent i = new Intent(Intent.ACTION_VIEW);
              i.setData(Uri.parse(url));
              ctx.startActivity(i);
          }else{
              Toast.makeText(ctx,"Check Your internet Connectivity",Toast.LENGTH_LONG).show();
          }

        }
    }
}
