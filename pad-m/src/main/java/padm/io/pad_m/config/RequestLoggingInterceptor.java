package padm.io.pad_m.config;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import padm.io.pad_m.domain.Log;
import padm.io.pad_m.security.AuthenticationFacade;
import padm.io.pad_m.service.LogService;

@Component
public class RequestLoggingInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingInterceptor.class);
    
    @Autowired
    LogService logService;
    
    @Autowired
    AuthenticationFacade session;
    
    private Log log;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	if (!request.getMethod().equals("GET") && !request.getRequestURI().equals("/error")) {
    		String username = ""; //TODO //session.getUsuario().getId();
    		log = new Log();
    		log.setAcao(request.getRequestURI());
    		log.setTabela(request.getMethod());
    		log.setIp(request.getRemoteAddr());
    		log.setUsuario(username);
    		log.setData(LocalDateTime.now());
    		log.setDetalhe(request.getQueryString());
    		//logService.save(log);
    	}
        //logger.info("Incoming request: method={}, URI={}, query={}", request.getMethod(), request.getRequestURI(), request.getQueryString());
        //System.out.println("Methodo request = " + request.getMethod());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    	if (this.log != null){
    	  if ( (response.getStatus()==302 || response.getStatus()==200)&& !log.getTabela().equals("GET")) {
    		  //System.out.println("** SALVAR REGISTRO DE LOG **");
    		  logService.save(log);
    	  }
    	 //System.out.println("Cheguei arqui no afterHandle " + log.toString());
    	}
        //logger.info("Outgoing response: status={}", response.getStatus());
        this.log = null;
    }

}
