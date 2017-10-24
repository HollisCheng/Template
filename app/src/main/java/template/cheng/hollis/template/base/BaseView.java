package template.cheng.hollis.template.base;

public interface BaseView {

  BaseActivity getBaseActivity();

  void showLoadingIndicator();

  void hideLoadingIndicator();

  void showDialog(String message);
}