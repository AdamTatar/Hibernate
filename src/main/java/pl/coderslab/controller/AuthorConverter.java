package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import pl.coderslab.dao.AuthorDao;
import pl.coderslab.model.Author;

public class AuthorConverter implements Converter<String, Author> {

	@Autowired
	private AuthorDao authorDao;
	
	@Override
	public Author convert(String input) {
		Author author = authorDao.find(Long.parseLong(input));
		return author;
	}

}
