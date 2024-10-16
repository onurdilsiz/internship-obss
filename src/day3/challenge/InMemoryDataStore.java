package day3.challenge;


import java.util.HashMap;

public class InMemoryDataStore implements DataStore {

        private final HashMap<String, String> store = new HashMap<>();

        @Override
        public void put(String key, String value) {
            store.put(key, value);
        }

        @Override
        public String get(String key) {
            if (!store.containsKey(key)) {
                throw new RuntimeException("Key not found: " + key);
            }else
            return  store.get(key);
        }

    @Override
    public void put(String key, String value, int ttl) {
        store.put(key, value);

    }


}

