package eight.lab.service;

import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eight.lab.entity.User;
import eight.lab.repository.UserRepository;

@Service
public class CastomUserDetailService implements UserDetailsService{

    private static final Logger logger = LoggerFactory.getLogger(CastomUserDetailService.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        logger.info("Attempting to load user by email: {}", usernameOrEmail);
        
        User user = userRepository.findByEmail(usernameOrEmail);
        if(user != null) {
            logger.info("User found: {} with roles: {}", user.getEmail(), 
                user.getRoles().stream().map(role -> role.getName()).collect(Collectors.toList()));
            
            return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(),
                    user.getRoles().stream()
                        .map((role) -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toList()));
                        
        } else {
            logger.warn("User not found for email: {}", usernameOrEmail);
            throw new UsernameNotFoundException("Invalid email or password");
        }
    }
}
