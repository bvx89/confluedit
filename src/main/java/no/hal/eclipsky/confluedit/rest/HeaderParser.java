package no.hal.eclipsky.confluedit.rest;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import java.util.List;
import java.util.Map;

public class HeaderParser {
    private static final String USER_AGENT = "user-agent";
    private static final String ACCEPT = "accept";
    private static final String ACCEPT_ENCODING = "accept-encoding";
    private static final String ACCEPT_CHARSET = "accept-charset";
    private static final String ACCEPT_LANGUAGE = "accept-language";
    private static final String COOKIE = "cookie";
    private static final String SESSION_NAME = "JSESSIONID";

    private HttpHeaders headers;

    public HeaderParser(HttpHeaders headers) {
        this.headers = headers;
    }

    public String getSessionId() {
        String[] cookies = getCookies().split("; ");
        for (String cookie : cookies) {
            String[] cookieParts = cookie.split("=");
            if (cookieParts[0].equals(SESSION_NAME)) {
                return cookieParts[1];
            }
        }
        return null;
    }

    public String getStartIndexCookie() {
        String cookieString = getHeaderInfoByName(COOKIE);
        String[] cookieList = cookieString.split(";");
        for (String cookie : cookieList) {
            String[] cookieParts = cookie.split("=");
            if (cookieParts[0].equals("startIndex")) {
                return cookieParts[1];
            }
        }

        return null;
    }

    public String getCookies() {
        return getHeaderInfoByName(COOKIE);
    }

    public String getUserAgent() {
        return getHeaderInfoByName(USER_AGENT);
    }

    public String getAccept() {
        String[] accepts = {ACCEPT, ACCEPT_CHARSET, ACCEPT_ENCODING, ACCEPT_LANGUAGE};
        StringBuilder sb = new StringBuilder(accepts.length * 20);
        for (String accept : accepts) {
            sb.append(getHeaderInfoByName(accept)).append(", ");
        }

        return sb.toString();
    }

    public String getHeaderInfoByName(String headerName) {
        List<String> headerList = headers.getRequestHeader(headerName);
        if (headerList == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String info : headerList) {
            sb.append(info).append("\n");
        }

        // Remove last \n
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    public String getAll() {
        MultivaluedMap<String, String> requestHeaders = headers.getRequestHeaders();
        if (requestHeaders == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, List<String>> entry : requestHeaders.entrySet()) {
            sb.append(entry.getKey()).append("=");
            for (String item : entry.getValue()) {
                sb.append(item).append(',');
            }
            sb.append("\n");
        }

        // Remove last \n
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
}
