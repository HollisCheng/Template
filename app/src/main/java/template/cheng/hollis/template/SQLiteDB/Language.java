package template.cheng.hollis.template.SQLiteDB;

public class Language {
    private int ID;
    private String Language;

    public Language() {
    }

    public Language(int ID, String language) {
        this.ID = ID;
        Language = language;
    }

    @Override
    public String toString() {
        return "Language{" +
                "ID=" + ID +
                ", Language='" + Language + '\'' +
                '}';
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }
}
