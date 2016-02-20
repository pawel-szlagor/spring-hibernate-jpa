package pl.spring.demo.service;

import java.util.List;

import pl.spring.demo.to.LibraryTo;

public interface LibraryService {

    List<LibraryTo> findAllLibraries();
    List<LibraryTo> findAllLibrariesByName(String name);
    List<LibraryTo> findAllLibrariesInCity(String cityName);
    List<LibraryTo> findLibrariesThatHaveBookByTitle(String bookTitle);
    LibraryTo saveOrUpdateLibrary(LibraryTo library);
}
