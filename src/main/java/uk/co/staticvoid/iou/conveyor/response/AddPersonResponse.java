package uk.co.staticvoid.iou.conveyor.response;

import uk.co.staticvoid.iou.domain.Person;

public class AddPersonResponse {
    private String uuid;

    public AddPersonResponse() {
    }

    public AddPersonResponse(Person person) {
        this.uuid = person.getUuid();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
