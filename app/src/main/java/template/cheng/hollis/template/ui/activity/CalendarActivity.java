package template.cheng.hollis.template.ui.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import me.srodrigo.androidhintspinner.HintAdapter;
import me.srodrigo.androidhintspinner.HintSpinner;
import template.cheng.hollis.template.R;
import template.cheng.hollis.template.calendar.FacilityCalendarView;
import template.cheng.hollis.template.dateTimeStringConvert.TimeRender;

public class CalendarActivity extends AppCompatActivity {
    private FacilityCalendarView facilityCalendarView;
    private Spinner time_spinner;
    private Spinner venue_spinner;
    private ArrayList<Date> BookedDate;
    private ArrayList<String> TimeFromList;
    private ArrayList<String> TimeRange;
    private ArrayList<String> VenueList;
    //    private ArrayList<Integer> VenueSeqList;
    private ArrayList<String> TimeToList;
    private String Venue = "";
    private String TimeFrom = "";
    private String TimeTo = "";
    private boolean is1st = true;
    private Date Date;
    private ArrayList<String> AvaDateListFromAPI;
    private Button btnSubmit;
    private boolean isSelected = false;
    private Date SelectingDate = new Date(System.currentTimeMillis());
    private boolean IsBookedVenue = false;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        //region SetCustom Toolbar!
        Toolbar tb = (Toolbar) findViewById(R.id.TBToolbar);
        TextView toolbar_title = (TextView) findViewById(R.id.toolbar_title);

        toolbar_title.setText(getString(R.string.FEEDB));
        setSupportActionBar(tb);

        // Get the ActionBar here to configure the way it behaves.
        final ActionBar ab = getSupportActionBar();
        //ab.setHomeAsUpIndicator(R.drawable.ic_menu); // set a custom icon for the default home button
        if (ab != null) {
            ab.setDisplayShowHomeEnabled(true); // show or hide the default home button
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setDisplayShowCustomEnabled(true); // enable overriding the default toolbar layout
            ab.setDisplayShowTitleEnabled(false); // disable the default title element here (for centered title)
        }
        //endregion

        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        tvResult = (TextView) findViewById(R.id.tvResult);
        time_spinner = (Spinner) findViewById(R.id.facility_time_spinner);
        venue_spinner = (Spinner) findViewById(R.id.facility_venue_spinner);
        facilityCalendarView = (FacilityCalendarView) findViewById(R.id.calendar_view);

//        Do this first
//        GetFacilityAvailableDate();
        StringDateToDateDate();

        //Selected Date
        facilityCalendarView.setOnDateSelectedListener(new FacilityCalendarView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull Date date) {
                int Day1 = Integer.parseInt(AvaDateListFromAPI.get(0).substring(0, 2));
                int dateDay = Integer.parseInt(TimeRender.getDate(date).substring(0, 2));
//                Log.w("BDA", "dateDay=" + dateDay + ",Day1=" + Day1);
                if (is1st && dateDay != Day1) {
                    Calendar c = Calendar.getInstance();
                    String Day = AvaDateListFromAPI.get(0).substring(0, 2);
                    String Month = AvaDateListFromAPI.get(0).substring(3, 5);
                    String Year = AvaDateListFromAPI.get(0).substring(6, 10);
                    c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(Day));
                    c.set(Calendar.MONTH, Integer.parseInt(Month) - 1);
                    c.set(Calendar.YEAR, Integer.parseInt(Year));
                    Date date11 = c.getTime();
                    facilityCalendarView.setCurrentDayDisplayGone(date11);
                }
                is1st = false;
                //clear data
                Date = null;
                TimeFrom = "";
                TimeTo = "";
                Venue = "";

