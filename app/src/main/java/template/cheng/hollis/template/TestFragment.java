package template.cheng.hollis.template;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TestFragment extends Fragment {

//    private ArrayList<FacilityListInfo> facilityListInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, container, false);

        //Log.w("FLF", "onCreateView");

//        facilityListInfo = Utility.FacilitiesList;
//
//        for (int j = 0; j < facilityListInfo.size(); j++) {
//            if (j % 2 == 0) {
//                facilityListInfo.get(j).setType(0);
//            } else {
//                facilityListInfo.get(j).setType(1);
//            }
//        }
//
//        RecyclerView rv_Facility_List = (RecyclerView) view.findViewById(R.id.rv_Facility_List);
//        LinearLayoutManager llm = new LinearLayoutManager(getContext());
//        llm.setOrientation(LinearLayoutManager.VERTICAL);
//        rv_Facility_List.setLayoutManager(llm);
//        Facility_List_adapter facility_List_adapter = new Facility_List_adapter(getContext(), facilityListInfo);
//        rv_Facility_List.setAdapter(facility_List_adapter);

        return view;
    }


}

