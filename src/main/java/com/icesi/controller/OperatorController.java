package com.icesi.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.icesi.model.Addresstype;
import com.icesi.model.BasicInfo;
import com.icesi.model.Businessentityaddress;
import com.icesi.model.Personphone;
import com.icesi.service.AddressServiceImp;
import com.icesi.service.AddressTypeServiceImp;
import com.icesi.service.BusinessEntityAddressServiceImp;
import com.icesi.service.BusinessEntityServiceImp;
import com.icesi.service.PersonServiceImp;
import com.icesi.service.PhoneNumbertypeServiceImp;
import com.icesi.service.PhoneServiceImp;

@Controller
public class OperatorController {
	
	BusinessEntityAddressServiceImp beaService;
	PhoneServiceImp phoneService;
	AddressTypeServiceImp adtService;
	BusinessEntityServiceImp beService;
	AddressServiceImp adService;
	PersonServiceImp personService;
	PhoneNumbertypeServiceImp pntService;
	
	
	@Autowired
	public OperatorController(BusinessEntityAddressServiceImp beaService,AddressTypeServiceImp adtService, BusinessEntityServiceImp beService, AddressServiceImp adService,
			PhoneServiceImp phoneService,PersonServiceImp personService,PhoneNumbertypeServiceImp pntService) {
		this.beaService = beaService;
		this.adtService = adtService;
		this.beService = beService;
		this.adService = adService;
		this.phoneService = phoneService;
		this.personService = personService;
		this.pntService = pntService;
	}
	
	@GetMapping("/user/business/")
    public String indexBusiness(Model model) {
		model.addAttribute("businessentityaddresses", beaService.findAll());
        return "users/indexBusiness";
    }
	
	@GetMapping("/user/business/delB/{id}")
	public String deleteBusiness(@PathVariable("id") Integer id, Model model) {
		Businessentityaddress bea = beaService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		
		beaService.delete(bea);
		model.addAttribute("businessentityaddresses", beaService.findAll());
		return "users/indexBusiness";
	}
	
