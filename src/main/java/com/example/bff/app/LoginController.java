package com.example.bff.app;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {


  @ModelAttribute
  public LoginForm setUpForm() {
    LoginForm form = new LoginForm();
    return form;
  }
  
  /**
   * ログイン画面のGETメソッド用処理
   */
  @GetMapping("/login")
  public String getLogin(Model model, HttpSession session) {
    // ログイン画面へ遷移
    return "login";
  }

  /**
   * LoginFormは@Validatedを利用すること！サンプル参照！
   *
   * @param form
   * @param result
   * @param session
   * @return
   */
  @PostMapping("/login")
  public String postLogin(LoginForm form, BindingResult result, HttpSession session) {
    if (result.hasErrors()) {
      return "login";
    }
    //return "forward:/authenticate"; TODO:なぜauthenticate？
    return "forward:/login";

  }

  /**
   * ログイン成功後のメニュー画面遷移処理
   */
  @GetMapping("/menu")
  public String menu(Model model) {
    return "menu";
  }

}
