package date.datelogics;

import android.app.DatePickerDialog;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import date.datelogics.Adapters.DateFormatsAdapter;
import date.datelogics.MoreApps.MoreAppsFragment;
import date.datelogics.SourceCode.SourceCodeListFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,DateFormatListener{

    ImageView datePicker;
    TextView selectedDate,modifiedDate;
    RecyclerView dateFormatView;
    LinearLayout dateLayout;
    DatePickerDialog datePickerDialog;
    Button disableDate,disableDateRange;
    private Toolbar mToolbar;
    Menu popUpMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolBar();
        initView();
        initListeners();
        setAdapter();
    }

private void initToolBar(){
    mToolbar = (Toolbar)findViewById(R.id.app_toolbar);
    setSupportActionBar(mToolbar);
    LayoutInflater mInflater= LayoutInflater.from(getApplicationContext());
    View mCustomView = mInflater.inflate(R.layout.toolbar_custom_view, null);
    mToolbar.addView(mCustomView);
    TextView toolBarTitle = (TextView)mToolbar.findViewById(R.id.title);
    toolBarTitle.setText("Date Manipulations");

}
    private  void initView(){
        dateLayout = (LinearLayout) findViewById(R.id.date_picker_layout);
        datePicker = (ImageView) dateLayout.findViewById(R.id.date_picker);
        selectedDate = (TextView) dateLayout.findViewById(R.id.display_date_label);
        disableDate = (Button) dateLayout.findViewById(R.id.current_date_diasable);
        disableDateRange = (Button) dateLayout.findViewById(R.id.range_date_diasable);
        dateFormatView = (RecyclerView)findViewById(R.id.format_recycler_view);
        modifiedDate = (TextView) dateLayout.findViewById(R.id.modified_date_label);
    }

    private void initListeners(){
        datePicker.setOnClickListener(this);
        disableDate.setOnClickListener(this);
        disableDateRange.setOnClickListener(this);

    }

    private void setAdapter(){
        DateFormatsAdapter sourceCodeAdapter = new DateFormatsAdapter(MainActivity.this,MainActivity.this,Utils.getData());
        dateFormatView.setAdapter(sourceCodeAdapter);

        dateFormatView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(MainActivity.this, LinearLayoutManager.VERTICAL);
        dateFormatView.addItemDecoration(itemDecoration);
        dateFormatView.setItemAnimator(new DefaultItemAnimator());
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.date_picker:
                launchDatePicker();
                show();
                break;
            case R.id.current_date_diasable:
                launchDatePicker();
                setMinLimit();
                show();

                break;
            case R.id.range_date_diasable:
                launchDatePicker();
                setMinLimit();
                setMaxLimit();
                show();
                break;
        }
    }



    private void setMinLimit(){
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
    }

    private  void setMaxLimit(){
        long maxLimit = Utils.getMaxDate();
        datePickerDialog.getDatePicker().setMaxDate(maxLimit);
    }
    final DatePickerDialog.OnDateSetListener  dateSetListener =  new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(
                DatePicker arg0,
        int year,
        int monthofyear,
        int dayOfMonth) {
         //   arg0.setMinDate(System.currentTimeMillis() - 1000);
            String monthString;
            String dayString;
            int mYear = year;
            if (monthofyear + 1 < 10) {
                monthString = "0"
                        + (monthofyear+1);
            } else {
                monthString = ""
                        + (monthofyear + 1);
            }
            if (dayOfMonth < 10) {
                dayString = "0"
                        + dayOfMonth;
            } else {
                dayString = ""
                        + dayOfMonth;
            }

            String selected_date_val = new StringBuilder()
                    // Month
                    // is
                    // 0
                    // based
                    // so
                    // addj
                    // 1
                    .append(monthString)
                    .append("-")
                    .append(dayString)
                    .append("-")
                    .append(mYear)
                    .append(" ").toString();
            selectedDate.setText(selected_date_val);
            modifiedDate.setText("Formatted Date");

        }
    };


    private  void launchDatePicker(){
        Calendar  c = Calendar.getInstance();
        int year =  c.get(Calendar.YEAR);
        int month =  c.get(Calendar.MONTH);
        int day =   c.get(Calendar.DAY_OF_MONTH);
        datePickerDialog =new DatePickerDialog(
                MainActivity.this,
                dateSetListener,
                year,
                month,
                day);


    }

    private void show(){
        if(datePickerDialog!=null){
            datePickerDialog.show();
        }
    }

    @Override
    public void setFormatType(String formatType) {
        if(!TextUtils.isEmpty(formatType)){
            if(!TextUtils.isEmpty(selectedDate.getText().toString()) && !selectedDate.getText().toString().equalsIgnoreCase("Display Date")){
                String inputDate = selectedDate.getText().toString();
                String readableDate = Utils.getReadableDate(inputDate,"MM-dd-yyyy",formatType);
                if(!TextUtils.isEmpty(readableDate)) {
                    modifiedDate.setText(readableDate);
                }

            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        this.popUpMenu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.action_settings){
            Fragment sourceFrag = new SourceCodeListFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frag_parentLayout, sourceFrag)
                    .addToBackStack(null)
                    .commit();

        }else if(item.getItemId()==R.id.moreApps){
            Fragment moreAppsFrag = new MoreAppsFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frag_parentLayout, moreAppsFrag)
                    .addToBackStack(null)
                    .commit();
        }
        return super.onOptionsItemSelected(item);
    }
}
