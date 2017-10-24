package template.cheng.hollis.template.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.google.gson.Gson;

import template.cheng.hollis.template.MainActivity;

public abstract class BaseFragment extends Fragment implements FragmentBaseView {

    private int currentMenuCode;

    public BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }

    public MainActivity getMainActivity() {
        return (MainActivity) getActivity();
    }

    public BaseApplication getBaseApplication() {
        return getBaseActivity().getBaseApplication();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() instanceof MainActivity) {
//            ((MainActivity) getActivity()).updateMenuBarVisibility(showMenuBar());
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
//            ((MainActivity) getActivity()).updateMenuBarVisibility(showMenuBar());
        }
    }

    @Override
    public boolean showMenuBar() {
        return false;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showLoadingIndicator() {
        getBaseActivity().showLoadingIndicator();
    }

    @Override
    public void hideLoadingIndicator() {
        getBaseActivity().hideLoadingIndicator();
    }

    @Override
    public void showDialog(String message) {
        getBaseActivity().showDialog(message);
    }

    public Gson getGson() {
        return getBaseApplication().getGson();
    }
}
