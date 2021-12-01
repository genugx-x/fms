package kr.co.example.fms;

import kr.co.example.fms.model.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserCustomService implements UserDetailsService {

    private final UserRepository userRepository;
    //private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       return userRepository.findByUsername(username).map(u-> new User(u.getUsername(), u.getPassword(), u.getAuthorities())).orElseThrow(()->new UsernameNotFoundException(username +" 사용자가 없거나 비밀번호가 틀립니다."));

    }

   public void saveUser(kr.co.example.fms.model.User user) {
        //user.setAddress(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        user.setRoles(kr.co.example.fms.model.User.ROLE.USER);
        userRepository.save(user);
    }

}
