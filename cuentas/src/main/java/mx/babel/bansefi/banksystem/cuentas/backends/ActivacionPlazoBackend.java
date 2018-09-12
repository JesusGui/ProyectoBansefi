package mx.babel.bansefi.banksystem.cuentas.backends;

import java.math.BigDecimal;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.DepositoIPFBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.cuentas.enums.TipoCargoEnum;
import mx.babel.bansefi.banksystem.cuentas.webservices.activacionplazo.ActivacionPlazoServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.activacionplazo.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.activacionplazo.Ejecutar.ITRACTVCNIMPSCNPAGTRN.STDAUTORIZAV.ARTRNMSJPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.activacionplazo.Ejecutar.ITRACTVCNIMPSCNPAGTRN.STDAUTORIZAV.STDTARGETTERMINALV;
import mx.babel.bansefi.banksystem.cuentas.webservices.activacionplazo.Ejecutar.ITRACTVCNIMPSCNPAGTRN.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.activacionplazo.Ejecutar.ITRACTVCNIMPSCNPAGTRN.TRACTVCNIMPSCNPAGEVT;
import mx.babel.bansefi.banksystem.cuentas.webservices.activacionplazo.Ejecutar.ITRACTVCNIMPSCNPAGTRN.TRACTVCNIMPSCNPAGEVT.CONDCUOTAV;
import mx.babel.bansefi.banksystem.cuentas.webservices.activacionplazo.Ejecutar.ITRACTVCNIMPSCNPAGTRN.TRACTVCNIMPSCNPAGEVT.CONDCUOTAV.KSESTRCTVLISTAV;
import mx.babel.bansefi.banksystem.cuentas.webservices.activacionplazo.Ejecutar.ITRACTVCNIMPSCNPAGTRN.TRACTVCNIMPSCNPAGEVT.CONDCUOTAV.KSESTRCTVRNGV;
import mx.babel.bansefi.banksystem.cuentas.webservices.activacionplazo.EjecutarResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author joseluis.pena
 *
 */
@Component
public class ActivacionPlazoBackend extends BackEndBean{

	private static final long serialVersionUID = 6564921993348101257L;

    @Autowired
    ServicioWebUtils servicioWebUtils;

	public Object ejecutarTRN(final DepositoIPFBean deposito, final Integer fechaVto){
		final Ejecutar.ITRACTVCNIMPSCNPAGTRN request = initPeticion(deposito, fechaVto);

		final EjecutarResult respuesta = ejecutarWS(request);

		try{
            super.verificaRespuesta(respuesta);
        }catch (final ControlableException ce){
            if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
                throw ce;
            }else{
                return null;
            }
        }

		return null;
	}

	/**
	 * Función que inicializa el objeto para realizar la petición al servicio web.
	 *
	 * @return Objeto a ser enviado al servicio web.
	 */
	private Ejecutar.ITRACTVCNIMPSCNPAGTRN initPeticion(final DepositoIPFBean deposito, final Integer fechaVto){
		final Ejecutar.ITRACTVCNIMPSCNPAGTRN peticion = new Ejecutar.ITRACTVCNIMPSCNPAGTRN();

		super.initialize(peticion);
		peticion.getSTDAUTORIZAV().setIMPAUT(new BigDecimal(0));
        peticion.getSTDAUTORIZAV().setIMPORTEAR(new BigDecimal(0));

        final ARTRNMSJPARMV artrnmsjparmv = new ARTRNMSJPARMV();
        super.initialize(artrnmsjparmv);
        for(int i = 0;i<10;i++){
            peticion.getSTDAUTORIZAV().getARTRNMSJPARMV().add(artrnmsjparmv);
        }

        final STDTARGETTERMINALV stdtargetterminalv = new STDTARGETTERMINALV();
        super.initialize(stdtargetterminalv);
        for(int i = 0;i<50;i++){
            peticion.getSTDAUTORIZAV().getSTDTARGETTERMINALV().add(stdtargetterminalv);
        }

		final TRACTVCNIMPSCNPAGEVT tractvcnimpscnpagevt = peticion.getTRACTVCNIMPSCNPAGEVT();
		tractvcnimpscnpagevt.getIPIMPSCNP().setCODNRBEEN(this.getEntidad());
        tractvcnimpscnpagevt.getIPIMPSCNP().setNUMSECAC(deposito.getNumAcuerdo());
        tractvcnimpscnpagevt.getIPIMPSCNP().setNUMSUBAC(deposito.getNumSubAc());


        tractvcnimpscnpagevt.setCODINTERNOUO(this.getSucursal());
        //TODO esto esta correcto?
        if(deposito.getTipoCargo() != null){
        	tractvcnimpscnpagevt.setCONTRIDA(deposito.getTipoCargo());
        }else{
        	tractvcnimpscnpagevt.setCONTRIDA(TipoCargoEnum.ACUERDO.getTipo());
        }
        tractvcnimpscnpagevt.getTRPETCNIMPSCNPAGEVTV().setFECHAVALOR(this.getFechaSistemaInteger());

        //TODO esto esta correcto?
        tractvcnimpscnpagevt.getTRPETCNIMPSCNPAGEVTV().setIMPAPNTE(deposito.getImporte());
        //TODO esto esta correcto?
        tractvcnimpscnpagevt.getTRPETCNIMPSCNPAGEVTV().setFECHAVTO(fechaVto);
        //TODO esto esta correcto?
        if(null!=deposito.getEstado()){
            tractvcnimpscnpagevt.getTRPETCNIMPSCNPAGEVTV().setCODECVIMPSCN(deposito.getEstado());
        }else{
            tractvcnimpscnpagevt.getTRPETCNIMPSCNPAGEVTV().setCODECVIMPSCN("A");
        }

        tractvcnimpscnpagevt.getTRPETCNIMPSCNPAGEVTV().setCODNUMRCOMONEDA(deposito.getMoneda());
        //TODO esto esta correcto?
        tractvcnimpscnpagevt.getPSVLIBRETAV().setSTDCHAR01("N");
        final KSESTRCTVLISTAV ksestrctvlistav = new KSESTRCTVLISTAV();
        super.initialize(ksestrctvlistav);
        final KSESTRCTVRNGV ksestrctvrngv = new KSESTRCTVRNGV();
        super.initialize(ksestrctvrngv);
        ksestrctvrngv.setRNGVALOR(new BigDecimal(0));


        for(int i = 0;i<10;i++){
            final CONDCUOTAV condcuotav = new CONDCUOTAV();
            super.initialize(condcuotav);
            condcuotav.getKSESTRCTVLISTAV().add(ksestrctvlistav);
            condcuotav.getKSESTRCTVRNGV().add(ksestrctvrngv);
            tractvcnimpscnpagevt.getCONDCUOTAV().add(condcuotav);
        }

        //TODO Hardoded
        peticion.getTRLBVALIDARLBEVTY().setIMPSDO(new BigDecimal(0));

        final STDTRNIPARMV stdtrniparmv = peticion.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_ACTVCN_IMPSCN_PAG_TRN);
        stdtrniparmv.setIDEMPLAUT(this.getUsuarioId());
        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());
		return peticion;
	}

    /**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRACTVCNIMPSCNPAGTRN contexto)
			throws NoControlableException{
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
			        ActivacionPlazoServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de activacion "
					+ "de plazo.", e);
		}
		return respuesta;
	}

}
