package com.assignments.proj.Api.model;

import java.util.List;

public class ListSkills {


        private List<Skills> technicalSkills;
        private List<Skills> productSkills;

        public ListSkills(List<Skills> tecknicalSkills, List<Skills> productSkills) {
            this.technicalSkills = tecknicalSkills;
            this.productSkills = productSkills;
        }

        public List<Skills> getTecknicalSkills() {
            return technicalSkills;
        }

        public List<Skills> getProductSkills() {
            return productSkills;
        }

        public void setTecknicalSkills(List<Skills> tecknicalSkills) {
            this.technicalSkills = tecknicalSkills;
        }

        public void setProductSkills(List<Skills> productSkills) {
            this.productSkills = productSkills;
        }
    }


