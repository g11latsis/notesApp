package gr.aueb.NoteApp.service;

import gr.aueb.NoteApp.dto.UserDto;
import gr.aueb.NoteApp.model.User;
import gr.aueb.NoteApp.repositories.UserRepository;
import gr.aueb.NoteApp.service.exceptions.EntityNotFoundException;
import gr.aueb.NoteApp.validators.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements IUserService {


    private final UserRepository userRepository;
    private final UserValidator userValidator;



    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserValidator userValidator) {
        this.userRepository = userRepository;
        this.userValidator = userValidator;
    }


    @Override
    public UserDto createUser(UserDto userDto) throws Exception {
        try {
            validateUser(userDto);

            User user = DtoToUser(userDto);
            userRepository.save(user);
            log.info("User created: " + user);
            return UserToDto(user);
        } catch (Exception e) {
            log.error("Error while saving user", e);
            throw new Exception("Error while saving user");
        }
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long userId) throws EntityNotFoundException {
        try {
            validateUser(userDto);

            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException(User.class, userId));

            // Update user fields
            user.setUsername(userDto.getUsername());
            user.setEmail(userDto.getEmail());
            // Update other fields as needed

            User updatedUser = userRepository.save(user);
            log.info("User updated: " + updatedUser);
            return UserToDto(updatedUser);
        } catch (Exception e) {
            log.error("Error while updating user", e);
            throw new EntityNotFoundException(User.class, userId);
        }
    }


    @Override
    public void deleteUser(Long id) throws EntityNotFoundException {
        try {
            userRepository.deleteById(id);
            log.info("User deleted: " + id);
        } catch (Exception e) {
            log.error("Error while deleting user", e);
            throw new EntityNotFoundException(User.class, id);
        }
    }

    @Override
    public UserDto getUser(Long id) throws EntityNotFoundException {
        try {
            User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(User.class, id));
            log.info("User retrieved: " + user);
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
            log.info("Users retrieved: " + users);
            return users.stream().map(this::UserToDto).toList();
        } catch (Exception e) {
            log.error("Error while getting all users", e);
            throw new EntityNotFoundException(User.class, null);
        }
    }

    @Override
    public UserDto login(String email, String password) throws EntityNotFoundException {
        try {
            User user = userRepository.findByEmail(email);
            if (user.getPassword().equals(password)) {
                log.info("User logged in: " + user);
                return this.UserToDto(user);
            } else {
                throw new EntityNotFoundException(User.class, null);
            }
        } catch (Exception e) {
            log.error("Error while logging in", e);
            throw new EntityNotFoundException(User.class, null);
        }
    }

    public User DtoToUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword()); // Encode the password
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

    // Helper method to validate user input
    private void validateUser(UserDto userDto) {
        Errors errors = new BeanPropertyBindingResult(userDto, "userDto");
        ValidationUtils.invokeValidator(userValidator, userDto, errors);

        if (errors.hasErrors()) {
            throw new IllegalArgumentException("Invalid user data: " + errors.getAllErrors());
        }
    }
}
