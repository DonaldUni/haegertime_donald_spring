package haegerConsulting.Haegertime_SpringBoot.services.User;

import haegerConsulting.Haegertime_SpringBoot.exceptions.UserExceptions.UserNotFoundExceptions;
import haegerConsulting.Haegertime_SpringBoot.model.User;
import haegerConsulting.Haegertime_SpringBoot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Iterable<User> getAllUser(){

        return userRepository.findAll();
    }

    public User getMyAccountData(long id) throws UserNotFoundExceptions{

        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()){

            return user.get();
        }else{

            throw new UserNotFoundExceptions();
        }
    }

    public void updateMyAccountData(long id, String oldPassword, String newPassword) throws UserNotFoundExceptions{

        Optional<User> optional_user = userRepository.findById(id);

        if (optional_user.isPresent()){

              User user = optional_user.get();
              if (user.getPassword().equals(oldPassword)){

                  user.setPassword(newPassword);
                  userRepository.save(user);
              }
        }else{

            throw new UserNotFoundExceptions();
        }
    }







}
