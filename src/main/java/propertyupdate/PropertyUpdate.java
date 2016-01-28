package propertyupdate;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.InputStream;

public class PropertyUpdate {

    public static void main(String[] args){

        Properties source = new Properties();
        Properties target = new Properties();
        Properties dest = new Properties();
        try(InputStream source_is = new FileInputStream(args[0]);
            InputStream target_is = new FileInputStream(args[1])){
            
            source.load(source_is);
            target.load(target_is);
            
            for (String key : source.stringPropertyNames()){
                if(target.getProperty(key) == null){
                    dest.setProperty(key, source.getProperty(key));
                }else{
                    dest.setProperty(key, source.getProperty(key));
                }
            }

            dest.store(System.out, null);
        } catch (Exception ex){
            System.out.println(ex);
        }
    }
}
