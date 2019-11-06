package HWJ.pages.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;

@Data
public class OrderDTO {

    private int id;

    private int petId;

    private int quantity;

    private String shipDate;

    private String complete;

    private String status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDTO)) return false;
        OrderDTO orderDTO = (OrderDTO) o;
        return id == orderDTO.id &&
                petId == orderDTO.petId &&
                quantity == orderDTO.quantity &&
                Objects.equals(shipDate, orderDTO.shipDate) &&
                Objects.equals(complete, orderDTO.complete) &&
                Objects.equals(status, orderDTO.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, petId, quantity, shipDate, complete, status);
    }

    private static int getRndValue() {
        return new Random().nextInt(100);
    }

    public static class OrderDTOBuilder {
        OrderDTO orderDTO = new OrderDTO();

        public OrderDTOBuilder withId(int id) {
            orderDTO.setId(id);
            return this;
        }

        public OrderDTOBuilder withPetId() {
            orderDTO.setPetId(getRndValue());
            return this;
        }

        public OrderDTOBuilder withQuantity(int quantity) {
            orderDTO.setQuantity(quantity);
            return this;
        }

        public OrderDTOBuilder withShipData() {
            orderDTO.setShipDate(String.valueOf(LocalDateTime.now()).concat("+0000"));
            return this;
        }

        public OrderDTOBuilder withComplete() {
            orderDTO.setComplete("false");
            return this;
        }

        public OrderDTOBuilder withStatus() {
            orderDTO.setStatus("Available");
            return this;
        }

        public OrderDTO build() {
            return orderDTO;
        }
    }
}