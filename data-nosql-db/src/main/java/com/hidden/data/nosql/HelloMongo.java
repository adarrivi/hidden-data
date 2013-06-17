package com.hidden.data.nosql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import com.hidden.data.nosql.model.Account;
import com.hidden.data.nosql.model.Person;

@Repository
public class HelloMongo {

	@Autowired
	MongoOperations mongoOperations;

	public void run() {

		if (mongoOperations.collectionExists(Person.class)) {
			mongoOperations.dropCollection(Person.class);
		}

		mongoOperations.createCollection(Person.class);

		Person p = new Person("John", 39);
		Account a = new Account("1234-59873-893-1", Account.Type.SAVINGS,
				123.45D);
		p.getAccounts().add(a);

		mongoOperations.insert(p);

		List<Person> results = mongoOperations.findAll(Person.class);
		System.out.println("Results: " + results);
	}

}
