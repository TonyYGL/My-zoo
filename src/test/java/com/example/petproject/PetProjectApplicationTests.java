package com.example.petproject;

import com.example.petproject.po.UserPo;
import com.example.petproject.repository.UserRepository;
import com.example.petproject.service.AdoptService;
import com.example.petproject.service.ImageFileService;
import com.example.petproject.service.LineLoginService;
import com.example.petproject.vo.ImageVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class PetProjectApplicationTests {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ImageFileService imageFileService;
	@Autowired
	private LineLoginService lineLoginService;
	@Autowired
	private AdoptService adoptService;

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

	@Test
	void findImages() {
		List<ImageVo> imageVoList = imageFileService.findImageByUserId(1);
		System.out.println("imageVoList = " + imageVoList);
	}

	@Test
	void lineAuth() {
		lineLoginService.lineAuth();
	}

	@Test
	void getAdoptData() {
		adoptService.getAdoptList(0);
	}

}
