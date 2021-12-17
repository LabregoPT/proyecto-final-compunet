package com.icesi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.icesi.businessDelegate.*;
import com.icesi.model.*;

@Controller
public class OperatorController {

	
	private UserDelegate du;
	private AdminDelegate da;
	
	
	@Autowired
	public OperatorController(UserDelegate du, AdminDelegate da) {
		this.du = du;
		this.da = da;
	}
	
	@GetMapping("/user/business/")
    public String indexBusiness(Model model) {
		model.addAttribute("businessentityaddresses", du.getAllAdresses());
        return "users/indexBusiness";
    }
	

	@GetMapping("/user/business/AddB")
	public String addBusiness(Model model) {
		model.addAttribute("businessentityaddress", new BusinessEntityAddress());
		model.addAttribute("businessentities", da.getAllEntities());
		model.addAttribute("addresstypes", du.getAllAddressTypes());
		model.addAttribute("addresses", da.getAllAddresses());
		return "users/addBusiness";
	}
	
	
	@PostMapping("/user/business/AddB")
	public String saveBusiness(@Validated(BasicInfo.class) BusinessEntityAddress bea, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("businessentityaddresses", du.getAllAdresses());
			return "users/indexBusiness";
		}
		if (!action.equals("Cancelar")) {
			du.createEntityAddress(bea);
		}
		return "redirect:/user/business/";
	}
	
	@GetMapping("/user/business/editB/{id}")
	public String showUpdateForm3(@PathVariable("id") Integer id, Model model) {
		BusinessEntityAddress bea  = du.getEntity(id);
		if (bea == null)
			throw new IllegalArgumentException("Invalid user Id:" + id);
		
		model.addAttribute("businessentityaddress", bea);
		model.addAttribute("businessentities", da.getAllEntities());
		model.addAttribute("addresstypes", du.getAllAddressTypes());
		model.addAttribute("addresses", da.getAllAddresses());
		return "users/updateBusiness";
	}
	
	@PostMapping("/user/business/editB/{id}")
	public String updateBusiness(@PathVariable("id") Integer id,
			@RequestParam(value = "action", required = true) String action, @Validated(BasicInfo.class) BusinessEntityAddress bea, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("businessentityaddresses", du.getAllAdresses());
			return "users/indexBusiness";
		}
		if (action != null && !action.equals("Cancel")) {
			bea.setId(id);
			du.updateEntity(id, bea);
			model.addAttribute("businessentityaddresses", du.getAllAdresses());
		}
		return "redirect:/user/business/";
	}

	
	//--------------------------------------
	
	@GetMapping("/user/phones/")
    public String indexPhones(Model model) {
		model.addAttribute("phones", du.getAllPhones());
        return "users/indexPhone";
    }
	

	
	@GetMapping("/user/phones/AddP")
	public String addPhone(Model model) {
		model.addAttribute("phone", new Phone());
		model.addAttribute("persons", da.getPersons());
		model.addAttribute("phonenumbertypes", du.getAllPhoneTypes());
		return "users/addPhone";
	}
	
	@PostMapping("/user/phones/AddP")
	public String savePhone(@Validated(BasicInfo.class) Phone phone, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("persons", da.getPersons());
			model.addAttribute("phonenumbertypes", du.getAllPhoneTypes());
			return "users/addPhone";
		}
		if (!action.equals("Cancelar")) {
			du.createPhone(phone);
		}	
		return "redirect:/user/phones/";
	}
	
	@GetMapping("/user/phones/editP/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		Phone phone = du.getPhone(id);
		if (phone == null)
			throw new IllegalArgumentException("Invalid user Id:" + id);
		
		model.addAttribute("phone", phone);
		model.addAttribute("persons", da.getPersons());
		model.addAttribute("phonenumbertypes", du.getAllPhoneTypes());
		return "users/updatePhone";
	}
	
	@PostMapping("/user/phones/editP/{id}")
	public String updatePhone(@PathVariable("id") Integer id,
			@RequestParam(value = "action", required = true) String action, @Validated(BasicInfo.class) Phone phone, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("persons", da.getPersons());
			model.addAttribute("phonenumbertypes", du.getAllPhoneTypes());
			return "users/updatePhone";
		}
		if (action != null && !action.equals("Cancel")) {
			phone.setId(id);
			du.updatePhone(id, phone);
			model.addAttribute("personphones", du.getAllPhones());
		}
		return "redirect:/user/phones/";
	}
	


}
