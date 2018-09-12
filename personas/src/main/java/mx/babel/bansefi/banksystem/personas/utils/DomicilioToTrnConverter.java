package mx.babel.bansefi.banksystem.personas.utils;

import java.util.ArrayList;
import java.util.List;

import mx.babel.bansefi.banksystem.base.beans.domain.DomicilioTipoBean;
import mx.babel.bansefi.banksystem.base.webservices.altadomicilio.Ejecutar.ITRPEALTADOMICTRNI;
import mx.babel.bansefi.banksystem.base.webservices.modificadomicilio.Ejecutar.ITRPEMODIDOMICTRNI;

import org.springframework.stereotype.Component;

/**
 * Clase que genera un metodo para generar un arreglo que necesita la TRN de
 * alta y modificacion de Domicilios
 * 
 * @author manuel.nieto
 * 
 */
@Component
public class DomicilioToTrnConverter {
	
	/**
	 * Funcion que convierte el objeto de DomicilioBean a un arreglo en formato de TRN
	 * Adaptado para el alta de domiclio
	 * @param domicilioTipoBean
	 * @return List<ITRPEALTADOMICTRNI.TRPEALTADOMICEVTY.COMPDOMICV>
	 */
	public List<ITRPEALTADOMICTRNI.TRPEALTADOMICEVTY.COMPDOMICV> convertDomicilioToTrnAlta(DomicilioTipoBean domicilioBean) {
		List<ITRPEALTADOMICTRNI.TRPEALTADOMICEVTY.COMPDOMICV> arregloTRN = new ArrayList<ITRPEALTADOMICTRNI.TRPEALTADOMICEVTY.COMPDOMICV>();

		// Nombre de calle
		if (val(domicilioBean.getNombreCalle())) {
			ITRPEALTADOMICTRNI.TRPEALTADOMICEVTY.COMPDOMICV nombreCalle = new ITRPEALTADOMICTRNI.TRPEALTADOMICEVTY.COMPDOMICV();
			nombreCalle.setCODCOMPDOMIC("01");
			nombreCalle.setVALCOMPDOMICDRLEN(domicilioBean.getNombreCalle()
					.length());
			nombreCalle.setVALCOMPDOMICDR(domicilioBean.getNombreCalle());
			
			arregloTRN.add(nombreCalle);
		}

		// Numero Exterior
		if (val(domicilioBean.getNumeroExterior())) {
			ITRPEALTADOMICTRNI.TRPEALTADOMICEVTY.COMPDOMICV numeroExterior = new ITRPEALTADOMICTRNI.TRPEALTADOMICEVTY.COMPDOMICV();
			numeroExterior.setCODCOMPDOMIC("02");
			numeroExterior.setVALCOMPDOMICDRLEN(domicilioBean
					.getNumeroExterior().length());
			numeroExterior.setVALCOMPDOMICDR(domicilioBean.getNumeroExterior());
			
			arregloTRN.add(numeroExterior);
		}

		// Piso
		if (val(domicilioBean.getInterior())) {
			ITRPEALTADOMICTRNI.TRPEALTADOMICEVTY.COMPDOMICV piso = new ITRPEALTADOMICTRNI.TRPEALTADOMICEVTY.COMPDOMICV();
			piso.setCODCOMPDOMIC("03");
			piso.setVALCOMPDOMICDRLEN(domicilioBean.getInterior().length());
			piso.setVALCOMPDOMICDR(domicilioBean.getInterior());
			
			arregloTRN.add(piso);
		}

		// Entrada
		if (val(domicilioBean.getEntrada())) {
			ITRPEALTADOMICTRNI.TRPEALTADOMICEVTY.COMPDOMICV entrada = new ITRPEALTADOMICTRNI.TRPEALTADOMICEVTY.COMPDOMICV();
			entrada.setCODCOMPDOMIC("04");
			entrada.setVALCOMPDOMICDRLEN(domicilioBean.getEntrada().length());
			entrada.setVALCOMPDOMICDR(domicilioBean.getEntrada());
			
			arregloTRN.add(entrada);
		}

		// Departamento
		if (val(domicilioBean.getDepartamento())) {
			ITRPEALTADOMICTRNI.TRPEALTADOMICEVTY.COMPDOMICV departamento = new ITRPEALTADOMICTRNI.TRPEALTADOMICEVTY.COMPDOMICV();
			departamento.setCODCOMPDOMIC("05");
			departamento.setVALCOMPDOMICDRLEN(domicilioBean.getDepartamento()
					.length());
			departamento.setVALCOMPDOMICDR(domicilioBean.getDepartamento());
			
			arregloTRN.add(departamento);
		}

		// Casa
		if (val(domicilioBean.getCasa())) {
			ITRPEALTADOMICTRNI.TRPEALTADOMICEVTY.COMPDOMICV casa = new ITRPEALTADOMICTRNI.TRPEALTADOMICEVTY.COMPDOMICV();
			casa.setCODCOMPDOMIC("06");
			casa.setVALCOMPDOMICDRLEN(domicilioBean.getCasa().length());
			casa.setVALCOMPDOMICDR(domicilioBean.getCasa());
			
			arregloTRN.add(casa);
		}

		// Bloque
		if (val(domicilioBean.getBloque())) {
			ITRPEALTADOMICTRNI.TRPEALTADOMICEVTY.COMPDOMICV bloque = new ITRPEALTADOMICTRNI.TRPEALTADOMICEVTY.COMPDOMICV();
			bloque.setCODCOMPDOMIC("07");
			bloque.setVALCOMPDOMICDRLEN(domicilioBean.getBloque().length());
			bloque.setVALCOMPDOMICDR(domicilioBean.getBloque());
			
			arregloTRN.add(bloque);
		}

		// Fase
		if (val(domicilioBean.getFase())) {
			ITRPEALTADOMICTRNI.TRPEALTADOMICEVTY.COMPDOMICV fase = new ITRPEALTADOMICTRNI.TRPEALTADOMICEVTY.COMPDOMICV();
			fase.setCODCOMPDOMIC("08");
			fase.setVALCOMPDOMICDRLEN(domicilioBean.getFase().length());
			fase.setVALCOMPDOMICDR(domicilioBean.getFase());
			
			arregloTRN.add(fase);
		}

		// Edificio
		if (val(domicilioBean.getEdificio())) {
			ITRPEALTADOMICTRNI.TRPEALTADOMICEVTY.COMPDOMICV edificio = new ITRPEALTADOMICTRNI.TRPEALTADOMICEVTY.COMPDOMICV();
			edificio.setCODCOMPDOMIC("09");
			edificio.setVALCOMPDOMICDRLEN(domicilioBean.getEdificio().length());
			edificio.setVALCOMPDOMICDR(domicilioBean.getEdificio());
			
			arregloTRN.add(edificio);
		}

		// Colonia
		if (val(domicilioBean.getColonia())) {
			ITRPEALTADOMICTRNI.TRPEALTADOMICEVTY.COMPDOMICV colonia = new ITRPEALTADOMICTRNI.TRPEALTADOMICEVTY.COMPDOMICV();
			colonia.setCODCOMPDOMIC("10");
			colonia.setVALCOMPDOMICDRLEN(domicilioBean.getColonia().length());
			colonia.setVALCOMPDOMICDR(domicilioBean.getColonia());
			
			arregloTRN.add(colonia);
		}

		// Unidad Habitacional
		if (val(domicilioBean.getUnidadHabitacional())) {
			ITRPEALTADOMICTRNI.TRPEALTADOMICEVTY.COMPDOMICV unidadH = new ITRPEALTADOMICTRNI.TRPEALTADOMICEVTY.COMPDOMICV();
			unidadH.setCODCOMPDOMIC("11");
			unidadH.setVALCOMPDOMICDRLEN(domicilioBean.getUnidadHabitacional()
					.length());
			unidadH.setVALCOMPDOMICDR(domicilioBean.getUnidadHabitacional());
			
			arregloTRN.add(unidadH);
		}

		// Manzana
		if (val(domicilioBean.getManzana())) {
			ITRPEALTADOMICTRNI.TRPEALTADOMICEVTY.COMPDOMICV manzana = new ITRPEALTADOMICTRNI.TRPEALTADOMICEVTY.COMPDOMICV();
			manzana.setCODCOMPDOMIC("12");
			manzana.setVALCOMPDOMICDRLEN(domicilioBean.getManzana().length());
			manzana.setVALCOMPDOMICDR(domicilioBean.getManzana());
			
			arregloTRN.add(manzana);
		}
		
		//Lote
		if(val(domicilioBean.getLote())){
			ITRPEALTADOMICTRNI.TRPEALTADOMICEVTY.COMPDOMICV lote = new ITRPEALTADOMICTRNI.TRPEALTADOMICEVTY.COMPDOMICV();
			lote.setCODCOMPDOMIC("13");
			lote.setVALCOMPDOMICDRLEN(domicilioBean.getLote().length());
			lote.setVALCOMPDOMICDR(domicilioBean.getLote());
			
			arregloTRN.add(lote);
		}
		
		//Otros datos
		if(val(domicilioBean.getOtrosDatos())){
			ITRPEALTADOMICTRNI.TRPEALTADOMICEVTY.COMPDOMICV otros = new ITRPEALTADOMICTRNI.TRPEALTADOMICEVTY.COMPDOMICV();
			otros.setCODCOMPDOMIC("99");
			otros.setVALCOMPDOMICDRLEN(domicilioBean.getOtrosDatos().length());
			otros.setVALCOMPDOMICDR(domicilioBean.getOtrosDatos());
			
			arregloTRN.add(otros);
		}
		
		return arregloTRN;

	}
	
