package com.example.demo.security;
import com.example.demo.mapper.UserDetailMapper;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Autowired
    UserRepo userRepo;

    @Autowired
    UserDetailMapper userDetailMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Logger log= LoggerFactory.getLogger(this.getClass());
        MyUserDetails userDetails=new MyUserDetails();
        User user=  userRepo.findUserByUsername(username);
        if (user!=null){
            userDetails.setUser(user);
        }
        else {
            throw new RuntimeException("Provided user does not exist");
        }
        return  userDetails;
    }
}