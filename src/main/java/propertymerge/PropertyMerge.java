package propertymerge;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.TreeSet;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PropertyMerge {

    public static void main(String[] args){

        try(InputStream sourceStream = new FileInputStream(args[0]);
            InputStream targetStream = new FileInputStream(args[1]);
            InputStream updateDataStream = new FileInputStream(args[0]+".json");){

            Properties source = new Properties();
            Properties target = new Properties(){
                    @Override
                    public synchronized Enumeration<Object> keys(){
                        return Collections.enumeration(new TreeSet<Object>(super.keySet()));
                    }
                };
            source.load(sourceStream);
            target.load(targetStream);
            
            ObjectMapper mapper = new ObjectMapper();
            UpdateData data = mapper.readValue(updateDataStream, UpdateData.class);

            Properties dest = (Properties) target.clone();
            for (String key : data.getCreateList()){
                if(target.getProperty(key) == null && source.getProperty(key) != null){
                    dest.setProperty(key, source.getProperty(key));
                }
            }

            for (String key : data.getUpdateList()){
                if(target.getProperty(key) != null && source.getProperty(key) != null){
                    dest.setProperty(key, source.getProperty(key));
                }
            }

            for (String key : data.getDeleteList()){
                if(dest.getProperty(key) != null){
                    dest.remove(key);
                }
            }

            dest.store(System.out, null);
        } catch (Exception ex){
            System.err.println(ex);
        }
    }
}
