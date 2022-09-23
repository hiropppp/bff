package com.example.bff;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
  // Spring Security5.7より大幅に設定方法が変更された
  // https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
  // https://www.docswell.com/s/MasatoshiTada/KGVY9K-spring-security-intro

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http.formLogin(login -> login             // フォーム認証の設定記述開始
        .loginProcessingUrl("/login")         // ユーザー名・パスワードの送信先URL
        .loginPage("/login")                  // ログイン画面のURL
        .defaultSuccessUrl("/menu")               // ログイン成功後のリダイレクト先URL
        .failureUrl("/login?error")   // ログイン失敗後のリダイレクト先URL
        .permitAll()
    ).logout(logout -> logout                 // ログアウトの設定記述開始
        .logoutSuccessUrl("/")                // ログアウト成功後のリダイレクト先URL;
    ).authorizeHttpRequests(authz -> authz    // URLごとの認可設定記述開始
        .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
        .permitAll()                          // "/css/**"などはログイン無しでもアクセス可能
        .mvcMatchers("/webjars/**").permitAll() // webjarsへアクセス許可
        .mvcMatchers("/css/**").permitAll()// cssへアクセス許可
        .mvcMatchers("/js/**").permitAll()// jsへアクセス許可
        .mvcMatchers("/").permitAll() // "/"はログイン無しでもアクセス可能
        .mvcMatchers("/actuator/health").permitAll() // ヘルスチェックはログイン無しでもアクセス可能
        .mvcMatchers("/greeting").permitAll() // "greeting"はログイン無しでもアクセス可能(後で削除予定
        .mvcMatchers("/login").permitAll() // ログイン画面はログイン無しでもアクセス可能
        .mvcMatchers("/user").hasRole("ADMIN") // ユーザ管理画面はROLE_ADMINのみアクセス可能
        .anyRequest().authenticated() // 他のURLはログイン後のみアクセス可能
    );
    return http.build();
  }

}
