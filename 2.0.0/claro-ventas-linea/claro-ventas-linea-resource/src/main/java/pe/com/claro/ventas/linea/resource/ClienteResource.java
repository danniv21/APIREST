package pe.com.claro.ventas.linea.resource;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import pe.com.claro.common.resource.exception.BadUsageException;
import pe.com.claro.common.resource.exception.NotFoundException;
import pe.com.claro.common.resource.util.MessageFilterBean;
import pe.com.claro.ventas.linea.canonical.request.linea.ClienteRequest;
import pe.com.claro.ventas.linea.canonical.response.linea.ClienteDireccionResponse;
import pe.com.claro.ventas.linea.domain.service.ClienteService;

@Stateless
@Path("/ventas/validacion/v2.0.0/clientes")
@Api(value = "/clientes", description = "Bienvenido!")
@Produces({ "application/json" })
public class ClienteResource {
	
	private static final Logger LOG = LoggerFactory.getLogger(ClienteResource.class);
	@EJB
	private ClienteService clienteService;

	@Context
	private Configuration configuration;

	@GET
	@Path("/{Id}")
	@ApiOperation(value = "Buscar Cliente por ID", notes = "Retorna un cliente cuando el ID > 0 "
			+ "de lo contrario devolvera error", response = ClienteDireccionResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Id invalido"),
			@ApiResponse(code = 404, message = "Cliente no encontrado") })
	public Response getClienteById(
			@ApiParam(value = "El Id del cliente debera estar dentro del rango", allowableValues = "range[1,10]",
			required = true) @PathParam("Id") Long Id)throws BadUsageException {
		ClienteDireccionResponse cliente = clienteService.findId(Id);
		String result=null;
		if (null != cliente) {
			try {
				result = new ObjectMapper().writeValueAsString(cliente);
			} catch (JsonProcessingException e) {
				 LOG.error("ERROR: [Exception] - [" + e.getMessage() + "] ", e ); 
				 throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
			}
			return Response.ok().entity(result).build();
		} else {
			throw new NotFoundException(404, configuration.getProperty("cliente.noencontrado").toString());
			
		}
	}

	@GET
	@ApiOperation(value = "Obtener lista de clientes mediante un rango determinado", notes = "Retorna una lista de clientes"
			+ " cuando los parametros sean START >=0 y SIZE>0 de lo contrario no retornara informacion", response = ClienteDireccionResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Id invalido"),
			@ApiResponse(code = 404, message = "Cliente no encontrado") })
	public Response getClienteAll(@BeanParam MessageFilterBean filterBean) throws NotFoundException, Exception {
		if (filterBean.getStart() >= 0 && filterBean.getSize() > 0) {
			List<ClienteDireccionResponse> clientes = clienteService.getAllClientesPaginated(filterBean.getStart(),
					filterBean.getSize());
			if (null != clientes) {
				String result = new ObjectMapper().writeValueAsString(clientes);
				return Response.ok().entity(result).build();
			} else {
				throw new NotFoundException(404, configuration.getProperty("cliente.noencontrado").toString());
			}
		}
		return null;
	}

	@DELETE
	@Path("/{Id}")
	@ApiOperation(value = "Eliminar Cliente")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Id invalido"),
			@ApiResponse(code = 404, message = "Cliente no encontrado") })
	public Response deleteById(
			@ApiParam(value = "El objeto cliente a eliminar", required = true) @PathParam("Id") Long Id) {
		if (clienteService.deleteById(Id)) {
			return Response.ok().build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Adicionar un nuevo cliente")
	@ApiResponses(value = { @ApiResponse(code = 405, message = "Input invalido") })
	public Response adicionarCliente(
			@ApiParam(value = "El objeto cliente que sera adincionado", required = true) ClienteRequest clienteRequest) {
		ClienteDireccionResponse clienteResponse = clienteService.adicionarCliente(clienteRequest);
		return Response.ok().entity(clienteResponse).build();
	}

	@PUT
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@ApiOperation(value = "Actualizar un cliente existente")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalido Id"),
			@ApiResponse(code = 404, message = "No se encuentra el cliente"),
			@ApiResponse(code = 405, message = "Excepcion de validacion") })
	public Response actualizarCliente(
			@ApiParam(value = "El objeto cliente que sera actualizado", required = true) ClienteRequest clienteRequest) {
		ClienteDireccionResponse clienteResponse = clienteService.actualizarCliente(clienteRequest);
		return Response.ok().entity(clienteResponse).build();
	}

	@PUT
	@Path("/message/{payload}")
	@ApiOperation(value = "Enviar Mensaje a la cola", notes = "Se envia mensaje del cliente a la cola "
			+ " del servidor de manera asincrona, retornando un valor satisfactorio", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Mensaje invalido"),
			@ApiResponse(code = 404, message = "Respuesta no encontrada") })
	public Response sendMessage(
			@ApiParam(value = "El objeto mensaje ", required = true) @PathParam("payload") String payload)
					throws NotFoundException, Exception {
		String output = clienteService.enviarMensajeCliente(payload);
		if (null != output) {
			return Response.ok().entity(output).build();
		} else {
			throw new NotFoundException(404, configuration.getProperty("mensage.noresultado").toString());
		}
	}

	@GET
	@Path("/catalogo/{Id}")
	@ApiOperation(value = "Buscar catalogo por ID", notes = "Retorna informacion de catalogo cuando el "
			+ "ID > 0 de lo contrario devolvera error", response = ClienteDireccionResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Id invalido"),
			@ApiResponse(code = 404, message = "Catalogo no encontrado") })
	public Response getCatalogoById(
			@ApiParam(value = "El Id del cliente debera estar dentro del rango de 1 al 9999", required = true) 
			@PathParam("Id") Long Id)
					throws NotFoundException, Exception {
		ClienteDireccionResponse cliente = clienteService.findCatalogoId(Id);
		if (null != cliente) {
			String result = new ObjectMapper().writeValueAsString(cliente);
			return Response.ok().entity(result).build();
		} else {
			throw new NotFoundException(404, configuration.getProperty("mensage.noresultado").toString());
		}
	}
}
