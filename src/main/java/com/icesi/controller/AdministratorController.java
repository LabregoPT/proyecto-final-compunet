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

import com.icesi.businessDelegate.AdminDelegate;
import com.icesi.model.Address;
import com.icesi.model.BasicInfo;
import com.icesi.model.BusinessEntity;
import com.icesi.model.Person;
import com.icesi.model.StateProvince;


@Controller
public class AdministratorController {
	
	private AdminDelegate ad;

	@Autowired
	
	public AdministratorController(AdminDelegate ad) {
		this.ad = ad;
	}
	
	//Persons
	//
	//----------------------------------------------------
	//
	
	@GetMapping("/admin/persons/")
    public String indexPerson(Model model) {
		model.addAttribute("persons", ad.getPersons());
        return "admin/indexPerson";
    }
	
	@GetMapping("/admin/entity/persons/{id}")
    public String indexPerson(@PathVariable("id") long id,Model model) {
		model.addAttribute("persons", ad.getAllPersonsByID(id));
        return "admin/indexPerson";
    }
	
	@GetMapping("/admin/persons/add")
	public String addPerson(Model model) {
		model.addAttribute("person", new Person());
		model.addAttribute("businessentities", ad.getAllEntities());
		return "admin/addPerson";
	}
	
	@PostMapping("/admin/persons/add")
	public String savePerson(@Validated(BasicInfo.class) Person person, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("businessentities", ad.getAllEntities());
			return "admin/addPerson";
		}
		if (!action.equals("Cancelar")) {
			
			ad.createPerson(person);
		}
		return "redirect:/admin/persons/";
	}
	
	@GetMapping("/admin/persons/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id,Model model) {
		Person person = ad.getPerson(id);
		if (person == null)
			throw new IllegalArgumentException("Invalid user Id:" + id);
		
		model.addAttribute("person", person);
		model.addAttribute("businessentities",ad.getAllEntities());
		return "admin/updatePerson";
	}
	
	
	@PostMapping("/admin/persons/edit/{id}")
	public String updatePerson(@PathVariable("id") long id, @RequestParam(value = "action", required = true) String action, @Validated(BasicInfo.class) Person person, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("persons", ad.getPersons());
			
			return "admin/indexPerson";
		}
		if (action != null && !action.equals("Cancel")) {
			person.setPersonid(id);
			ad.updatePerson(id, person);
			model.addAttribute("persons", ad.getPersons());
		}
		return "redirect:/admin/persons/";
	}
	
	//States & Provinces
	//
	//----------------------------------------------------
	//
	
	@GetMapping("/admin/address/")
    public String indexState(Model model) {
		model.addAttribute("stateprovinces", ad.getAllStates());
        return "admin/indexState";
    }
	
	@GetMapping("/admin/address/addState")
	public String addState(Model model) {
		model.addAttribute("stateprovince", new StateProvince());
		return "admin/addState";
	}
	
	@PostMapping("/admin/address/addState")
	public String saveState(@Validated(BasicInfo.class) StateProvince sp, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("stateprovinces", ad.getAllStates());
			return "admin/addState";
		}
		if (!action.equals("Cancelar")) {
			ad.createState(sp);
		}	
		return "redirect:/admin/address/";
	}
	
	@GetMapping("/admin/address/editState/{id}")
	public String showUpdateForm2(@PathVariable("id") Integer id, Model model) {
		StateProvince sp = ad.getState(id);
		if (sp == null)
			throw new IllegalArgumentException("Invalid user Id:" + id);
		
		model.addAttribute("stateprovince", sp);
		return "admin/updateState";
	}
	
	@PostMapping("/admin/address/editState/{id}")
	public String updateState(@PathVariable("id") Integer id,
			@RequestParam(value = "action", required = true) String action, @Validated(BasicInfo.class) StateProvince sp, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("stateprovinces", ad.getAllStates());
			
			return "admin/indexState";
		}
		if (action != null && !action.equals("Cancel")) {
			ad.updateState(id, sp);
			model.addAttribute("stateprovinces", ad.getAllStates());
		}
		return "redirect:/admin/address/";
	}
	
	//Addresses
	//
	//----------------------------------------------------
	//
	
	@GetMapping("/admin/states/")
    public String indexAddress(Model model) {
		model.addAttribute("addresses", ad.getAllAddresses());
        return "admin/indexAddress";
    }
	
	@GetMapping("/admin/address/addresses/{id}")
    public String indexAddress(@PathVariable("id") Integer id,Model model) {
		model.addAttribute("addresses", ad.getAllAddressesByID(id));
        return "admin/indexAddress";
    }
	
	@GetMapping("/admin/states/addAddress")
	public String addAddress(Model model) {
		model.addAttribute("address", new Address());
		model.addAttribute("stateprovinces", ad.getAllStates());
		return "admin/addAddress";
	}
	
	@PostMapping("/admin/states/addAddress")
	public String saveAddress(@Validated(BasicInfo.class) Address address, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("addresses", ad.getAllAddresses());
			return "admin/addAddress";
		}
		if (!action.equals("Cancelar")) {
			ad.createAddresses(address);
		}	
		return "redirect:/admin/states/";
	}
	
	@GetMapping("/admin/states/editAddress/{id}")
	public String showUpdateForm3(@PathVariable("id") Integer id, Model model) {
		Address a = ad.getAddress(id);
		
		
		model.addAttribute("address", a);
		model.addAttribute("stateprovinces", ad.getAllStates());
		return "admin/updateAddress";
	}
	
	@PostMapping("/admin/states/editAddress/{id}")
	public String updateAddress(@PathVariable("id") Integer id,
			@RequestParam(value = "action", required = true) String action, @Validated(BasicInfo.class) Address a, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("addresses", ad.getAllAddresses());
			
			return "admin/indexAddress";
		}
		if (action != null && !action.equals("Cancel")) {
			a.setAddressid(id);
			ad.updateAddresses(id, a);
			model.addAttribute("addresses", ad.getAllAddresses());
		}
		return "redirect:/admin/states/";
	}
	
	
	//Business Entities
	//
	//----------------------------------------------------
	//
	
	@GetMapping("/admin/entity/")
    public String indexEntity(Model model) {
		model.addAttribute("businessentities", ad.getAllEntities());
        return "admin/indexEntity";
    }
		
	@GetMapping("/admin/entity/addEntity")
	public String addEntity(Model model) {
		model.addAttribute("businessentity", new BusinessEntity());
		return "admin/addEntity";
	}
	
	
	@PostMapping("/admin/entity/addEntity")
	public String saveEntity(@Validated(BasicInfo.class) BusinessEntity be, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("businessentities", ad.getAllEntities());
			return "admin/addEntity";
		}
		if (!action.equals("Cancelar")) {
			ad.createEntity(be);
		}	
		return "redirect:/admin/entity/";
	}
	
	@GetMapping("/admin/entity/editEntity/{id}")
	public String showUpdateForm4(@PathVariable("id") Integer id, Model model) {
		
		if (Optional.of(ad.getEntity(id)).isEmpty())
			throw new IllegalArgumentException("Invalid user Id:" + id);
		
		model.addAttribute("businessentity", ad.getEntity(id));
		return "admin/updateEntity";
	}
	
	@PostMapping("/admin/entity/editEntity/{id}")
	public String updateEntity(@PathVariable("id") Integer id,
			@RequestParam(value = "action", required = true) String action, @Validated(BasicInfo.class) BusinessEntity be, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
		
			
			return "admin/updateEntity";
		}
		if (action != null && !action.equals("Cancel")) {
			ad.updateEntity(id, be);
			model.addAttribute("businessentities", ad.getAllEntities());
		}
		return "redirect:/admin/entity/";
	}
	
}
	