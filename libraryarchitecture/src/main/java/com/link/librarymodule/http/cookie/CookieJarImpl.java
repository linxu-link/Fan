package com.link.librarymodule.http.cookie;


import com.link.librarymodule.http.cookie.store.CookieStore;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

import java.util.List;

/**
 * Created by goldze on 2017/5/13.
 */
public class CookieJarImpl implements CookieJar {

    private CookieStore cookieStore;

    public CookieJarImpl(CookieStore cookieStore) {
        if (cookieStore == null) {
            throw new IllegalArgumentException("cookieStore can not be null!");
        }
        this.cookieStore = cookieStore;
    }

    @Override
    public synchronized void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        cookieStore.saveCookie(url, cookies);
    }

    @Override
    public synchronized List<Cookie> loadForRequest(HttpUrl url) {
        return cookieStore.loadCookie(url);
    }

    public CookieStore getCookieStore() {
        return cookieStore;
    }
}