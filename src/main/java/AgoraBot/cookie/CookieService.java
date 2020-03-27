package AgoraBot.cookie;

import org.apache.http.client.CookieStore;

public interface CookieService {
    CookieStore getCookies();

    String getSessKey();

    void setCredential(String username, String password);
}
