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

import com.icesi.model.Address;
import com.icesi.model.BasicInfo;
import com.icesi.model.Businessentity;
import com.icesi.model.Person;
import com.icesi.model.Stateprovince;
import com.icesi.service.AddressServiceImp;
import com.icesi.service.StateProvinceServiceImp;
import com.icesi.serviceDAO.BusinessEntityServiceImpDAO;
import com.icesi.serviceDAO.PersonServiceImpDAO;


@Controller
public class AdministratorController {
	
	PersonServiceImpDAO personService;
	BusinessEntityServiceImpDAO beService;
	
	
	StateProvinceServiceImp spService;
	AddressServiceImp adService;
	
	@Autowired
	public AdministratorController(PersonServiceImpDAO personService, BusinessEntityServiceImpDAO beService,AddressServiceImp adService,StateProvinceServiceImp spService) {
		this.personService = personService;
		this.beService = beService;
		this.adService = adService;
		this.spService = spService;
	}
	
	@GetMapping("/admin/persons/")
    public String indexPerson(Model model) {
		model.addAttribute("persons", personService.findAll());
        return "admin/indexPerson";
    }
	
	@GetMapping("/admin/persons/del/{id}")
	public String deletePerson(@PathVariable("id") Integer id, Model model) {
		Person person = personService.findById(id);
		if (person == null)
			throw new IllegalArgumentException("Invalid user Id:" + id);
		
		
		personService.delete(person);
		model.addAttribute("persons", personService.findAll());
		return "admin/indexPerson";
	}
	
	@GetMapping("/admin/persons/add")
	public String addPerson(Model model) {
		model.addAttribute("person", new Person());
		model.addAttribute("businessentities", beService.findAll());
		return "admin/addPerson";
	}
	
