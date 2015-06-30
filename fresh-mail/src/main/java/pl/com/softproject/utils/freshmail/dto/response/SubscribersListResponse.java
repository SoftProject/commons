package pl.com.softproject.utils.freshmail.dto.response;

import java.util.ArrayList;
import java.util.List;

/**
 * Class SubscribersListResponse
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class SubscribersListResponse extends StatusResponse {

    private List<SubscribersList> lists = new ArrayList<>();

    public SubscribersListResponse() {
    }

    public SubscribersListResponse(String status, List<SubscribersList> lists) {
        super(status);
        this.lists = lists;
    }

    public List<SubscribersList> getLists() {
        return lists;
    }

    public void setLists(List<SubscribersList> lists) {
        this.lists = lists;
    }

    @Override
    public String toString() {
        return "SubscribersListResponse{" +
               "status=" + super.getStatus() + "," +
               "lists=" + lists +
               '}';
    }
}