	@GetMapping("/user/business/AddB")
	public String addBusiness(Model model) {
		model.addAttribute("businessentityaddress", new Businessentityaddress());
		model.addAttribute("businessentities", beService.findAll());
		model.addAttribute("addresstypes", adtService.findAll());
		model.addAttribute("addresses", adService.findAll());
		return "users/addBusiness";
	}
	
	
	@PostMapping("/user/business/AddB")
	public String saveBusiness(@Validated(BasicInfo.class) Businessentityaddress bea, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("businessentityaddresses", beaService.findAll());
			//return "users/addBusiness";
		}
		if (!action.equals("Cancelar")) {
			beaService.save(bea);
		}	
		return "redirect:/user/business/";
	}
	
	@GetMapping("/user/business/editB/{id}")
	public String showUpdateForm3(@PathVariable("id") Integer id, Model model) {
		Optional<Businessentityaddress> bea = beaService.findById(id);
		if (bea.isEmpty())
			throw new IllegalArgumentException("Invalid user Id:" + id);
		
		model.addAttribute("businessentityaddress", bea.get());
		model.addAttribute("businessentities", beService.findAll());
		model.addAttribute("addresstypes", adtService.findAll());
		model.addAttribute("addresses", adService.findAll());
		return "users/updateBusiness";
	}
	
	@PostMapping("/user/business/editB/{id}")
	public String updateBusiness(@PathVariable("id") Integer id,
			@RequestParam(value = "action", required = true) String action, @Validated(BasicInfo.class) Businessentityaddress bea, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("businessentityaddresses", beaService.findAll());
			model.addAttribute("businessentities", beService.findAll());
			model.addAttribute("addresstypes", adtService.findAll());
			model.addAttribute("addresses", adService.findAll());
			return "users/indexBusiness";
		}
		if (action != null && !action.equals("Cancel")) {
			bea.setId(id);
			beaService.update(bea);
			model.addAttribute("businessentityaddresses", beaService.findAll());
		}
		return "redirect:/user/business/";
	}

	
	//--------------------------------------
	
	@GetMapping("/user/phones/")
    public String indexPhones(Model model) {
		model.addAttribute("personphones", phoneService.findAll());
        return "users/indexPhone";
    }
	
	@GetMapping("/user/phones/delP/{id}")
	public String deletePhone(@PathVariable("id") Integer id, Model model) {
		Personphone phone = phoneService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		
		phoneService.delete(phone);
		model.addAttribute("personphones", phoneService.findAll());
		return "users/indexPhone";
	}
	
	@GetMapping("/user/phones/AddP")
	public String addPhone(Model model) {
		model.addAttribute("personphone", new Personphone());
		model.addAttribute("persons", personService.findAll());
		model.addAttribute("phonenumbertypes", pntService.findAll());
		return "users/addPhone";
	}
	
	@PostMapping("/user/phones/AddP")
	public String savePhone(@Validated(BasicInfo.class) Personphone phone, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("persons", personService.findAll());
			model.addAttribute("phonenumbertypes", pntService.findAll());
			return "users/addPhone";
		}
		if (!action.equals("Cancelar")) {
			phoneService.save(phone);
		}	
		return "redirect:/user/phones/";
	}
	
	@GetMapping("/user/phones/editP/{id}")
	public String showUpdateForm4(@PathVariable("id") Integer id, Model model) {
		Optional<Personphone> phone = phoneService.findById(id);
		if (phone.isEmpty())
			throw new IllegalArgumentException("Invalid user Id:" + id);
		
		model.addAttribute("personphone", phone.get());
		model.addAttribute("persons", personService.findAll());
		model.addAttribute("phonenumbertypes", pntService.findAll());
		return "users/updatePhone";
	}
	
	@PostMapping("/user/phones/editP/{id}")
	public String updatePhone(@PathVariable("id") Integer id,
			@RequestParam(value = "action", required = true) String action, @Validated(BasicInfo.class) Personphone phone, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("phones", phoneService.findAll());
			model.addAttribute("businessentities", beService.findAll());
			return "users/indexPhone";
		}
		if (action != null && !action.equals("Cancel")) {
			phone.setId(id);
			phoneService.update(phone);
			model.addAttribute("personphones", phoneService.findAll());
		}
		return "redirect:/user/phones/";
	}
	
	//--------------------------------------
	
	@GetMapping("/user/type/")
    public String indexType(Model model) {
		model.addAttribute("addresstypes", adtService.findAll());
        return "users/indexType";
    }
	
	@GetMapping("/user/type/delType/{id}")
	public String deleteType(@PathVariable("id") Integer id, Model model) {
		Addresstype adt = adtService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		
		adtService.delete(adt);
		model.addAttribute("addresstypes", adtService.findAll());
		return "users/indexType";
	}
	
	@GetMapping("/user/type/AddType")
	public String addType(Model model) {
		model.addAttribute("addresstype", new Addresstype());
		return "users/addType";
	}
	
	@PostMapping("/user/type/AddType")
	public String saveType(@Validated(BasicInfo.class) Addresstype adt, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if(bindingResult.hasErrors()) {
			//model.addAttribute("users", userService.findAll());
			return "users/addType";
		}
		if (!action.equals("Cancelar")) {
			adtService.save(adt);
		}	
		return "redirect:/user/type/";
	}
	
	@GetMapping("/user/type/editType/{id}")
	public String showUpdateForm2(@PathVariable("id") Integer id, Model model) {
		Optional<Addresstype> adt = adtService.findById(id);
		if (adt.isEmpty())
			throw new IllegalArgumentException("Invalid user Id:" + id);
		
		model.addAttribute("addresstype", adt.get());
		return "users/updateType";
	}
	
	@PostMapping("/user/type/editType/{id}")
	public String updateType(@PathVariable("id") Integer id,
			@RequestParam(value = "action", required = true) String action, @Validated(BasicInfo.class) Addresstype adt, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("addresstypes", adtService.findAll());
			
			return "users/indexType";
		}
		if (action != null && !action.equals("Cancel")) {
			adt.setAddresstypeid(id);
			adtService.update(adt);
			model.addAttribute("addresstypes", adtService.findAll());
		}
		return "redirect:/user/type/";
	}

}
