package template.cheng.hollis.template.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import space.sye.z.library.RefreshRecyclerView;
import space.sye.z.library.listener.OnLoadMoreListener;
import space.sye.z.library.manager.RecyclerMode;
import space.sye.z.library.manager.RecyclerViewManager;
import template.cheng.hollis.template.ui.adapter.HappensLatestPAdapter;
import template.cheng.hollis.template.R;
import template.cheng.hollis.template.ui.adapter.ShoppingAdapter;
import template.cheng.hollis.template.coordinatorLayout_Card_Tab_Filter.IconList;
import template.cheng.hollis.template.coordinatorLayout_Card_Tab_Filter.OffersInfo;
import template.cheng.hollis.template.coordinatorLayout_Card_Tab_Filter.ShopList;
import template.cheng.hollis.template.objectInfo.ShoppingItemInfo;

public class TestFragment extends Fragment {

//    private RefreshRecyclerView RRV_HSV_privileges;
    private RecyclerView RRV_HSV_privileges;
    private RefreshRecyclerView RRV_SV_Shopping;
    private LinearLayoutManager llm;
    private GridLayoutManager SGL;
    private HappensLatestPAdapter adapter;
    private ShoppingAdapter Sadapter;
    private Handler handler;
    private Handler handlerV;
    private ArrayList<OffersInfo> mData;
    private ArrayList<ShoppingItemInfo> mDataV;
    private int from = 1;
    private int fromV = 1;
    private int to = 10;
    private int toV = 10;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, container, false);

        //TODO demo horizontal RecyclerView and vertical RecyclerView scroll smooth way
        RRV_HSV_privileges = (RecyclerView) view.findViewById(R.id.RRV_HSV);
        RRV_SV_Shopping = (RefreshRecyclerView) view.findViewById(R.id.RRV_SV_Shopping);

        handler = new Handler();
        mData = new ArrayList<>();
        from = 1;
        to = 10;

        handlerV = new Handler();
        mDataV = new ArrayList<>();
        fromV = 1;
        toV = 10;

        new Thread(new Runnable() {
            @Override
            public void run() {
                GetHappenPrivilegeList();
                GetHotShopping();
            }
        }).start();

        llm = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false) {
//            @Override
//            public boolean canScrollHorizontally() {
//                return true;
//            }

            @Override
            public boolean canScrollVertically() {
                return true;
            }
        };
        RRV_HSV_privileges.setLayoutManager(llm);
        adapter = new HappensLatestPAdapter(getContext(), mData, getActivity());
        RRV_HSV_privileges.setAdapter(adapter);
        RRV_HSV_privileges.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

//        RecyclerViewManager.with(adapter, llm)
////                .setMode(RecyclerMode.BOTH)
////                .setOnBothRefreshListener(new OnBothRefreshListener() {
////                    @Override
////                    public void onPullDown() {
////                        refreshRecyclerView.onRefreshCompleted();
////                        adapter.notifyDataSetChanged();
////                    }
////
////                    @Override
////                    public void onLoadMore() {
////                        handler.postDelayed(new Runnable() {
////                            @Override
////                            public void run() {
////                                GetPrivilegeList(from, to);
////                            }
////                        }, 1000);
////                    }
////                })
//                .setMode(RecyclerMode.BOTTOM)
//                .setOnLoadMoreListener(new OnLoadMoreListener() {
//                    @Override
//                    public void onLoadMore() {
//                        handler.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                GetHappenPrivilegeList();
//                            }
//                        }, 0);
//                    }
//                })
//                .into(RRV_HSV_privileges, getContext());

//        mRecyclerView.setAdapter(adapter);
//        mRecyclerView.setOnScrollListener(new EndlessRecyclerOnScrollListener(llm) {
//            @Override
//            public void onLoadMore(int current_page) {
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        GetPrivilegeList(from, to);
////                        Toast.makeText(getContext(), "Loading More...", Toast.LENGTH_LONG).show();
//                        //Log.w("MPRF", "LoadMore form=" + from + ",to=" + to);
//                        PBLoad.setVisibility(View.VISIBLE);
//                    }
//                }, 1000);
//            }
//        });

        //Ver
        //TODO GetHotShopping
//        SGL = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        SGL = new GridLayoutManager(getContext(), 2);

//        llm = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
//        RRV_HSV_privileges.setLayoutManager(llm);
        Sadapter = new ShoppingAdapter(getContext(), mDataV, getActivity());

        RecyclerViewManager.with(Sadapter, SGL)
//                .setMode(RecyclerMode.BOTH)
//                .setOnBothRefreshListener(new OnBothRefreshListener() {
//                    @Override
//                    public void onPullDown() {
//                        refreshRecyclerView.onRefreshCompleted();
//                        adapter.notifyDataSetChanged();
//                    }
//
//                    @Override
//                    public void onLoadMore() {
//                        handler.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                GetPrivilegeList(from, to);
//                            }
//                        }, 1000);
//                    }
//                })
                .setMode(RecyclerMode.BOTTOM)
                .setOnLoadMoreListener(new OnLoadMoreListener() {
                    @Override
                    public void onLoadMore() {
                        handlerV.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                GetHotShopping();
                            }
                        }, 0);
                    }
                })
                .into(RRV_SV_Shopping, getContext());

//        mRecyclerView.setAdapter(adapter);
//        mRecyclerView.setOnScrollListener(new EndlessRecyclerOnScrollListener(llm) {
//            @Override
//            public void onLoadMore(int current_page) {
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        GetPrivilegeList(from, to);
////                        Toast.makeText(getContext(), "Loading More...", Toast.LENGTH_LONG).show();
//                        //Log.w("MPRF", "LoadMore form=" + from + ",to=" + to);
//                        PBLoad.setVisibility(View.VISIBLE);
//                    }
//                }, 1000);
//            }
//        });

        return view;
    }

    private void GetHappenPrivilegeList() {
        for (int i = 0; i < 10; i++) {
            mData.add(new OffersInfo(1, "category", "categoryGA", "Desc", "distance", "endDate", new ArrayList<IconList>(), false, false, false, false,
                    false, i, "Merchant_Name", "Merchant_PhotoPath", "Merchant_Status", "Name", "OfferPIN", "OfferRefNo", "OfferStatus",
                    "PhotoPath", "PhotoPathIn", "PostDate", i, "ReferralCode", i, i, new ArrayList<ShopList>(), "StartDate", "Status",
                    "TCLink", i, "WebSite", i + 0.1, i + 0.1, "NameGA", 1, "", ""));
        }
    }

    private void GetHotShopping() {
        for (int i = 0; i < 10; i++) {
            mDataV.add(new ShoppingItemInfo(i, "Desc", "PhotoPath", "Title", "Type", "UnitPrice", "WebSite"));
        }
    }

}

