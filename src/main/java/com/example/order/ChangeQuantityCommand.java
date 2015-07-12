package com.example.order;

import com.example.Command;

import javax.validation.constraints.Min;
import java.util.List;

public class ChangeQuantityCommand {

    private Command command = new Command(this);

    @Min(value = 1, message = "Order quantity must be greater than or equal to 1.")
    public final int quantity;

    public ChangeQuantityCommand(ChangeQuantityDetail detail) {
        int temp;
        try {
            temp = Integer.parseInt(detail.quantity);
        } catch (NumberFormatException e) {
            temp = -1;
        }
        quantity = temp;
    }

    public boolean isValid() {
        return command.isValid();
    }

    public List<String> getValidationErrors() {
        return command.getValidationErrors();
    }
}
