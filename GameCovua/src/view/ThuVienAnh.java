package view;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;


/**
 *
 * @author nhat_IT
 */
public class ThuVienAnh {
    protected static ResourceBundle resources;
    static{
        try{
            resources = ResourceBundle.getBundle("lib.Source",Locale.getDefault());
        }catch(Exception e){
            System.out.println("không tìm thấy nguồn thư viện ảnh");
            javax.swing.JOptionPane.showMessageDialog(null,
                    "không tìm thấy nguồn thư viện",
                    "Lỗi",javax.swing.JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }
    public String getResourceString(String key){
        String str;
        try{
            str = resources.getString(key);
        }catch(Exception e){
            str = null;
        }
        return str;
    }
    protected URL getResource(String key){
        String name = getResourceString(key);
        if(name != null){
            URL url = this.getClass().getResource(name);
            return url;
        }
        return null;
    }
}
