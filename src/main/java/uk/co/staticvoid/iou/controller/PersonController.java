package uk.co.staticvoid.iou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uk.co.staticvoid.iou.conveyor.request.AddPersonRequest;
import uk.co.staticvoid.iou.conveyor.request.GetPersonRequest;
import uk.co.staticvoid.iou.conveyor.response.AddPersonResponse;
import uk.co.staticvoid.iou.conveyor.response.GetPersonResponse;
import uk.co.staticvoid.iou.service.PersonService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }


    @GetMapping(path = "/person/{personUuid}", produces = APPLICATION_JSON_VALUE)
    public @ResponseBody
    GetPersonResponse getPerson(@PathVariable("personUuid") String personUuid) {
        return new GetPersonResponse(personService.getPersonByUuid(new GetPersonRequest(personUuid)));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/person", consumes = APPLICATION_JSON_VALUE)
    public @ResponseBody
    AddPersonResponse addPerson(@RequestBody AddPersonRequest request) {
        return new AddPersonResponse(personService.addPerson(request));
    }

}
