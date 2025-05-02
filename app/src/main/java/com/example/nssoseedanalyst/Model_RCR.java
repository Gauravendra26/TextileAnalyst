package com.example.nssoseedanalyst;

public class Model_RCR {
    int sr_id, sr_status, status, verification_id,verification_status;

    String sr_registration_no, sr_sirname, sr_applicant, sr_behalf_of,sr_parent_sirname, sr_parentsname, sr_gender,
            sr_social_status,
            sr_training_certificate, sr_training_place, sr_training_from, sr_training_to, sr_educational_certificate,
            sr_passport_photo, sr_aadhaar_number,sr_country_code, sr_mobile,sr_applicant_type, sr_unit_name,sr_village,
            sr_taluk, sr_state, sr_district, sr_pin, sr_address,sr_production_center,sr_production_center_taluk,
            sr_production_center_state,sr_production_center_district,sr_production_center_pin, sr_production_center_sector,
            sr_production_center_address, sr_produced,sr_unit, sr_production_level,sr_ownership_type, sr_establishment,
            sr_other_details, sr_quantity_silkworm, sr_quantity_silkworm2, sr_workers, sr_unit_two, sr_page1, sr_page2, sr_page3,
            sr_page4, sr_page5, sr_page6, sr_sign, sr_remark, created_at, updated_at  ,name,
            nameOfTheRsp,
            registration_no ,
            registered_address ,
            contact_no ,
            DisplayedRegistrationCertificateProminently ,
            mulberry_area_acre ,
            regularity_of_soil_testing_once_in_2_years,
            recommended_application_of_soil_inputs_fertilizers_fym_after_each_crop ,
            is_pruning_harvest_schedule_followed_to_enable_a_minimum_of_32_crops_annum ,
            mulberry_variety  ,
            pest_disease_freeness_in_garden  ,
            nutrient_deficiency_symptoms_in_leaves  ,
            average_single_leaf_weight_g  ,
            irrigation_facility ,
            overall_status  ,
            incubation_facility  ,
            whether_rearing_house_is_as_per_recommended_plan  ,
            whether_equipments_appliances_as_per_seed_act_guidelines_available ,
            visual_health_of_chawki_worms  ,
            wt_of_100_ii_instar_larvae_g  ,
            bed_spacing  ,
            maintenance_of_temp_rh ,
            no_of_batches_detected  ,
            lot_details_of_source_dfls  ,
            skilled_person_for_testing ,
            disinfection_register  ,
            dfl_procurement_register  ,
            rearing_performance_register  ,
            pebrine_testing_register  ,
            chawki_supply_register  ,
            farm_management_register  ,
            temp_rh_maintenance_register  ,
            bill_book ,
            whether_following_self_certification_for_each_supply_lot  ,
            image  ,
            extra1 ,
            extra2 ,
            extra3,extra4 ;

