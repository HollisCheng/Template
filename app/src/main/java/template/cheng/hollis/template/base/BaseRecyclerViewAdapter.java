package template.cheng.hollis.template.base;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;


public abstract class BaseRecyclerViewAdapter<T> extends
        RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public abstract ArrayList<T> getDataList();

    public abstract void setDataList(ArrayList<T> newDataList);

    public void updateList(final ArrayList<T> oldDataList, final ArrayList<T> newDataList) {

//        Observable<DiffUtil.DiffResult> mObservable = Observable
//                .create(new ObservableOnSubscribe<DiffUtil.DiffResult>() {
//                    @Override
//                    public void subscribe(ObservableEmitter<DiffUtil.DiffResult> e) throws Exception {
//                        CommonDiffUtil mCommonDiffUtil = new CommonDiffUtil<>(oldDataList, newDataList);
//                        e.onNext(DiffUtil.calculateDiff(mCommonDiffUtil));
//                    }
//                });
//
//        mObservable
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<DiffUtil.DiffResult>() {
//                    @Override
//                    public void accept(DiffUtil.DiffResult diffResult) throws Exception {
//                        setDataList(newDataList);
//                        diffResult.dispatchUpdatesTo(BaseRecyclerViewAdapter.this);
//                    }
//                });

//        CommonDiffUtil mCommonDiffUtil = new CommonDiffUtil<>(oldDataList, newDataList);
//        DiffUtil.DiffResult result = DiffUtil.calculateDiff(mCommonDiffUtil);
//        setDataList(newDataList);
//        result.dispatchUpdatesTo(add.this);

        setDataList(newDataList);
        notifyDataSetChanged();
    }
}

