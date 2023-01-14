package ggxnet.reload.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  private final DBUserDetailsService userDetailsService;
  private final SessionFilter sessionFilter;

  WebSecurityConfig(DBUserDetailsService userDetailsService, SessionFilter sessionFilter) {
    this.userDetailsService = userDetailsService;
    this.sessionFilter = sessionFilter;
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

    http = http.cors().and().csrf().disable();

    http = http.exceptionHandling().authenticationEntryPoint(
        (request, response, ex) -> response.sendError(
            HttpServletResponse.SC_UNAUTHORIZED,
            ex.getMessage())
    ).and();

    http.authorizeRequests()
        .antMatchers(HttpMethod.POST,
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
        .antMatchers(HttpMethod.GET,
            "/characters",
            "/palettes",
            "/sprites")
        .permitAll();
    http.authorizeRequests()
        .antMatchers("/login").permitAll()
        .antMatchers("/registration").permitAll()
        .anyRequest().authenticated();

    http.addFilterBefore(
        sessionFilter,
        UsernamePasswordAuthenticationFilter.class
    );

  }

  @Bean
  public HttpFirewall getHttpFirewall() {
    StrictHttpFirewall strictHttpFirewall = new StrictHttpFirewall();
    strictHttpFirewall.setAllowSemicolon(true);
    return strictHttpFirewall;
  }

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/webjars/**");
  }
}
