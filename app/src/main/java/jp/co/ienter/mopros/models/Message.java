package jp.co.ienter.mopros.models;

/**
 * Created by donghv on 7/30/18.
 */

public class Message {
    private int seq_no;
    private String nonyu_code;
    private String message;
    private ResPattern res_pattern;
    private String sender;
    private int is_read;
    private String read_date;
    private String response;

    public class ResPattern{
        private String pattern_1;
        private String pattern_2;
        private String pattern_3;
        private String pattern_4;

        public ResPattern(String pattern_1, String pattern_2, String pattern_3, String pattern_4) {
            this.pattern_1 = pattern_1;
            this.pattern_2 = pattern_2;
            this.pattern_3 = pattern_3;
            this.pattern_4 = pattern_4;
        }

        public String getPattern_1() {
            return pattern_1;
        }
        public String getPattern(int i){
            String p = "";
            if(i == 1){
                p = pattern_1;
            }
            if(i == 2){
                p = pattern_2;
            }
            if(i == 3){
                p = pattern_3;
            }
            if(i == 4){
                p = pattern_4;
            }
            return p;
        }
        public void setPattern_1(String pattern_1) {
            this.pattern_1 = pattern_1;
        }

        public String getPattern_2() {
            return pattern_2;
        }

        public void setPattern_2(String pattern_2) {
            this.pattern_2 = pattern_2;
        }

        public String getPattern_3() {
            return pattern_3;
        }

        public void setPattern_3(String pattern_3) {
            this.pattern_3 = pattern_3;
        }

        public String getPattern_4() {
            return pattern_4;
        }

        public void setPattern_4(String pattern_4) {
            this.pattern_4 = pattern_4;
        }
    }

    public int getSeq_no() {
        return seq_no;
    }

    public void setSeq_no(int seq_no) {
        this.seq_no = seq_no;
    }

    public String getNonyu_code() {
        return nonyu_code;
    }

    public void setNonyu_code(String nonyu_code) {
        this.nonyu_code = nonyu_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResPattern getRes_pattern() {
        return res_pattern;
    }

    public void setRes_pattern(ResPattern res_pattern) {
        this.res_pattern = res_pattern;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public int getIs_read() {
        return is_read;
    }

    public void setIs_read(int is_read) {
        this.is_read = is_read;
    }

    public String getRead_date() {
        return read_date;
    }

    public void setRead_date(String read_date) {
        this.read_date = read_date;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
