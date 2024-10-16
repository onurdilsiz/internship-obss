package day3.challenge;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        DataStore dataStore = new FileDataStore();
        dataStore.put("key1","value1");
        dataStore.put("key2","value2",2);

        System.out.println(dataStore.get("key2"));
        Thread.sleep(3000);
        System.out.println(dataStore.get("key2"));


    }
}
