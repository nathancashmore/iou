package uk.co.staticvoid.iou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uk.co.staticvoid.iou.model.User;

@Controller
public class UserController {

    @RequestMapping("/user")
    public @ResponseBody User userDetails(@RequestParam(value="name", required=false, defaultValue="Stranger") String name) {
        return new User(name);
    }
}
