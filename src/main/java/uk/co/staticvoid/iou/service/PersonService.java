package uk.co.staticvoid.iou.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.staticvoid.iou.conveyor.request.AddPersonRequest;
import uk.co.staticvoid.iou.conveyor.request.GetPersonRequest;
import uk.co.staticvoid.iou.domain.Person;
import uk.co.staticvoid.iou.domain.PersonRepository;
import uk.co.staticvoid.iou.exceptions.StoredDataNotFound;

@Slf4j
@Service
public class PersonService {
    private final PersonRepository repository;

    @Autowired
    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public Person addPerson(AddPersonRequest addPersonRequest) {
        Person person = new Person(addPersonRequest.getNickname());
        return repository.save(person);
    }

    public Person getPersonByUuid(GetPersonRequest getPersonRequest) {
        return repository.findByUuid(getPersonRequest.getUuid()).orElseThrow(() ->
            new StoredDataNotFound("Unable to find a person with UUID " + getPersonRequest.getUuid()));
    }
}
