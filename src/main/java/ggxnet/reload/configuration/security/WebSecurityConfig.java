package ggxnet.reload.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

@Configuration
@EnableWebSecurity
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  private final DBUserDetailsService userDetailsService;

  WebSecurityConfig(DBUserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf()
        .disable()
        .authorizeRequests()
        .antMatchers("/home", "/error", "/registration")
        .permitAll()
        .antMatchers(HttpMethod.GET, "/characters", "/palettes", "/sprites")
        .permitAll()
        .antMatchers(
            HttpMethod.POST,
            "/",
            "/rest/register",
            "/rest/enter",
            "/rest/leave",
            "/rest/read",
            "/rest/get-config",
            "/rest/set-config",
            "/rest/win",
            "/rest/draw",
            "/rest/palettes",
            "/rest/lose")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .sessionManagement()
        .disable()
        .formLogin()
        .loginPage("/login")
        .defaultSuccessUrl("/", true)
        .permitAll()
        .and()
        .logout()
        .permitAll();
  }

  @Bean
  public HttpFirewall getHttpFirewall() {
    StrictHttpFirewall strictHttpFirewall = new StrictHttpFirewall();
    strictHttpFirewall.setAllowSemicolon(true);
    return strictHttpFirewall;
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/webjars/**");
  }
}
