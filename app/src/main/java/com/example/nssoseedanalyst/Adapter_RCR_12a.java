package com.example.nssoseedanalyst;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter_RCR_12a extends RecyclerView.Adapter<Adapter_RCR_12a.MyViewHolder> {


    private Context context;
    private List<Model_RCR_12a> liveList;

    private ProductClick productClick;

    //make interface like this
    public interface ProductClick {


        void RCRClick12a_complete(int position,int sp_id, int sp_status, int status, int verification_id, int verification_status,
                                  String sp_registration_no, String sp_sirname, String sp_applicant, String sp_behalf_of,
                                  String sp_parent_sirname, String sp_parentsname, String sp_gender, String sp_social_status,
                                  String sp_training_certificate, String sp_training_place, String sp_training_from,
                                  String sp_training_to, String sp_educational_certificate, String sp_passport_photo,
                                  String sp_aadhaar_number, String sp_country_code, String sp_mobile, String sp_applicant_type,
                                  String sp_unit_name, String sp_village, String sp_taluk, String sp_state, String sp_district,
                                  String sp_pin, String sp_address, String sp_production_center, String sp_production_center_taluk,
                                  String sp_production_center_state, String sp_production_center_district,
                                  String sp_production_center_pin, String sp_production_center_sector,
                                  String sp_production_center_address, String sp_produced, String sp_unit,
                                  String sp_production_level, String sp_ownership_type, String sp_establishment,
                                  String sp_other_details, String sp_quantity_silkworm, String sp_quantity_silkworm2,
                                  String sp_workers, String sp_unit_two, String sp_page1, String sp_page2, String sp_page3,
                                  String sp_page4, String sp_page5, String sp_page6, String sp_sign, String sp_remark,
                                  String created_at, String updated_at, String name,  String nameOfTheRsp,
                                  String registration_no, String registered_address, String contact_no,
                                  String displayed_ther_registration_certificate_prominently,
                                  String number_of_regd_seed_cocoon_producers_with_rsp,
                                  String whether_seed_cocoons_are_purchased_from_regd_seed_cocoon_producers_for_every_crop,
                                  String whether_seed_cocoons_are_purchased_as_per_norms,
                                  String whether_seed_cocoons_are_properly_sorted_and_sex_separated,
                                  String whether_pupal_testing_is_done_for_pebrine, String sex_separation_efficiency,
                                  String male_female_ratio, String whether_each_parental_pupae_are_kept_in_separate_room,
                                  String whether_proper_temp_rh_maintained_in_cocoon_pupae_oviposition_rooms,
                                  String whether_seeds_are_properly_washed_dried_and_packed_as_recommended,
                                  String whether_male_moths_are_preserved_in_cold_room,
                                  String whether_cold_rooms_have_uninterrupted_power_supply,
                                  String whether_proper_temp_humidity_are_maintained_for_oviposition,
                                  String whether_incubation_chamber_available,
                                  String whether_seeds_are_properly_labeled_for_race_lot_no_dol,
                                  String no_of_batches_detected, String lot_details_of_source_seed_cocoon,
                                  String skilled_person_for_testing, String disinfection_register,
                                  String seed_cocoon_procurement_register, String cocoon_processing_register,
                                  String pebrine_testing_register, String dfl_supply_register,
                                  String test_hatching_register_for_dfls_supplied, String temp_rh_maintenance_register,
                                  String bill_book_for_dfl_supply, String whether_following_self_certification_for_each_supply_lot,
                                  String whether_quarterly_reports_are_being_regularly_submitted_to_nsso, String image,
                                  String extra1, String extra2, String extra3, String extra4);
        void RCRClick12a_underprocess(int position,int sp_id, int sp_status, int status, int verification_id, int verification_status,
                                      String sp_registration_no, String sp_sirname, String sp_applicant, String sp_behalf_of,
                                      String sp_parent_sirname, String sp_parentsname, String sp_gender, String sp_social_status,
                                      String sp_training_certificate, String sp_training_place, String sp_training_from,
                                      String sp_training_to, String sp_educational_certificate, String sp_passport_photo,
                                      String sp_aadhaar_number, String sp_country_code, String sp_mobile, String sp_applicant_type,
                                      String sp_unit_name, String sp_village, String sp_taluk, String sp_state, String sp_district,
                                      String sp_pin, String sp_address, String sp_production_center, String sp_production_center_taluk,
                                      String sp_production_center_state, String sp_production_center_district,
                                      String sp_production_center_pin, String sp_production_center_sector,
                                      String sp_production_center_address, String sp_produced, String sp_unit,
                                      String sp_production_level, String sp_ownership_type, String sp_establishment,
                                      String sp_other_details, String sp_quantity_silkworm, String sp_quantity_silkworm2,
                                      String sp_workers, String sp_unit_two, String sp_page1, String sp_page2, String sp_page3,
                                      String sp_page4, String sp_page5, String sp_page6, String sp_sign, String sp_remark,
                                      String created_at, String updated_at,  String name,   String nameOfTheRsp,
                                      String registration_no, String registered_address, String contact_no,
                                      String displayed_ther_registration_certificate_prominently,
                                      String number_of_regd_seed_cocoon_producers_with_rsp,
                                      String whether_seed_cocoons_are_purchased_from_regd_seed_cocoon_producers_for_every_crop,
                                      String whether_seed_cocoons_are_purchased_as_per_norms,
                                      String whether_seed_cocoons_are_properly_sorted_and_sex_separated,
                                      String whether_pupal_testing_is_done_for_pebrine, String sex_separation_efficiency,
                                      String male_female_ratio, String whether_each_parental_pupae_are_kept_in_separate_room,
                                      String whether_proper_temp_rh_maintained_in_cocoon_pupae_oviposition_rooms,
                                      String whether_seeds_are_properly_washed_dried_and_packed_as_recommended,
                                      String whether_male_moths_are_preserved_in_cold_room,
                                      String whether_cold_rooms_have_uninterrupted_power_supply,
                                      String whether_proper_temp_humidity_are_maintained_for_oviposition,
                                      String whether_incubation_chamber_available,
                                      String whether_seeds_are_properly_labeled_for_race_lot_no_dol,
                                      String no_of_batches_detected, String lot_details_of_source_seed_cocoon,
                                      String skilled_person_for_testing, String disinfection_register,
                                      String seed_cocoon_procurement_register, String cocoon_processing_register,
                                      String pebrine_testing_register, String dfl_supply_register,
                                      String test_hatching_register_for_dfls_supplied, String temp_rh_maintenance_register,
                                      String bill_book_for_dfl_supply, String whether_following_self_certification_for_each_supply_lot,
                                      String whether_quarterly_reports_are_being_regularly_submitted_to_nsso, String image,
                                      String extra1, String extra2, String extra3, String extra4);

        void RCRClick12a_data(int position,int sp_id, int sp_status, int status, int verification_id, int verification_status,
                              String sp_registration_no, String sp_sirname, String sp_applicant, String sp_behalf_of,
                              String sp_parent_sirname, String sp_parentsname, String sp_gender, String sp_social_status,
                              String sp_training_certificate, String sp_training_place, String sp_training_from,
                              String sp_training_to, String sp_educational_certificate, String sp_passport_photo,
                              String sp_aadhaar_number, String sp_country_code, String sp_mobile, String sp_applicant_type,
                              String sp_unit_name, String sp_village, String sp_taluk, String sp_state, String sp_district,
                              String sp_pin, String sp_address, String sp_production_center, String sp_production_center_taluk,
                              String sp_production_center_state, String sp_production_center_district,
                              String sp_production_center_pin, String sp_production_center_sector,
                              String sp_production_center_address, String sp_produced, String sp_unit,
                              String sp_production_level, String sp_ownership_type, String sp_establishment,
                              String sp_other_details, String sp_quantity_silkworm, String sp_quantity_silkworm2,
                              String sp_workers, String sp_unit_two, String sp_page1, String sp_page2, String sp_page3,
                              String sp_page4, String sp_page5, String sp_page6, String sp_sign, String sp_remark,
                              String created_at, String updated_at, String name);


    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName, tvID;

        RelativeLayout rlNotEvaluated, rlLayoutRCR,rlDone,rlUnderProcess,rlVerificationDone;
        public MyViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tvName);
            tvID = view.findViewById(R.id.tvID);
            rlNotEvaluated = view.findViewById(R.id.rlNotEvaluated);
            rlLayoutRCR = view.findViewById(R.id.rlLayoutRCR);
            rlUnderProcess = view.findViewById(R.id.rlUnderProcess);
            rlDone = view.findViewById(R.id.rlDone);
            rlVerificationDone = view.findViewById(R.id.rlVerificationDone);
        }
    }

    public Adapter_RCR_12a(Context context, List<Model_RCR_12a> liveList) {
        //List<SiderImageModel> slider_image_list
        this.context = context;
        this.liveList = liveList;
        ////this.slider_image_list = slider_image_list;

    }

    public void setData(List<Model_RCR_12a> newData) {
        this.liveList = newData;
        // Save data to cache
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {

        return liveList.size();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_rcr, parent, false);
        return new MyViewHolder(itemView);


    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Model_RCR_12a live = liveList.get(position);

        holder.tvName.setText(live.getSp_sirname()+" "+live.getSp_applicant());
        holder.tvID.setText(live.getSp_registration_no());

        // Check if there is another item with the same sp_registration_no
        boolean hasDuplicateRegistrationNo = false;
        int duplicateItemPosition = -1;

        for (int i = 0; i < getItemCount(); i++) {
            if (i != position && liveList.get(i).getSp_registration_no().equals(live.getSp_registration_no())) {
                hasDuplicateRegistrationNo = true;
                duplicateItemPosition = i;
                break;
            }
        }

        if (hasDuplicateRegistrationNo) {
            // Compare verification_id values for the duplicate items
            int currentVerificationId = live.getVerification_id();
            int duplicateVerificationId = liveList.get(duplicateItemPosition).getVerification_id();

            if (currentVerificationId < duplicateVerificationId) {
                // Current item has a smaller verification_id, show the views accordingly

                if (live.getVerification_status()==0)
                {
                    holder.rlNotEvaluated.setVisibility(View.VISIBLE);

                    holder.rlDone.setVisibility(View.GONE);
                    holder.rlUnderProcess.setVisibility(View.GONE);

                    holder.rlNotEvaluated.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            productClick.RCRClick12a_data(position, live.getSp_id(), live.getSp_status(),live.getStatus(),
                                    live.getVerification_id(),  live.getVerification_status(), live.getSp_registration_no(),
                                    live.getSp_sirname(), live.getSp_applicant(),live.getSp_behalf_of(),
                                    live.getSp_parent_sirname(),
                                    live.getSp_parentsname(), live.getSp_gender(), live.getSp_social_status(),
                                    live.getSp_training_certificate(),
                                    live.getSp_training_place(), live.getSp_training_from(), live.getSp_training_to(),
                                    live.getSp_educational_certificate(), live.getSp_passport_photo(), live.getSp_aadhaar_number(),
                                    live.getSp_country_code(), live.getSp_mobile(), live.getSp_applicant_type(),
                                    live.getSp_unit_name(),
                                    live.getSp_village(), live.getSp_taluk(), live.getSp_state(), live.getSp_district(),
                                    live.getSp_pin(), live.getSp_address(),
                                    live.getSp_production_center(), live.getSp_production_center_taluk(),
                                    live.getSp_production_center_state(),
                                    live.getSp_production_center_district(), live.getSp_production_center_pin(),
                                    live.getSp_production_center_sector(),
                                    live.getSp_production_center_address(),
                                    live.getSp_produced(), live.getSp_unit(), live.getSp_production_level(),
                                    live.getSp_ownership_type(),
                                    live.getSp_establishment(), live.getSp_other_details(), live.getSp_quantity_silkworm(),
                                    live.getSp_quantity_silkworm2(), live.getSp_workers(),
                                    live.getSp_unit_two(), live.getSp_page1(), live.getSp_page2(), live.getSp_page3(),
                                    live.getSp_page4(),
                                    live.getSp_page5(), live.getSp_page6(), live.getSp_sign(), live.getSp_remark(),
                                    live.getCreated_at(), live.getUpdated_at(), live.getName());
                            final Animation myAnim = AnimationUtils.loadAnimation(context, R.anim.bounce);
                            holder.rlNotEvaluated.startAnimation(myAnim);

                        }
                    });


                }
                else if (live.getVerification_status()==1)
                {
                    holder.rlDone.setVisibility(View.VISIBLE);
                    holder.rlNotEvaluated.setVisibility(View.GONE);
                    holder.rlUnderProcess.setVisibility(View.GONE);

//            Toast.makeText(context, ""+live.getRegistration_no(), Toast.LENGTH_SHORT).show();

                    holder.rlDone.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            productClick.RCRClick12a_complete(position, live.getSp_id(), live.getSp_status(),live.getStatus(),live.getVerification_id(),
                                    live.getVerification_status(),live.getSp_registration_no(),
                                    live.getSp_sirname(), live.getSp_applicant(),live.getSp_behalf_of(), live.getSp_parent_sirname(),
                                    live.getSp_parentsname(), live.getSp_gender(), live.getSp_social_status(), live.getSp_training_certificate(),
                                    live.getSp_training_place(), live.getSp_training_from(), live.getSp_training_to(),
                                    live.getSp_educational_certificate(), live.getSp_passport_photo(), live.getSp_aadhaar_number(),
                                    live.getSp_country_code(), live.getSp_mobile(), live.getSp_applicant_type(), live.getSp_unit_name(),
                                    live.getSp_village(), live.getSp_taluk(), live.getSp_state(), live.getSp_district(), live.getSp_pin(), live.getSp_address(),
                                    live.getSp_production_center(), live.getSp_production_center_taluk(), live.getSp_production_center_state(),
                                    live.getSp_production_center_district(), live.getSp_production_center_pin(), live.getSp_production_center_sector(),
                                    live.getSp_production_center_address(),
                                    live.getSp_produced(), live.getSp_unit(), live.getSp_production_level(), live.getSp_ownership_type(),
                                    live.getSp_establishment(), live.getSp_other_details(), live.getSp_quantity_silkworm(), live.getSp_quantity_silkworm2(),
                                    live.getSp_workers(),
                                    live.getSp_unit_two(), live.getSp_page1(), live.getSp_page2(), live.getSp_page3(), live.getSp_page4(),
                                    live.getSp_page5(), live.getSp_page6(), live.getSp_sign(), live.getSp_remark(),live.getCreated_at()
                                    ,live.getUpdated_at(), live.getName(), live.getNameOfTheRsp(),live.getRegistration_no()
                                    ,live.getRegistered_address(),live.getContact_no(),live.getDisplayed_ther_registration_certificate_prominently()
                                    ,live.getNumber_of_regd_seed_cocoon_producers_with_rsp(),live.getWhether_seed_cocoons_are_purchased_from_regd_seed_cocoon_producers_for_every_crop()
                                    ,live.getWhether_seed_cocoons_are_purchased_as_per_norms(),live.getWhether_seed_cocoons_are_properly_sorted_and_sex_separated()
                                    ,live.getWhether_pupal_testing_is_done_for_pebrine(),live.getSex_separation_efficiency()
                                    ,live.getMale_female_ratio(),live.getWhether_each_parental_pupae_are_kept_in_separate_room()
                                    ,live.getWhether_proper_temp_rh_maintained_in_cocoon_pupae_oviposition_rooms()
                                    ,live.getWhether_seeds_are_properly_washed_dried_and_packed_as_recommended()
                                    ,live.getWhether_male_moths_are_preserved_in_cold_room(),live.getWhether_cold_rooms_have_uninterrupted_power_supply()
                                    ,live.getWhether_proper_temp_humidity_are_maintained_for_oviposition(),live.getWhether_incubation_chamber_available()
                                    ,live.getWhether_seeds_are_properly_labeled_for_race_lot_no_dol(),live.getNo_of_batches_detected()
                                    ,live.getLot_details_of_source_seed_cocoon(),live.getSkilled_person_for_testing()
                                    ,live.getDisinfection_register(),live.getSeed_cocoon_procurement_register()
                                    ,live.getCocoon_processing_register(),live.getPebrine_testing_register(),
                                    live.getDfl_supply_register(),live.getTest_hatching_register_for_dfls_supplied()
                                    ,live.getTemp_rh_maintenance_register(),live.getBill_book_for_dfl_supply()
                                    ,live.getWhether_following_self_certification_for_each_supply_lot()
                                    ,live.getWhether_quarterly_reports_are_being_regularly_submitted_to_nsso()
                                    ,live.getImage() ,live.getExtra1() ,live.getExtra2() ,live.getExtra3(),live.getExtra4());
                            final Animation myAnim = AnimationUtils.loadAnimation(context, R.anim.bounce);
                            holder.rlDone.startAnimation(myAnim);

                        }
                    });




                }
                else if (live.getVerification_status()==2)
                {
                    holder.rlNotEvaluated.setVisibility(View.VISIBLE);

                    holder.rlDone.setVisibility(View.GONE);
                    holder.rlUnderProcess.setVisibility(View.GONE);

                    holder.rlNotEvaluated.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            productClick.RCRClick12a_underprocess(position, live.getSp_id(), live.getSp_status(),live.getStatus(),live.getVerification_id(),
                                    live.getVerification_status(),live.getSp_registration_no(),
                                    live.getSp_sirname(), live.getSp_applicant(),live.getSp_behalf_of(), live.getSp_parent_sirname(),
                                    live.getSp_parentsname(), live.getSp_gender(), live.getSp_social_status(), live.getSp_training_certificate(),
                                    live.getSp_training_place(), live.getSp_training_from(), live.getSp_training_to(),
                                    live.getSp_educational_certificate(), live.getSp_passport_photo(), live.getSp_aadhaar_number(),
                                    live.getSp_country_code(), live.getSp_mobile(), live.getSp_applicant_type(), live.getSp_unit_name(),
                                    live.getSp_village(), live.getSp_taluk(), live.getSp_state(), live.getSp_district(), live.getSp_pin(), live.getSp_address(),
                                    live.getSp_production_center(), live.getSp_production_center_taluk(), live.getSp_production_center_state(),
                                    live.getSp_production_center_district(), live.getSp_production_center_pin(), live.getSp_production_center_sector(),
                                    live.getSp_production_center_address(),
                                    live.getSp_produced(), live.getSp_unit(), live.getSp_production_level(), live.getSp_ownership_type(),
                                    live.getSp_establishment(), live.getSp_other_details(), live.getSp_quantity_silkworm(), live.getSp_quantity_silkworm2(),
                                    live.getSp_workers(),
                                    live.getSp_unit_two(), live.getSp_page1(), live.getSp_page2(), live.getSp_page3(), live.getSp_page4(),
                                    live.getSp_page5(), live.getSp_page6(), live.getSp_sign(), live.getSp_remark(),live.getCreated_at()
                                    ,live.getUpdated_at(), live.getName(), live.getNameOfTheRsp(),live.getRegistration_no()
                                    ,live.getRegistered_address(),live.getContact_no(),live.getDisplayed_ther_registration_certificate_prominently()
                                    ,live.getNumber_of_regd_seed_cocoon_producers_with_rsp(),live.getWhether_seed_cocoons_are_purchased_from_regd_seed_cocoon_producers_for_every_crop()
                                    ,live.getWhether_seed_cocoons_are_purchased_as_per_norms(),live.getWhether_seed_cocoons_are_properly_sorted_and_sex_separated()
                                    ,live.getWhether_pupal_testing_is_done_for_pebrine(),live.getSex_separation_efficiency()
                                    ,live.getMale_female_ratio(),live.getWhether_each_parental_pupae_are_kept_in_separate_room()
                                    ,live.getWhether_proper_temp_rh_maintained_in_cocoon_pupae_oviposition_rooms()
                                    ,live.getWhether_seeds_are_properly_washed_dried_and_packed_as_recommended()
                                    ,live.getWhether_male_moths_are_preserved_in_cold_room(),live.getWhether_cold_rooms_have_uninterrupted_power_supply()
                                    ,live.getWhether_proper_temp_humidity_are_maintained_for_oviposition(),live.getWhether_incubation_chamber_available()
                                    ,live.getWhether_seeds_are_properly_labeled_for_race_lot_no_dol(),live.getNo_of_batches_detected()
                                    ,live.getLot_details_of_source_seed_cocoon(),live.getSkilled_person_for_testing()
                                    ,live.getDisinfection_register(),live.getSeed_cocoon_procurement_register()
                                    ,live.getCocoon_processing_register(),live.getPebrine_testing_register(),
                                    live.getDfl_supply_register(),live.getTest_hatching_register_for_dfls_supplied()
                                    ,live.getTemp_rh_maintenance_register(),live.getBill_book_for_dfl_supply()
                                    ,live.getWhether_following_self_certification_for_each_supply_lot()
                                    ,live.getWhether_quarterly_reports_are_being_regularly_submitted_to_nsso()
                                    ,live.getImage() ,live.getExtra1() ,live.getExtra2() ,live.getExtra3(),live.getExtra4());
                            final Animation myAnim = AnimationUtils.loadAnimation(context, R.anim.bounce);
                            holder.rlNotEvaluated.startAnimation(myAnim);

                        }
                    });

                }

            } else {
                if (live.getVerification_status()==0)
                {
                    holder.rlVerificationDone.setVisibility(View.VISIBLE);
                    holder.rlDone.setVisibility(View.GONE);
                    holder.rlNotEvaluated.setVisibility(View.GONE);
                    holder.rlUnderProcess.setVisibility(View.GONE);


                    holder.rlVerificationDone.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(context, "This is old data of Registration No. "
                                    +live.getRegistration_no(), Toast.LENGTH_SHORT).show();
                            final Animation myAnim = AnimationUtils.loadAnimation(context, R.anim.bounce);
                            holder.rlVerificationDone.startAnimation(myAnim);

                        }
                    });


                }
                else if (live.getVerification_status()==1)
                {
                    holder.rlVerificationDone.setVisibility(View.VISIBLE);
                    holder.rlDone.setVisibility(View.GONE);
                    holder.rlNotEvaluated.setVisibility(View.GONE);
                    holder.rlUnderProcess.setVisibility(View.GONE);

//            Toast.makeText(context, ""+live.getRegistration_no(), Toast.LENGTH_SHORT).show();

                    holder.rlVerificationDone.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(context, "This is old data of Registration No. "
                                    +live.getRegistration_no(), Toast.LENGTH_SHORT).show();

                            final Animation myAnim = AnimationUtils.loadAnimation(context, R.anim.bounce);
                            holder.rlVerificationDone.startAnimation(myAnim);

                        }
                    });




                }
                else if (live.getVerification_status()==2)
                {
                    holder.rlVerificationDone.setVisibility(View.VISIBLE);
                    holder.rlDone.setVisibility(View.GONE);
                    holder.rlNotEvaluated.setVisibility(View.GONE);
                    holder.rlUnderProcess.setVisibility(View.GONE);

//            Toast.makeText(context, ""+live.getRegistration_no(), Toast.LENGTH_SHORT).show();

                    holder.rlVerificationDone.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(context, "This is old data of Registration No. "
                                    +live.getRegistration_no(), Toast.LENGTH_SHORT).show();

                            final Animation myAnim = AnimationUtils.loadAnimation(context, R.anim.bounce);
                            holder.rlVerificationDone.startAnimation(myAnim);

                        }
                    });

                }

            }
        } else {
            // No duplicate sp_registration_no, show views based on verification_status
            if (live.getVerification_status()==0)
            {
                holder.rlNotEvaluated.setVisibility(View.VISIBLE);

                holder.rlDone.setVisibility(View.GONE);
                holder.rlUnderProcess.setVisibility(View.GONE);

                holder.rlNotEvaluated.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        productClick.RCRClick12a_data(position, live.getSp_id(), live.getSp_status(),live.getStatus(),
                                live.getVerification_id(),  live.getVerification_status(), live.getSp_registration_no(),
                                live.getSp_sirname(), live.getSp_applicant(),live.getSp_behalf_of(),
                                live.getSp_parent_sirname(),
                                live.getSp_parentsname(), live.getSp_gender(), live.getSp_social_status(),
                                live.getSp_training_certificate(),
                                live.getSp_training_place(), live.getSp_training_from(), live.getSp_training_to(),
                                live.getSp_educational_certificate(), live.getSp_passport_photo(), live.getSp_aadhaar_number(),
                                live.getSp_country_code(), live.getSp_mobile(), live.getSp_applicant_type(),
                                live.getSp_unit_name(),
                                live.getSp_village(), live.getSp_taluk(), live.getSp_state(), live.getSp_district(),
                                live.getSp_pin(), live.getSp_address(),
                                live.getSp_production_center(), live.getSp_production_center_taluk(),
                                live.getSp_production_center_state(),
                                live.getSp_production_center_district(), live.getSp_production_center_pin(),
                                live.getSp_production_center_sector(),
                                live.getSp_production_center_address(),
                                live.getSp_produced(), live.getSp_unit(), live.getSp_production_level(),
                                live.getSp_ownership_type(),
                                live.getSp_establishment(), live.getSp_other_details(), live.getSp_quantity_silkworm(),
                                live.getSp_quantity_silkworm2(), live.getSp_workers(),
                                live.getSp_unit_two(), live.getSp_page1(), live.getSp_page2(), live.getSp_page3(),
                                live.getSp_page4(),
                                live.getSp_page5(), live.getSp_page6(), live.getSp_sign(), live.getSp_remark(),
                                live.getCreated_at(), live.getUpdated_at(), live.getName());
                        final Animation myAnim = AnimationUtils.loadAnimation(context, R.anim.bounce);
                        holder.rlNotEvaluated.startAnimation(myAnim);

                    }
                });


            }
            else if (live.getVerification_status()==1)
            {
                holder.rlDone.setVisibility(View.VISIBLE);
                holder.rlNotEvaluated.setVisibility(View.GONE);
                holder.rlUnderProcess.setVisibility(View.GONE);

//            Toast.makeText(context, ""+live.getRegistration_no(), Toast.LENGTH_SHORT).show();

                holder.rlDone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        productClick.RCRClick12a_complete(position, live.getSp_id(), live.getSp_status(),live.getStatus(),live.getVerification_id(),
                                live.getVerification_status(),live.getSp_registration_no(),
                                live.getSp_sirname(), live.getSp_applicant(),live.getSp_behalf_of(), live.getSp_parent_sirname(),
                                live.getSp_parentsname(), live.getSp_gender(), live.getSp_social_status(), live.getSp_training_certificate(),
                                live.getSp_training_place(), live.getSp_training_from(), live.getSp_training_to(),
                                live.getSp_educational_certificate(), live.getSp_passport_photo(), live.getSp_aadhaar_number(),
                                live.getSp_country_code(), live.getSp_mobile(), live.getSp_applicant_type(), live.getSp_unit_name(),
                                live.getSp_village(), live.getSp_taluk(), live.getSp_state(), live.getSp_district(), live.getSp_pin(), live.getSp_address(),
                                live.getSp_production_center(), live.getSp_production_center_taluk(), live.getSp_production_center_state(),
                                live.getSp_production_center_district(), live.getSp_production_center_pin(), live.getSp_production_center_sector(),
                                live.getSp_production_center_address(),
                                live.getSp_produced(), live.getSp_unit(), live.getSp_production_level(), live.getSp_ownership_type(),
                                live.getSp_establishment(), live.getSp_other_details(), live.getSp_quantity_silkworm(), live.getSp_quantity_silkworm2(),
                                live.getSp_workers(),
                                live.getSp_unit_two(), live.getSp_page1(), live.getSp_page2(), live.getSp_page3(), live.getSp_page4(),
                                live.getSp_page5(), live.getSp_page6(), live.getSp_sign(), live.getSp_remark(),live.getCreated_at()
                                ,live.getUpdated_at(), live.getName(), live.getNameOfTheRsp(),live.getRegistration_no()
                                ,live.getRegistered_address(),live.getContact_no(),live.getDisplayed_ther_registration_certificate_prominently()
                                ,live.getNumber_of_regd_seed_cocoon_producers_with_rsp(),live.getWhether_seed_cocoons_are_purchased_from_regd_seed_cocoon_producers_for_every_crop()
                                ,live.getWhether_seed_cocoons_are_purchased_as_per_norms(),live.getWhether_seed_cocoons_are_properly_sorted_and_sex_separated()
                                ,live.getWhether_pupal_testing_is_done_for_pebrine(),live.getSex_separation_efficiency()
                                ,live.getMale_female_ratio(),live.getWhether_each_parental_pupae_are_kept_in_separate_room()
                                ,live.getWhether_proper_temp_rh_maintained_in_cocoon_pupae_oviposition_rooms()
                                ,live.getWhether_seeds_are_properly_washed_dried_and_packed_as_recommended()
                                ,live.getWhether_male_moths_are_preserved_in_cold_room(),live.getWhether_cold_rooms_have_uninterrupted_power_supply()
                                ,live.getWhether_proper_temp_humidity_are_maintained_for_oviposition(),live.getWhether_incubation_chamber_available()
                                ,live.getWhether_seeds_are_properly_labeled_for_race_lot_no_dol(),live.getNo_of_batches_detected()
                                ,live.getLot_details_of_source_seed_cocoon(),live.getSkilled_person_for_testing()
                                ,live.getDisinfection_register(),live.getSeed_cocoon_procurement_register()
                                ,live.getCocoon_processing_register(),live.getPebrine_testing_register(),
                                live.getDfl_supply_register(),live.getTest_hatching_register_for_dfls_supplied()
                                ,live.getTemp_rh_maintenance_register(),live.getBill_book_for_dfl_supply()
                                ,live.getWhether_following_self_certification_for_each_supply_lot()
                                ,live.getWhether_quarterly_reports_are_being_regularly_submitted_to_nsso()
                                ,live.getImage() ,live.getExtra1() ,live.getExtra2() ,live.getExtra3(),live.getExtra4());
                        final Animation myAnim = AnimationUtils.loadAnimation(context, R.anim.bounce);
                        holder.rlDone.startAnimation(myAnim);

                    }
                });




            }
            else if (live.getVerification_status()==2)
            {
                holder.rlNotEvaluated.setVisibility(View.VISIBLE);

                holder.rlDone.setVisibility(View.GONE);
                holder.rlUnderProcess.setVisibility(View.GONE);

                holder.rlNotEvaluated.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        productClick.RCRClick12a_underprocess(position, live.getSp_id(), live.getSp_status(),live.getStatus(),live.getVerification_id(),
                                live.getVerification_status(),live.getSp_registration_no(),
                                live.getSp_sirname(), live.getSp_applicant(),live.getSp_behalf_of(), live.getSp_parent_sirname(),
                                live.getSp_parentsname(), live.getSp_gender(), live.getSp_social_status(), live.getSp_training_certificate(),
                                live.getSp_training_place(), live.getSp_training_from(), live.getSp_training_to(),
                                live.getSp_educational_certificate(), live.getSp_passport_photo(), live.getSp_aadhaar_number(),
                                live.getSp_country_code(), live.getSp_mobile(), live.getSp_applicant_type(), live.getSp_unit_name(),
                                live.getSp_village(), live.getSp_taluk(), live.getSp_state(), live.getSp_district(), live.getSp_pin(), live.getSp_address(),
                                live.getSp_production_center(), live.getSp_production_center_taluk(), live.getSp_production_center_state(),
                                live.getSp_production_center_district(), live.getSp_production_center_pin(), live.getSp_production_center_sector(),
                                live.getSp_production_center_address(),
                                live.getSp_produced(), live.getSp_unit(), live.getSp_production_level(), live.getSp_ownership_type(),
                                live.getSp_establishment(), live.getSp_other_details(), live.getSp_quantity_silkworm(), live.getSp_quantity_silkworm2(),
                                live.getSp_workers(),
                                live.getSp_unit_two(), live.getSp_page1(), live.getSp_page2(), live.getSp_page3(), live.getSp_page4(),
                                live.getSp_page5(), live.getSp_page6(), live.getSp_sign(), live.getSp_remark(),live.getCreated_at()
                                ,live.getUpdated_at(), live.getName(), live.getNameOfTheRsp(),live.getRegistration_no()
                                ,live.getRegistered_address(),live.getContact_no(),live.getDisplayed_ther_registration_certificate_prominently()
                                ,live.getNumber_of_regd_seed_cocoon_producers_with_rsp(),live.getWhether_seed_cocoons_are_purchased_from_regd_seed_cocoon_producers_for_every_crop()
                                ,live.getWhether_seed_cocoons_are_purchased_as_per_norms(),live.getWhether_seed_cocoons_are_properly_sorted_and_sex_separated()
                                ,live.getWhether_pupal_testing_is_done_for_pebrine(),live.getSex_separation_efficiency()
                                ,live.getMale_female_ratio(),live.getWhether_each_parental_pupae_are_kept_in_separate_room()
                                ,live.getWhether_proper_temp_rh_maintained_in_cocoon_pupae_oviposition_rooms()
                                ,live.getWhether_seeds_are_properly_washed_dried_and_packed_as_recommended()
                                ,live.getWhether_male_moths_are_preserved_in_cold_room(),live.getWhether_cold_rooms_have_uninterrupted_power_supply()
                                ,live.getWhether_proper_temp_humidity_are_maintained_for_oviposition(),live.getWhether_incubation_chamber_available()
                                ,live.getWhether_seeds_are_properly_labeled_for_race_lot_no_dol(),live.getNo_of_batches_detected()
                                ,live.getLot_details_of_source_seed_cocoon(),live.getSkilled_person_for_testing()
                                ,live.getDisinfection_register(),live.getSeed_cocoon_procurement_register()
                                ,live.getCocoon_processing_register(),live.getPebrine_testing_register(),
                                live.getDfl_supply_register(),live.getTest_hatching_register_for_dfls_supplied()
                                ,live.getTemp_rh_maintenance_register(),live.getBill_book_for_dfl_supply()
                                ,live.getWhether_following_self_certification_for_each_supply_lot()
                                ,live.getWhether_quarterly_reports_are_being_regularly_submitted_to_nsso()
                                ,live.getImage() ,live.getExtra1() ,live.getExtra2() ,live.getExtra3(),live.getExtra4());
                        final Animation myAnim = AnimationUtils.loadAnimation(context, R.anim.bounce);
                        holder.rlNotEvaluated.startAnimation(myAnim);

                    }
                });

            }

        }




    }

    @Override
    public int getItemViewType(int position) {

        return super.getItemViewType(position);
    }

    public void set(ProductClick onClick) {
        this.productClick = onClick;
    }

}
