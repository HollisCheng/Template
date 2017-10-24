package template.cheng.hollis.template.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import org.greenrobot.eventbus.EventBus;


public class EventBusBaseActivity extends BaseActivity {


  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    registerEventBus();
  }

  @Override
  protected void onPause() {
    super.onPause();
    unregisterEventBus();
  }

  @Override
  protected void onResume() {
    super.onResume();
    registerEventBus();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    unregisterEventBus();
  }


  private void registerEventBus() {
    if (!EventBus.getDefault().isRegistered(this)) {
      EventBus.getDefault().register(this);
    }
  }

  private void unregisterEventBus() {
    if (EventBus.getDefault().isRegistered(this)) {
      EventBus.getDefault().unregister(this);
    }
  }
}
