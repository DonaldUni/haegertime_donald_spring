package haegerConsulting.Haegertime_SpringBoot.services.User;

import haegerConsulting.Haegertime_SpringBoot.exceptions.UserExceptions.DuplicateUserException;
import haegerConsulting.Haegertime_SpringBoot.exceptions.UserExceptions.UserNotFoundExceptions;
import haegerConsulting.Haegertime_SpringBoot.exceptions.UserExceptions.UsernameEmptyException;
import haegerConsulting.Haegertime_SpringBoot.model.User;
import haegerConsulting.Haegertime_SpringBoot.model.enumerations.Power;
import haegerConsulting.Haegertime_SpringBoot.model.enumerations.Status;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AdminService extends UserService{



    public User create(User user) throws UsernameEmptyException, DuplicateUserException {

        if (user.getId() != null && userRepository.existsById(user.getId())){

            throw new DuplicateUserException();
        }
        if (user.getUserName().isEmpty()){

            throw new UsernameEmptyException();
        }

        return userRepository.save(user);

    }

    public void updateUser(User newUser) throws UserNotFoundExceptions{

        Optional<User> optional_user = userRepository.findById(newUser.getId());

        if (optional_user.isPresent()){

            userRepository.save(newUser);
        }else{

            throw new UserNotFoundExceptions();
        }
    }

    public void updateUsername(Long user_id, String newUsername) throws UserNotFoundExceptions{

        Optional<User> optional_user = userRepository.findById(user_id);

        if (optional_user.isPresent()){

            var user = optional_user.get();  // ich habe var Typ nur zum testen benutzt
            user.setUserName(newUsername);
            userRepository.save(user);
        }else{

            throw new UserNotFoundExceptions();
        }
    }

    public void updatePower(Long user_id, Power newPower) throws UserNotFoundExceptions{

        Optional<User> optional_user = userRepository.findById(user_id);

        if (optional_user.isPresent()){

            var user = optional_user.get();  // ich habe var Typ nur zum testen benutzt
            user.setPower(newPower);
            userRepository.save(user);
        }else{

            throw new UserNotFoundExceptions();
        }
    }

    public void updateStatus(Long user_id, Status newStatus) throws UserNotFoundExceptions{

        Optional<User> optional_user = userRepository.findById(user_id);

        if (optional_user.isPresent()){

            User user = optional_user.get();
            user.setStatus(newStatus);
            userRepository.save(user);
        }else{

            throw new UserNotFoundExceptions();
        }
    }

    public void deleteById(Long id) throws UserNotFoundExceptions {

        if (userRepository.existsById(id)){

            userRepository.deleteById(id);
        }else {

            throw new UserNotFoundExceptions();
        }
    }

    public void deleteByUsername(String username) throws UserNotFoundExceptions{

        if (userRepository.existsByUsername(username)){

            userRepository.deleteByUsername(username);
        }else {

            throw new UserNotFoundExceptions();
        }
    }

    public void deleteAll() {

        userRepository.deleteAll();
    }
}
