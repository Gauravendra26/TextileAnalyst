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

public class Adapter_RCR extends RecyclerView.Adapter<Adapter_RCR.MyViewHolder> {


    private Context context;
    private List<Model_RCR> liveList;

    private ProductClick productClick;

    //make interface like this
    public interface ProductClick {


        void RCRClick(int position,int sr_id, int sr_status, int status, int verification_id, int verification_status,
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
                      String created_at, String updated_at,String name);

        void RCRClick_12bUnder(int position,int sr_id, int sr_status, int status, int verification_id, int verification_status,
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
                               String created_at, String updated_at,String name);

        void RCRClick_12b(int position,int sr_id, int sr_status, int status, int verification_id, int verification_status,
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
                          String created_at, String updated_at,String name,   String nameOfTheRsp,
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
                          String extra1, String extra2, String extra3, String extra4);

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

    public Adapter_RCR(Context context, List<Model_RCR> liveList) {
        //List<SiderImageModel> slider_image_list
        this.context = context;
        this.liveList = liveList;
        ////this.slider_image_list = slider_image_list;

    }

    public void setData(List<Model_RCR> newData) {
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

        Model_RCR live = liveList.get(position);

        holder.tvName.setText(live.getSr_sirname()+" "+live.getSr_applicant());
        holder.tvID.setText(live.getSr_registration_no());
        // Check if there is another item with the same sp_registration_no
        boolean hasDuplicateRegistrationNo = false;
        int duplicateItemPosition = -1;

        for (int i = 0; i < getItemCount(); i++) {
            if (i != position && liveList.get(i).getSr_registration_no().equals(live.getSr_registration_no())) {
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
                {            holder.rlNotEvaluated.setVisibility(View.VISIBLE);

                    holder.rlDone.setVisibility(View.GONE);
                    holder.rlUnderProcess.setVisibility(View.GONE);

                    holder.rlNotEvaluated.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            productClick.RCRClick(position, live.getSr_id(), live.getSr_status(),live.getStatus(),
                                    live.getVerification_id(),  live.getVerification_status(), live.getSr_registration_no(),
                                    live.getSr_sirname(), live.getSr_applicant(),live.getSr_behalf_of(),
                                    live.getSr_parent_sirname(),
                                    live.getSr_parentsname(), live.getSr_gender(), live.getSr_social_status(),
                                    live.getSr_training_certificate(),
                                    live.getSr_training_place(), live.getSr_training_from(), live.getSr_training_to(),
                                    live.getSr_educational_certificate(), live.getSr_passport_photo(), live.getSr_aadhaar_number(),
                                    live.getSr_country_code(), live.getSr_mobile(), live.getSr_applicant_type(),
                                    live.getSr_unit_name(),
                                    live.getSr_village(), live.getSr_taluk(), live.getSr_state(), live.getSr_district(),
                                    live.getSr_pin(), live.getSr_address(),
                                    live.getSr_production_center(), live.getSr_production_center_taluk(),
                                    live.getSr_production_center_state(),
                                    live.getSr_production_center_district(), live.getSr_production_center_pin(),
                                    live.getSr_production_center_sector(),
                                    live.getSr_production_center_address(),
                                    live.getSr_produced(), live.getSr_unit(), live.getSr_production_level(),
                                    live.getSr_ownership_type(),
                                    live.getSr_establishment(), live.getSr_other_details(), live.getSr_quantity_silkworm(),
                                    live.getSr_quantity_silkworm2(), live.getSr_workers(),
                                    live.getSr_unit_two(), live.getSr_page1(), live.getSr_page2(), live.getSr_page3(),
                                    live.getSr_page4(),
                                    live.getSr_page5(), live.getSr_page6(), live.getSr_sign(), live.getSr_remark(),
                                    live.getCreated_at(), live.getUpdated_at(), live.getName());
                            final Animation myAnim = AnimationUtils.loadAnimation(context, R.anim.bounce);
                            holder.rlNotEvaluated.startAnimation(myAnim);

                        }
                    });

                } else if (live.getVerification_status()==1)
                {
                    holder.rlDone.setVisibility(View.VISIBLE);
                    holder.rlNotEvaluated.setVisibility(View.GONE);
                    holder.rlUnderProcess.setVisibility(View.GONE);

                    holder.rlDone.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            productClick.RCRClick_12b(position, live.getSr_id(), live.getSr_status(),live.getStatus(),
                                    live.getVerification_id(),  live.getVerification_status(), live.getSr_registration_no(),
                                    live.getSr_sirname(), live.getSr_applicant(),live.getSr_behalf_of(),
                                    live.getSr_parent_sirname(),
                                    live.getSr_parentsname(), live.getSr_gender(), live.getSr_social_status(),
                                    live.getSr_training_certificate(),
                                    live.getSr_training_place(), live.getSr_training_from(), live.getSr_training_to(),
                                    live.getSr_educational_certificate(), live.getSr_passport_photo(), live.getSr_aadhaar_number(),
                                    live.getSr_country_code(), live.getSr_mobile(), live.getSr_applicant_type(),
                                    live.getSr_unit_name(),
                                    live.getSr_village(), live.getSr_taluk(), live.getSr_state(), live.getSr_district(),
                                    live.getSr_pin(), live.getSr_address(),
                                    live.getSr_production_center(), live.getSr_production_center_taluk(),
                                    live.getSr_production_center_state(),
                                    live.getSr_production_center_district(), live.getSr_production_center_pin(),
                                    live.getSr_production_center_sector(),
                                    live.getSr_production_center_address(),
                                    live.getSr_produced(), live.getSr_unit(), live.getSr_production_level(),
                                    live.getSr_ownership_type(),
                                    live.getSr_establishment(), live.getSr_other_details(), live.getSr_quantity_silkworm(),
                                    live.getSr_quantity_silkworm2(), live.getSr_workers(),
                                    live.getSr_unit_two(), live.getSr_page1(), live.getSr_page2(), live.getSr_page3(),
                                    live.getSr_page4(),
                                    live.getSr_page5(), live.getSr_page6(), live.getSr_sign(), live.getSr_remark(),
                                    live.getCreated_at(), live.getUpdated_at(), live.getName() ,live.getNameOfTheRsp(),live.getRegistration_no()
                                    ,live.getRegistered_address(),live.getContact_no(), live.getDisplayedRegistrationCertificateProminently()
                                    ,live.getMulberry_area_acre(),live.getRegularity_of_soil_testing_once_in_2_years()
                                    ,live.getRecommended_application_of_soil_inputs_fertilizers_fym_after_each_crop(),
                                    live.getIs_pruning_harvest_schedule_followed_to_enable_a_minimum_of_32_crops_annum()
                                    ,live.getMulberry_variety(),live.getPest_disease_freeness_in_garden()
                                    ,live.getNutrient_deficiency_symptoms_in_leaves(),live.getAverage_single_leaf_weight_g()
                                    ,live.getIrrigation_facility(),live.getOverall_status()
                                    ,live.getIncubation_facility(),live.getWhether_rearing_house_is_as_per_recommended_plan()
                                    ,live.getWhether_equipments_appliances_as_per_seed_act_guidelines_available(),
                                    live.getVisual_health_of_chawki_worms()
                                    ,live.getWt_of_100_ii_instar_larvae_g(),live.getBed_spacing()
                                    ,live.getMaintenance_of_temp_rh(),live.getNo_of_batches_detected()
                                    ,live.getLot_details_of_source_dfls(),live.getSkilled_person_for_testing()
                                    ,live.getDisinfection_register(),live.getDfl_procurement_register(),
                                    live.getRearing_performance_register(),live.getPebrine_testing_register()
                                    ,live.getChawki_supply_register(),live.getFarm_management_register()
                                    ,live.getTemp_rh_maintenance_register()
                                    ,live.getBill_book() ,live.getWhether_following_self_certification_for_each_supply_lot()
                                    ,live.getImage() ,live.getExtra1() ,live.getExtra2() ,live.getExtra3(),live.getExtra4());

                            final Animation myAnim = AnimationUtils.loadAnimation(context, R.anim.bounce);
                            holder.rlDone.startAnimation(myAnim);

                        }
                    });




                } else if (live.getVerification_status()==2)
                {
                    holder.rlNotEvaluated.setVisibility(View.VISIBLE);

                    holder.rlDone.setVisibility(View.GONE);
                    holder.rlUnderProcess.setVisibility(View.GONE);

                    holder.rlNotEvaluated.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            productClick.RCRClick_12bUnder(position, live.getSr_id(), live.getSr_status(),live.getStatus(),
                                    live.getVerification_id(),  live.getVerification_status(), live.getSr_registration_no(),
                                    live.getSr_sirname(), live.getSr_applicant(),live.getSr_behalf_of(),
                                    live.getSr_parent_sirname(),
                                    live.getSr_parentsname(), live.getSr_gender(), live.getSr_social_status(),
                                    live.getSr_training_certificate(),
                                    live.getSr_training_place(), live.getSr_training_from(), live.getSr_training_to(),
                                    live.getSr_educational_certificate(), live.getSr_passport_photo(), live.getSr_aadhaar_number(),
                                    live.getSr_country_code(), live.getSr_mobile(), live.getSr_applicant_type(),
                                    live.getSr_unit_name(),
                                    live.getSr_village(), live.getSr_taluk(), live.getSr_state(), live.getSr_district(),
                                    live.getSr_pin(), live.getSr_address(),
                                    live.getSr_production_center(), live.getSr_production_center_taluk(),
                                    live.getSr_production_center_state(),
                                    live.getSr_production_center_district(), live.getSr_production_center_pin(),
                                    live.getSr_production_center_sector(),
                                    live.getSr_production_center_address(),
                                    live.getSr_produced(), live.getSr_unit(), live.getSr_production_level(),
                                    live.getSr_ownership_type(),
                                    live.getSr_establishment(), live.getSr_other_details(), live.getSr_quantity_silkworm(),
                                    live.getSr_quantity_silkworm2(), live.getSr_workers(),
                                    live.getSr_unit_two(), live.getSr_page1(), live.getSr_page2(), live.getSr_page3(),
                                    live.getSr_page4(),
                                    live.getSr_page5(), live.getSr_page6(), live.getSr_sign(), live.getSr_remark(),
                                    live.getCreated_at(), live.getUpdated_at(), live.getName());

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
            {            holder.rlNotEvaluated.setVisibility(View.VISIBLE);

                holder.rlDone.setVisibility(View.GONE);
                holder.rlUnderProcess.setVisibility(View.GONE);

                holder.rlNotEvaluated.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        productClick.RCRClick(position, live.getSr_id(), live.getSr_status(),live.getStatus(),
                                live.getVerification_id(),  live.getVerification_status(), live.getSr_registration_no(),
                                live.getSr_sirname(), live.getSr_applicant(),live.getSr_behalf_of(),
                                live.getSr_parent_sirname(),
                                live.getSr_parentsname(), live.getSr_gender(), live.getSr_social_status(),
                                live.getSr_training_certificate(),
                                live.getSr_training_place(), live.getSr_training_from(), live.getSr_training_to(),
                                live.getSr_educational_certificate(), live.getSr_passport_photo(), live.getSr_aadhaar_number(),
                                live.getSr_country_code(), live.getSr_mobile(), live.getSr_applicant_type(),
                                live.getSr_unit_name(),
                                live.getSr_village(), live.getSr_taluk(), live.getSr_state(), live.getSr_district(),
                                live.getSr_pin(), live.getSr_address(),
                                live.getSr_production_center(), live.getSr_production_center_taluk(),
                                live.getSr_production_center_state(),
                                live.getSr_production_center_district(), live.getSr_production_center_pin(),
                                live.getSr_production_center_sector(),
                                live.getSr_production_center_address(),
                                live.getSr_produced(), live.getSr_unit(), live.getSr_production_level(),
                                live.getSr_ownership_type(),
                                live.getSr_establishment(), live.getSr_other_details(), live.getSr_quantity_silkworm(),
                                live.getSr_quantity_silkworm2(), live.getSr_workers(),
                                live.getSr_unit_two(), live.getSr_page1(), live.getSr_page2(), live.getSr_page3(),
                                live.getSr_page4(),
                                live.getSr_page5(), live.getSr_page6(), live.getSr_sign(), live.getSr_remark(),
                                live.getCreated_at(), live.getUpdated_at(), live.getName());
                        final Animation myAnim = AnimationUtils.loadAnimation(context, R.anim.bounce);
                        holder.rlNotEvaluated.startAnimation(myAnim);

                    }
                });

            } else if (live.getVerification_status()==1)
            {
                holder.rlDone.setVisibility(View.VISIBLE);
                holder.rlNotEvaluated.setVisibility(View.GONE);
                holder.rlUnderProcess.setVisibility(View.GONE);

                holder.rlDone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        productClick.RCRClick_12b(position, live.getSr_id(), live.getSr_status(),live.getStatus(),
                                live.getVerification_id(),  live.getVerification_status(), live.getSr_registration_no(),
                                live.getSr_sirname(), live.getSr_applicant(),live.getSr_behalf_of(),
                                live.getSr_parent_sirname(),
                                live.getSr_parentsname(), live.getSr_gender(), live.getSr_social_status(),
                                live.getSr_training_certificate(),
                                live.getSr_training_place(), live.getSr_training_from(), live.getSr_training_to(),
                                live.getSr_educational_certificate(), live.getSr_passport_photo(), live.getSr_aadhaar_number(),
                                live.getSr_country_code(), live.getSr_mobile(), live.getSr_applicant_type(),
                                live.getSr_unit_name(),
                                live.getSr_village(), live.getSr_taluk(), live.getSr_state(), live.getSr_district(),
                                live.getSr_pin(), live.getSr_address(),
                                live.getSr_production_center(), live.getSr_production_center_taluk(),
                                live.getSr_production_center_state(),
                                live.getSr_production_center_district(), live.getSr_production_center_pin(),
                                live.getSr_production_center_sector(),
                                live.getSr_production_center_address(),
                                live.getSr_produced(), live.getSr_unit(), live.getSr_production_level(),
                                live.getSr_ownership_type(),
                                live.getSr_establishment(), live.getSr_other_details(), live.getSr_quantity_silkworm(),
                                live.getSr_quantity_silkworm2(), live.getSr_workers(),
                                live.getSr_unit_two(), live.getSr_page1(), live.getSr_page2(), live.getSr_page3(),
                                live.getSr_page4(),
                                live.getSr_page5(), live.getSr_page6(), live.getSr_sign(), live.getSr_remark(),
                                live.getCreated_at(), live.getUpdated_at(), live.getName() ,live.getNameOfTheRsp(),live.getRegistration_no()
                                ,live.getRegistered_address(),live.getContact_no(), live.getDisplayedRegistrationCertificateProminently()
                                ,live.getMulberry_area_acre(),live.getRegularity_of_soil_testing_once_in_2_years()
                                ,live.getRecommended_application_of_soil_inputs_fertilizers_fym_after_each_crop(),
                                live.getIs_pruning_harvest_schedule_followed_to_enable_a_minimum_of_32_crops_annum()
                                ,live.getMulberry_variety(),live.getPest_disease_freeness_in_garden()
                                ,live.getNutrient_deficiency_symptoms_in_leaves(),live.getAverage_single_leaf_weight_g()
                                ,live.getIrrigation_facility(),live.getOverall_status()
                                ,live.getIncubation_facility(),live.getWhether_rearing_house_is_as_per_recommended_plan()
                                ,live.getWhether_equipments_appliances_as_per_seed_act_guidelines_available(),
                                live.getVisual_health_of_chawki_worms()
                                ,live.getWt_of_100_ii_instar_larvae_g(),live.getBed_spacing()
                                ,live.getMaintenance_of_temp_rh(),live.getNo_of_batches_detected()
                                ,live.getLot_details_of_source_dfls(),live.getSkilled_person_for_testing()
                                ,live.getDisinfection_register(),live.getDfl_procurement_register(),
                                live.getRearing_performance_register(),live.getPebrine_testing_register()
                                ,live.getChawki_supply_register(),live.getFarm_management_register()
                                ,live.getTemp_rh_maintenance_register()
                                ,live.getBill_book() ,live.getWhether_following_self_certification_for_each_supply_lot()
                                ,live.getImage() ,live.getExtra1() ,live.getExtra2() ,live.getExtra3(),live.getExtra4());

                        final Animation myAnim = AnimationUtils.loadAnimation(context, R.anim.bounce);
                        holder.rlDone.startAnimation(myAnim);

                    }
                });




            } else if (live.getVerification_status()==2)
            {
                holder.rlNotEvaluated.setVisibility(View.VISIBLE);

                holder.rlDone.setVisibility(View.GONE);
                holder.rlUnderProcess.setVisibility(View.GONE);

                holder.rlNotEvaluated.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        productClick.RCRClick_12bUnder(position, live.getSr_id(), live.getSr_status(),live.getStatus(),
                                live.getVerification_id(),  live.getVerification_status(), live.getSr_registration_no(),
                                live.getSr_sirname(), live.getSr_applicant(),live.getSr_behalf_of(),
                                live.getSr_parent_sirname(),
                                live.getSr_parentsname(), live.getSr_gender(), live.getSr_social_status(),
                                live.getSr_training_certificate(),
                                live.getSr_training_place(), live.getSr_training_from(), live.getSr_training_to(),
                                live.getSr_educational_certificate(), live.getSr_passport_photo(), live.getSr_aadhaar_number(),
                                live.getSr_country_code(), live.getSr_mobile(), live.getSr_applicant_type(),
                                live.getSr_unit_name(),
                                live.getSr_village(), live.getSr_taluk(), live.getSr_state(), live.getSr_district(),
                                live.getSr_pin(), live.getSr_address(),
                                live.getSr_production_center(), live.getSr_production_center_taluk(),
                                live.getSr_production_center_state(),
                                live.getSr_production_center_district(), live.getSr_production_center_pin(),
                                live.getSr_production_center_sector(),
                                live.getSr_production_center_address(),
                                live.getSr_produced(), live.getSr_unit(), live.getSr_production_level(),
                                live.getSr_ownership_type(),
                                live.getSr_establishment(), live.getSr_other_details(), live.getSr_quantity_silkworm(),
                                live.getSr_quantity_silkworm2(), live.getSr_workers(),
                                live.getSr_unit_two(), live.getSr_page1(), live.getSr_page2(), live.getSr_page3(),
                                live.getSr_page4(),
                                live.getSr_page5(), live.getSr_page6(), live.getSr_sign(), live.getSr_remark(),
                                live.getCreated_at(), live.getUpdated_at(), live.getName());

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
