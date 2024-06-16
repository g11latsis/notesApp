package gr.aueb.NoteApp.service;

import gr.aueb.NoteApp.dto.UserDto;
import gr.aueb.NoteApp.service.exceptions.EntityNotFoundException;

import java.util.List;

public interface IUserService {
    UserDto createUser(UserDto userDto) throws Exception;
    UserDto updateUser(UserDto userDto, Long userId) throws EntityNotFoundException;
    void deleteUser(Long id) throws EntityNotFoundException;
    UserDto getUser(Long id) throws EntityNotFoundException;
    List<UserDto> getAllUsers() throws EntityNotFoundException;
    UserDto login(String email, String password) throws EntityNotFoundException;

}
