//package service.serviceImpl;
//
//import model.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import repository.UserRepository;
//
//import java.util.HashSet;
//import java.util.Objects;
//import java.util.Set;
//
//@Service
//public class UserDetailsSecurityServiceImpl implements UserDetailsService {
//
//
//    private final UserRepository userRepository;
//
//    @Autowired
//    public UserDetailsSecurityServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username).orElse(null);
//        if (Objects.isNull(user)) {
//            throw new UsernameNotFoundException(username);
//        }
//
//        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//        grantedAuthorities.add(new SimpleGrantedAuthority(user.getUserRole().name()));
//        return new org.springframework.security.core.userdetails.User(
//                user.getName(),
//                user.getPassword(),
//                grantedAuthorities);
//    }
//}
