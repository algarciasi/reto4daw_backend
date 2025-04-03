package empleos.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class DataUserConfiguration {

	//Ejemplo sin seguridad con permitAll
	/*@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()) // Desactiva CSRF para pruebas
				.authorizeHttpRequests(auth -> auth.anyRequest().permitAll()) // Permite todas las peticiones
				.formLogin(login -> login.disable()) // Desactiva formulario de login
				.httpBasic(basic -> basic.disable()); // Desactiva autenticación básica
		
		return http.build();
	}*/
	
	
	// Ejemplo con seguridad basica
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Solo en desarrollo; en prod deberías activarlo con protección adecuada
            .authorizeHttpRequests(auth -> auth
                // Endpoints públicos
                .requestMatchers("/usuarios/registro", "/swagger-ui/**", "/v3/api-docs/**").permitAll()

                // Empresa
                .requestMatchers("/vacantes/**", "/solicitudes/todas", "/empresas/editar/**").hasRole("EMPRESA")

                // Admin
                .requestMatchers("/empresas/**", "/categorias/**", "/usuarios/admin/**", "/usuarios/baja/**").hasRole("ADMON")

                // Cliente
                .requestMatchers("/solicitudes/**", "/usuarios/seguimiento/**", "/usuarios/cancelar/**").hasRole("CLIENTE")

                // Todo lo demás requiere login
                .anyRequest().authenticated()
            )
            .formLogin(form -> form.permitAll()) // Login web
            .httpBasic(Customizer.withDefaults()); // Auth básico Postman

        return http.build();
    }

    // Codificador de contraseñas
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Configura el AuthenticationManager que Spring necesita para autenticación
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


}