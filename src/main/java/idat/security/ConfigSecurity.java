package idat.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import idat.model.Usuario;
import idat.service.UsuarioService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ConfigSecurity extends WebSecurityConfigurerAdapter{

	@Autowired
	private UsuarioService user;
	
	@Override
	protected void configure(AuthenticationManagerBuilder arg0) throws Exception {
		
		//Los usuarios se deben crean en bd
		for (Usuario usuario : user.listar()) {
			arg0.inMemoryAuthentication()
			.withUser(usuario.getUsername())
			.password(encrip().encode(usuario.getPassword()))
			.roles(usuario.getRol());
		}
	}

	@Override
	protected void configure(HttpSecurity arg0) throws Exception {
		arg0
		.csrf().disable()
		.authorizeRequests()
		.antMatchers(HttpMethod.GET,"/alumnos/","/usuarios/").hasAnyRole("USER","ADMIN").and()
        .authorizeRequests()
		.antMatchers("/alumnos/*", "/usuarios/*").hasRole("ADMIN")//Aqui ponemos esto para indicar que todos los recurso con excepción a los indicados anteriormente se bloquean
		.anyRequest()
        .authenticated()
		.and()
		.httpBasic();
	}
	
	@Bean
	public PasswordEncoder encrip() {
		return new BCryptPasswordEncoder();
	}

}
