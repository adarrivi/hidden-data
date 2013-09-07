package com.hidden.data.loader.service;

import com.hidden.data.db.model.Author;

public interface AuthorService {

	Author createAuthorIfDoesntExist(String authorName);

}
