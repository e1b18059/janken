package oit.is.z0755.kaizi.janken.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Lec03AuthConfiguration
 */
@Configuration
@EnableWebSecurity
public class Lec03AuthConfiguration extends WebSecurityConfigurerAdapter {

  /**
   * 誰がログインできるか(認証処理)
   */
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    // $ sshrun htpasswd -nbBC 10 user1 pAssw0rd
    auth.inMemoryAuthentication().withUser("user1")
        .password("$2y$10$oiXbdOuy3nnbRt//p5R6eOORuxeeQqw60NumGLTL1NcIN9uhmQp.i").roles("USER");
    auth.inMemoryAuthentication().withUser("user2")
        .password("$2y$10$tDVJWAqs7kFDDPo1jN43auuxZ.nIb1cmksQAfuWn8x7AVRpiju2dy").roles("USER");

    // 開発中は↓の書き方でも良いが，平文でパスワードが保存される
    // auth.inMemoryAuthentication().withUser("user1").password(passwordEncoder().encode("password1")).roles("USER");
    // auth.inMemoryAuthentication().withUser("user2").password(passwordEncoder().encode("password2")).roles("USER");
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /**
   * 認証されたユーザがどこにアクセスできるか（認可処理）
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {

    // Spring Securityのフォームを利用してログインを行う
    http.formLogin();

    // http://localhost:8080/lec02 で始まるURLへのアクセスはログインが必要
    // antMatchers().authenticated がantMatchersへのアクセスに認証を行うことを示す
    // antMatchers()の他にanyRequest()と書くとあらゆるアクセス先を表現できる
    // authenticated()の代わりにpermitAll()と書くと認証処理が不要であることを示す
    http.authorizeRequests().antMatchers("/lec02/**").authenticated();

    // Spring Securityの機能を利用してログアウト．ログアウト時は http://localhost:8080/ に戻る
    http.logout().logoutSuccessUrl("/");
  }

}
