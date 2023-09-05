package Controller;


import Repository.UserRepository;

import com.example.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/users")

public class UserController {
    @Autowired
    public UserRepository userRepository;
    //create a new user
    @PostMapping("/add")
    public User adduser (@RequestBody User user)
    {
        User user1=new User();
        user1.setUserName(user.getUserName());
        user1.setEmail(user.getEmail());
        userRepository.save(user1);
        return user1;




    }
    //view all users
    @GetMapping("/viewAll")
    public @ResponseBody  Iterable<User> getAllUsers()
    {
        return userRepository.findAll();
    }
    //updating an existing customer
    @PutMapping("edit/{id}")
    public String update (@RequestBody User updateUser,@PathVariable Integer id)
    {
        return  userRepository.findById(Long.valueOf(id)).map(user -> {
            user.setUserName(updateUser.getUserName());
            user.setEmail(updateUser.getEmail());
            userRepository.save(user);
            return "User details have been sccessfully updated";
        }).orElseGet(()->{
            return "No such user exist";
        });
    }
    //delete a customer
    @DeleteMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer id)
    {
        userRepository.deleteById(Long.valueOf(id));
        return "User has been successfully deleted !!!!";
    }


}
