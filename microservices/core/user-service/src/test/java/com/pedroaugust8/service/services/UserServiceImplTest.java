package com.pedroaugust8.service.services;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.pedroaugust8.common.models.User;
import com.pedroaugust8.common.exceptions.UserException;
import com.pedroaugust8.common.interfaces.UserRepository;

public class UserServiceImplTest {
	private UserServiceImpl target;
	private List<User> mockList;
	private UserRepository repository;
	private List<User> actualList;

	@SuppressWarnings("unchecked")
	@Before
	public void setup(){
		mockList = mock(List.class);
		repository = mock(UserRepository.class);
		
		when(repository.list()).thenReturn(mockList);
		target = new UserServiceImpl(repository);
	}
	
	@Test
	public void save() throws UserException{
		User User = new User();	
		target.save(User);	
		verify(repository).save(User);
	}
	
	@Test
	public void list(){
		actualList = target.list();
		Assert.assertEquals(mockList, actualList);
	}
	
	@Test
	public void delete() throws UserException{	
		target.delete("1");
		verify(repository).delete("1");	
	} 
	
	@Test
	public void get() {	
		target.get("1");
		verify(repository).get("1");	
	} 
	
	
}