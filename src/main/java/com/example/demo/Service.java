package com.example.demo;

import com.example.demo.entity.GeoEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Service {
    private static String PATH_PREFIX = "https://nominatim.openstreetmap.org/search?";
    private static String PATH_SUFFIX = "&country=russia&format=json&polygon_geojson=1";
    private static String SPLITTER = "!@#$%^%$#@!";

    private Map<String, GeoEntity[]> cache = new HashMap<>();

    @GetMapping("{type}/{pathVariable}")
    public String getFromOSM(@PathVariable String type,
                             @PathVariable String pathVariable) {

        GeoEntity[] geoEntities;
        String cacheKey = type + SPLITTER + pathVariable;

        if (cache.containsKey(cacheKey))
            geoEntities = cache.get(cacheKey);

        else {
            geoEntities = get(type, pathVariable);
            cache.put(cacheKey, geoEntities);
        }

        StringBuilder stringBuilder = new StringBuilder("Get method result: <br>");
        if (geoEntities.length > 0) {
            for (GeoEntity geoEntity : geoEntities) {
                geoEntity.setTypeFromRequest("q".equals(type) ? "query" : type);
                stringBuilder.append(geoEntity.printAtBrowser());
            }
        } else {
            stringBuilder.append("something went wrong, Check parameters please");
        }

        return stringBuilder.toString();
    }

    private GeoEntity[] get(String type, String pathVariable) {
        String request = PATH_PREFIX +
                type + "=" + pathVariable +
                PATH_SUFFIX;
        return new RestTemplate().getForObject(request, GeoEntity[].class, (Object) null);
    }
}