                SelectingDate = date;
//                Log.w("onDateSelected", TimeRender.getDayFromDate(date));
//                Log.w("onDateSelected", date + "");
                Date = date;
//                Log.w("BDA", TimeRender.NoticeGetDate(Date));
//                Log.w("BDA", "IsExpiredDate=" + IsExpiredDate + ",IsExpiredDate2=" + IsExpiredDate2);
//                if (Integer.parseInt(TimeRender.getDayFromDate(date)) == Integer.parseInt(IsExpiredDate.substring(0, 2)) ||
//                        Integer.parseInt(TimeRender.getDayFromDate(date)) == Integer.parseInt(IsExpiredDate2.substring(0, 2))) {
//                    tvNotAllowMsg.setVisibility(View.VISIBLE);
//                    LLCBA.setVisibility(View.GONE);
//                    facility_btnConfirm.setVisibility(View.GONE);
//                } else {
//                    tvNotAllowMsg.setVisibility(View.GONE);
//                    LLCBA.setVisibility(View.VISIBLE);
//                    facility_btnConfirm.setVisibility(View.VISIBLE);
//                }


//                Utility.mTracker.send(new HitBuilders.EventBuilder()
//                        .setCategory("Facilities-" + Utility.facilityBookingInfo.getFacilityListInfo().getTitle())
//                        .setAction("Select")
//                        .setLabel("Date")
//                        .setValue(10)
//                        .build());
//                Utility.showLoadingDialog(BookingDetailsActivity.this, BookingDetailsActivity.this.getString(R.string.Loading));
                GetFacilityAvailableTime();
            }
        });

        facilityCalendarView.setOnMonthChangedListener(new FacilityCalendarView.OnMonthChangedListener() {
            @Override
            public void onMonthChanged(@NonNull Date monthDate) {
                //Log.w("onMonthChanged", TimeRender.getMonthFromDate(monthDate));
                //Log.w("onMonthChanged", monthDate + "");
//                Month = TimeRender.getDayFromDate(monthDate);
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isSelected) {
//                    Utility.mTracker.send(new HitBuilders.EventBuilder()
//                            .setCategory("Facilities-" + Utility.facilityBookingInfo.getFacilityListInfo().getTitle())
//                            .setAction("Click")
//                            .setLabel("Book Now")
//                            .setValue(100)
//                            .build());
                    //                    putValue();
//                    if (Utility.OwnUnitInfoAL.size() == 0) {
//                        Utility.NoticeDialogOK(BookingDetailsActivity.this, getString(R.string.YHNVATBF));
//                    } else {
//                        Intent intent = new Intent(BookingDetailsActivity.this, BookingConfirmationActivity.class);
//                    Utility.facilityBookingInfo.setDate(Date);
//                    Utility.facilityBookingInfo.setTimeFrom(TimeFrom);
//                    Utility.facilityBookingInfo.setTimeTo(TimeTo);
//                    Utility.facilityBookingInfo.setVenue(Venue);
                    tvResult.setText("Date = " + Date +
                            "\nTimeRender.NoticeGetDate(Date) = " + TimeRender.NoticeGetDate(Date) +
                            "\nTimeFrom = " + TimeFrom +
                            "\nTimeTo = " + TimeTo +
                            "\nVenue = " + Venue
                    );
//                        Utility.facilityBookingInfo.setVenueSeq(VenueSeq);
//                        Utility.facilityBookingInfo.setConFlatFloor(ConfirmationFlatFloor);
//                        Utility.facilityBookingInfo.setConBlock(ConfirmationBlock);
                    //update fee
//                        FacilityListInfo facilityListInfo = Utility.facilityBookingInfo.getFacilityListInfo();
//                        facilityListInfo.setFee(TheSelectedDateFee);
//                        Utility.facilityBookingInfo.setFacilityListInfo(facilityListInfo);
//                        startActivity(intent);
//                    }
                } else {
                    okDialog();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        //TODO same delay, maybe use boolean check if propic changed then use NavUtils,,,do after API
//        NavUtils.navigateUpFromSameTask(this);
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
//            NavUtils.navigateUpFromSameTask(this);
            overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void StringDateToDateDate() {
        //insert Date from API?!
        AvaDateListFromAPI = new ArrayList<>();
        AvaDateListFromAPI.add("05/08/2016 17:25:52");
        AvaDateListFromAPI.add("06/08/2016 17:25:52");
        AvaDateListFromAPI.add("07/08/2016 17:25:52");
        AvaDateListFromAPI.add("08/08/2016 17:25:52");
        AvaDateListFromAPI.add("09/08/2016 17:25:52");
        AvaDateListFromAPI.add("10/08/2016 17:25:52");
        AvaDateListFromAPI.add("11/08/2016 17:25:52");

        BookedDate = new ArrayList<>();
        Calendar c = Calendar.getInstance();
        for (int i = 0; i < AvaDateListFromAPI.size(); i++) {
            //05/02/2016
            String Day = AvaDateListFromAPI.get(i).substring(0, 2);
            String Month = AvaDateListFromAPI.get(i).substring(3, 5);
            String Year = AvaDateListFromAPI.get(i).substring(6, 10);
            c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(Day));
            c.set(Calendar.MONTH, Integer.parseInt(Month) - 1);
            c.set(Calendar.YEAR, Integer.parseInt(Year));
            Date date = c.getTime();
//            Log.w("BDA", "Day=" + Day + ",Month=" + Month + ",Year=" + Year + ",Date=" + date);
            BookedDate.add(date);
        }

        FacilityCalendarView.setSaveDate(BookedDate);

//        facilityCalendarView.setFirstDayOfWeek(Calendar.MONDAY);
        facilityCalendarView.setIsOverflowDateVisible(true);
//        calendarView.setCurrentDay(new Date(System.currentTimeMillis()));
//        facilityCalendarView.setBackButtonColor(R.color.colorAccent);
//        facilityCalendarView.setNextButtonColor(R.color.colorAccent);
        facilityCalendarView.refreshCalendar(Calendar.getInstance(Locale.getDefault()));

        theFirstDayOfAVAList();

    }

    private void theFirstDayOfAVAList() {
        Calendar c = Calendar.getInstance();
        String Day = AvaDateListFromAPI.get(0).substring(0, 2);
        String Month = AvaDateListFromAPI.get(0).substring(3, 5);
        String Year = AvaDateListFromAPI.get(0).substring(6, 10);
        c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(Day));
        c.set(Calendar.MONTH, Integer.parseInt(Month) - 1);
        c.set(Calendar.YEAR, Integer.parseInt(Year));
        Date date = c.getTime();
        facilityCalendarView.setCurrentDay(date);
        Date = date;

        GetFacilityAvailableTime();


    }

    private void GetFacilityAvailableVenue() {
//        try {
//            JSONObject jsonObj = new JSONObject();
//            jsonObj.put("EstateID", Utility.PrimaryEstateID);
//            jsonObj.put("FacilityID", FacilityID);
//            jsonObj.put("FacilityID", FacilityID);
//            jsonObj.put("Lang", Utility.APILang());
//            jsonObj.put("Date", TimeRender.NoticeGetDate(Date));
//            jsonObj.put("TimeFrom", TimeFrom);
//            jsonObj.put("TimeTo", TimeTo);
        //Log.w("BDA", "HTTP REQ JSON=" + Utility.ipURL + "GetFacilityAvailableVenue?json=" + jsonObj.toString());
//            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, Utility.ipURL + "GetFacilityAvailableVenue?json=" + jsonObj.toString(), null, new Response.Listener<JSONObject>() {
//                @Override
//                public void onResponse(JSONObject response) {
//                    try {
        //Log.w("BDA", "GetFacilityAvailableVenue?json=" + response.toString());
//
//                        JSONArray output = response.getJSONArray("Output");
        VenueList = new ArrayList<>();
//        VenueSeqList = new ArrayList<>();
//        for (int i = 0; i < output.length(); i++) {
//            JSONObject JOget = output.getJSONObject(i);
        VenueList.add("1號場");
//            VenueSeqList.add(JOget.getInt("VenueID"));
//        }
//
//                    } catch (Exception e) {
        //Log.w("BDA", "GetFacilityAvailableVenue:onResponse:Catch:" + e.toString());
//                    }

//                    //Log.w("BDA","TimeFromList: " + TimeFromList + "TimeToList: " + TimeToList + "venueList: " + venueList);

        //set Time Spinner
//                    ArrayList<String> TimeCombine = new ArrayList<>();
//                    for (int x = 0; x < TimeFromList.size(); x++) {
//                        TimeCombine.add(TimeFromList.get(x) + " - " + TimeToList.get(x));
//                    }

        if (VenueList.size() == 0) {
//                        okDialog();
            isSelected = false;
            IsBookedVenue = true;
            VenueList.add(getString(R.string.Booked));
            HintSpinner<String> hintSpinner = new HintSpinner<>(
                    venue_spinner,
                    new HintAdapter<>(CalendarActivity.this, R.string.Booked, VenueList),
                    new HintSpinner.Callback<String>() {
                        @Override
                        public void onItemSelected(int position, String itemAtPosition) {
                            // Here you handle the on item selected event (this skips the hint selected event)
                        }
                    });
            hintSpinner.init();
        } else {
//            ArrayAdapter<String> adapterTime = new ArrayAdapter<>(CalendarActivity.this, android.R.layout.simple_spinner_item, VenueList);
            //R.layout.spinner_show_text_grey is for change the display of the spinner text color
            ArrayAdapter<String> adapterTime = new ArrayAdapter<>(CalendarActivity.this, R.layout.spinner_show_text_grey, VenueList);
            adapterTime.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            venue_spinner.setAdapter(adapterTime);
            venue_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                            Toast.makeText(BookingDetailsActivity.this, "ID= " + position + " 您選擇" + parent.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
//                    Utility.mTracker.send(new HitBuilders.EventBuilder()
//                            .setCategory("Facilities-" + Utility.facilityBookingInfo.getFacilityListInfo().getTitle())
//                            .setAction("Select")
//                            .setLabel("Venue")
//                            .setValue(10)
//                            .build());
                    Venue = parent.getSelectedItem().toString();
//                    VenueSeq = VenueSeqList.get(position);
                    //Log.w("BDA", "Venue=" + Venue + ",VenueSeq=" + VenueSeq);
                    isSelected = true;

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
//
//                    Utility.dismissLoadingDialog();
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    error.printStackTrace();
//                    if (error instanceof NoConnectionError) {
        Log.w("BDA", "No internet Access, Check your internet connection.");
//                    }
//                }
//            });
//            Utility.mQueue.add(Utility.SetVolleyTimeoutAndNoCache(jsonRequest));
//
//        } catch (Exception e) {
        //Log.w("FacilityAvailableVenue", "GetFacilityAvailableVenue:Catch:" + e.toString());
//        }
    }

    private void GetFacilityAvailableTime() {
//        try {
//            JSONObject jsonObj = new JSONObject();
//            jsonObj.put("EstateID", Utility.estateInfo.getEstateID());
//            jsonObj.put("FacilityID", FacilityID);
//            jsonObj.put("FacilityID", FacilityID);
//            jsonObj.put("MemberID", Utility.userData.getMemberID());
//            jsonObj.put("Date", TimeRender.NoticeGetDate(Date));

//            //Log.w("BDA","HTTP REQ JSON=" + Utility.ipURL + "GetFacilityAvailableTime?json=" + jsonObj.toString());
//            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, Utility.ipURL + "GetFacilityAvailableTime?json=" + jsonObj.toString(), null, new Response.Listener<JSONObject>() {
//                @Override
//                public void onResponse(JSONObject response) {
//                    try {
        //Log.w("BDA", "GetFacilityAvailableTime?json=" + response.toString());

//                        JSONObject output = response.getJSONObject("Output");
//                        if (output.getBoolean("Success")) {
        TimeFromList = new ArrayList<>();
        TimeToList = new ArrayList<>();
        TimeRange = new ArrayList<>();

//        TheSelectedDateFee = output.getInt("ResidentFee");
//        tvPricePerHr.setText("$" + TheSelectedDateFee + "");

//        JSONArray TimeList = output.getJSONArray("TimeList");

//        for (int i = 0; i < TimeList.length(); i++) {
//            JSONObject TimeListObj = TimeList.getJSONObject(i);
//                                if (TimeListObj.getBoolean("IsExpired")) {
//        TimeFromList.add(TimeListObj.getString("TimeFrom"));
//        TimeToList.add(TimeListObj.getString("TimeTo"));
//        TimeRange.add(TimeListObj.getString("TimeRange"));

        TimeFromList.add("08:00");
        TimeToList.add("09:00");
        TimeRange.add("08:00 - 09:00");

        TimeFromList.add("09:00");
        TimeToList.add("10:00");
        TimeRange.add("09:00 - 10:00");

        TimeFromList.add("10:00");
        TimeToList.add("11:00");
        TimeRange.add("10:00 - 11:00");

        TimeFromList.add("11:00");
        TimeToList.add("12:00");
        TimeRange.add("11:00 - 12:00");
//                                } else {
//                                    //Log.w("BDA","GetFacilityAvailableTime:onResponse,IsExpired=false");
//                                }
//                            }
//                        } else {
        //Log.w("BDA", "GetFacilityAvailableTime:onResponse,Success=false");
//                        }
//
//                    } catch (Exception e) {

        //Log.w("BDA", "GetFacilityAvailableTime:onResponse:Catch:" + e.toString());
//                    }

//                    //Log.w("BDA","TimeFromList: " + TimeFromList + "TimeToList: " + TimeToList + "venueList: " + venueList);

        //set Time Spinner
//                    ArrayList<String> TimeCombine = new ArrayList<>();
//                    for (int x = 0; x < TimeFromList.size(); x++) {
//                        TimeCombine.add(TimeFromList.get(x) + " - " + TimeToList.get(x));
//                    }

        ArrayAdapter<String> adapterTime = new ArrayAdapter<>(CalendarActivity.this, R.layout.spinner_show_text_grey, TimeRange);
//        ArrayAdapter<String> adapterTime = new ArrayAdapter<>(CalendarActivity.this, android.R.layout.simple_spinner_item, TimeRange);
//                    adapterTime.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        time_spinner.setAdapter(adapterTime);
        time_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                            Toast.makeText(BookingDetailsActivity.this, "ID= " + position + " 您選擇" + parent.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
//                            Utility.mTracker.send(new HitBuilders.EventBuilder()
//                                    .setCategory("Facilities-" + Utility.facilityBookingInfo.getFacilityListInfo().getTitle())
//                                    .setAction("Select")
//                                    .setLabel("Time")
//                                    .setValue(10)
//                                    .build());
                TimeFrom = TimeFromList.get(position);
                TimeTo = TimeToList.get(position);
                GetFacilityAvailableVenue();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//                    Utility.dismissLoadingDialog();
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    error.printStackTrace();
//                    if (error instanceof NoConnectionError) {
        //Log.w("BDA", "No internet Access, Check your internet connection.");
//                    }
//                }
//            });
//            Utility.mQueue.add(Utility.SetVolleyTimeoutAndNoCache(jsonRequest));
//
//        } catch (Exception e) {
        //Log.w("FacilityAvailableTime", "GetFacilityAvailableTime:Catch:" + e.toString());
//        }
    }

    public void okDialog() {
        android.support.v7.app.AlertDialog.Builder alertDlg = new android.support.v7.app.AlertDialog.Builder(this);
        if (IsBookedVenue) {
            alertDlg.setMessage(getString(R.string.TTHBRBOPCAT));
        } else {
            alertDlg.setMessage(getString(R.string.PFIAF));
        }
        alertDlg.setCancelable(true);
        alertDlg.setPositiveButton(getString(R.string.OK), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alertDlg.create().show();
    }
}