	@PostMapping("/admin/persons/add")
	public String savePerson(@Validated(BasicInfo.class) Person person, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("businessentities", beService.findAll());
			return "admin/addPerson";
		}
		if (!action.equals("Cancelar")) {
			
			personService.save(person);
		}
		return "redirect:/admin/persons/";
	}
	
	@GetMapping("/admin/persons/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id,Model model) {
		Person person = personService.findById(id);
		if (person == null)
			throw new IllegalArgumentException("Invalid user Id:" + id);
		
		model.addAttribute("person", person);
		model.addAttribute("businessentities", beService.findAll());
		return "admin/updatePerson";
	}
	
	
	@PostMapping("/admin/persons/edit/{id}")
	public String updatePerson(@PathVariable("id") long id, @RequestParam(value = "action", required = true) String action, @Validated(BasicInfo.class) Person person, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("persons", personService.findAll());
			
			return "admin/indexPerson";
		}
		if (action != null && !action.equals("Cancel")) {
			person.setPersonid(id);
			personService.update(person);
			model.addAttribute("persons", personService.findAll());
		}
		return "redirect:/admin/persons/";
	}
	
	//-------------------------------------------------
	
	@GetMapping("/admin/address/")
    public String indexState(Model model) {
		model.addAttribute("stateprovinces", spService.findAll());
        return "admin/indexState";
    }
	
	@GetMapping("/admin/address/delState/{id}")
	public String deleteState(@PathVariable("id") Integer id, Model model) {
		Stateprovince sp = spService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		spService.delete(sp);
		model.addAttribute("stateprovinces", spService.findAll());
		return "admin/indexState";
	}
	
	@GetMapping("/admin/address/addState")
	public String addState(Model model) {
		model.addAttribute("stateprovince", new Stateprovince());
		return "admin/addState";
	}
	
	@PostMapping("/admin/address/addState")
	public String saveState(@Validated(BasicInfo.class) Stateprovince sp, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("stateprovinces", spService.findAll());
			return "admin/addState";
		}
		if (!action.equals("Cancelar")) {
			spService.save(sp);
		}	
		return "redirect:/admin/address/";
	}
	
	@GetMapping("/admin/address/editState/{id}")
	public String showUpdateForm2(@PathVariable("id") Integer id, Model model) {
		Optional<Stateprovince> sp = spService.findById(id);
		if (sp.isEmpty())
			throw new IllegalArgumentException("Invalid user Id:" + id);
		
		model.addAttribute("stateprovince", sp.get());
		return "admin/updateState";
	}
	
	@PostMapping("/admin/address/editState/{id}")
	public String updateState(@PathVariable("id") Integer id,
			@RequestParam(value = "action", required = true) String action, @Validated(BasicInfo.class) Stateprovince sp, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("stateprovinces", spService.findAll());
			
			return "admin/indexState";
		}
		if (action != null && !action.equals("Cancel")) {
			spService.update(sp,id);
			model.addAttribute("stateprovinces", spService.findAll());
		}
		return "redirect:/admin/address/";
	}
	
	//-------------------------------------------------
	
	
	@GetMapping("/admin/states/")
    public String indexAddress(Model model) {
		model.addAttribute("addresses", adService.findAll());
        return "admin/indexAddress";
    }
	
	@GetMapping("/admin/states/delAddress/{id}")
	public String deleteAddress(@PathVariable("id") Integer id, Model model) {
		Address ad = adService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		adService.delete(ad);
		model.addAttribute("addresses", adService.findAll());
		return "admin/indexAddress";
	}
	
	@GetMapping("/admin/states/addAddress")
	public String addAddress(Model model) {
		model.addAttribute("address", new Address());
		model.addAttribute("stateprovinces", spService.findAll());
		return "admin/addAddress";
	}
	
	@PostMapping("/admin/states/addAddress")
	public String saveAddress(@Validated(BasicInfo.class) Address address, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("addresses", adService.findAll());
			return "admin/addAddress";
		}
		if (!action.equals("Cancelar")) {
			adService.save(address);
		}	
		return "redirect:/admin/states/";
	}
	
	@GetMapping("/admin/states/editAddress/{id}")
	public String showUpdateForm3(@PathVariable("id") Integer id, Model model) {
		Optional<Address> ad = adService.findById(id);
		if (ad.isEmpty())
			throw new IllegalArgumentException("Invalid user Id:" + id);
		
		model.addAttribute("address", ad.get());
		model.addAttribute("stateprovinces", spService.findAll());
		return "admin/updateAddress";
	}
	
	@PostMapping("/admin/states/editAddress/{id}")
	public String updateAddress(@PathVariable("id") Integer id,
			@RequestParam(value = "action", required = true) String action, @Validated(BasicInfo.class) Address ad, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("addresses", adService.findAll());
			
			return "admin/indexAddress";
		}
		if (action != null && !action.equals("Cancel")) {
			ad.setAddressid(id);
			adService.update(ad);
			model.addAttribute("addresses", adService.findAll());
		}
		return "redirect:/admin/states/";
	}
	
	
	//-------------------------------------------------
	
	@GetMapping("/admin/entity/")
    public String indexEntity(Model model) {
		model.addAttribute("businessentities", beService.findAll());
        return "admin/indexEntity";
    }
	
	
	@GetMapping("/admin/entity/delEntity/{id}")
	public String deleteEntity(@PathVariable("id") Integer id, Model model) {
		Businessentity be = beService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		beService.delete(be);
		model.addAttribute("businessentities", beService.findAll());
		return "admin/indexEntity";
	}
	
	@GetMapping("/admin/entity/addEntity")
	public String addEntity(Model model) {
		model.addAttribute("businessentity", new Businessentity());
		return "admin/addEntity";
	}
	
	
	@PostMapping("/admin/entity/addEntity")
	public String saveEntity(@Validated(BasicInfo.class) Businessentity be, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("businessentities", beService.findAll());
			return "admin/addEntity";
		}
		if (!action.equals("Cancelar")) {
			beService.save(be);
		}	
		return "redirect:/admin/entity/";
	}
	
	@GetMapping("/admin/entity/editEntity/{id}")
	public String showUpdateForm4(@PathVariable("id") Integer id, Model model) {
		Optional<Businessentity> be = beService.findById(id);
		if (be.isEmpty())
			throw new IllegalArgumentException("Invalid user Id:" + id);
		
		model.addAttribute("businessentity", be.get());
		return "admin/updateEntity";
	}
	
	@PostMapping("/admin/entity/editEntity/{id}")
	public String updateEntity(@PathVariable("id") Integer id,
			@RequestParam(value = "action", required = true) String action, @Validated(BasicInfo.class) Businessentity be, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("businessentities", beService.findAll());
			
			return "admin/indexEntity";
		}
		if (action != null && !action.equals("Cancel")) {
			beService.update(be,id);
			model.addAttribute("businessentities", beService.findAll());
		}
		return "redirect:/admin/entity/";
	}
	
	
	//----------------------------------------------------
	
	@GetMapping("/admin/entity/persons/{id}")
    public String indexPerson(@PathVariable("id") long id,Model model) {
		model.addAttribute("persons", personService.findAllById(id));
        return "admin/indexPerson";
    }
	
	@GetMapping("/admin/address/addresses/{id}")
    public String indexAddress(@PathVariable("id") Integer id,Model model) {
		model.addAttribute("addresses", adService.findAllById(id));
        return "admin/indexAddress";
    }
    
}
