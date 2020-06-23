package cl.icap.fullstack.controller;


import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cl.icap.fullstack.model.dto.Agenda_ViajesDto;
import cl.icap.fullstack.service.Agenda_ViajesService;


/*llevar para mi codigo con security*/
@Controller 
@RequestMapping(value="/agenda_viajes")
public class Agenda_ViajesController {
	@Autowired
	Agenda_ViajesService agenda_ViajesService;
	
	
	@RequestMapping(value="/list")
	public @ResponseBody List<Agenda_ViajesDto> ajaxList(HttpServletRequest req, HttpServletResponse res) {
		return agenda_ViajesService.list();
	}

	@RequestMapping(value="/get")
	public @ResponseBody Agenda_ViajesDto ajaxGet(HttpServletRequest req, HttpServletResponse res) {

		return agenda_ViajesService.get(Integer.parseInt(req.getParameter("id_agenda"))); 
		
	}
	
	@RequestMapping(value="/insert")
	public @ResponseBody int ajaxInsert(HttpServletRequest req, HttpServletResponse res) {
		int rows=0;
		
		String requestData;
		try {
			requestData = req.getReader().lines().collect(Collectors.joining());
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			Agenda_ViajesDto agenda_ViajesDto = gson.fromJson(requestData, Agenda_ViajesDto.class);
			agenda_ViajesDto = agenda_ViajesService.insert(agenda_ViajesDto);
			
			if(agenda_ViajesDto != null) {
				
				rows=1;
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rows;
			
		}	
		
	
	@RequestMapping(value="/update")
	public @ResponseBody int ajaxUpdate(HttpServletRequest req, HttpServletResponse res) {
		int rows=0;
		
		String requestData;
		try {
			requestData = req.getReader().lines().collect(Collectors.joining());
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			Agenda_ViajesDto agenda_ViajesDto = gson.fromJson(requestData, Agenda_ViajesDto.class);
			agenda_ViajesDto = agenda_ViajesService.update(agenda_ViajesDto);
			
			if(agenda_ViajesDto == null) {
				rows=0;	
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
			return rows;
			
		}	
		
		
	@RequestMapping(value="/delete")
	public @ResponseBody int ajaxDelete(HttpServletRequest req, HttpServletResponse res) {
		int rows=0;
		
		try {
			Agenda_ViajesDto agenda_ViajesDto = new Agenda_ViajesDto();
			agenda_ViajesDto.setId_agenda(Integer.parseInt(req.getParameter("id_agenda")));
			agenda_ViajesService.delete(agenda_ViajesDto);
			rows=1;
		} catch (Exception e) {
			e.printStackTrace();
		}
			return rows;
			
		}	
		

}
