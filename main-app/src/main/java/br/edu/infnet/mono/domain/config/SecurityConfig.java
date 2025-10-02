package br.edu.infnet.mono.domain.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    private static final String URI_CONDOMINIO = "/condominio";
    private static final String URI_MORADIA = "/moradia";
    private static final String URI_USUARIO = "/usuario";
    private static final String URI_USUARIO_CONDOMINIO = URI_USUARIO + "-condominio";
    private static final String URI_USUARIO_SISTEMA = URI_USUARIO + "-sistema";
    private static final String URI_VISITANTE = "/visitante";
    private static final String URI_CORRESPONDENCIA = "/correspondencia";
    private static final String ROLE_ADMIN = "ADMIN";
    private static final String ROLE_USER = "USER";
    private static final String ADMIN_PASSWORD = "admin123";
    private static final String USER_PASSWORD = "user123";

    private static final DateTimeFormatter formatarData = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(
                        authorize -> authorize.requestMatchers("/h2-console/**").permitAll()
                                .requestMatchers(HttpMethod.POST, URI_CONDOMINIO).hasRole(ROLE_ADMIN)
                                .requestMatchers(HttpMethod.PUT, URI_CONDOMINIO + "/**").hasRole(ROLE_ADMIN)
                                .requestMatchers(HttpMethod.DELETE, URI_CONDOMINIO + "/**").hasRole(ROLE_ADMIN)
                                .requestMatchers(HttpMethod.GET, URI_CONDOMINIO).hasAnyRole(ROLE_ADMIN, ROLE_USER)

                                .requestMatchers(HttpMethod.POST, URI_MORADIA).hasRole(ROLE_ADMIN)
                                .requestMatchers(HttpMethod.PUT, URI_MORADIA + "/**").hasRole(ROLE_ADMIN)
                                .requestMatchers(HttpMethod.DELETE, URI_MORADIA + "/**").hasRole(ROLE_ADMIN)
                                .requestMatchers(HttpMethod.GET, URI_MORADIA).hasAnyRole(ROLE_ADMIN, ROLE_USER)

                                .requestMatchers(HttpMethod.POST, URI_USUARIO).hasRole(ROLE_ADMIN)
                                .requestMatchers(HttpMethod.PUT, URI_USUARIO + "/**").hasRole(ROLE_ADMIN)
                                .requestMatchers(HttpMethod.DELETE, URI_USUARIO + "/**").hasRole(ROLE_ADMIN)
                                .requestMatchers(HttpMethod.GET, URI_USUARIO).hasAnyRole(ROLE_ADMIN, ROLE_USER)

                                .requestMatchers(HttpMethod.POST, URI_USUARIO_CONDOMINIO).hasRole(ROLE_ADMIN)
                                .requestMatchers(HttpMethod.PUT, URI_USUARIO_CONDOMINIO + "/**").hasRole(ROLE_ADMIN)
                                .requestMatchers(HttpMethod.DELETE, URI_USUARIO_CONDOMINIO + "/**").hasRole(ROLE_ADMIN)
                                .requestMatchers(HttpMethod.GET, URI_USUARIO_CONDOMINIO).hasAnyRole(ROLE_ADMIN, ROLE_USER)

                                .requestMatchers(HttpMethod.POST, URI_USUARIO_SISTEMA).hasRole(ROLE_ADMIN)
                                .requestMatchers(HttpMethod.PUT, URI_USUARIO_SISTEMA + "/**").hasRole(ROLE_ADMIN)
                                .requestMatchers(HttpMethod.DELETE, URI_USUARIO_SISTEMA + "/**").hasRole(ROLE_ADMIN)
                                .requestMatchers(HttpMethod.GET, URI_USUARIO_SISTEMA).hasAnyRole(ROLE_ADMIN, ROLE_USER)

                                .requestMatchers(HttpMethod.POST, URI_VISITANTE).hasAnyRole(ROLE_ADMIN, ROLE_USER)
                                .requestMatchers(HttpMethod.PUT, URI_VISITANTE + "/**").hasAnyRole(ROLE_ADMIN, ROLE_USER)
                                .requestMatchers(HttpMethod.DELETE, URI_VISITANTE + "/**").hasRole(ROLE_ADMIN)
                                .requestMatchers(HttpMethod.GET, URI_VISITANTE).hasAnyRole(ROLE_ADMIN, ROLE_USER)

                                .requestMatchers(HttpMethod.POST, URI_CORRESPONDENCIA).hasAnyRole(ROLE_ADMIN, ROLE_USER)
                                .requestMatchers(HttpMethod.PUT, URI_CORRESPONDENCIA + "/**").hasAnyRole(ROLE_ADMIN, ROLE_USER)
                                .requestMatchers(HttpMethod.DELETE, URI_CORRESPONDENCIA + "/**").hasRole(ROLE_ADMIN)
                                .requestMatchers(HttpMethod.GET, URI_CORRESPONDENCIA).hasAnyRole(ROLE_ADMIN, ROLE_USER)

                                .anyRequest().authenticated())
                .httpBasic(withDefaults())
                .exceptionHandling(exception -> exception
                        .accessDeniedHandler(accessDeniedHandler())
                        .authenticationEntryPoint(authenticationEntryPoint()));

        http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));

        return http.build();
    }
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("UTF-8");

            String timestamp = LocalDateTime.now().format(formatarData);

            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", HttpStatus.UNAUTHORIZED.value());
            errorResponse.put("timestamp", timestamp);

            String mensagem = "AUTENTICAÇÃO NECESSÁRIA! Você precisa estar autenticado. Por favor, forneça credenciais válidas.";

            errorResponse.put("mensagem", mensagem);

            response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
        };
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("UTF-8");

            String method = request.getMethod();
            String uri = request.getRequestURI();
            String timestamp = LocalDateTime.now().format(formatarData);

            Map<String, Object> errorResponse = criarResponseErro(timestamp, method, uri);

            response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
        };
    }

    private static Map<String, Object> criarResponseErro(String timestamp, String method, String uri) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("status", HttpStatus.FORBIDDEN.value());
        errorResponse.put("timestamp", timestamp);

        String mensagem;
        if ("POST".equals(method) &&
                (uri.contains(URI_CONDOMINIO) ||
                        uri.contains(URI_USUARIO_CONDOMINIO) ||
                        uri.contains(URI_USUARIO_SISTEMA) ||
                        uri.contains(URI_MORADIA) ||
                        uri.contains(URI_USUARIO))) {
            mensagem = "ACESSO NEGADO! Você não tem permissão. Apenas usuários com perfil de ADMINISTRADOR podem executar esta ação.";
        } else {
            mensagem = String.format("ACESSO NEGADO! Você não tem permissão para acessar o recurso '%s' via %s. " +
                    "Apenas usuários com perfil de ADMINISTRADOR podem executar esta ação.", uri, method);
        }

        errorResponse.put("mensagem", mensagem);
        return errorResponse;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.builder().username("admin").password(passwordEncoder().encode(ADMIN_PASSWORD)).roles(ROLE_ADMIN).build();
        UserDetails user = User.builder().username("user").password(passwordEncoder().encode(USER_PASSWORD)).roles(ROLE_USER).build();

        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
