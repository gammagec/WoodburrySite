package net.woodburry.client;

import com.chrisgammage.ginjitsu.client.JitsuInjector;
import net.woodburry.client.home_page.HomePage;
import net.woodburry.client.login_page.LoginPage;
import net.woodburry.client.new_account_page.NewAccountPage;

/**
 * Created with IntelliJ IDEA.
 * User: gamma_000
 * Date: 5/31/13
 * Time: 11:12 PM
 */
public interface WoodburryInjector extends JitsuInjector {
    MainPage mainPage();
    LoginPage loginPage();
    HomePage homePage();
    NewAccountPage newAccountPage();
    GlobalEventBus globalEventBus();
}
