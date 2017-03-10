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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import pe.com.claro.common.resource.exception.NotFoundException;
import pe.com.claro.common.resource.util.MessageFilterBean;
import pe.com.claro.ventas.linea.domain.service.ClienteService;
import pe.com.claro.ventas.linea.model.Cliente;


@Stateless
@Path("/ventas/validacion/v2.0.0/clientes")	
@Api(value = "/clientes", description = "Bienvenido!")
@Produces({"application/json"})
public class ClienteResource
{
   @EJB
   private ClienteService customerService;
   
	@GET
	@Path("/{Id}")
	@ApiOperation(value = "Buscar Cliente por ID", notes = "Retorna un ciente cuando el ID > 0 de lo contrario devolvera error", response = Cliente.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Id invalido"),
			@ApiResponse(code = 404, message = "Cliente no encontrado") })
	public Response getClienteById(
			@ApiParam(value = "El Id del cliente debera estar dentro del rango", 
			allowableValues = "range[1,10]", 
			required = true) @PathParam("Id") Long Id)
			throws NotFoundException,Exception {
		Cliente cliente = customerService.findId(Id);
		if (null != cliente) {
			String result = new ObjectMapper().writeValueAsString(cliente);
			return Response.ok().entity(result).build();
		} else {
			throw new NotFoundException(404, "Cliente no encontrado");
		}
	}
	
	
	@GET
	public Response getClienteAll(@BeanParam MessageFilterBean filterBean)throws NotFoundException,Exception  {
		if (filterBean.getStart() >= 0 && filterBean.getSize() > 0) {
			List<Cliente> clientes=customerService.getAllClientesPaginated(filterBean.getStart(), filterBean.getSize());
			if (null != clientes) {
				String result = new ObjectMapper().writeValueAsString(clientes);
				return Response.ok().entity(result).build();
			} else {
				throw new NotFoundException(404, "Cliente no encontrado");
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
	    @ApiParam(value = "Cliente a eliminar", required = true)@PathParam("Id") Long Id) {
	    if (customerService.deleteById(Id)) {
	      return Response.ok().build();
	    } else {
	     return Response.status(Response.Status.NOT_FOUND).build();
	    }
	  }

	  @POST
	  @Consumes({MediaType.APPLICATION_JSON})
	  @ApiOperation(value = "Adicionar un nuevo cliente")
	  @ApiResponses(value = { @ApiResponse(code = 405, message = "Input invalido") })
	  public Response adicionarCliente(
	      @ApiParam(value = "El objeto cliente que sera adicionado", required = true) Cliente cliente) {
		  Cliente clienteResponse=customerService.adicionarCliente(cliente);
	    return Response.ok().entity(clienteResponse).build();
	  }
	  
	  
	  @PUT 
	  @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	  @ApiOperation(value = "Actualizar un cliente existente")
	  @ApiResponses(value = { @ApiResponse(code = 400, message = "Invalido Id"),
	      @ApiResponse(code = 404, message = "No se encuentra el cliente"),
	      @ApiResponse(code = 405, message = "Excepcion de validacion") })
	  public Response actualizarCliente(
	      @ApiParam(value = "El objeto cliente que sera actualizado", required = true) Cliente cliente) {
		  Cliente clienteResponse=customerService.actualizarCliente(cliente);
	    return Response.ok().entity(clienteResponse).build();
	  }

}
