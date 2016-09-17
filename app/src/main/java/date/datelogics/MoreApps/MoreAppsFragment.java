package date.datelogics.MoreApps;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import date.datelogics.DividerItemDecoration;
import date.datelogics.R;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MoreAppsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MoreAppsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View moreAppsView = null;
    RecyclerView moreAppsRecyclerView;
    List<String> moreAppsList = Collections.EMPTY_LIST;


    public MoreAppsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MoreAppsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MoreAppsFragment newInstance(String param1, String param2) {
        MoreAppsFragment fragment = new MoreAppsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        moreAppsView = inflater.inflate(R.layout.fragment_more_apps, container, false);
        initToolBar();
        moreAppsRecyclerView = (RecyclerView) moreAppsView.findViewById(R.id.more_apps_list);
        MoreAppsAdapter moreAppsAdapter = new MoreAppsAdapter(getActivity(), getData(), getURLHash());
        moreAppsRecyclerView.setAdapter(moreAppsAdapter);

        moreAppsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL);
        moreAppsRecyclerView.addItemDecoration(itemDecoration);
        moreAppsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        return moreAppsView;
    }

    private void initToolBar() {
        Toolbar mToolBar = (Toolbar) getActivity().findViewById(R.id.app_toolbar);
        TextView toolBarTitle = (TextView) mToolBar.findViewById(R.id.title);
        toolBarTitle.setText("More Apps");
    }

    private List<String> getData(){
        moreAppsList = new ArrayList<>();
        moreAppsList.add("All About Android Lists");
        moreAppsList.add("Android Tutorial App");
        moreAppsList.add("Layout Generator");

        return  moreAppsList;
    }

    private HashMap<Integer,String> getURLHash(){
        HashMap<Integer,String> urlHash = new HashMap<>();
        urlHash.put(0,"https://play.google.com/store/apps/details?id=list.listtemplates");
        urlHash.put(1,"https://play.google.com/store/apps/details?id=com.andr.tutorialapp");
        urlHash.put(2,"https://play.google.com/store/apps/details?id=layout.layoutgenerator");
        return  urlHash;
    }

}
