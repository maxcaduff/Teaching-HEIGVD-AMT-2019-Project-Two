package io.swagger.api;

import io.swagger.UserRepository;
import io.swagger.model.Body;
import io.swagger.model.PublicUser;
import io.swagger.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.utils.JWTHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.util.Random;

@Controller
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-12-25T21:37:52.105Z[GMT]")
public class UserApiController implements UserApi {

    private final UserRepository userRepository;

    private final MailSender mailSender;

    private static final Random random = new Random();

    private static final Logger log = LoggerFactory.getLogger(UserApiController.class);

//    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;


    @Autowired
    public UserApiController( HttpServletRequest req, MailSender ms, UserRepository repo) {
        Assert.noNullElements(new Object[] {ms,repo}, "mailsender and repository must not be null.");
//        this.objectMapper = om;
        this.request = req;
        this.mailSender = ms;
        this.userRepository = repo;

    }

    @Override
    public ResponseEntity<Void> addUser(@ApiParam(value = "New user to add", required=true )  @Valid @RequestBody PublicUser body) {

        // check if email already exists
        if ( userRepository.findOne(body.getEmail()) != null) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }

        String newPass = sendRandomPassword(body.getEmail(), false);

        if (!newPass.equals("")) {
            User u = new User();
            u.setPublicData(body);
            u.setPassword(newPass);
            userRepository.save(u);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Void> deleteUser() {
        String token = request.getHeader("Authorization");

        String verifiedMail = JWTHandler.verifyJWT(token);
        if (verifiedMail.equals("")) // token invalid
            return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
        if ( !userRepository.exists(verifiedMail))
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        // deletes logged user
        userRepository.delete(verifiedMail);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", null);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

    }

    @Override
    public ResponseEntity<PublicUser> getUser(@NotNull @ApiParam(value = "Email of the user to fetch", required = true) @Valid @RequestParam(value = "email", required = true) String email) {
        String accept = request.getHeader("Accept");
        if (accept != null && (accept.contains("application/json") || accept.contains("*/*"))) {
            User user = userRepository.findOne(email);
            if (user != null) {
                return new ResponseEntity<PublicUser>(user.getPublicData(), HttpStatus.OK);
            }
            return new ResponseEntity<PublicUser>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<PublicUser>( HttpStatus.NOT_ACCEPTABLE);
    }

    @Override
    public ResponseEntity<Void> login(@ApiParam(value = "User credentials", required=true ) @Valid @RequestBody Body body) {

        // password can be null in body for other methods so it is checked here.
        if ( body.getPassword() == null)
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        // checking couple mail / passw in db
        boolean exists = userRepository.existsUserByEmailAndPasswordEquals(body.getEmail(), body.getPassword());
        if (!exists)
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

        String jwt = JWTHandler.createJWT(body.getEmail());

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", jwt);

        return new ResponseEntity<Void>(headers, HttpStatus.NO_CONTENT);

    }

    @Override
    public ResponseEntity<Void> updateUser(@ApiParam(value = "New values for user", required=true ) @Valid @RequestBody User body) {
        String token = request.getHeader("Authorization");

        String verifiedMail = JWTHandler.verifyJWT(token);
        if (verifiedMail.equals(""))
            return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
        // this check prevents modifying another user or own email.
        if (body.getEmail().equals(verifiedMail)) {
            userRepository.save(body);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);

    }

    @Override
    public ResponseEntity<Void> reset(@ApiParam(value = "User credentials", required=true )  @Valid @RequestBody Body body) {

        User user = userRepository.findOne(body.getEmail());
        if ( user == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        String newPass = sendRandomPassword(body.getEmail(), true);

        if (!newPass.equals("")) {
            user.setPassword(newPass);
            userRepository.save(user);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
    }



    public String sendRandomPassword (String to, boolean reset) {
        // creates a random pass & sends it to user mail. It is hard to check if the message was well delivered,
        // an upgrade would be to store a timestamp and delete the user if he didn't connect in x hours.
        String randomPass = "";

        for (int i = 0; i < 10; i++) {
            int type = random.nextInt(3); // type: 0-> generate number, 1 -> uppercase, 2-> lowercase
            int asciiChar = random.nextInt (type == 0 ? 10 : 26) + (type == 0 ? '0' : (type == 1? 'A' : 'a'));
            randomPass += (char)asciiChar;
        };

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject("Your password for AMT Auth API" + (reset ? " was reset." : "."));
            message.setText("Your new random generated password is: "+ randomPass + "\nYou can change it once connected.");
            //System.out.println("message sent: "+message);
            mailSender.send(message);
            return randomPass;
        }
        catch (MailSendException e) {
            return "";
        }

    }

    // missing get parameter
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Void> handleException(MissingServletRequestParameterException ex) {
        return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
    }
    // missing or invalid parameters
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Void> handleException(MethodArgumentNotValidException ex) {
        return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
    }
    // missing body
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Void> handleException(HttpMessageNotReadableException ex) {
        return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
    }

}
