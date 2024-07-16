package com.xlri.prometheus;

import com.xlri.prometheus.response.BaseResponse;
import com.xlri.prometheus.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public BaseResponse saveUser(User user) {
        BaseResponse response = new BaseResponse();
        if(isInvalidUserRequest(user)){
            response.setResultCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage("Username or Password is Missing!");
            return response;
        }

        if(isUserAlreadyExists(user)){
            response.setResultCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage("Username already exists! Please try another one!");
            return response;
        }
        try{
            userRepository.save(user);
        } catch (Exception e){
            response.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Something went wrong! Please try another one!");
            return response;
        }

        response.setResultCode(HttpStatus.OK.value());
        response.setMessage("User successfully saved!");
        return response;
    }

    public UserResponse findByUsername(User loginUser) {

        return validateAndReturnUser(loginUser);

    }

    public UserResponse validateAndReturnUser(User loginUser) {

        UserResponse response = new UserResponse();

        if(!StringUtils.hasText(loginUser.getUsername())){
            response.setResultCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage("Username is empty! Please try again!");
            return response;
        }

        if(!StringUtils.hasText(loginUser.getPassword())){
            response.setResultCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage("Password is empty! Please try again!");
            return response;
        }

        User dbUser = userRepository.findByUsername(loginUser.getUsername());

        if(null == dbUser){
            response.setResultCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage("Username does not exist! Please try another one or create a new one!");
            return response;
        }

        if(!loginUser.getPassword().equals(dbUser.getPassword())){
            response.setResultCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage("Password does not match! Please try again!");
            return response;
        }


        return convertUserResponse(dbUser);
    }

    private UserResponse convertUserResponse(User user){
        UserResponse response = new UserResponse();
        response.setUsername(user.getUsername());
        response.setPassword(user.getPassword());
        response.setResultCode(HttpStatus.OK.value());
        return response;
    }

    private boolean isUserAlreadyExists(User user) {
        return userRepository.findByUsername(user.getUsername()) != null;
    }

    private boolean isInvalidUserRequest(User user) {
        return !StringUtils.hasText(user.getUsername()) || !StringUtils.hasText(user.getPassword());
    }
}