package com.hb.betterreadsapp.userbook;

import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserBookController {

	@PostMapping("/addUserBook")
	public String addBookForUser(@RequestBody MultiValueMap<String, String> formData) {
		return "";
	}
	
}
