package uk.co.staticvoid.iou.conveyor.response;

import uk.co.staticvoid.iou.domain.Person;

public class GetPersonResponse {
    private String nickname;
    private String uuid;

    public GetPersonResponse() {
    }

    public GetPersonResponse(Person person) {
        this.nickname = person.getNickname();
        this.uuid = person.getUuid();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
