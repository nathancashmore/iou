package uk.co.staticvoid.iou.conveyor.request;

public class GetPersonRequest {
    private String Uuid;

    public GetPersonRequest() {
    }

    public GetPersonRequest(String uuid) {
        Uuid = uuid;
    }

    public String getUuid() {
        return Uuid;
    }

    public void setUuid(String uuid) {
        Uuid = uuid;
    }
}
