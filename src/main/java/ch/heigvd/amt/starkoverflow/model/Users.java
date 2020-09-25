package ch.heigvd.amt.starkoverflow.model;

import java.util.HashMap;

public class Users {
    public static final Users INSTANCE = new Users();

    private HashMap<String, PersonDTO> users = new HashMap<>();

    private Users() {

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

    public PersonDTO login(LoginCommand cmd){
        if(users.containsKey(cmd.email)){
            return users.get(cmd.email);
        }

        return null;
    }
}
