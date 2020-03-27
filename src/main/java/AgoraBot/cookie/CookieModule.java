package AgoraBot.cookie;

import com.google.inject.AbstractModule;

public class CookieModule extends AbstractModule {

    @Override
    protected void configure() {
        super.configure();

//        bindConstant().annotatedWith(Username.class).to("****");
//        bindConstant().annotatedWith(Password.class).to("****");
        bind(CookieService.class).to(LoginCookieFetcher.class);
    }
}
