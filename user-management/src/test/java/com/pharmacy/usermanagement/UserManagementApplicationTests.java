package com.pharmacy.usermanagement;

import com.pharmacy.usermanagement.dao.UserRepository;
import com.pharmacy.usermanagement.models.Users;
import com.pharmacy.usermanagement.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserManagementApplicationTests {

	@MockBean
	private UserRepository userRepository;
	@Autowired
	private UserService userService;

	@Test
	public void getUsersTest() {
		when(userRepository.findAll()).thenReturn(Stream
				.of(new Users("id1", "name1", "email1", "pass1", "as", "admin"))
				.collect(Collectors.toList()));
		assertEquals(1, userRepository.findAll().size());
	}

	@Test
	public void getUsersByIdTest() {
		String id = "id1";
		Users user = new Users("id1", "name1", "email1", "pass1", "as", "admin");
		when(userRepository.findByUserId(id)).thenReturn(user);
		assertEquals(id, userRepository.findByUserId(id).getUserId());

	}

	@Test
	public void createUsersTest() {
		Users user = new Users("id1", "name1", "email1", "pass1", "as", "admin");
		when(userRepository.save(user)).thenReturn(user);
		assertEquals(user, userRepository.save(user));
	}

	@Test
	public void deleteUsersTest() {
		Users user = new Users("id1", "name1", "email1", "pass1", "as", "admin");
		userRepository.delete(user);
		verify(userRepository, times(1)).delete(user);
	}

}
