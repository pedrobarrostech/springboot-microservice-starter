package com.pedroaugust8.service.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.pedroaugust8.common.models.User;

/**
 * @author Pedro Barros
 */
public interface UserDaoMongo extends MongoRepository<User, String> {
}
