package com.ivoronline.springboot_dto_modelmapper_skip.controllers;

import com.ivoronline.springboot_dto_modelmapper_skip.DTOs.AuthorDTO;
import com.ivoronline.springboot_dto_modelmapper_skip.entities.Author;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.modelmapper.ModelMapper;

@Controller
public class MyController {

  @ResponseBody
  @RequestMapping("AddAuthor")
  public String addAuthor(@RequestBody AuthorDTO authorDTO) {

    //CUSTOMIZE MODEL MAPPER
    ModelMapper                authorDTOEntity = new ModelMapper();
    TypeMap<AuthorDTO, Author> typeMap         = authorDTOEntity.typeMap(AuthorDTO.class, Author.class);
    typeMap.addMappings(mapper -> {
      mapper.map(AuthorDTO::getAuthorName, Author::setName);
      mapper.skip(Author::setAge );
    });

    //CONVERT AUTHORDTO TO AUTHOR
    Author author = authorDTOEntity.map(authorDTO, Author.class);

    //RETURN SOMETHING
    return author.id + ": " + author.name + " is " + author.age + " years old";

  }

}












