package com.hidden.data.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hidden.data.web.dto.UserDto;

@Controller
@RequestMapping("/userManagement")
public class UserManagementController {

	private static final String BASE_FOLDER = "userManagement/";

	private List<UserDto> allUsers = new ArrayList<UserDto>();

	@RequestMapping(method = RequestMethod.GET)
	public String dbManagementPage() {
		return BASE_FOLDER + "userManagement";
	}

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public @ResponseBody
	List<UserDto> list() {
		return allUsers;
	}

	@RequestMapping(value = "createUser", method = RequestMethod.POST)
	public void createUser(HttpServletResponse response) {
		response.getBufferSize();
		UserDto dto = new UserDto("user" + allUsers.size());
		allUsers.add(dto);
	}
}
