package com.task.permission.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * CustomUserDetails sınıfına ait nesnenin constructor injection ile UserDetailsService olarak
     * bağlanması
     */
    private final UserDetailsService userDetailsService;


    public WebSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    // Password encoder olarak hash'leme kullanılmadan düz metin kullanılmıştır.
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //AuthenticationManager'a userDetailsService ve password encoder verilmiştir.
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * permission sayfasına yalnızca EMPLOYEE girilebilir
         * main sayfasına EMPLOYEE ve MANAGER girebilir
         * h-2 console'a herhangi bir kişi girebilir.
         * frontend dosyalarının tutulduğu assets klasörüne herkes erişebilir
         * / index sayfasına herkes erişebilir
         */
        http.authorizeRequests()
                .antMatchers("/permission").hasRole("EMPLOYEE")
                .antMatchers("/main").hasAnyRole("MANAGER","EMPLOYEE")
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/assets/**").permitAll()

                .antMatchers("/**").permitAll()
                .and().formLogin()
                //login end point ile giriş işlemi yapılır
                .loginPage("/login")
                //başarılı olursa main sayfasına yönlendirilir
                .defaultSuccessUrl("/main")
                .permitAll()
        .and()
        .logout()
                //logout ile çıkış işlemi yapılır
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                //index sayfasına geri dönülür.
                .logoutSuccessUrl("/")
        .permitAll();
        //.and().httpBasic();
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
}
