package com.hidden.data.loader.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hidden.data.db.dao.AuthorDao;
import com.hidden.data.db.model.Author;
import com.hidden.data.loader.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	private AuthorDao authorDao;

	@Override
	public Author createAuthorIfDoesntExist(String authorName) {
		Author author = authorDao.findByName(authorName);
		if (author.isEmpty()) {
			author.setName(authorName);
			author = authorDao.save(author);
		}
		return author;
	}
}
