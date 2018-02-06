package uk.co.staticvoid.iou.conveyor.request;

public class AddPersonRequest {

    String nickname;

    public AddPersonRequest() {
    }

    public AddPersonRequest(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
