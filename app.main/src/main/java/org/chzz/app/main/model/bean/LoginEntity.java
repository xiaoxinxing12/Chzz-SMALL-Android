package org.chzz.app.main.model.bean;

/**
 * Created by copy on 2016/4/25.
 */
public class LoginEntity extends BaseEntity {

    /**
     * code : 0
     * msg : {"hospitalId":1,"id":1,"cookie":"642E426FE35F63227876BE3212B06872","managerTypeCode":"0","managerTypeValue":"医院","hospitalName":"医友虚拟医院"}
     */

    private String code;
    /**
     * hospitalId : 1
     * id : 1
     * cookie : 642E426FE35F63227876BE3212B06872
     * managerTypeCode : 0
     * managerTypeValue : 医院
     * hospitalName : 医友虚拟医院
     */

    private MsgBean msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public MsgBean getMsg() {
        return msg;
    }

    public void setMsg(MsgBean msg) {
        this.msg = msg;
    }

    public static class MsgBean {
        private int hospitalId;
        private int id;
        private String cookie;
        private String managerTypeCode;
        private String managerTypeValue;
        private String hospitalName;

        public int getHospitalId() {
            return hospitalId;
        }

        public void setHospitalId(int hospitalId) {
            this.hospitalId = hospitalId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCookie() {
            return cookie;
        }

        public void setCookie(String cookie) {
            this.cookie = cookie;
        }

        public String getManagerTypeCode() {
            return managerTypeCode;
        }

        public void setManagerTypeCode(String managerTypeCode) {
            this.managerTypeCode = managerTypeCode;
        }

        public String getManagerTypeValue() {
            return managerTypeValue;
        }

        public void setManagerTypeValue(String managerTypeValue) {
            this.managerTypeValue = managerTypeValue;
        }

        public String getHospitalName() {
            return hospitalName;
        }

        public void setHospitalName(String hospitalName) {
            this.hospitalName = hospitalName;
        }
    }
}
