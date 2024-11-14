package com.deliverynow.user.adapters.controller;

import com.deliverynow.user.adapters.controller.request.CustomerRequest;
import com.deliverynow.user.adapters.controller.response.BaseResponse;
import com.deliverynow.user.adapters.controller.response.CustomerResponse;
import com.deliverynow.user.application.controller.CustomerController;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.RestResponse;

@Path("/customer")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Cliente controller", description = "Cliente operations")
public class CustomerRestController {

    CustomerController customerController;

    public CustomerRestController(CustomerController customerController) {
        this.customerController = customerController;
    }

    @POST
    @Operation(summary = "Inserir um novo cliente")
    public RestResponse<Void> insertClient(@Valid CustomerRequest customerRequest) {
        customerController.insertCustomer(customerRequest);
        return RestResponse.ok();
    }

    @GET
    @Operation(summary = "Buscar cliente por documento")
    public RestResponse<BaseResponse<CustomerResponse>> getClient(@QueryParam("document") String document) {
        var clientResponse = customerController.getCustomer(document);
        return RestResponse.ok(new BaseResponse<>(clientResponse));
    }

    @GET
    @Path("session")
    @Operation(summary = "Criar sessão do cliente")
    public RestResponse<BaseResponse<String>> getClientSession(@QueryParam("document") String document) {
        var session = customerController.getCustomerSession(document);
        return RestResponse.ok(new BaseResponse<>(session));
    }
}
