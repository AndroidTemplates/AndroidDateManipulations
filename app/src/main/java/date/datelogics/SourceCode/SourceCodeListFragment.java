package date.datelogics.SourceCode;


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
 * Use the {@link SourceCodeListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SourceCodeListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
   View sourceCodeView = null;
   RecyclerView sourceRecyclerView ;
    List<String> sourceCodeList = Collections.EMPTY_LIST;
    public SourceCodeListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SourceCodeListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SourceCodeListFragment newInstance(String param1, String param2) {
        SourceCodeListFragment fragment = new SourceCodeListFragment();
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
        sourceCodeView =  inflater.inflate(R.layout.fragment_source_code_list, container, false);
        initToolBar();
        sourceRecyclerView = (RecyclerView)sourceCodeView.findViewById(R.id.source_code_recycler_view);
        SourceCodeAdapter sourceCodeAdapter = new SourceCodeAdapter(getActivity(),getData(),getURLHash());
        sourceRecyclerView.setAdapter(sourceCodeAdapter);

        sourceRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL);
        sourceRecyclerView.addItemDecoration(itemDecoration);
        sourceRecyclerView.setItemAnimator(new DefaultItemAnimator());
        return  sourceCodeView;
    }


    private List<String> getData(){
        sourceCodeList = new ArrayList<>();
        sourceCodeList.add("Get Source");
      /*  sourceCodeList.add("Heterogeneous");
        sourceCodeList.add("Expandable");
        sourceCodeList.add("Webservice");
        sourceCodeList.add("Database");
        sourceCodeList.add("Animated");
        sourceCodeList.add("Indexed");
        sourceCodeList.add("Checked");
        sourceCodeList.add("Sectioned");
        sourceCodeList.add("DragDrop");
        sourceCodeList.add("SearchList");*/
        return  sourceCodeList;
    }

    private HashMap<Integer,String> getURLHash(){
        HashMap<Integer,String> urlHash = new HashMap<>();
        urlHash.put(0,"https://github.com/AndroidTemplates/AllAboutLists/tree/master/app/src/main/java/list/listtemplates/simplelistTypes");
      /*  urlHash.put(1,"https://github.com/AndroidTemplates/AllAboutLists/tree/master/app/src/main/java/list/listtemplates/HeterogenousLists");
        urlHash.put(2,"https://github.com/AndroidTemplates/AllAboutLists/tree/master/app/src/main/java/list/listtemplates/ExpandableLists");
        urlHash.put(3,"https://github.com/AndroidTemplates/AllAboutLists/tree/master/app/src/main/java/list/listtemplates/WebServiceLists");
        urlHash.put(4,"https://github.com/AndroidTemplates/AllAboutLists/tree/master/app/src/main/java/list/listtemplates/DBLists");
        urlHash.put(5,"https://github.com/AndroidTemplates/AllAboutLists/tree/master/app/src/main/java/list/listtemplates/AnimatedLists");
        urlHash.put(6,"https://github.com/AndroidTemplates/AllAboutLists/tree/master/app/src/main/java/list/listtemplates/IndexedLists");
        urlHash.put(7,"https://github.com/AndroidTemplates/AllAboutLists/tree/master/app/src/main/java/list/listtemplates/CheckedList");
        urlHash.put(8,"https://github.com/AndroidTemplates/AllAboutLists/tree/master/app/src/main/java/list/listtemplates/SectionedLists");
        urlHash.put(9,"https://github.com/AndroidTemplates/AllAboutLists/tree/master/app/src/main/java/list/listtemplates/DragDropList");
        urlHash.put(10,"https://github.com/AndroidTemplates/AllAboutLists/tree/master/app/src/main/java/list/listtemplates/SearchLists");*/
        return  urlHash;
    }

    private void initToolBar(){
        Toolbar mToolBar = (Toolbar)getActivity().findViewById(R.id.app_toolbar);
        TextView toolBarTitle = (TextView)mToolBar.findViewById(R.id.title);
        toolBarTitle.setText("DateLogic Source");

    }

}
