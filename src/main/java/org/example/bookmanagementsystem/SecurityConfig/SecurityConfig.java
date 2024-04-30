    package org.example.bookmanagementsystem.SecurityConfig;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.security.config.Customizer;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.core.userdetails.User;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.security.core.userdetails.UserDetailsService;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.security.provisioning.InMemoryUserDetailsManager;
    import org.springframework.security.web.SecurityFilterChain;


    @Configuration

    public class SecurityConfig {

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                    .csrf(csrf -> csrf.disable())
                    .cors(cors -> cors.disable())
                    .authorizeHttpRequests(req->req

                            .requestMatchers("/author/**","/book/**","/book/**","/category/**").hasRole("ADMIN").anyRequest().authenticated()

                    ).httpBasic(Customizer.withDefaults());
            return http.build();
        }
        @Bean
        public UserDetailsService userDetailsService() {

            UserDetails admin=User.builder()
                    .username("admin")
                    .password(passwordEncoder().encode(("admin123")))
                    .roles("ADMIN")
                    .build();
            return new InMemoryUserDetailsManager(admin);
        }
        @Bean
        public PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
        }





    }


