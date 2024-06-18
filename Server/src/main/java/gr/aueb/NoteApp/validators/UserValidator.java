package gr.aueb.NoteApp.validators;

import gr.aueb.NoteApp.dto.UserDto;
import gr.aueb.NoteApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    private final UserRepository userRepository;

    @Autowired
    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDto userDto = (UserDto) target;

        // Check uniqueness of username
        if (userRepository.existsByUsername(userDto.getUsername())) {
            errors.rejectValue("username", "Duplicate.userForm.username", "Username already exists");
        }

        // Check uniqueness of email
        if (userRepository.existsByEmail(userDto.getEmail())) {
            errors.rejectValue("email", "Duplicate.userForm.email", "Email already exists");
        }
    }
}