    public Model_RCR(int sr_id, int sr_status, int status, int verification_id, int verification_status,
                     String sr_registration_no, String sr_sirname, String sr_applicant, String sr_behalf_of,
                     String sr_parent_sirname, String sr_parentsname, String sr_gender, String sr_social_status,
                     String sr_training_certificate, String sr_training_place, String sr_training_from,
                     String sr_training_to, String sr_educational_certificate, String sr_passport_photo,
                     String sr_aadhaar_number, String sr_country_code, String sr_mobile, String sr_applicant_type,
                     String sr_unit_name, String sr_village, String sr_taluk, String sr_state, String sr_district,
                     String sr_pin, String sr_address, String sr_production_center, String sr_production_center_taluk,
                     String sr_production_center_state, String sr_production_center_district,
                     String sr_production_center_pin, String sr_production_center_sector,
                     String sr_production_center_address, String sr_produced, String sr_unit,
                     String sr_production_level, String sr_ownership_type, String sr_establishment,
                     String sr_other_details, String sr_quantity_silkworm, String sr_quantity_silkworm2,
                     String sr_workers, String sr_unit_two, String sr_page1, String sr_page2, String sr_page3,
                     String sr_page4, String sr_page5, String sr_page6, String sr_sign, String sr_remark,
                     String created_at, String updated_at,String name,  String nameOfTheRsp,
                     String registration_no, String registered_address, String contact_no,
                     String DisplayedRegistrationCertificateProminently, String mulberry_area_acre,
                     String regularity_of_soil_testing_once_in_2_years,
                     String recommended_application_of_soil_inputs_fertilizers_fym_after_each_crop,
                     String is_pruning_harvest_schedule_followed_to_enable_a_minimum_of_32_crops_annum,
                     String mulberry_variety, String pest_disease_freeness_in_garden,
                     String nutrient_deficiency_symptoms_in_leaves, String average_single_leaf_weight_g,
                     String irrigation_facility, String overall_status, String incubation_facility,
                     String whether_rearing_house_is_as_per_recommended_plan,
                     String whether_equipments_appliances_as_per_seed_act_guidelines_available,
                     String visual_health_of_chawki_worms, String wt_of_100_ii_instar_larvae_g,
                     String bed_spacing, String maintenance_of_temp_rh, String no_of_batches_detected,
                     String lot_details_of_source_dfls, String skilled_person_for_testing,
                     String disinfection_register, String dfl_procurement_register,
                     String rearing_performance_register, String pebrine_testing_register,
                     String chawki_supply_register, String farm_management_register,
                     String temp_rh_maintenance_register, String bill_book,
                     String whether_following_self_certification_for_each_supply_lot, String image,
                     String extra1, String extra2, String extra3, String extra4) {
        this.sr_id = sr_id;
        this.sr_status = sr_status;
        this.status = status;
        this.verification_id = verification_id;
        this.verification_status = verification_status;
        this.sr_registration_no = sr_registration_no;
        this.sr_sirname = sr_sirname;
        this.sr_applicant = sr_applicant;
        this.sr_behalf_of = sr_behalf_of;
        this.sr_parent_sirname = sr_parent_sirname;
        this.sr_parentsname = sr_parentsname;
        this.sr_gender = sr_gender;
        this.sr_social_status = sr_social_status;
        this.sr_training_certificate = sr_training_certificate;
        this.sr_training_place = sr_training_place;
        this.sr_training_from = sr_training_from;
        this.sr_training_to = sr_training_to;
        this.sr_educational_certificate = sr_educational_certificate;
        this.sr_passport_photo = sr_passport_photo;
        this.sr_aadhaar_number = sr_aadhaar_number;
        this.sr_country_code = sr_country_code;
        this.sr_mobile = sr_mobile;
        this.sr_applicant_type = sr_applicant_type;
        this.sr_unit_name = sr_unit_name;
        this.sr_village = sr_village;
        this.sr_taluk = sr_taluk;
        this.sr_state = sr_state;
        this.sr_district = sr_district;
        this.sr_pin = sr_pin;
        this.sr_address = sr_address;
        this.sr_production_center = sr_production_center;
        this.sr_production_center_taluk = sr_production_center_taluk;
        this.sr_production_center_state = sr_production_center_state;
        this.sr_production_center_district = sr_production_center_district;
        this.sr_production_center_pin = sr_production_center_pin;
        this.sr_production_center_sector = sr_production_center_sector;
        this.sr_production_center_address = sr_production_center_address;
        this.sr_produced = sr_produced;
        this.sr_unit = sr_unit;
        this.sr_production_level = sr_production_level;
        this.sr_ownership_type = sr_ownership_type;
        this.sr_establishment = sr_establishment;
        this.sr_other_details = sr_other_details;
        this.sr_quantity_silkworm = sr_quantity_silkworm;
        this.sr_quantity_silkworm2 = sr_quantity_silkworm2;
        this.sr_workers = sr_workers;
        this.sr_unit_two = sr_unit_two;
        this.sr_page1 = sr_page1;
        this.sr_page2 = sr_page2;
        this.sr_page3 = sr_page3;
        this.sr_page4 = sr_page4;
        this.sr_page5 = sr_page5;
        this.sr_page6 = sr_page6;
        this.sr_sign = sr_sign;
        this.sr_remark = sr_remark;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.name = name;
        this.nameOfTheRsp = nameOfTheRsp;
        this.registration_no = registration_no;
        this.registered_address = registered_address;
        this.contact_no = contact_no;
        this.DisplayedRegistrationCertificateProminently = DisplayedRegistrationCertificateProminently;
        this.mulberry_area_acre = mulberry_area_acre;
        this.regularity_of_soil_testing_once_in_2_years = regularity_of_soil_testing_once_in_2_years;
        this.recommended_application_of_soil_inputs_fertilizers_fym_after_each_crop = recommended_application_of_soil_inputs_fertilizers_fym_after_each_crop;
        this.is_pruning_harvest_schedule_followed_to_enable_a_minimum_of_32_crops_annum = is_pruning_harvest_schedule_followed_to_enable_a_minimum_of_32_crops_annum;
        this.mulberry_variety = mulberry_variety;
        this.pest_disease_freeness_in_garden = pest_disease_freeness_in_garden;
        this.nutrient_deficiency_symptoms_in_leaves = nutrient_deficiency_symptoms_in_leaves;
        this.average_single_leaf_weight_g = average_single_leaf_weight_g;
        this.irrigation_facility = irrigation_facility;
        this.overall_status = overall_status;
        this.incubation_facility = incubation_facility;
        this.whether_rearing_house_is_as_per_recommended_plan = whether_rearing_house_is_as_per_recommended_plan;
        this.whether_equipments_appliances_as_per_seed_act_guidelines_available = whether_equipments_appliances_as_per_seed_act_guidelines_available;
        this.visual_health_of_chawki_worms = visual_health_of_chawki_worms;
        this.wt_of_100_ii_instar_larvae_g = wt_of_100_ii_instar_larvae_g;
        this.bed_spacing = bed_spacing;
        this.maintenance_of_temp_rh = maintenance_of_temp_rh;
        this.no_of_batches_detected = no_of_batches_detected;
        this.lot_details_of_source_dfls = lot_details_of_source_dfls;
        this.skilled_person_for_testing = skilled_person_for_testing;
        this.disinfection_register = disinfection_register;
        this.dfl_procurement_register = dfl_procurement_register;
        this.rearing_performance_register = rearing_performance_register;
        this.pebrine_testing_register = pebrine_testing_register;
        this.chawki_supply_register = chawki_supply_register;
        this.farm_management_register = farm_management_register;
        this.temp_rh_maintenance_register = temp_rh_maintenance_register;
        this.bill_book = bill_book;
        this.whether_following_self_certification_for_each_supply_lot = whether_following_self_certification_for_each_supply_lot;
        this.image = image;
        this.extra1 = extra1;
        this.extra2 = extra2;
        this.extra3 = extra3;
        this.extra4 = extra4;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtra4() {
        return extra4;
    }

    public void setExtra4(String extra4) {
        this.extra4 = extra4;
    }

    public int getSr_id() {
        return sr_id;
    }

    public void setSr_id(int sr_id) {
        this.sr_id = sr_id;
    }

    public int getSr_status() {
        return sr_status;
    }

    public void setSr_status(int sr_status) {
        this.sr_status = sr_status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getVerification_id() {
        return verification_id;
    }

    public void setVerification_id(int verification_id) {
        this.verification_id = verification_id;
    }

    public int getVerification_status() {
        return verification_status;
    }

    public void setVerification_status(int verification_status) {
        this.verification_status = verification_status;
    }

    public String getSr_registration_no() {
        return sr_registration_no;
    }

    public void setSr_registration_no(String sr_registration_no) {
        this.sr_registration_no = sr_registration_no;
    }

    public String getSr_sirname() {
        return sr_sirname;
    }

    public void setSr_sirname(String sr_sirname) {
        this.sr_sirname = sr_sirname;
    }

    public String getSr_applicant() {
        return sr_applicant;
    }

    public void setSr_applicant(String sr_applicant) {
        this.sr_applicant = sr_applicant;
    }

    public String getSr_behalf_of() {
        return sr_behalf_of;
    }

    public void setSr_behalf_of(String sr_behalf_of) {
        this.sr_behalf_of = sr_behalf_of;
    }

    public String getSr_parent_sirname() {
        return sr_parent_sirname;
    }

    public void setSr_parent_sirname(String sr_parent_sirname) {
        this.sr_parent_sirname = sr_parent_sirname;
    }

    public String getSr_parentsname() {
        return sr_parentsname;
    }

    public void setSr_parentsname(String sr_parentsname) {
        this.sr_parentsname = sr_parentsname;
    }

    public String getSr_gender() {
        return sr_gender;
    }

    public void setSr_gender(String sr_gender) {
        this.sr_gender = sr_gender;
    }

    public String getSr_social_status() {
        return sr_social_status;
    }

    public void setSr_social_status(String sr_social_status) {
        this.sr_social_status = sr_social_status;
    }

    public String getSr_training_certificate() {
        return sr_training_certificate;
    }

    public void setSr_training_certificate(String sr_training_certificate) {
        this.sr_training_certificate = sr_training_certificate;
    }

    public String getSr_training_place() {
        return sr_training_place;
    }

    public void setSr_training_place(String sr_training_place) {
        this.sr_training_place = sr_training_place;
    }

    public String getSr_training_from() {
        return sr_training_from;
    }

    public void setSr_training_from(String sr_training_from) {
        this.sr_training_from = sr_training_from;
    }

    public String getSr_training_to() {
        return sr_training_to;
    }

    public void setSr_training_to(String sr_training_to) {
        this.sr_training_to = sr_training_to;
    }

    public String getSr_educational_certificate() {
        return sr_educational_certificate;
    }

    public void setSr_educational_certificate(String sr_educational_certificate) {
        this.sr_educational_certificate = sr_educational_certificate;
    }

    public String getSr_passport_photo() {
        return sr_passport_photo;
    }

    public void setSr_passport_photo(String sr_passport_photo) {
        this.sr_passport_photo = sr_passport_photo;
    }

    public String getSr_aadhaar_number() {
        return sr_aadhaar_number;
    }

    public void setSr_aadhaar_number(String sr_aadhaar_number) {
        this.sr_aadhaar_number = sr_aadhaar_number;
    }

    public String getSr_country_code() {
        return sr_country_code;
    }

    public void setSr_country_code(String sr_country_code) {
        this.sr_country_code = sr_country_code;
    }

    public String getSr_mobile() {
        return sr_mobile;
    }

    public void setSr_mobile(String sr_mobile) {
        this.sr_mobile = sr_mobile;
    }

    public String getSr_applicant_type() {
        return sr_applicant_type;
    }

    public void setSr_applicant_type(String sr_applicant_type) {
        this.sr_applicant_type = sr_applicant_type;
    }

    public String getSr_unit_name() {
        return sr_unit_name;
    }

    public void setSr_unit_name(String sr_unit_name) {
        this.sr_unit_name = sr_unit_name;
    }

    public String getSr_village() {
        return sr_village;
    }

    public void setSr_village(String sr_village) {
        this.sr_village = sr_village;
    }

    public String getSr_taluk() {
        return sr_taluk;
    }

    public void setSr_taluk(String sr_taluk) {
        this.sr_taluk = sr_taluk;
    }

    public String getSr_state() {
        return sr_state;
    }

    public void setSr_state(String sr_state) {
        this.sr_state = sr_state;
    }

    public String getSr_district() {
        return sr_district;
    }

    public void setSr_district(String sr_district) {
        this.sr_district = sr_district;
    }

    public String getSr_pin() {
        return sr_pin;
    }

    public void setSr_pin(String sr_pin) {
        this.sr_pin = sr_pin;
    }

    public String getSr_address() {
        return sr_address;
    }

    public void setSr_address(String sr_address) {
        this.sr_address = sr_address;
    }

    public String getSr_production_center() {
        return sr_production_center;
    }

    public void setSr_production_center(String sr_production_center) {
        this.sr_production_center = sr_production_center;
    }

    public String getSr_production_center_taluk() {
        return sr_production_center_taluk;
    }

    public void setSr_production_center_taluk(String sr_production_center_taluk) {
        this.sr_production_center_taluk = sr_production_center_taluk;
    }

    public String getSr_production_center_state() {
        return sr_production_center_state;
    }

    public void setSr_production_center_state(String sr_production_center_state) {
        this.sr_production_center_state = sr_production_center_state;
    }

    public String getSr_production_center_district() {
        return sr_production_center_district;
    }

    public void setSr_production_center_district(String sr_production_center_district) {
        this.sr_production_center_district = sr_production_center_district;
    }

    public String getSr_production_center_pin() {
        return sr_production_center_pin;
    }

    public void setSr_production_center_pin(String sr_production_center_pin) {
        this.sr_production_center_pin = sr_production_center_pin;
    }

    public String getSr_production_center_sector() {
        return sr_production_center_sector;
    }

    public void setSr_production_center_sector(String sr_production_center_sector) {
        this.sr_production_center_sector = sr_production_center_sector;
    }

    public String getSr_production_center_address() {
        return sr_production_center_address;
    }

    public void setSr_production_center_address(String sr_production_center_address) {
        this.sr_production_center_address = sr_production_center_address;
    }

    public String getSr_produced() {
        return sr_produced;
    }

    public void setSr_produced(String sr_produced) {
        this.sr_produced = sr_produced;
    }

    public String getSr_unit() {
        return sr_unit;
    }

    public void setSr_unit(String sr_unit) {
        this.sr_unit = sr_unit;
    }

    public String getSr_production_level() {
        return sr_production_level;
    }

    public void setSr_production_level(String sr_production_level) {
        this.sr_production_level = sr_production_level;
    }

    public String getSr_ownership_type() {
        return sr_ownership_type;
    }

    public void setSr_ownership_type(String sr_ownership_type) {
        this.sr_ownership_type = sr_ownership_type;
    }

    public String getSr_establishment() {
        return sr_establishment;
    }

    public void setSr_establishment(String sr_establishment) {
        this.sr_establishment = sr_establishment;
    }

    public String getSr_other_details() {
        return sr_other_details;
    }

    public void setSr_other_details(String sr_other_details) {
        this.sr_other_details = sr_other_details;
    }

    public String getSr_quantity_silkworm() {
        return sr_quantity_silkworm;
    }

    public void setSr_quantity_silkworm(String sr_quantity_silkworm) {
        this.sr_quantity_silkworm = sr_quantity_silkworm;
    }

    public String getSr_quantity_silkworm2() {
        return sr_quantity_silkworm2;
    }

    public void setSr_quantity_silkworm2(String sr_quantity_silkworm2) {
        this.sr_quantity_silkworm2 = sr_quantity_silkworm2;
    }

    public String getSr_workers() {
        return sr_workers;
    }

    public void setSr_workers(String sr_workers) {
        this.sr_workers = sr_workers;
    }

    public String getSr_unit_two() {
        return sr_unit_two;
    }

    public void setSr_unit_two(String sr_unit_two) {
        this.sr_unit_two = sr_unit_two;
    }

    public String getSr_page1() {
        return sr_page1;
    }

    public void setSr_page1(String sr_page1) {
        this.sr_page1 = sr_page1;
    }

    public String getSr_page2() {
        return sr_page2;
    }

    public void setSr_page2(String sr_page2) {
        this.sr_page2 = sr_page2;
    }

    public String getSr_page3() {
        return sr_page3;
    }

    public void setSr_page3(String sr_page3) {
        this.sr_page3 = sr_page3;
    }

    public String getSr_page4() {
        return sr_page4;
    }

    public void setSr_page4(String sr_page4) {
        this.sr_page4 = sr_page4;
    }

    public String getSr_page5() {
        return sr_page5;
    }

    public void setSr_page5(String sr_page5) {
        this.sr_page5 = sr_page5;
    }

    public String getSr_page6() {
        return sr_page6;
    }

    public void setSr_page6(String sr_page6) {
        this.sr_page6 = sr_page6;
    }

    public String getSr_sign() {
        return sr_sign;
    }

    public void setSr_sign(String sr_sign) {
        this.sr_sign = sr_sign;
    }

    public String getSr_remark() {
        return sr_remark;
    }

    public void setSr_remark(String sr_remark) {
        this.sr_remark = sr_remark;
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



    public String getNameOfTheRsp() {
        return nameOfTheRsp;
    }

    public void setNameOfTheRsp(String nameOfTheRsp) {
        this.nameOfTheRsp = nameOfTheRsp;
    }

    public String getRegistration_no() {
        return registration_no;
    }

    public void setRegistration_no(String registration_no) {
        this.registration_no = registration_no;
    }

    public String getRegistered_address() {
        return registered_address;
    }

    public void setRegistered_address(String registered_address) {
        this.registered_address = registered_address;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getDisplayedRegistrationCertificateProminently() {
        return DisplayedRegistrationCertificateProminently;
    }

    public void setDisplayedRegistrationCertificateProminently(String displayedRegistrationCertificateProminently) {
        DisplayedRegistrationCertificateProminently = displayedRegistrationCertificateProminently;
    }

    public String getMulberry_area_acre() {
        return mulberry_area_acre;
    }

    public void setMulberry_area_acre(String mulberry_area_acre) {
        this.mulberry_area_acre = mulberry_area_acre;
    }

    public String getRegularity_of_soil_testing_once_in_2_years() {
        return regularity_of_soil_testing_once_in_2_years;
    }

    public void setRegularity_of_soil_testing_once_in_2_years(String regularity_of_soil_testing_once_in_2_years) {
        this.regularity_of_soil_testing_once_in_2_years = regularity_of_soil_testing_once_in_2_years;
    }

    public String getRecommended_application_of_soil_inputs_fertilizers_fym_after_each_crop() {
        return recommended_application_of_soil_inputs_fertilizers_fym_after_each_crop;
    }

    public void setRecommended_application_of_soil_inputs_fertilizers_fym_after_each_crop(String recommended_application_of_soil_inputs_fertilizers_fym_after_each_crop) {
        this.recommended_application_of_soil_inputs_fertilizers_fym_after_each_crop = recommended_application_of_soil_inputs_fertilizers_fym_after_each_crop;
    }

    public String getIs_pruning_harvest_schedule_followed_to_enable_a_minimum_of_32_crops_annum() {
        return is_pruning_harvest_schedule_followed_to_enable_a_minimum_of_32_crops_annum;
    }

    public void setIs_pruning_harvest_schedule_followed_to_enable_a_minimum_of_32_crops_annum(String is_pruning_harvest_schedule_followed_to_enable_a_minimum_of_32_crops_annum) {
        this.is_pruning_harvest_schedule_followed_to_enable_a_minimum_of_32_crops_annum = is_pruning_harvest_schedule_followed_to_enable_a_minimum_of_32_crops_annum;
    }

    public String getMulberry_variety() {
        return mulberry_variety;
    }

    public void setMulberry_variety(String mulberry_variety) {
        this.mulberry_variety = mulberry_variety;
    }

    public String getPest_disease_freeness_in_garden() {
        return pest_disease_freeness_in_garden;
    }

    public void setPest_disease_freeness_in_garden(String pest_disease_freeness_in_garden) {
        this.pest_disease_freeness_in_garden = pest_disease_freeness_in_garden;
    }

    public String getNutrient_deficiency_symptoms_in_leaves() {
        return nutrient_deficiency_symptoms_in_leaves;
    }

    public void setNutrient_deficiency_symptoms_in_leaves(String nutrient_deficiency_symptoms_in_leaves) {
        this.nutrient_deficiency_symptoms_in_leaves = nutrient_deficiency_symptoms_in_leaves;
    }

    public String getAverage_single_leaf_weight_g() {
        return average_single_leaf_weight_g;
    }

    public void setAverage_single_leaf_weight_g(String average_single_leaf_weight_g) {
        this.average_single_leaf_weight_g = average_single_leaf_weight_g;
    }

    public String getIrrigation_facility() {
        return irrigation_facility;
    }

    public void setIrrigation_facility(String irrigation_facility) {
        this.irrigation_facility = irrigation_facility;
    }

    public String getOverall_status() {
        return overall_status;
    }

    public void setOverall_status(String overall_status) {
        this.overall_status = overall_status;
    }

    public String getIncubation_facility() {
        return incubation_facility;
    }

    public void setIncubation_facility(String incubation_facility) {
        this.incubation_facility = incubation_facility;
    }

    public String getWhether_rearing_house_is_as_per_recommended_plan() {
        return whether_rearing_house_is_as_per_recommended_plan;
    }

    public void setWhether_rearing_house_is_as_per_recommended_plan(String whether_rearing_house_is_as_per_recommended_plan) {
        this.whether_rearing_house_is_as_per_recommended_plan = whether_rearing_house_is_as_per_recommended_plan;
    }

    public String getWhether_equipments_appliances_as_per_seed_act_guidelines_available() {
        return whether_equipments_appliances_as_per_seed_act_guidelines_available;
    }

    public void setWhether_equipments_appliances_as_per_seed_act_guidelines_available(String whether_equipments_appliances_as_per_seed_act_guidelines_available) {
        this.whether_equipments_appliances_as_per_seed_act_guidelines_available = whether_equipments_appliances_as_per_seed_act_guidelines_available;
    }

    public String getVisual_health_of_chawki_worms() {
        return visual_health_of_chawki_worms;
    }

    public void setVisual_health_of_chawki_worms(String visual_health_of_chawki_worms) {
        this.visual_health_of_chawki_worms = visual_health_of_chawki_worms;
    }

    public String getWt_of_100_ii_instar_larvae_g() {
        return wt_of_100_ii_instar_larvae_g;
    }

    public void setWt_of_100_ii_instar_larvae_g(String wt_of_100_ii_instar_larvae_g) {
        this.wt_of_100_ii_instar_larvae_g = wt_of_100_ii_instar_larvae_g;
    }

    public String getBed_spacing() {
        return bed_spacing;
    }

    public void setBed_spacing(String bed_spacing) {
        this.bed_spacing = bed_spacing;
    }

    public String getMaintenance_of_temp_rh() {
        return maintenance_of_temp_rh;
    }

    public void setMaintenance_of_temp_rh(String maintenance_of_temp_rh) {
        this.maintenance_of_temp_rh = maintenance_of_temp_rh;
    }

    public String getNo_of_batches_detected() {
        return no_of_batches_detected;
    }

    public void setNo_of_batches_detected(String no_of_batches_detected) {
        this.no_of_batches_detected = no_of_batches_detected;
    }

    public String getLot_details_of_source_dfls() {
        return lot_details_of_source_dfls;
    }

    public void setLot_details_of_source_dfls(String lot_details_of_source_dfls) {
        this.lot_details_of_source_dfls = lot_details_of_source_dfls;
    }

    public String getSkilled_person_for_testing() {
        return skilled_person_for_testing;
    }

    public void setSkilled_person_for_testing(String skilled_person_for_testing) {
        this.skilled_person_for_testing = skilled_person_for_testing;
    }

    public String getDisinfection_register() {
        return disinfection_register;
    }

    public void setDisinfection_register(String disinfection_register) {
        this.disinfection_register = disinfection_register;
    }

    public String getDfl_procurement_register() {
        return dfl_procurement_register;
    }

    public void setDfl_procurement_register(String dfl_procurement_register) {
        this.dfl_procurement_register = dfl_procurement_register;
    }

    public String getRearing_performance_register() {
        return rearing_performance_register;
    }

    public void setRearing_performance_register(String rearing_performance_register) {
        this.rearing_performance_register = rearing_performance_register;
    }

    public String getPebrine_testing_register() {
        return pebrine_testing_register;
    }

    public void setPebrine_testing_register(String pebrine_testing_register) {
        this.pebrine_testing_register = pebrine_testing_register;
    }

    public String getChawki_supply_register() {
        return chawki_supply_register;
    }

    public void setChawki_supply_register(String chawki_supply_register) {
        this.chawki_supply_register = chawki_supply_register;
    }

    public String getFarm_management_register() {
        return farm_management_register;
    }

    public void setFarm_management_register(String farm_management_register) {
        this.farm_management_register = farm_management_register;
    }

    public String getTemp_rh_maintenance_register() {
        return temp_rh_maintenance_register;
    }

    public void setTemp_rh_maintenance_register(String temp_rh_maintenance_register) {
        this.temp_rh_maintenance_register = temp_rh_maintenance_register;
    }

    public String getBill_book() {
        return bill_book;
    }

    public void setBill_book(String bill_book) {
        this.bill_book = bill_book;
    }

    public String getWhether_following_self_certification_for_each_supply_lot() {
        return whether_following_self_certification_for_each_supply_lot;
    }

    public void setWhether_following_self_certification_for_each_supply_lot(String whether_following_self_certification_for_each_supply_lot) {
        this.whether_following_self_certification_for_each_supply_lot = whether_following_self_certification_for_each_supply_lot;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getExtra1() {
        return extra1;
    }

    public void setExtra1(String extra1) {
        this.extra1 = extra1;
    }

    public String getExtra2() {
        return extra2;
    }

    public void setExtra2(String extra2) {
        this.extra2 = extra2;
    }

    public String getExtra3() {
        return extra3;
    }

    public void setExtra3(String extra3) {
        this.extra3 = extra3;
    }
}