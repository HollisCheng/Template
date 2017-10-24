package template.cheng.hollis.template;

public class Constant {

  //Menu item code
  public static final int MENU_EVENT      = 1;
  public static final int MENU_CHAT       = 2;
  public static final int MENU_ACTIVITY   = 3;
  public static final int MENU_EXPLORE    = 4;
  public static final int MENU_MORE       = 5;

  //Fragment Tag
  public static final String TAG_FRAGMENT_AFTER_ADD     = "Fragment_Added";
  public static final String TAG_FRAGMENT_AFTER_REMOVE  = "Fragment_Removed";
  public static final String TAG_RONG_IM                = "Rong_IM";

  //Folder Constant (in app cache folder)
  public static final String UPLOAD_FOLDER            = "/upload_file";
  public static final String UPLOAD_COMPRESSED_FOLDER = UPLOAD_FOLDER.concat("/compressed");
}
