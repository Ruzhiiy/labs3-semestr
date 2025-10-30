package five.lab.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum Systems {
    SERVICE_ONE("Service 1"),
    ERP("Enterprise Resousrce Planning"),
    CRM("Customer Relashionship Managment"),
    WMS("Warehouse Managment System");

    private final String name;

    Systems(String name){
        this.name = name;
    }

    @JsonValue
    public String getName(){
        return name;
    }

    @Override
    public String toString(){
        return name;
    }
    
    @JsonCreator
    public static Systems fromString(String value){
        if(value == null){
            return null;
        }
        for(Systems system : Systems.values()){
            if(system.name().equalsIgnoreCase(value) || system.getName().equalsIgnoreCase(value)){
                log.info("Тип системы: " + system.name);
                return system;
            }
        }
        log.error("В поле systemName можно указать только CRM, ERP, WMS");
        throw new IllegalArgumentException("Unknown System value: " + value);
    }
}
