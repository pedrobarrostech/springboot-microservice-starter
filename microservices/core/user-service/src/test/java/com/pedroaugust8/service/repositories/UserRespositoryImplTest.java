package com.pedroaugust8.service.repositories;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.pedroaugust8.common.models.User;
import com.pedroaugust8.service.repositories.UserDaoMongo;
import com.pedroaugust8.service.repositories.UserRepositoryImpl;
import com.pedroaugust8.common.exceptions.UserException;

public class UserRespositoryImplTest {
	private UserRepositoryImpl target;
	private List<User> mockList;
	private UserDaoMongo dao;
	private List<User> actualList;

	@SuppressWarnings("unchecked")
	@Before
	public void setup(){
		mockList = mock(List.class);
		dao = mock(UserDaoMongo.class);
		
		when(dao.findAll()).thenReturn(mockList);
		target = new UserRepositoryImpl(dao);
	}
	
	@Test
	public void save() throws UserException{
		User User = new User();	
		target.save(User);	
		verify(dao).save(User);
	}
	
	@Test
	public void list(){
		actualList = target.list();
		Assert.assertEquals(mockList, actualList);
	}
	
	@Test
	public void delete() throws UserException{	
		target.delete("1");
		verify(dao).delete("1");	
	} 
	
	@Test
	public void get() {	
		target.get("1");
		verify(dao).findOne("1");	
	} 
	
	
}