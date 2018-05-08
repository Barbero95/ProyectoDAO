package DAOs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;


public class I18NManager {
    private static I18NManager instance;
    private HashMap<String, ResourceBundle> data;
    private List<String> lista = new ArrayList<>();


    private I18NManager(){
        data = new HashMap<String, ResourceBundle>();
    }

    public static I18NManager getInstance(){
        if (instance == null) instance = new I18NManager();
        return instance;
    }
    public String getText(String language, String Key){
        ResourceBundle rb = data.get(language);
        if (rb == null){
            rb = ResourceBundle.getBundle(language);
            data.put(language, rb);

        }
        return rb.getString(Key);
    }
}
