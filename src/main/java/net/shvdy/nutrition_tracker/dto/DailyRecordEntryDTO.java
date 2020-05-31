package net.shvdy.nutrition_tracker.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.shvdy.nutrition_tracker.model.entity.Food;

/**
 * 21.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class DailyRecordEntryDTO {
    private Food food;
    private int quantity;
    private String quantityError;
    private String foodDTOJSON;
    private String foodName;
    private int entryCalories;
    private int entryCarbs;
    private int entryFats;
    private int entryProt;

    public DailyRecordEntryDTO() {
    }

    public DailyRecordEntryDTO(Food food, int quantity, String foodDTOJSON, int entryCalories,
                               int entryCarbs, int entryFats, int entryProt) {
        this.food = food;
        this.quantity = quantity;
        this.foodDTOJSON = foodDTOJSON;
        this.entryCalories = entryCalories;
        this.entryCarbs = entryCarbs;
        this.entryFats = entryFats;
        this.entryProt = entryProt;
    }

    public static DailyRecordEntryDTOBuilder builder() {
        return new DailyRecordEntryDTOBuilder();
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getEntryCalories() {
        return entryCalories;
    }

    public void setEntryCalories(int entryCalories) {
        this.entryCalories = entryCalories;
    }

    public int getEntryCarbs() {
        return entryCarbs;
    }

    public void setEntryCarbs(int entryCarbs) {
        this.entryCarbs = entryCarbs;
    }

    public int getEntryFats() {
        return entryFats;
    }

    public void setEntryFats(int entryFats) {
        this.entryFats = entryFats;
    }

    public int getEntryProt() {
        return entryProt;
    }

    public void setEntryProt(int entryProt) {
        this.entryProt = entryProt;
    }

    public String getFoodDTOJSON() {
        return foodDTOJSON;
    }

    public void setFoodDTOJSON(String foodDTOJSON) {
        this.foodDTOJSON = foodDTOJSON;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getQuantityError() {
        return quantityError;
    }

    public void setQuantityError(String quantityError) {
        this.quantityError = quantityError;
    }

    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "";
        }
    }

    public static final class DailyRecordEntryDTOBuilder {
        private Food food;
        private int quantity;
        private String foodDTOJSON;
        private String foodName;
        private int entryCalories;
        private int entryCarbs;
        private int entryFats;
        private int entryProt;

        private DailyRecordEntryDTOBuilder() {
        }

        public DailyRecordEntryDTOBuilder food(Food food) {
            this.food = food;
            return this;
        }

        public DailyRecordEntryDTOBuilder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public DailyRecordEntryDTOBuilder foodDTOJSON(String foodDTOJSON) {
            this.foodDTOJSON = foodDTOJSON;
            return this;
        }

        public DailyRecordEntryDTOBuilder entryCalories(int entryCalories) {
            this.entryCalories = entryCalories;
            return this;
        }

        public DailyRecordEntryDTOBuilder entryCarbs(int entryCarbs) {
            this.entryCarbs = entryCarbs;
            return this;
        }

        public DailyRecordEntryDTOBuilder entryFats(int entryFats) {
            this.entryFats = entryFats;
            return this;
        }

        public DailyRecordEntryDTOBuilder entryProt(int entryProt) {
            this.entryProt = entryProt;
            return this;
        }

        public DailyRecordEntryDTOBuilder foodName(String foodName) {
            this.foodName = foodName;
            return this;
        }

        public DailyRecordEntryDTO build() {
            DailyRecordEntryDTO dailyRecordEntryDTO = new DailyRecordEntryDTO();
            dailyRecordEntryDTO.setFood(food);
            dailyRecordEntryDTO.setQuantity(quantity);
            dailyRecordEntryDTO.setFoodDTOJSON(foodDTOJSON);
            dailyRecordEntryDTO.setEntryCalories(entryCalories);
            dailyRecordEntryDTO.setEntryCarbs(entryCarbs);
            dailyRecordEntryDTO.setEntryFats(entryFats);
            dailyRecordEntryDTO.setEntryProt(entryProt);
            dailyRecordEntryDTO.setFoodName(foodName);
            return dailyRecordEntryDTO;
        }
    }

}