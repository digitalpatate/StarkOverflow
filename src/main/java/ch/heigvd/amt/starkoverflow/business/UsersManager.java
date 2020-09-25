package ch.heigvd.amt.starkoverflow.business;

import ch.heigvd.amt.starkoverflow.model.LoginCommand;
import ch.heigvd.amt.starkoverflow.model.PersonDTO;

import java.util.HashMap;
import java.util.Map;

public class UsersManager {
    public static final UsersManager INSTANCE = new UsersManager();

    private HashMap<String, PersonDTO> users = new HashMap<>();

    private UsersManager() {

    }

    public boolean addUser(PersonDTO user){
        if(exist(user)){
            return false;
        }

        users.put(user.email, user);
        return true;
    }

    public boolean deleteUser(PersonDTO user){
        if(exist(user)){
            users.remove(user.email);
            return true;
        }

        return false;
    }

    public boolean exist(PersonDTO user){
        if(users.containsValue(user)){
            return true;
        }

        return false;
    }

    public PersonDTO login(LoginCommand cmd) throws Exception {
        if(users.containsKey(cmd.email)){
            PersonDTO user = users.get(cmd.email);
            if(cmd.password.equals(user.password)) {
                return users.get(cmd.email);
            }
        }

        throw new Exception("No user found");
    }

    public void dump(){
        for(Map.Entry<String, PersonDTO> entry : users.entrySet()){
            System.out.println(entry.getKey() + "->"
                    + "\nemail: " + entry.getValue().email
                    + "\nprofilePicture: " + entry.getValue().profilePicture
                    + "\nname: " + entry.getValue().name
                    + "\nsurname: " + entry.getValue().surname
                    + "\npassword: " + entry.getValue().password);
        }
    }
}
