package com.blog;

import ch.qos.logback.core.CoreConstants;
import com.blog.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    private UserRepository userRepository;
    
	@Test
	void contextLoads() {
	}
        
        @Test
        public void repoTest(){
            String className=this.userRepository.getClass().getName();
            String packName=this.userRepository.getClass().getPackageName();
            System.out.println(className);
            System.out.println(packName);
        }

}
