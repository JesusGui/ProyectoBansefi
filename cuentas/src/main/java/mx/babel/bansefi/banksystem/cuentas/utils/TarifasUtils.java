package mx.babel.bansefi.banksystem.cuentas.utils;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.bansefi.banksystem.base.backends.busquedas.BusquedaIdInternaBackEnd;
import mx.babel.bansefi.banksystem.base.beans.domain.ClienteBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta.RelacionadoBean;
import mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta.TipoRelacionPersonaCuenta;
import mx.babel.bansefi.banksystem.base.beans.productossimples.CondicionBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.ProductoSimpleBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionRangoBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionValorRangoBean;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaNombreCondicionesBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaProductosSimplesBackend;
import mx.babel.bansefi.banksystem.cuentas.beans.TarifaBean;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TarifasUtils {

    private static final Charset ENCODING = StandardCharsets.UTF_8;

    @Autowired
    BusquedaIdInternaBackEnd busquedaIdInternaBackEnd;

    //Carga los productos simples relacionados una la tarifa
    @Autowired
    ConsultaProductosSimplesBackend consultaProductosSimplesBackend;

    //Consulta los nombres de las condiciones a partir del producto Simple
    @Autowired
    ConsultaNombreCondicionesBackend consultaNombreCondicionesBackend;


	public static List<TarifaBean> filtrarPrestamoCredito(final List<TarifaBean> listado){
		final List<TarifaBean> resultado = new ArrayList<TarifaBean>();
		if(null != listado){
			for(final TarifaBean tarifa : listado){
				if(TarifasUtils.isPrestamoCredito(tarifa)){
					resultado.add(tarifa);
				}
			}
		}
		return resultado;
	}

	public static List<TarifaBean> filtrarPlazo(final List<TarifaBean> listado){
		final List<TarifaBean> resultado = new ArrayList<TarifaBean>();
		if(null != listado){
			for(final TarifaBean tarifa : listado){
				if(TarifasUtils.esPlazo(tarifa)){
					resultado.add(tarifa);
				}
			}
		}
		return resultado;
	}

	public static List<TarifaBean> filtrarVista(final List<TarifaBean> listado){
		final List<TarifaBean> resultado = new ArrayList<TarifaBean>();
		if(null != listado){
			for(final TarifaBean tarifa : listado){
				if(TarifasUtils.isVista(tarifa)){
					resultado.add(tarifa);
				}
			}
		}
		return resultado;
	}

	public static List<TarifaBean> filtrarOtrosProductos(final List<TarifaBean> listado){
		final List<TarifaBean> resultado = new ArrayList<TarifaBean>();
		if(null != listado){
			for(final TarifaBean tarifa : listado){
				if(TarifasUtils.isOtrosProductos(tarifa)){
					resultado.add(tarifa);
				}
			}
		}
		return resultado;
	}


    public static List<TarifaBean> filtrarMediosPagoProductos(
            final List<TarifaBean> listado) {
        final List<TarifaBean> resultado = new ArrayList<TarifaBean>();
        if(null != listado){
            for(final TarifaBean tarifa : listado){
                if(TarifasUtils.isMediosPago(tarifa)){
                    resultado.add(tarifa);
                }
            }
        }
        return resultado;
    }

	/**Otros Productos:  Lineas 8 y 9
     *                   Lineas 5 y grupos <> 50
	 *                   Lineas 3 y grupos <> 11 y 51
	 *                   Lineas 3, grupo 11 y producto V8XX
	 * @param tarifa
	 * @return si los datos de la tarifa pertenecen a otros productos
	 */
	private static boolean isOtrosProductos(final TarifaBean tarifa){
		if(tarifa!= null && tarifa.getLinea() != null){
			final int linea = Integer.parseInt(tarifa.getLinea());
			if(linea == 5 || linea == 8 || linea == 9){
				return true;
			}else if (tarifa.getGrupo() != null){
				final int grupo = Integer.parseInt(tarifa.getGrupo());
				if((linea == 3 && grupo != 11 && grupo != 51)
				  || (linea == 5 && grupo != 50 )){
					return true;
				}
			}
		}
		return false;
	}

	/**Vista: Linea 3 y grupo 11
	 * @param tarifa
	 * @return si los datos de la tarifa pertenecen a vista
	 */
	private static boolean isVista(final TarifaBean tarifa){
		if(tarifa!= null && tarifa.getLinea() != null
				&& tarifa.getGrupo() != null){
			final int linea = Integer.parseInt(tarifa.getLinea());
			final int grupo = Integer.parseInt(tarifa.getGrupo());
			if(linea == 3 && grupo == 11){
					return true;
			}
		}
		return false;
	}


	/**Plazo: Linea 3 y grupo 51
	 * @param tarifa
	 * @return si los datos de la tarifa pertenecen a plazo
	 */
	public static boolean esPlazo(final TarifaBean tarifa){
		if(tarifa!= null && tarifa.getLinea() != null){
			final int linea = Integer.parseInt(tarifa.getLinea());
		    if(linea == 3 && tarifa.getGrupo() != null){
				final int grupo = Integer.parseInt(tarifa.getGrupo());
				if(grupo == 51){
					return true;
				}
		    }
		}
		return false;
	}

	/**Prestamo o credito: Linea 1
	 * @param tarifa
	 * @return
	 */
	public static boolean isPrestamoCredito(final TarifaBean tarifa){
		if(tarifa!= null && tarifa.getLinea() != null){
			final int linea = Integer.parseInt(tarifa.getLinea());
			if(linea == 1){
				return true;
			}
		}
		return false;
	}

	/**Plazo: Linea 5 y grupo 50
     * @param tarifa
     * @return si los datos de la tarifa pertenecen a medios de pago
     */
    public static boolean isMediosPago(final TarifaBean tarifa){
        if(tarifa!= null && tarifa.getLinea() != null){
            final int linea = Integer.parseInt(tarifa.getLinea());
            if(linea == 5 && tarifa.getGrupo() != null){
                final int grupo = Integer.parseInt(tarifa.getGrupo());
                if(grupo == 50){
                    return true;
                }
            }
        }
        return false;
    }

    public static String tokenizeTarifa(final TarifaBean tarifa) {
        return tarifa.getLinea()+"$"+tarifa.getGrupo()+"$"+tarifa.getProducto()+"$"+tarifa.getId();
    }

    public static String[] untokenizeTarifa(final String tarifaId) {
        if(StringUtils.isNotBlank(tarifaId)){
            return tarifaId.split("\\$");
        }
        return null;
    }

    public static boolean isSameId(final String[] tarifaIds, final TarifaBean tarifa) {
        return StringUtils.equals(tarifaIds[0], tarifa.getLinea()) &&
                StringUtils.equals(tarifaIds[1], tarifa.getGrupo()) &&
                StringUtils.equals(tarifaIds[2], tarifa.getProducto()) &&
                StringUtils.equals(tarifaIds[3], tarifa.getId());
    }

    public static List<TarifaBean> cargarCuentasFrecuentes(final List<TarifaBean> tarifas, final String rutaFichero) {
        final List<TarifaBean> tarifasFrecuentes = new ArrayList<TarifaBean>();
        if(null != tarifas && !tarifas.isEmpty()){
            final List<String> idsTarifasFrecuentes = TarifasUtils.readSmallTextFile(rutaFichero);
            if(idsTarifasFrecuentes != null){
                for(final String idTarifa : idsTarifasFrecuentes){
                    final String[] tarifaIds = TarifasUtils.untokenizeTarifa(idTarifa);
                    for(final TarifaBean tarifa : tarifas){
                        if(TarifasUtils.isSameId(tarifaIds, tarifa)){
                            tarifasFrecuentes.add(tarifa);
                        }
                    }
                }
            }
        }
        return tarifasFrecuentes;

    }

    public static void guardarCuentasFrecuentes(final List<TarifaBean> tarifasFrecuentes, final String rutaFichero) {
        final List<String> idsTarifasFrecuentes = new ArrayList<String>();
        if(tarifasFrecuentes != null){
            for(final TarifaBean tarifa:tarifasFrecuentes){
                idsTarifasFrecuentes.add(TarifasUtils.tokenizeTarifa(tarifa));
            }
        }
        TarifasUtils.writeSmallTextFile(idsTarifasFrecuentes, rutaFichero);

    }

    private static List<String> readSmallTextFile(final String aFileName){
        final Path path = Paths.get(aFileName);
        try {
            if(Files.exists(path)){
                return Files.readAllLines(path, TarifasUtils.ENCODING);
            }
        } catch (final IOException e) {
            throw new NoControlableException("No ha sido posible cargar las tarifas frecuentes del usuario", e);
        }
        return new ArrayList<String>();
      }

    private static void writeSmallTextFile(final List<String> aLines, final String aFileName) {
        final Path path = Paths.get(aFileName);
        final Path parentDir = path.getParent();
        try {
            if (!Files.exists(parentDir)){
                Files.createDirectories(parentDir);
            }
            Files.write(path, aLines, TarifasUtils.ENCODING );
        } catch (final IOException e) {
            throw new NoControlableException("No ha sido posible guardar las tarifas frecuentes del usuario", e);
        }
      }

    public ClienteBean getClienteFromCuenta(final CuentaBean cuenta) {
        if(null!=cuenta){
            if(null!= cuenta.getPersonasRelacionadas()){
                for(final RelacionadoBean rel : cuenta.getPersonasRelacionadas()){
                    if (TipoRelacionPersonaCuenta.TITULAR.equals(rel.getTipo()) && rel.getNumero() == 1) {
                       return busquedaIdInternaBackEnd.ejecutarTRN(rel.getPersona().getIdInterna().intValue());
                    }
                }
            }
        }
        final ClienteBean cliente = new ClienteBean();
        cliente.setNombre(cuenta.getNombreTitular());
        return cliente;
    }

    public static BigDecimal getImporteMinimoIPF(final List<ProductoSimpleBean> pdsList) {
        if(null!=pdsList){
            for(final ProductoSimpleBean pds : pdsList){
                if(StringUtils.equals(pds.getId(), "029") && pds.getCondiciones()!=null){
                    for(final CondicionBean condicion: pds.getCondiciones()){
                        if(StringUtils.equals(condicion.getClave(), "D06") && condicion instanceof CondicionRangoBean ){
                            return ((CondicionRangoBean)condicion).getMinimo();
                        }
                    }
                }
            }
            for(final ProductoSimpleBean pds : pdsList){
                if(StringUtils.equals(pds.getId(), "500") && pds.getCondiciones()!=null){
                    for(final CondicionBean condicion: pds.getCondiciones()){
                        if(StringUtils.equals(condicion.getClave(), "K03") && condicion instanceof CondicionRangoBean ){
                            return ((CondicionRangoBean)condicion).getMinimo();
                        }
                    }
                }
            }
        }
        throw new NoControlableException("Ha ocurrido un error al consultar las condiciones.",
                "Importe Mínimo para el déposito IPF no encontrado.");
    }

    public static Boolean isImporteMinimoIPF(final String idPds, final CondicionBean condicion) {
        if(null!=condicion && condicion instanceof CondicionValorRangoBean
                && StringUtils.isNoneBlank(idPds, condicion.getClave())){
            final String idParmCd = condicion.getClave();
            if((StringUtils.equals(idPds, "500")&&StringUtils.equals(idParmCd, "K03"))
                ||(StringUtils.equals(idPds, "029")&&StringUtils.equals(idParmCd, "D06"))){
                return true;
            }
        }
        return false;
    }

    public static BigDecimal getCondicionImporteCuotaIPF(final List<ProductoSimpleBean> pdsList) {
        if(null!=pdsList){
            for(final ProductoSimpleBean pds : pdsList){
                if(StringUtils.equals(pds.getId(), "500") && pds.getCondiciones()!=null){
                    for(final CondicionBean condicion: pds.getCondiciones()){
                        if(StringUtils.equals(condicion.getClave(), "K02") && condicion instanceof CondicionValorRangoBean ){
                            return ((CondicionValorRangoBean)condicion).getValor();
                        }
                    }
                }
            }
        }
        throw new NoControlableException("Ha ocurrido un error al consultar las condiciones.",
                "Importe Cuota para el déposito IPF no encontrado");
    }

    public static BigDecimal getCondicionImporteMinimoCuotaIPF(final List<ProductoSimpleBean> pdsList) {
        if(null!=pdsList){
            for(final ProductoSimpleBean pds : pdsList){
                if(StringUtils.equals(pds.getId(), "500") && pds.getCondiciones()!=null){
                    for(final CondicionBean condicion: pds.getCondiciones()){
                        if(StringUtils.equals(condicion.getClave(), "K03") && condicion instanceof CondicionValorRangoBean ){
                            return ((CondicionValorRangoBean)condicion).getValor();
                        }
                    }
                }
            }
        }
        throw new NoControlableException("Ha ocurrido un error al consultar las condiciones.",
                "Importe Mínimo Cuota para el déposito IPF no encontrado");
    }

    public static Date getCondicionFechaVencimientoIPF(final List<ProductoSimpleBean> pdsList) {
        if(null!=pdsList){
            for(final ProductoSimpleBean pds : pdsList){
                if(StringUtils.equals(pds.getId(), "029") && pds.getCondiciones()!=null){
                    for(final CondicionBean condicion: pds.getCondiciones()){
                        if(StringUtils.equals(condicion.getClave(), "AA9") && condicion instanceof CondicionValorRangoBean ){
                            return ((CondicionValorRangoBean)condicion).getValorFecha();
                        }
                    }
                }
            }
        }
        throw new NoControlableException("Ha ocurrido un error al consultar las condiciones.",
                "Fecha Vencimiento para el déposito IPF no encontrado");
    }


    /** Metodo que rellena la descripcion de las condiciones y elimina
     *  aquellas que no la tienen
     * @param resultado
     * @param tarifaSeleccionada
     * @return
     */
    public List<ProductoSimpleBean> fillPdsCondDescriptions(
            final List<ProductoSimpleBean> resultado, final TarifaBean tarifaSeleccionada) {

        //TODO sustituir el long por el numero de acuerdo recien contratado cuando toque
        final List<ProductoSimpleBean> listadoProductosSimples = consultaProductosSimplesBackend.
                ejecutarTRN(tarifaSeleccionada);


         final Map<String, String[]> mapClavesDescCondiciones = new HashMap<String, String[]>();
         if(resultado != null && !resultado.isEmpty()){
             for(final ProductoSimpleBean pdsBean : resultado){
                 if(pdsBean.getCondiciones() != null && !pdsBean.getCondiciones().isEmpty()){
                     for(final CondicionBean condicion : pdsBean.getCondiciones()){
                         mapClavesDescCondiciones.put(pdsBean.getId()+"-"+condicion.getIdCcps(), new String[]{pdsBean.getId(),condicion.getIdCcps()});
                     }
                 }
             }

             final Map<String, String> mapDescCondiciones = new HashMap<String, String>();
             if(!mapClavesDescCondiciones.isEmpty()){
                 for(final String[] value:mapClavesDescCondiciones.values()){
                     mapDescCondiciones.putAll(this.consultaNombreCondicionesBackend
                             .ejecutarTRN(value[0], value[1]));
                 }
             }

             final Map<String, String> mapDescripciones = new HashMap<String, String>();
             for(final ProductoSimpleBean pds : listadoProductosSimples){
                 mapDescripciones.put(pds.getId(), pds.getDescripcion());
             }


             for(final ProductoSimpleBean pdsBean : resultado){
                 if(mapDescripciones.containsKey(pdsBean.getId())){
                     pdsBean.setDescripcion(mapDescripciones.get(pdsBean.getId()));
                 }else{
                     pdsBean.setDescripcion(pdsBean.getId());
                 }
                 if(!mapDescCondiciones.isEmpty() && pdsBean.getCondiciones() != null && !pdsBean.getCondiciones().isEmpty()){
                     final List<CondicionBean> condicionesConDesc = new ArrayList<>();
                     for(final CondicionBean condicion : pdsBean.getCondiciones()){
                         final String nombreCondicion = mapDescCondiciones.get(pdsBean.getId()+"-"+condicion.getClave());
                         if(StringUtils.isNotBlank(nombreCondicion)){
                             condicion.setDescripcion(nombreCondicion);
                             condicionesConDesc.add(condicion);
                         }
                     }
                     pdsBean.setCondiciones(condicionesConDesc);
                 }
             }
         }
         return resultado;
    }

    /**Plazo: Linea 3, grupo 51, producto p005 o p007
     * @param tarifa
     * @return si los datos de la tarifa pertenecen a plazo
     */
    public static boolean esPlazoConCuota(final TarifaBean tarifa){
        if(tarifa!= null && tarifa.getLinea() != null){
            final int linea = Integer.parseInt(tarifa.getLinea());
            if(linea == 3 && tarifa.getGrupo() != null){
                final int grupo = Integer.parseInt(tarifa.getGrupo());
                if(grupo == 51 ){
                    final String producto = tarifa.getProducto();
                    if(StringUtils.equals(producto, "P005")
                       ||StringUtils.equals(producto, "P007")){
                        return true;
                    }
                }
            }
        }
        return false;
    }


}
