package org.name.interceptors;

import org.name.api.FileApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Created by j on 25/03/2018. */
@Component
public class LogInterceptor implements HandlerInterceptor {

  private static Logger log = LoggerFactory.getLogger(FileApi.class);

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {

    log.info(
        "******** Incoming "
            + request.getMethod()
            + " request to "
            + request.getRequestURI()
            + " from host: "
            + request.getRemoteHost()
            + " with servername: "
            + request.getServerName()
            + " ********");
    log.info("protocol: " + request.getProtocol());
    log.info("query: " + request.getQueryString());
    return true;
  }

  @Override
  public void postHandle(
      HttpServletRequest request,
      HttpServletResponse response,
      Object handler,
      ModelAndView modelAndView)
      throws Exception {

    log.info("******** Response generated with status " + response.getStatus() + " ********");
    log.info("Headers: " + response.getHeaderNames());
    log.info("Content type: " + response.getContentType());
    log.info("Encoding: " + response.getCharacterEncoding());
    log.info("Number of bytes: " + response.getHeader("Content-Length"));
  }

  @Override
  public void afterCompletion(
      HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
      throws Exception {
    if (ex == null) {
      log.info("Request Successfully Completed");
    } else {
      log.error("unable to handle request", ex);
    }
  }
}
