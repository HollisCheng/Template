package template.cheng.hollis.template.youtubeAPI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import template.cheng.hollis.template.MainActivity;
import template.cheng.hollis.template.objectInfo.KeyWordsInfo;
import template.cheng.hollis.template.R;
import template.cheng.hollis.template.textView.SweetSansRegTextView;
import template.cheng.hollis.template.Utility;

public class PropertiesNamePageActivity extends AppCompatActivity {

    private ArrayList<KeyWordsInfo> dictionaryWords;
    private ArrayList<KeyWordsInfo> filteredList;
    private PropertiesNamePageAdapter mAdapter;
    private EditText searchBox;
    private RecyclerView recyclerView;
    private TextView SSRB_PNP_Done;
    private TextView btnCancelPNP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_properties_name_prage);

//        Bundle params = getIntent().getExtras();
//        if (params != null) {
//            Title = params.getString("Title");
////            imgContent = params.getStringArrayList("imgContent");
//        }
//
//        //region SetCustom Toolbar!
//        Toolbar tb = (Toolbar) findViewById(R.id.TBToolbar);
//        TextView toolbar_title = (TextView) findViewById(R.id.toolbar_title);
//        toolbar_title.setText(Title);
//        setSupportActionBar(tb);
//
//        // Get the ActionBar here to configure the way it behaves.
//        final ActionBar ab = getSupportActionBar();
//        //ab.setHomeAsUpIndicator(R.drawable.ic_menu); // set a custom icon for the default home button
//        if (ab != null) {
//            ab.setDisplayShowHomeEnabled(true); // show or hide the default home button
//            ab.setDisplayHomeAsUpEnabled(true);
//            ab.setDisplayShowCustomEnabled(true); // enable overriding the default toolbar layout
//            ab.setDisplayShowTitleEnabled(false); // disable the default title element here (for centered title)
//        }
//        //endregion

        Utility.PrintLog(getClass().getName(), "onCreate:Utility.IsRegisterPropertyName=" + Utility.IsRegisterPropertyName + ",Utility.IsAddPPropertyName=" + Utility.IsAddPPropertyName);
        dictionaryWords = Utility.KeyWordsInfoAL;
        filteredList = new ArrayList<>();
        filteredList.addAll(dictionaryWords);
        searchBox = (EditText) findViewById(R.id.ET_search_box);
        recyclerView = (RecyclerView) findViewById(R.id.RV_item_list);
        SSRB_PNP_Done = (TextView) findViewById(R.id.SSRB_PNP_Done);
        btnCancelPNP = (TextView) findViewById(R.id.btnCancelPNP);
        mAdapter = new PropertiesNamePageAdapter(filteredList);
        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(mAdapter);

        //TODO Cancel Btn OnClick
        btnCancelPNP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.IsRegisterPropertyName = false;
                Utility.IsAddPPropertyName = false;
                Utility.PrintLog(getClass().getName(), "onOptionsItemSelected:Utility.IsRegisterPropertyName=" + Utility.IsRegisterPropertyName + ",Utility.IsAddPPropertyName=" + Utility.IsAddPPropertyName);
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                finish();

            }
        });


        // search suggestions using the edittext widget
        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mAdapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        SSRB_PNP_Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utility.IsRegisterPropertyName) {
                    MainActivity.propertiesNamePageCallBack.setSelectText(searchBox.getText().toString());
                    MainActivity.propertiesNamePageCallBack.getMyInterface().myMethod(true);
                    Utility.IsRegisterPropertyName = false;
                    Utility.IsAddPPropertyName = false;
                    finish();
                } else if (Utility.IsAddPPropertyName) {
//                    AddPropertyActivity.propertiesNamePageCallBack.setSelectText(searchBox.getText().toString());
//                    AddPropertyActivity.propertiesNamePageCallBack.getMyInterface().myMethod(true);
//                    Utility.IsRegisterPropertyName = false;
//                    Utility.IsAddPPropertyName = false;
//                    finish();
                }
                Utility.PrintLog(getClass().getName(), "onDoneClick:Utility.IsRegisterPropertyName=" + Utility.IsRegisterPropertyName + ",Utility.IsAddPPropertyName=" + Utility.IsAddPPropertyName);
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Utility.IsRegisterPropertyName = false;
        Utility.IsAddPPropertyName = false;
        Utility.PrintLog(getClass().getName(), "onBackPressed:Utility.IsRegisterPropertyName=" + Utility.IsRegisterPropertyName + ",Utility.IsAddPPropertyName=" + Utility.IsAddPPropertyName);
        finish();
    }

    public class PropertiesNamePageAdapter extends RecyclerView.Adapter<PropertiesNamePageAdapter.MyViewHolder> implements Filterable {

        private List<KeyWordsInfo> PNObjList;
        private CustomFilter mFilter;

        @Override
        public Filter getFilter() {
            return mFilter;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final SweetSansRegTextView TVPropertiesNamePage;
            public KeyWordsInfo mItem;

            public MyViewHolder(View view) {
                super(view);
                mView = view;
                TVPropertiesNamePage = (SweetSansRegTextView) view.findViewById(R.id.TVPropertiesNamePage);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + TVPropertiesNamePage.getText() + "'";
            }
        }

        public PropertiesNamePageAdapter(List<KeyWordsInfo> PNObjList) {
            this.PNObjList = PNObjList;
            mFilter = new CustomFilter(PropertiesNamePageAdapter.this);
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_row, parent, false);
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_properties_name, parent, false);

            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            holder.mItem = PNObjList.get(position);
            //if device is using eng get engName, if TChi get TChiName
            if (Utility.AppLang.toLowerCase().equals("zh")) {
                holder.TVPropertiesNamePage.setText(PNObjList.get(position).getNameTChi());
                if (Utility.isCJK(PNObjList.get(position).getNameTChi()))
                    holder.TVPropertiesNamePage.setTextSize(12);
            } else if (Utility.AppLang.toLowerCase().equals("en")) {
                holder.TVPropertiesNamePage.setText(PNObjList.get(position).getNameEng());
            } else {
                holder.TVPropertiesNamePage.setText(PNObjList.get(position).getNameSChi());
                if (Utility.isCJK(PNObjList.get(position).getNameSChi()))
                    holder.TVPropertiesNamePage.setTextSize(12);
            }

            holder.TVPropertiesNamePage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utility.IsRegisterPropertyName) {
                        if (Utility.AppLang.toLowerCase().equals("zh")) {
                            MainActivity.propertiesNamePageCallBack.setSelectText(PNObjList.get(position).getNameTChi());
                        } else if (Utility.AppLang.toLowerCase().equals("en")) {
                            MainActivity.propertiesNamePageCallBack.setSelectText(PNObjList.get(position).getNameEng());
                        } else {
                            MainActivity.propertiesNamePageCallBack.setSelectText(PNObjList.get(position).getNameSChi());
                        }
                        MainActivity.propertiesNamePageCallBack.getMyInterface().myMethod(true);
                        Utility.IsRegisterPropertyName = false;
                        Utility.IsAddPPropertyName = false;
                        finish();
                    } else if (Utility.IsAddPPropertyName) {
//                        if (Utility.AppLang.toLowerCase().equals("zh")) {
//                            AddPropertyActivity.propertiesNamePageCallBack.setSelectText(PNObjList.get(position).getNameTChi());
//                        } else if (Utility.AppLang.toLowerCase().equals("en")) {
//                            AddPropertyActivity.propertiesNamePageCallBack.setSelectText(PNObjList.get(position).getNameEng());
//                        } else {
//                            AddPropertyActivity.propertiesNamePageCallBack.setSelectText(PNObjList.get(position).getNameSChi());
//                        }
//                        AddPropertyActivity.propertiesNamePageCallBack.getMyInterface().myMethod(true);
//                        Utility.IsRegisterPropertyName = false;
//                        Utility.IsAddPPropertyName = false;
//                        finish();
                    }
                    Utility.PrintLog(getClass().getName(), "onItemClick:Utility.IsRegisterPropertyName=" + Utility.IsRegisterPropertyName + ",Utility.IsAddPPropertyName=" + Utility.IsAddPPropertyName);
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                }
            });

        }

        @Override
        public int getItemCount() {
            return PNObjList.size();
        }

        public class CustomFilter extends Filter {
            private PropertiesNamePageAdapter mAdapter;

            private CustomFilter(PropertiesNamePageAdapter mAdapter) {
                super();
                this.mAdapter = mAdapter;
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                filteredList.clear();
                final FilterResults results = new FilterResults();
                if (constraint.length() == 0) {
                    filteredList.addAll(dictionaryWords);
                } else {
                    final String filterPattern = constraint.toString().toLowerCase().trim();
                    for (final KeyWordsInfo mWords : dictionaryWords) {
                        for (int i = 0; i < mWords.getEstateKeywordList().size(); i++) {
                            if (mWords.getEstateKeywordList().get(i).toLowerCase().contains(filterPattern)) {
                                if (filteredList.size() == 0) {
                                    filteredList.add(mWords);
                                } else {
                                    boolean exist = false;
                                    for (int p = 0; p < filteredList.size(); p++) {
                                        if (filteredList.get(p).getEstateID() == mWords.getEstateID()) {
                                            exist = true;
                                        }
                                    }
                                    if (!exist)
                                        filteredList.add(mWords);
                                }
                            }
                        }

                    }
                }
                System.out.println("Count Number " + filteredList.size());
                results.values = filteredList;
                results.count = filteredList.size();
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                System.out.println("Count Number 2 " + ((List<KeyWordsInfo>) results.values).size());
                this.mAdapter.notifyDataSetChanged();
            }
        }
    }


}