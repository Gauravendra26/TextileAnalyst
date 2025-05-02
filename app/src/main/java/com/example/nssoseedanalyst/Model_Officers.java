package com.example.nssoseedanalyst;

public class Model_Officers {
    int seed_analyst_seed_office_map_id,seedanalyst_id,seedofficer_id,user_id,user_role,user_status;
    String user_name,user_phone,email,user_empid,user_image,created_at,updated_at;

    public Model_Officers(int seed_analyst_seed_office_map_id, int seedanalyst_id, int seedofficer_id,
                          int user_id, int user_role, int user_status, String user_name, String user_phone,
                          String email, String user_empid, String user_image, String created_at, String updated_at) {
        this.seed_analyst_seed_office_map_id = seed_analyst_seed_office_map_id;
        this.seedanalyst_id = seedanalyst_id;
        this.seedofficer_id = seedofficer_id;
        this.user_id = user_id;
        this.user_role = user_role;
        this.user_status = user_status;
        this.user_name = user_name;
        this.user_phone = user_phone;
        this.email = email;
        this.user_empid = user_empid;
        this.user_image = user_image;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getSeed_analyst_seed_office_map_id() {
        return seed_analyst_seed_office_map_id;
    }

    public void setSeed_analyst_seed_office_map_id(int seed_analyst_seed_office_map_id) {
        this.seed_analyst_seed_office_map_id = seed_analyst_seed_office_map_id;
    }

    public int getSeedanalyst_id() {
        return seedanalyst_id;
    }

    public void setSeedanalyst_id(int seedanalyst_id) {
        this.seedanalyst_id = seedanalyst_id;
    }

    public int getSeedofficer_id() {
        return seedofficer_id;
    }

    public void setSeedofficer_id(int seedofficer_id) {
        this.seedofficer_id = seedofficer_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getUser_role() {
        return user_role;
    }

    public void setUser_role(int user_role) {
        this.user_role = user_role;
    }

    public int getUser_status() {
        return user_status;
    }

    public void setUser_status(int user_status) {
        this.user_status = user_status;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser_empid() {
        return user_empid;
    }

    public void setUser_empid(String user_empid) {
        this.user_empid = user_empid;
    }

    public String getUser_image() {
        return user_image;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