	/**
	 * Funcion que convierte el objeto de DomicilioBean a un arreglo en formato de TRN
	 * Adaptado para la modificacion de domiclio
	 * @param domicilioBean
	 * @return
	 */
	public List<ITRPEMODIDOMICTRNI.TRPEMODIDOMICEVTY.COMPDOMICV> convertDomicilioToTrnModificacion(DomicilioTipoBean domicilioBean) {
		List<ITRPEMODIDOMICTRNI.TRPEMODIDOMICEVTY.COMPDOMICV> arregloTRN = new ArrayList<ITRPEMODIDOMICTRNI.TRPEMODIDOMICEVTY.COMPDOMICV>();

		// Nombre de calle
		if (val(domicilioBean.getNombreCalle())) {
			ITRPEMODIDOMICTRNI.TRPEMODIDOMICEVTY.COMPDOMICV nombreCalle = new ITRPEMODIDOMICTRNI.TRPEMODIDOMICEVTY.COMPDOMICV();
			nombreCalle.setCODCOMPDOMIC("01");
			nombreCalle.setVALCOMPDOMICDRLEN(domicilioBean.getNombreCalle()
					.length());
			nombreCalle.setVALCOMPDOMICDR(domicilioBean.getNombreCalle());
			
			arregloTRN.add(nombreCalle);
		}

		// Numero Exterior
		if (val(domicilioBean.getNumeroExterior())) {
			ITRPEMODIDOMICTRNI.TRPEMODIDOMICEVTY.COMPDOMICV numeroExterior = new ITRPEMODIDOMICTRNI.TRPEMODIDOMICEVTY.COMPDOMICV();
			numeroExterior.setCODCOMPDOMIC("02");
			numeroExterior.setVALCOMPDOMICDRLEN(domicilioBean
					.getNumeroExterior().length());
			numeroExterior.setVALCOMPDOMICDR(domicilioBean.getNumeroExterior());
			
			arregloTRN.add(numeroExterior);
		}

		// Piso
		if (val(domicilioBean.getInterior())) {
			ITRPEMODIDOMICTRNI.TRPEMODIDOMICEVTY.COMPDOMICV piso = new ITRPEMODIDOMICTRNI.TRPEMODIDOMICEVTY.COMPDOMICV();
			piso.setCODCOMPDOMIC("03");
			piso.setVALCOMPDOMICDRLEN(domicilioBean.getInterior().length());
			piso.setVALCOMPDOMICDR(domicilioBean.getInterior());
			
			arregloTRN.add(piso);
		}

		// Entrada
		if (val(domicilioBean.getEntrada())) {
			ITRPEMODIDOMICTRNI.TRPEMODIDOMICEVTY.COMPDOMICV entrada = new ITRPEMODIDOMICTRNI.TRPEMODIDOMICEVTY.COMPDOMICV();
			entrada.setCODCOMPDOMIC("04");
			entrada.setVALCOMPDOMICDRLEN(domicilioBean.getEntrada().length());
			entrada.setVALCOMPDOMICDR(domicilioBean.getEntrada());
			
			arregloTRN.add(entrada);
		}

		// Departamento
		if (val(domicilioBean.getDepartamento())) {
			ITRPEMODIDOMICTRNI.TRPEMODIDOMICEVTY.COMPDOMICV departamento = new ITRPEMODIDOMICTRNI.TRPEMODIDOMICEVTY.COMPDOMICV();
			departamento.setCODCOMPDOMIC("05");
			departamento.setVALCOMPDOMICDRLEN(domicilioBean.getDepartamento()
					.length());
			departamento.setVALCOMPDOMICDR(domicilioBean.getDepartamento());
			
			arregloTRN.add(departamento);
		}

		// Casa
		if (val(domicilioBean.getCasa())) {
			ITRPEMODIDOMICTRNI.TRPEMODIDOMICEVTY.COMPDOMICV casa = new ITRPEMODIDOMICTRNI.TRPEMODIDOMICEVTY.COMPDOMICV();
			casa.setCODCOMPDOMIC("06");
			casa.setVALCOMPDOMICDRLEN(domicilioBean.getCasa().length());
			casa.setVALCOMPDOMICDR(domicilioBean.getCasa());
			
			arregloTRN.add(casa);
		}

		// Bloque
		if (val(domicilioBean.getBloque())) {
			ITRPEMODIDOMICTRNI.TRPEMODIDOMICEVTY.COMPDOMICV bloque = new ITRPEMODIDOMICTRNI.TRPEMODIDOMICEVTY.COMPDOMICV();
			bloque.setCODCOMPDOMIC("07");
			bloque.setVALCOMPDOMICDRLEN(domicilioBean.getBloque().length());
			bloque.setVALCOMPDOMICDR(domicilioBean.getBloque());
			
			arregloTRN.add(bloque);
		}

		// Fase
		if (val(domicilioBean.getFase())) {
			ITRPEMODIDOMICTRNI.TRPEMODIDOMICEVTY.COMPDOMICV fase = new ITRPEMODIDOMICTRNI.TRPEMODIDOMICEVTY.COMPDOMICV();
			fase.setCODCOMPDOMIC("08");
			fase.setVALCOMPDOMICDRLEN(domicilioBean.getFase().length());
			fase.setVALCOMPDOMICDR(domicilioBean.getFase());
			
			arregloTRN.add(fase);
		}

		// Edificio
		if (val(domicilioBean.getEdificio())) {
			ITRPEMODIDOMICTRNI.TRPEMODIDOMICEVTY.COMPDOMICV edificio = new ITRPEMODIDOMICTRNI.TRPEMODIDOMICEVTY.COMPDOMICV();
			edificio.setCODCOMPDOMIC("09");
			edificio.setVALCOMPDOMICDRLEN(domicilioBean.getEdificio().length());
			edificio.setVALCOMPDOMICDR(domicilioBean.getEdificio());
			
			arregloTRN.add(edificio);
		}

		// Colonia
		if (val(domicilioBean.getColonia())) {
			ITRPEMODIDOMICTRNI.TRPEMODIDOMICEVTY.COMPDOMICV colonia = new ITRPEMODIDOMICTRNI.TRPEMODIDOMICEVTY.COMPDOMICV();
			colonia.setCODCOMPDOMIC("10");
			colonia.setVALCOMPDOMICDRLEN(domicilioBean.getColonia().length());
			colonia.setVALCOMPDOMICDR(domicilioBean.getColonia());
			
			arregloTRN.add(colonia);
		}

		// Unidad Habitacional
		if (val(domicilioBean.getUnidadHabitacional())) {
			ITRPEMODIDOMICTRNI.TRPEMODIDOMICEVTY.COMPDOMICV unidadH = new ITRPEMODIDOMICTRNI.TRPEMODIDOMICEVTY.COMPDOMICV();
			unidadH.setCODCOMPDOMIC("11");
			unidadH.setVALCOMPDOMICDRLEN(domicilioBean.getUnidadHabitacional()
					.length());
			unidadH.setVALCOMPDOMICDR(domicilioBean.getUnidadHabitacional());
			
			arregloTRN.add(unidadH);
		}

		// Manzana
		if (val(domicilioBean.getManzana())) {
			ITRPEMODIDOMICTRNI.TRPEMODIDOMICEVTY.COMPDOMICV manzana = new ITRPEMODIDOMICTRNI.TRPEMODIDOMICEVTY.COMPDOMICV();
			manzana.setCODCOMPDOMIC("12");
			manzana.setVALCOMPDOMICDRLEN(domicilioBean.getManzana().length());
			manzana.setVALCOMPDOMICDR(domicilioBean.getManzana());
			
			arregloTRN.add(manzana);
		}
		
		//Lote
		if(val(domicilioBean.getLote())){
			ITRPEMODIDOMICTRNI.TRPEMODIDOMICEVTY.COMPDOMICV lote = new ITRPEMODIDOMICTRNI.TRPEMODIDOMICEVTY.COMPDOMICV();
			lote.setCODCOMPDOMIC("13");
			lote.setVALCOMPDOMICDRLEN(domicilioBean.getLote().length());
			lote.setVALCOMPDOMICDR(domicilioBean.getLote());
			
			arregloTRN.add(lote);
		}
		
		//Otros datos
		if(val(domicilioBean.getOtrosDatos())){
			ITRPEMODIDOMICTRNI.TRPEMODIDOMICEVTY.COMPDOMICV otros = new ITRPEMODIDOMICTRNI.TRPEMODIDOMICEVTY.COMPDOMICV();
			otros.setCODCOMPDOMIC("99");
			otros.setVALCOMPDOMICDRLEN(domicilioBean.getOtrosDatos().length());
			otros.setVALCOMPDOMICDR(domicilioBean.getOtrosDatos());
			
			arregloTRN.add(otros);
		}
		
		return arregloTRN;

	}
	/**
	 * Valida el campo string
	 * 
	 * @param campo
	 * @return
	 */
	public boolean val(String campo) {
		if (campo != null && !("").equals(campo)) {
			return true;
		} else {
			return false;
		}
	}
}
