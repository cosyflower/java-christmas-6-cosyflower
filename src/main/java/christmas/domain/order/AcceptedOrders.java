package christmas.domain.order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AcceptedOrders {
    private final List<Order> acceptedOrders;

    private AcceptedOrders(List<Order> acceptedOrders) {
//        validate(acceptedOrders);
        this.acceptedOrders = new ArrayList<>(acceptedOrders);
    }

//    private void validate(List<Order> acceptedOrders) {
//        isDuplicated(acceptedOrders);
//    }

//    private void isDuplicated(List<Order> acceptedOrders) {
//        // Order의 MenuProduct를 확인해야 한다
//
//        if (acceptedOrders.stream().distinct().count()) {
//
//        }
//    }

    public static AcceptedOrders from(List<Order> acceptedOrders) {
        return new AcceptedOrders(acceptedOrders);
    }

    public List<Order> getAcceptedOrders() {
        return Collections.unmodifiableList(acceptedOrders);
    }
}
