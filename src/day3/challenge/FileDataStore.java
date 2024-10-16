package day3.challenge;

import java.io.*;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;

class FileDataStore implements DataStore {
    private Map<String , StoredValue> store= new HashMap<>() ;
    private final String FILE_PATH = "datastore.txt";


    @Override
    public void put(String key, String value) {
        store.put(key, new StoredValue(value,-1));
        saveToFile();
    }

    @Override
    public String get(String key) {
        if (!store.containsKey(key)){
            throw new RuntimeException("Key not found");
        }
        StoredValue storedValue = store.get(key);


        if(storedValue.getTimestamp() != -1 && storedValue.getTimestamp()<System.currentTimeMillis()){
            store.remove(key);
            saveToFile();
            throw new RuntimeException("Key has expired");
        }
        return storedValue.getValue();
    }

    @Override
    public void put(String key, String value, int ttl) {
        long expiryDate = System.currentTimeMillis() + ttl*1000 ;
        store.put(key, new StoredValue(value, expiryDate));
        saveToFile();


    }
    private void saveToFile(){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH))){
            out.writeObject(store);
            out.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void loadFromFile(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))){
                store = (HashMap<String,StoredValue>) ois.readObject();
        }catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}