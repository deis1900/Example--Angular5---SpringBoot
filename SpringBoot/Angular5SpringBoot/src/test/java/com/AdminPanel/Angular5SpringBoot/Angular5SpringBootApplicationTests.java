package com.AdminPanel.Angular5SpringBoot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Angular5SpringBootApplicationTests {

	@Test
	public void contextLoads() {

	}
	@Test
	public void getPassword(String username) {
		System.out.println(new BCryptPasswordEncoder().encode(username));
	}

}
