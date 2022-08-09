package br.com.rrssistema.microserviceproductapi.config.interceptor;

import br.com.rrssistema.microserviceproductapi.config.exception.ValidationException;
import br.com.rrssistema.microserviceproductapi.modules.jwt.service.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Arrays;
import java.util.UUID;
import java.util.regex.Pattern;

import static org.springframework.util.ObjectUtils.isEmpty;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

    private static final String AUTHORIZATION = "Authorization";
    private static final String TRANSACTION_ID = "transactionid";

    @Autowired
    private JwtService jwtService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        if (isOptions(request)) {
            return true;
        }
        if (isPublicEndpoint(request)) {
            return true;
        }
        if (isEmpty(request.getHeader(TRANSACTION_ID))) {
            throw new ValidationException("The transactionId header is required.");
        }
        var authorization = request.getHeader(AUTHORIZATION);
        jwtService.validateAuthorization(authorization);
        request.setAttribute("serviceid", UUID.randomUUID().toString());
        return true;
    }

    private boolean isPublicEndpoint(HttpServletRequest request) {
        log.info("VERIFICANDO ENDPOINTS PUBLICO: " + request.getRequestURI());

        return Arrays.stream(PublicEndPoints.values())
            .anyMatch(publicEndPoint ->
                    match(publicEndPoint.getPublicEndPoint(), request.getRequestURI()));
    }

    private static boolean match(String publicEndpoint, String requestEndpoint) {
        var pattern = Pattern.compile(publicEndpoint);
        return pattern.matcher(requestEndpoint).find();
    }

    private boolean isOptions(HttpServletRequest request) {
        return HttpMethod.OPTIONS.name().equals(request.getMethod());
    }
}
