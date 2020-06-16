package cl.icap.fullstack.service;

import java.util.List;

import cl.icap.fullstack.model.dto.ValoresDto;

public interface ValoreService {
		public ValoresDto insert(ValoresDto valoresDto);
		public ValoresDto get(int  tramo_id);
		public ValoresDto update(ValoresDto valoresDto);
		public void delete (ValoresDto valoresDto);
		public List<ValoresDto> list();

	}


