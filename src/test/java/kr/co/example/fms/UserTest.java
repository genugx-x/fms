package kr.co.example.fms;


import kr.co.example.fms.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.DecimalFormat;
import java.text.NumberFormat;


@SpringBootTest
public class UserTest {

    @Autowired
    private UserCustomService userCustomService;

    /*
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
     */


//    @Test
//    public void 유저생성(){
//        User user = new User();
//        user.setUsername("user");
//        user.setPassword(bCryptPasswordEncoder.encode("!qsol1235"));
//        user.setFullName("퀀텀솔루션");
//
//        userCustomService.saveUser(user);
//
//        User user1 = new User();
//        user1.setUsername("keti");
//        user1.setPassword(bCryptPasswordEncoder.encode("keti1234"));
//        user1.setFullName("한국전자기술연구원");
//        userCustomService.saveUser(user1);
//
//       /*
//        for(int i=1; i<10; i++){
//
//            String numberFormat = new DecimalFormat("00").format(i);
//
//            User user1 = new User();
//            user1.setUsername("fishery" + numberFormat);
//            user1.setPassword("!qsol1235");
//            user1.setFullName("어업인" + numberFormat);
//            user1.setDeviceId(i);
//            userCustomService.saveUser(user1);
//        }*/
//
//
//
//
//
//
//    }
}
