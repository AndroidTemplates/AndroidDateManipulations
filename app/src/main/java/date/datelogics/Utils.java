package date.datelogics;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by CHANDRASAIMOHAN on 9/17/2016.
 */
public class Utils {
     public static List<String > getData(){
         List<String> dateFormats = new ArrayList<>();
         dateFormats.add("MM/dd/yy");
         dateFormats.add("MM-dd-yy");
         dateFormats.add("dd/MM/yy");
         dateFormats.add("dd-MM-yy");
         dateFormats.add("MMM dd,yyyy");
         dateFormats.add("dd MMM yyyy");
         dateFormats.add("MM/dd");
         dateFormats.add("MM-dd");
         dateFormats.add("MM-dd-yyyy");
         dateFormats.add("MM/dd/yy");
         return  dateFormats;
     }


    public static String getReadableDate(String originalDate,String inputFormat,String outputFormat){
        String readableDate="";
        SimpleDateFormat input = new SimpleDateFormat(inputFormat);
        SimpleDateFormat output = new SimpleDateFormat(outputFormat);
        try {
            Date oneWayTripDate = input.parse(originalDate); // parse input
            readableDate = (output.format(oneWayTripDate));
        } catch (ParseException e) {
            Log.e("Utils",
                    "ParseException. Error is:"+e.getMessage() +". Method[getReadableDate] paramaters are:originalDate["
                            + originalDate + "], inputFormat[" + inputFormat
                            + "],outputFormat[" + outputFormat + "] Error is:"+e.getMessage());
            e.printStackTrace();
        }
        return readableDate;
    }


    public static long getMaxDate(){
        long maxDate = 0;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 30);
        maxDate = cal.getTimeInMillis();
        return  maxDate;
    }

    public static boolean checkNetworkConnectivity(Context ctx) {
        ConnectivityManager connectionManager = (ConnectivityManager) ctx
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connectionManager.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }
}
