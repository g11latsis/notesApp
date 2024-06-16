package gr.aueb.NoteApp.service;

import gr.aueb.NoteApp.dto.UserDto;
import gr.aueb.NoteApp.model.User;
import gr.aueb.NoteApp.repositories.UserRepository;
import gr.aueb.NoteApp.service.exceptions.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements IUserService {


    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto createUser(UserDto userDto) throws Exception {
        try {
            User user = DtoToUser(userDto);
            userRepository.save(user);
            return UserToDto(user);
        } catch (Exception e) {
            log.error("Error while saving user", e);
            throw new Exception("Error while saving user");
        }
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long userId) throws EntityNotFoundException {
        try {
            User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException(User.class, userId));
            return this.UserToDto(user);
        } catch (Exception e) {
            log.error("Error while updating user", e);
            throw new EntityNotFoundException(User.class, userId);
        }
    }

    @Override
    public void deleteUser(Long id) throws EntityNotFoundException {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            log.error("Error while deleting user", e);
            throw new EntityNotFoundException(User.class, id);
        }
    }

    @Override
    public UserDto getUser(Long id) throws EntityNotFoundException {
        try {
            User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(User.class, id));
            return this.UserToDto(user);
        } catch (Exception e) {
            log.error("Error while getting user", e);
            throw new EntityNotFoundException(User.class, id);
        }
    }

    @Override
    public List<UserDto> getAllUsers() throws EntityNotFoundException {
try {
            List<User> users = userRepository.findAll();
            return users.stream().map(this::UserToDto).toList();
        } catch (Exception e) {
            log.error("Error while getting all users", e);
            throw new EntityNotFoundException(User.class, null);
        }
    }

    @Override
    public UserDto login(String email, String password) throws EntityNotFoundException {
        try {
            User user= this.userRepository.findByEmailAndPassword(email, password);
            return this.UserToDto(user);
        } catch (Exception e) {
            log.error("Error while logging in", e);
            throw new EntityNotFoundException(User.class, null);
        }
    }


    public User DtoToUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return user;
    }

    public UserDto UserToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        return userDto;
    }
}
