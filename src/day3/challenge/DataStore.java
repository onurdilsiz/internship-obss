package day3.challenge;

interface DataStore {
    void put(String key, String value);
    String get(String key);
    void put(String key, String value, int ttl);	// Yeni eklenen method

}