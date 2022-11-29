package vttp.pafworkshop21.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Customer {
    private String firstName;
    private String lastName;
    private String id;
    private String address;
    
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public JsonObject toJson(){
        JsonObject o = Json.createObjectBuilder()
                        .add("id", getId())
                        .add("first_name", getFirstName())
                        .add("last_name", getLastName())
                        .add("address", getAddress())
                        .build();
        return o;
    }

    
}
