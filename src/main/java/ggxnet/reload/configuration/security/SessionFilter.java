package ggxnet.reload.configuration.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class SessionFilter extends OncePerRequestFilter {
  private final SessionRegistry sessionRegistry;
  private final CurrentUserService currentUserService;

  @Autowired
  public SessionFilter(
      final SessionRegistry sessionRegistry, final CurrentUserService currentUserService) {
    this.sessionRegistry = sessionRegistry;
    this.currentUserService = currentUserService;
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws ServletException, IOException {
    final String sessionId = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (sessionId == null || sessionId.length() == 0) {
      chain.doFilter(request, response);
      return;
    }

    final String username = sessionRegistry.getUsernameForSession(sessionId);
    if (username == null) {
      chain.doFilter(request, response);
      return;
    }

    final CurrentUser currentUser = currentUserService.loadUserByUsername(username);
    final UsernamePasswordAuthenticationToken auth =
        new UsernamePasswordAuthenticationToken(currentUser, null, currentUser.getAuthorities());
    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    SecurityContextHolder.getContext().setAuthentication(auth);

    chain.doFilter(request, response);
  }
}
