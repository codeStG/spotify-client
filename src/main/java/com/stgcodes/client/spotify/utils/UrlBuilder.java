package com.stgcodes.client.spotify.utils;

public class UrlBuilder {
    private final String typeStr;

    public UrlBuilder(Class<?> type) {
        this.typeStr = type.getSimpleName().toLowerCase() + "s";
    }

    public String buildUrlByQuery(String query) {
        return "/search?q=" + query + "&type=album,artist,track";
    }

    public String buildUrlById(String id) {
        return "/" + typeStr + "/" + id;
    }

    public String buildUrlByCommaSeparatedIds(String ids) {
        return "/" + typeStr + "?ids=" + ids;
    }
}
