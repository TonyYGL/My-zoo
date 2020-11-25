package com.example.petproject;

import com.example.petproject.po.UserPo;
import com.example.petproject.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class PetProjectApplicationTests {
	@Autowired
	private UserRepository userRepository;

	@Test
	void contextLoads() {

	}

	@Test
	void userCrudTest() {
		UserPo userPo = new UserPo();
		userPo.setName("keng liu");
		userPo.setEmail("pox810324@gmail.com");
		userPo.setPassword("eerqwqwd");
		userPo.setGenderId(1);
		userPo.setAccount("KKKK");
		userPo.setCreateDate(LocalDate.now());
		userPo.setLevel(1);
		userRepository.save(userPo);
	}

}
