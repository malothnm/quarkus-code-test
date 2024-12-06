package com.zimblesystems.resource;

import com.zimblesystems.service.GreetingsService;
import com.zimblesystems.model.dto.PersonDTO;
import io.quarkus.runtime.Startup;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestPath;

@Path("/hello")
public class GreetingResource {

    @Inject
    GreetingsService greetingsService;

//    @Inject
//    NOServiceTest noServiceTest;


    @GET
    @Path("/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(@RestPath String name ) {
        return greetingsService.createGreetings(name);

    }

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Uni<Response> addPerson(@Valid PersonDTO personDTO){

        return greetingsService.addPerson(personDTO)
                .onItem().transform(aVoid -> Response.status(Response.Status.CREATED).build())
        ;

    }


    @GET
    @Path("/view/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Uni<Response> viewPerson(String name){

        return greetingsService.getPerson(name)
                .onItem().transform(personDTOOptional -> {
                    if(personDTOOptional.isPresent()){
                        return Response.ok(personDTOOptional.get()).build();
                    }else{
                        return Response.status(Response.Status.NOT_FOUND).build();
                    }
                })

                ;

    }
}
