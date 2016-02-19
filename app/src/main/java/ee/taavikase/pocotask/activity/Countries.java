package ee.taavikase.pocotask.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Countries {
    private Map<String, String> mCountriesMap;

    public Countries() {
        insertCountries();
    }

    private void insertCountries() {
        mCountriesMap = new HashMap<>();
        mCountriesMap.put("Estonia", "ET");
        mCountriesMap.put("Latvia", "LV");
        mCountriesMap.put("Lithuania", "LT");
        mCountriesMap.put("Finland", "FI");
        mCountriesMap.put("Sweden", "SE");
        mCountriesMap.put("Norway", "NO");
        mCountriesMap.put("Denmark", "DK");
        mCountriesMap.put("Belgium", "BE");
        mCountriesMap.put("Germany", "DE");
        mCountriesMap.put("Spain", "ES");
    }

    public ArrayList<String> getCountries() {
        Set<String> keys = mCountriesMap.keySet();

        ArrayList<String> countries = new ArrayList<>();

        for (String key : keys) {
            countries.add(key);
        }

        return countries;
    }

    public String getCountryCode(String country) {
        return mCountriesMap.get(country);
    }
}
